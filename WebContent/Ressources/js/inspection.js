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
	
	self.suggestedInspectors = ko.observableArray();
	self.otherInspectors = ko.observableArray();
	self.allInspectors = ko.observableArray();

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
	};
	
	self.readSecondInspector = function(json) {
    	var secondInspector = self.inspectorWithID(json.secondInspectorID);
    	self.secondInspector(secondInspector);
	};
	
	self.readInspectors = function() {
    	var jsonDiv = $('#json-variables');
    	var json = JSON.parse(jsonDiv.text());
    	
		self.allInspectors([]);
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
		console.log(slots);
		callback(slots);
	};
	
	// Setters
	self.slotChanged = function(event, delta, revertFunc) {
		
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
            		slot.color = "yellow";
            		slot.rendering = "background";
            	});
            	self.firstInspector().slots = json.slots;
            	console.log(json.slots);
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
		console.log(self.firstInspector());
		self.fetchFirstInspectorSlots();
	});
	self.secondInspectorHandler = ko.computed(function() {
		console.log(self.secondInspector());
	});
	self.firstOtherInspectorHandler = ko.computed(function() {
		//console.log(self.firstOtherInspector());
	});
	self.secondOtherInspectorHandler = ko.computed(function() {
		//console.log(self.secondOtherInspector());
	});
	
	// Start
	self.readInspectors();
	createCalendar(self);
}

var inspectionViewModel = new InspectionViewModel();
ko.applyBindings(inspectionViewModel);