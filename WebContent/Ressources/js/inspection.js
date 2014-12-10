var colorStudent = "#31708F";
var colorSupervisor = "#A94442";
var colorFirstInspector = "#8A6D3B";
var colorSecondInspector = "#3C763D";
var colorInspection = "#337AB7";

function Inspector(json) {
	var self = this;
	
	self.id = json.id;
	self.username= json.username;
	self.name = json.name;
	self.keywords = json.keywords;
	self.load = json.load;
	self.capacity = json.capacity;
	
	self.formattedCapacity = function() {
		return self.load + "/" + self.capacity;
	};
	
	self.slots = [];
}


function InspectionViewModel() {
	var self = this;

	self.defaultDate = "2014-11-12";
	
	self.studentID = null;
	self.studentSlots = [];
	
	self.supervisorID = null;
	self.supervisorSlots = [];
	
	self.inspectionSlot = ko.observable({
            title  : 'Inspection',
            start  : '2014-11-14T12:30:00',
            end  : '2014-11-14T13:00:00',
            constraint: "businessHours",
            color: colorInspection
    });
	
	self.suggestedInspectors = ko.observableArray([]);
	self.otherInspectors = ko.observableArray([]);
	self.allInspectors = ko.observableArray([]);

	self.firstInspector = ko.observable();
	self.secondInspector = ko.observable();

	self.firstOtherInspector = ko.observable();
	self.secondOtherInspector = ko.observable();
	
	// Inputs
	self.readSuggestedInspectors = function(json) {
    	var inspectors = [];
    	json.suggestedInspectors.forEach(function(inspectorJson) {
    		var inspector = new Inspector(inspectorJson);
    		inspectors.push(inspector);
    	});
    	self.suggestedInspectors(inspectors);
    	ko.utils.arrayPushAll(self.allInspectors, inspectors);
    	self.firstInspector(null);
	};
	
	self.readOtherInspectors = function(json) {
    	var inspectors = [];
    	json.otherInspectors.forEach(function(inspectorJson) {
    		var inspector = new Inspector(inspectorJson);
    		inspectors.push(inspector);
    	});
    	self.otherInspectors(inspectors);
    	ko.utils.arrayPushAll(self.allInspectors, inspectors);
    	self.secondInspector(null);
	};
	
	self.readFirstInspector = function(json) {
    	var firstInspector = self.inspectorWithID(json.firstInspectorID);
    	self.firstInspector(firstInspector);
    	if (self.otherInspectors().indexOf(firstInspector) >= 0) {
        	self.firstOtherInspector(firstInspector);
    	}
	};
	
	self.readSecondInspector = function(json) {
    	var secondInspector = self.inspectorWithID(json.secondInspectorID);
    	self.secondInspector(secondInspector);
    	if (self.otherInspectors().indexOf(secondInspector) >= 0) {
        	self.secondOtherInspector(secondInspector);
    	}
	};
	
	self.readStudent = function(json) {
		self.supervisorID = json.supervisorID;
		self.studentID = json.studentID;
	};
	
	self.readInspectors = function() {
    	var jsonDiv = $('#json-variables');
    	var json = JSON.parse(jsonDiv.text());
    	
		self.allInspectors([]);
		self.readStudent(json);
		self.readSuggestedInspectors(json);
		self.readOtherInspectors(json);
		self.readFirstInspector(json);
		self.readSecondInspector(json);
	};
	
	// Getters
	self.inspectorWithID = function(inspectorID) {
		var inspector = null;
		self.allInspectors().forEach(function(i) {
			if (i.id == inspectorID) {
				inspector = i;
			}
		});
		return inspector;
	};
	
	self.getSlots = function(start, end, timezone, callback) {
		var slots = [];
		if (self.firstInspector() != null) {
			slots = slots.concat(self.firstInspector().slots);
		}
		if (self.secondInspector() != null) {
			slots = slots.concat(self.secondInspector().slots);
		}
		slots = slots.concat(self.studentSlots);
		slots = slots.concat(self.supervisorSlots);
		slots.push(self.inspectionSlot());
		callback(slots);
	};
	
	
	// Get availability
	self.studentIsAvailable = ko.computed(function() {
		return !slotsOverlap(self.studentSlots, self.inspectionSlot());
	});
	self.supervisorIsAvailable = ko.computed(function() {
		return !slotsOverlap(self.supervisorSlots, self.inspectionSlot());
	});
	self.firstInspectorIsAvailable = ko.computed(function() {
		var user = self.firstInspector();
		if (user) {
			return !slotsOverlap(user.slots, self.inspectionSlot());
		} else {
			return true;
		}
	});
	self.secondInspectorIsAvailable = ko.computed(function() {
		var user = self.secondInspector();
		if (user) {
			return !slotsOverlap(user.slots, self.inspectionSlot());
		} else {
			return true;
		}
	});
	
	
	// Setters
	self.slotChanged = function(event, delta, revertFunc) {
		self.inspectionSlot(event);
	};
    
    self.setCalendarNeedUpdate = function() {
    	$('#calendar').fullCalendar( 'refetchEvents' );
    };
    
    // Fetchers
    self.fetchFirstInspectorSlots = function() {
    	if (self.firstInspector() != null) {
        	var userType = 'inspector';
        	var userID = self.firstInspector().id;
        	
        	self.fetchAvailability(userType, userID, function(result) {
        		// if success
            	var json = JSON.parse(result);
            	
            	json.slots.forEach(function(slot) {
            		slot.color = colorFirstInspector;
            		slot.rendering = "background";
            		slot.user = "first inspector";
            	});
            	self.firstInspector().slots = json.slots;
            	self.setCalendarNeedUpdate();
        	});
    	}
    };
    self.fetchSecondInspectorSlots = function() {
    	if (self.secondInspector() != null) {
        	var userType = 'inspector';
        	var userID = self.secondInspector().id;
        	
        	self.fetchAvailability(userType, userID, function(result) {
        		// if success
            	var json = JSON.parse(result);
            	
            	json.slots.forEach(function(slot) {
            		slot.color = colorSecondInspector;
            		slot.rendering = "background";
            		slot.user = "second inspector";
            	});
            	self.secondInspector().slots = json.slots;
            	self.setCalendarNeedUpdate();
        	});
    	}
    };
    self.fetchSupervisorSlots = function() {
    	if (self.secondInspector() != null) {
        	var userType = 'inspector';
        	var userID = self.supervisorID;
        	
        	self.fetchAvailability(userType, userID, function(result) {
        		// if success
            	var json = JSON.parse(result);
            	
            	json.slots.forEach(function(slot) {
            		slot.color = colorSupervisor;
            		slot.rendering = "background";
            		slot.user = "supervisor";
            	});
            	self.supervisorSlots = json.slots;
            	self.setCalendarNeedUpdate();
        	});
    	}
    };
    self.fetchStudentSlots = function() {
    	if (self.secondInspector() != null) {
        	var userType = 'student';
        	var userID = self.studentID;
        	
        	self.fetchAvailability(userType, userID, function(result) {
        		// if success
            	var json = JSON.parse(result);
            	
            	json.slots.forEach(function(slot) {
            		slot.color = colorStudent;
            		slot.rendering = "background";
            		slot.user = "student";
            	});
            	self.studentSlots = json.slots;
            	self.setCalendarNeedUpdate();
        	});
    	}
    };
    
    self.fetchAvailability = function(userType, userID, success) {
    	var contentType = 'json';
		$.ajax({
			url: '/PIMS/availability/?type=' + userType + '&id=' + userID +'&content='+ contentType,
			success: success,
			error: null
		});
    };
	
	// Bind other inspectors changes
	self.firstInspectorHandler = ko.computed(function() {
		self.fetchFirstInspectorSlots();
	});
	self.secondInspectorHandler = ko.computed(function() {
		self.fetchSecondInspectorSlots();
	});
	self.firstOtherInspectorHandler = ko.computed(function() {
		//console.log(self.firstOtherInspector());
	});
	self.secondOtherInspectorHandler = ko.computed(function() {
		//console.log(self.secondOtherInspector());
	});
	
	// Submit
    self.submitInspection = function() {
    	var usersAvailable = self.studentIsAvailable();
    	usersAvailable = usersAvailable && self.supervisorIsAvailable();
    	usersAvailable = usersAvailable && self.firstInspectorIsAvailable();
    	usersAvailable = usersAvailable && self.secondInspectorIsAvailable();
    	var forceSave = false;
    	
    	if (!usersAvailable) {
    		forceSave = confirm("Some person may not be available at this time. Do you wish to confirm this time for the inspection ?");
    	}
    	
    	if (usersAvailable || forceSave) {
        	var url = document.url;
        	var data = {
        		firstInspector: self.firstInspector().id,
        		secondInspector: self.secondInspector().id,
        		slot: JSON.stringify(self.inspectionSlot())
        	};
        	$.post(url, data, self.submitHandler);
    	}
    };
    
    self.submitHandler = function(data, status) {
    	if (status == "success") {
        	popSuccess('Inspection saved');
    	} else {
    		popError('Error');
    	}
    };
	
	// Start
	self.readInspectors();
	self.fetchStudentSlots();
	self.fetchSupervisorSlots();
	createCalendar(self);
}

var inspectionViewModel = new InspectionViewModel();
ko.applyBindings(inspectionViewModel);