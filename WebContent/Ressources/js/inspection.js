function uniq(a) {
    var seen = {};
    return a.filter(function(item) {
        var k = item.id;
        return k == null ? true : seen.hasOwnProperty(k) ? false : (seen[k] = true);
    });
}

var colorStudent = "#31708F";
var colorSupervisor = "#A94442";
var colorFirstInspector = "#8A6D3B";
var colorSecondInspector = "#3C763D";
var colorInspection = "#337AB7";
var colorOtherInspection = colorSupervisor;

function Inspector(json) {
	var self = this;
	
	self.id = json.id;
	self.username= json.username;
	self.name = json.name;
	self.keywords = json.keywords;
	self.load = ko.observable(json.load);
	self.capacity = json.capacity+10;
	self.suggested = json.suggested;

	self.isFirstInspector = json.isFirstInspector;
	self.isSecondInspector = json.isSecondInspector;
	console.log(self.isFirstInspector, " - ", self.isSecondInspector);
	
	self.increaseLoad = function() {
		self.load(self.load() + 1);
	};
	
	self.decreaseLoad = function() {
		self.load(self.load() - 1);
	};
	
	self.formattedCapacity = function() {
		return self.capacity - self.load() + "/" + self.capacity;
	};
	
	self.overloaded = function() {
		return self.load() >= self.capacity;
	};
	
	self.slots = [];
	self.otherInspectionSlots = [];
}


