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
	self.load = ko.observable(json.load);
	self.capacity = json.capacity;
	self.isFirstInspector = false;
	
	self.setFirstInspector = function(isFirstInspector) {
		if (self.isFirstInspector != isFirstInspector) {
			self.isFirstInspector = isFirstInspector;
			if (self.isFirstInspector) {
				self.load(self.load() + 1);
			} else {
				self.load(self.load() - 1);
			}
		}
	};
	
	self.formattedCapacity = function() {
		return self.load() + "/" + self.capacity;
	};
	
	self.slots = [];
}


function InspectionViewModel() {
	var self = this;

	self.startDate = null;
	self.endDate = null;
	
	self.studentID = null;
	self.studentSlots = [];
	
	self.supervisorID = null;
	self.supervisorSlots = [];
	
	self.inspectionSlot = ko.observable({
            title  : 'Inspection',
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
    		if (inspectorJson.id != self.supervisorID) {
        		var inspector = new Inspector(inspectorJson);
        		inspectors.push(inspector);
    		}
    	});
    	self.suggestedInspectors(inspectors);
    	ko.utils.arrayPushAll(self.allInspectors, inspectors);
    	self.firstInspector(null);
	};
	
	self.readOtherInspectors = function(json) {
    	var inspectors = [];
    	json.otherInspectors.forEach(function(inspectorJson) {
    		if (inspectorJson.id != self.supervisorID) {
	    		var inspector = new Inspector(inspectorJson);
	    		inspectors.push(inspector);
    		}
    	});
    	self.otherInspectors(inspectors);
    	ko.utils.arrayPushAll(self.allInspectors, inspectors);
    	self.secondInspector(null);
	};
	
	self.readFirstInspector = function(json) {
		if (json.firstInspectorID >= 0) {// Get first inspector from back end if it exist
	    	var firstInspector = self.inspectorWithID(json.firstInspectorID);
	    	firstInspector.isFirstInspector = true; // set selection with out changing his load
	    	self.firstInspector(firstInspector);
	    	if (self.otherInspectors().indexOf(firstInspector) >= 0) {
	        	self.firstOtherInspector(firstInspector);
	    	}
		}
	};
	
	self.readSecondInspector = function(json) {
		if (json.secondInspectorID >= 0) { // Get second inspector from back end if it exist
	    	var secondInspector = self.inspectorWithID(json.secondInspectorID);
	    	self.secondInspector(secondInspector);
	    	if (self.otherInspectors().indexOf(secondInspector) >= 0) {
	        	self.secondOtherInspector(secondInspector);
	    	}
		}
	};
	
	self.readInspection = function() {
    	var jsonDiv = $('#json-variables');
    	var json = JSON.parse(jsonDiv.text());
    	
		self.allInspectors([]);
		self.readStudent(json);
		self.readSuggestedInspectors(json);
		self.readOtherInspectors(json);
		self.readFirstInspector(json);
		self.readSecondInspector(json);
		
		self.readInspectionDate(json);
	};
	
	self.readStudent = function(json) {
		self.supervisorID = json.supervisorID;
		self.studentID = json.studentID;
	};
	
	self.readInspectionDate = function(json) {
		self.startDate = json.firstDay;
		self.endDate = moment(self.startDate).add(5, 'days').format("YYYY-MM-DD");
		if (json.inspectionStart && json.inspectionEnd) {
			self.inspectionSlot().start = json.inspectionStart;
			self.inspectionSlot().end = json.inspectionEnd;
		} else {
			self.inspectionSlot().start = self.startDate + 'T09:00:00';
			self.inspectionSlot().end = self.startDate + 'T09:30:00';
		}
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
	self.inspectorMarkedFirstInspector = function() {
		var inspector = null;
		self.allInspectors().forEach(function(i) {
			if (i.isFirstInspector) {
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
			url: '/PIMS/availability/?type=' + userType + '&id=' + userID +'&content='+ contentType +'&start='+ self.startDate +'&end='+ self.endDate,
			success: success,
			error: null
		});
    };
	
	// Bind other inspectors changes
	self.firstInspectorHandler = ko.computed(function() {
		self.fetchFirstInspectorSlots();
		
		var firstInspector = self.inspectorMarkedFirstInspector();
		if (firstInspector) {
			firstInspector.setFirstInspector(false); // unmark old first inspector
		}
		firstInspector = self.firstInspector();
		if (firstInspector) {
			firstInspector.setFirstInspector(true); // mark new first inspector
		}
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
    	if (!self.firstInspector() || !self.secondInspector()) { 
    		alert('You first need to chose a first and a second inspector');
    	} else if (self.firstInspector() == self.secondInspector()) {
    		alert('The first and the second inspector can\'t be the same person');
    	} else {
        	var usersAvailable = self.studentIsAvailable();
        	usersAvailable = usersAvailable && self.supervisorIsAvailable();
        	usersAvailable = usersAvailable && self.firstInspectorIsAvailable();
        	usersAvailable = usersAvailable && self.secondInspectorIsAvailable();
        	var forceSave = false;
        	
        	if (!usersAvailable) {
        		forceSave = confirm("Some people may not be available at this time. Do you wish to confirm this time for the inspection ?");
        	}
        	
        	var firstInspectorOverload = (self.firstInspector().load() > self.firstInspector().capacity);
        	if (firstInspectorOverload && (usersAvailable || forceSave)) {
        		forceSave = confirm(self.firstInspector().name + " seems to be overloaded. Do you wish to confirm him/her as the first inspector ?");
        	}
        	
        	if ((usersAvailable && !firstInspectorOverload) || forceSave) {
        		self.postInspection();
        	}
    	}
    };
    
    self.postInspection = function() {
    	var url = document.url;
    	var data = {
    		firstInspector: self.firstInspector().id,
    		secondInspector: self.secondInspector().id,
    		slot: JSON.stringify(self.inspectionSlot())
    	};
    	$.post(url, data, self.submitHandler);
    };
    
    self.submitHandler = function(data, status) {
    	if (status == "success") {
        	popSuccess('Inspection saved');
    	} else {
    		popError('Error');
    	}
    };
	
	// Start
	self.readInspection();
	self.fetchStudentSlots();
	self.fetchSupervisorSlots();
	createCalendar(self);
}

var inspectionViewModel = new InspectionViewModel();
ko.applyBindings(inspectionViewModel);