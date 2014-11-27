// Class to present and edit available slots
var staticIndex = 1;
function Slot(json) {
    var self = this;
    self.id = staticIndex++;
    self.end = ko.observable();
    self.start = ko.observable();
    
    self.formattedStart = ko.computed(function() {
    	return self.start() ? self.start().format('lll') : "";
    });
    
    self.formattedEnd = ko.computed(function() {
    	return self.end() ? self.end().format('lll') : "";
    });
    
    self.title = function() {
    	return 'Unavailability '+ self.id;
    };
    
    self.totalTime = function() {
    	return self.end().diff(self.start(), 'hours', true);
    };
    
    self.fromJSON = function(json) {
        self.id = json.id;
        self.start(moment(json.start));
        self.end(moment(json.end));
        self.color = json.color;
        self.rendering = json.rendering;
        self.constraint = json.constraint;
        self.overlap = json.overlap;
    };
    
    self.toJSON = function() {
    	return {
    		id: self.id,
    		start: self.start().format(),
    		end: self.end().format(),
    		title: self.title(),
    		color: self.color,
    		rendering: self.rendering,
    		constraint: self.constraint,
    		overlap: self.overlap
    	};
    };
    
    if (json) {
        self.fromJSON(json);
    }
}

// Overall viewmodel for this screen, along with initial state
function AvailabilityViewModel() {
    var self = this;

    // Non-editable catalog data - would come from the server
    self.maxHours = 5;
    var data = [
		 			{
		 				id: 1,
						start: '2014-11-13T15:00:00',
						end: '2014-11-13T15:30:00',
						constraint: 'businessHours',
						color: '#F1A72F',
						overlap: shouldOverlap
					},
					{
						id: 2,
						start: '2014-11-13T11:00:00',
						end: '2014-11-13T11:30:00',
						constraint: 'businessHours',
						color: '#F1A72F',
						overlap: shouldOverlap
					}
				];
    
    self.slots = ko.observableArray([]);
    
    // Operations
    self.addSlot = function() {
    	var earliestDate = moment('2014-11-10T09:00:00');
    	var latestDate = moment('2014-11-10T18:00:00');
    	var deltaHours = 0.5;
    	var slot = self.firstAvailableSlot(earliestDate, latestDate, deltaHours);
        self.slots.push(slot);
        self.setCalendarNeedUpdate();
    };
    
    self.removeSlot = function(slot) { 
        self.slots.remove(slot);
        self.setCalendarNeedUpdate();
    };
    
    self.removeAllSlots = function() { 
        self.slots.removeAll();
        self.setCalendarNeedUpdate();
    };
    
    self.addSlotsFromJSON = function(json) {
    	var tempSlots = [];
    	json.forEach(function(slotData) {
    		var newSlot = new Slot(slotData);
    		tempSlots.push(newSlot);
    	});
    	self.slots(tempSlots);
    };
    self.addSlotsFromJSON(data);
    
    
    // Getters
    
    self.slotWithID = function(slotID) {
    	var slot = null;
    	self.slots().forEach(function(slotIte) {
    	    if (slotIte.id == slotID) {
    	    	slot = slotIte;
    	    }
    	});
    	return slot;
    };
    
    self.firstAvailableSlot = function(earliestDate, latestDate, deltaHours) {
    	
    	var slots = self.slots().slice(0); // make a copy by value
    	slots.sort(function(a,b) { // order by starting date --> No event can go above an other one !!!!
    		return a.start().diff(b.start());
    	});

    	var event = slots[0];
    	var start = earliestDate;
    	var end = event ? event.start() : latestDate;	

    	var expectedEnd = moment(start).add(deltaHours, 'hours');
    	var dayDiff = latestDate.diff(expectedEnd, 'days', true);
    	var hourDiff = latestDate.diff(expectedEnd, 'hours', true);
    	for (var i = 0; i <= slots.length && (dayDiff >= 0 || hourDiff >= 0); i++) { // && not the same day or before end of day
    		if (end.diff(start, 'hours', true) >= deltaHours) {
    			return new Slot({
    				id: staticIndex,
    				start: start.format(),
    				end: expectedEnd.format(),
    				constraint: 'businessHours',
    				color: '#F1A72F',
    				overlap: shouldOverlap
    			});
        	}

        	start = event.end();
    		event = slots[i+1];
    		end = event ? event.start() : latestDate;
        	expectedEnd = moment(start).add(deltaHours, 'hours');
        	dayDiff = latestDate.diff(expectedEnd, 'days', true);
        	hourDiff = latestDate.diff(expectedEnd, 'hours', true);
    	}
    	console.log('fuck');
    	return null;
    };
    
    self.findAvailableSlot = function(start, end, deltaHours) {
    	
    	if (end.diff(start, 'hours', true) >= deltaHours) {
    		return true;
    	} else {
    		return false;
    	}
    	
    };
    
    self.totalTime = ko.computed(function() {
    	var totalTime = 0;
    	self.slots().forEach(function(slot) {
    		totalTime += slot.totalTime();
    	});
    	return totalTime;
    });
    
    self.quota = function() {
    	return Math.round(self.totalTime() / self.maxHours * 100.0);
    };
    
    self.avaibilityQuota = ko.computed(function() {
    	return self.quota() + "%";
    });
    
    self.avaibilityQuotaLimited = ko.computed(function() {
    	return Math.min(self.quota(), 100) + "%";
    });
    
    self.quotaClass= ko.computed(function() {
    	var quota = self.quota();
    	if (quota < 60) {
    		return 'progress-bar-success';
    	} else if (quota <= 100) {
    		return 'progress-bar-warning';
    	} else {
    		return 'progress-bar-danger';
    	}
    });
    
    self.fetchSlots = function() {
    	var userType = getParameterByName('type');
    	var userID = getParameterByName('id');
    	var contentType = 'json';
		$.ajax({
			url: '/PIMS/keywords/?type=' + userType + '&id=' + userID +'&content='+ contentType,
			success: this.handleFetchKeywords,
			error: null
		});
    };
    
    self.handleFetchSlotss = function(result) {
    	var json = JSON.parse(result);
    	
    	
    	// read exisiting keywords
    	json.existingKeywords.forEach(function(keywordInfo) {
    	    console.log(keywordInfo);
    	    self.existingKeywords.push(new Keyword(keywordInfo[0], keywordInfo[1]));
    	});
    	
    	// Reset arrays
    	self.availableKeywords.removeAll();
    	self.selectedKeywords.removeAll();
        ko.utils.arrayPushAll(self.availableKeywords, self.existingKeywords);
    	
        // find selected keywords
    	json.selectedKeywords.forEach(function(keywordInfo) {
    	    var keyword = self.keywoardWithID(keywordInfo[0]);
    	    if (keyword) {
        	    self.addKeyword(keyword);
    	    }
    	});
    };
    
    self.submitSlots = function() {

    	var userType = getParameterByName('type');
    	var userID = getParameterByName('id');
    	var url = '/PIMS/keywords/';
    	var data = {
    		userType: userType,
    		userID: userID,
    		keywords: JSON.stringify(self.selectedKeywords())
    	};
    	
    	$.post(url, data, self.generateSubmitHandler());
    };
    
    self.generateSubmitHandler = function() {
    	var nbKeywords = self.selectedKeywords().length;
    	var submitHandler = function(data, status) {
        	console.log(data);
        	console.log(status);
        	if (status == "success") {
            	popSuccess(nbKeywords);
        	} else {
        		popError(nbKeywords);
        	}
        };
        return submitHandler;
    };
    
    // Link knockout and fullcalendar    
    self.getSlots = function(start, end, timezone, callback) {
    	var slots = [];
    	var i = 0;
    	self.slots().forEach(function(slot) {
    		slots[i++] = slot.toJSON();
    	});
    	callback(slots);
    };
    
    self.setCalendarNeedUpdate = function() {
    	$('#calendar').fullCalendar( 'refetchEvents' );
    };
    
    self.slotChanged = function(event, delta, revertFunc) {
    	var slot = self.slotWithID(event.id);
    	slot.fromJSON(event);
    };
    
}

var availabilityKnockout = new AvailabilityViewModel();
ko.applyBindings(availabilityKnockout);