function InspectionViewModel() {
	var self = this;

	self.startDate = null;
	self.endDate = null;
	self.inspectionWeekID = null;
	
	self.studentID = null;
	self.studentSlots = [];
	self.studentOtherInspectionSlots = [];
	
	self.supervisorID = null;
	self.supervisorSlots = [];
	self.supervisorOtherInspectionSlots = [];
	
	self.inspectionSlot = ko.observable({
            title  : 'Project inspection',
            constraint: "businessHours",
            color: "orange"
    });
	
	self.allInspectors = ko.observableArray([]);
	self.showOverloaded = ko.observable(false);
	
	// Splitted list of inspectors
	self.filteredInspectors = function(isSuggested, canBeOverloaded) {
		return self.allInspectors().filter(function (inspector) {
			return inspector.suggested == isSuggested && (canBeOverloaded || !inspector.overloaded() || inspector.isFirstInspector || inspector.isSecondInspector);
		});
	};
	self.suggestedInspectors = ko.computed(function() {
		return self.filteredInspectors(true, self.showOverloaded());
	});
	self.otherInspectors = ko.computed(function() {
		return self.filteredInspectors(false, self.showOverloaded());
	});

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
    	//self.suggestedInspectors(inspectors);
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
    	//self.otherInspectors(inspectors);
    	ko.utils.arrayPushAll(self.allInspectors, inspectors);
    	self.secondInspector(null);
	};
	
	self.readInspection = function() {
    	var jsonDiv = $('#json-variables');
    	var json = JSON.parse(jsonDiv.text());
    	
		self.allInspectors([]);
		self.readInspectionDate(json);
		self.readStudent(json);
		self.readSuggestedInspectors(json);
		self.readOtherInspectors(json);
		
		self.inspectionSlot().id = json.inspectionID;
	};
	
	self.readStudent = function(json) {
		self.supervisorID = json.supervisorID;
		self.studentID = json.studentID;
	};
	
	self.readInspectionDate = function(json) {
		self.startDate = moment(json.firstDay).format("YYYY-MM-DD");
		self.endDate = moment(self.startDate).add(4, 'days').format("YYYY-MM-DD");
		self.inspectionWeekID = json.inspectionWeekID;
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
		return self.allInspectors().filter(function(inspector) {
	        return inspector.isFirstInspector;
	    })[0];
	};
	self.inspectorMarkedSecondInspector = function() {
		return self.allInspectors().filter(function(inspector) {
	        return inspector.isSecondInspector;
	    })[0];
	};
	
	self.getSlots = function(start, end, timezone, callback) {
		var slots = [];
		slots.push(self.inspectionSlot());
		if (self.firstInspector() != null) {
			slots = slots.concat(self.firstInspector().slots);
			slots = slots.concat(self.firstInspector().otherInspectionSlots);
		}
		if (self.secondInspector() != null) {
			slots = slots.concat(self.secondInspector().slots);
			slots = slots.concat(self.secondInspector().otherInspectionSlots);
		}
		slots = slots.concat(self.studentSlots);
		slots = slots.concat(self.studentOtherInspectionSlots);
		slots = slots.concat(self.supervisorSlots);
		slots = slots.concat(self.supervisorOtherInspectionSlots);
		callback(uniq(slots));
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
	self.otherInspectionOverlaped = function () {
		
	};
	
	
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
            	console.log(self.firstInspector());
            	self.firstInspector().slots = json.slots;
            	
            	json.inspections.forEach(function(slot) {
            		slot.color = colorFirstInspector;
            		slot.title = "First inspector";
            		slot.overlap = false;
            		slot.editable = false;
            	});
            	self.firstInspector().otherInspectionSlots = json.inspections;
            	
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
            	console.log(self.secondInspector());
            	self.secondInspector().slots = json.slots;
            	
            	json.inspections.forEach(function(slot) {
            		slot.color = colorSecondInspector;
            		slot.title = "Second inspector";
            		slot.overlap = false;
            		slot.editable = false;
            	});
            	self.secondInspector().otherInspectionSlots = json.inspections;
            	
            	self.setCalendarNeedUpdate();
        	});
    	}
    };
    self.fetchSupervisorSlots = function() {
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
        	
        	json.inspections.forEach(function(slot) {
        		slot.color = colorSupervisor;
        		slot.title = "Supervisor";
        		slot.overlap = false;
        		slot.editable = false;
        	});
        	self.supervisorOtherInspectionSlots = json.inspections;
        	
        	self.setCalendarNeedUpdate();
    	});
    };
    self.fetchStudentSlots = function() {
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
        	
        	json.inspections.forEach(function(slot) {
        		slot.color = colorStudent;
        		slot.title = "Student";
        		slot.overlap = false;
        		slot.editable = false;
        	});
        	self.studentOtherInspectionSlots = json.inspections;
        	
        	self.setCalendarNeedUpdate();
    	});
    };
    
    self.fetchAvailability = function(userType, userID, success) {
    	var contentType = 'json';
		$.ajax({
			url: '/PIMS/availability/?type=' + userType + '&id=' + userID +'&content='+ contentType +'&week='+ self.inspectionWeekID,
			success: function(result) {
				success(result);
				self.updateAvailabilities();
			},
			error: null
		});
    };
	
	// Bind other inspectors changes
	self.firstInspectorHandler = ko.computed(function() {
		console.log('first inspector changed:', self.firstInspector());
		self.fetchFirstInspectorSlots();
		
		var oldFirst = self.inspectorMarkedFirstInspector();
		var newFirst = self.firstInspector();
		if (newFirst != null) {
			newFirst.isFirstInspector = true;
			newFirst.increaseLoad();
		}
		if (oldFirst != null) {
			oldFirst.isFirstInspector = false;
			oldFirst.decreaseLoad();
		}
	});
	self.secondInspectorHandler = ko.computed(function() {
		console.log('second inspector changed:', self.secondInspector());
		self.fetchSecondInspectorSlots();
		
		var oldSecond = self.inspectorMarkedSecondInspector();
		var newSecond = self.secondInspector();
		if (newSecond != null) {
			newSecond.isSecondInspector = true;
		}
		if (oldSecond != null) {
			oldSecond.isSecondInspector = false;
		}
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
    
    self.updateAvailabilities = function() {
    	self.inspectionSlot.valueHasMutated();
    };
	
	// Start
	self.readInspection();
	self.fetchStudentSlots();
	self.fetchSupervisorSlots();
	
	createCalendar(self);
}

var inspectionViewModel = new InspectionViewModel();
ko.applyBindings(inspectionViewModel);