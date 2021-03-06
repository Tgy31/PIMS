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
    self.maxHours = ko.observable(1);
    self.startDate = null;
    self.endDate = null;
    
    self.slots = ko.observableArray([]);
    self.otherSlots = null; // list of slots fetched but not used
    
    // Operations
    self.addSlot = function() {
    	var earliestDate = moment(self.startDate + 'T09:00:00');
    	var latestDate = moment(self.startDate + 'T18:00:00');
    	var deltaHours = 1.0;
    	var slot = self.firstAvailableSlot(earliestDate, latestDate, deltaHours);
    	if (slot) {
            self.slots.push(slot);
            self.setCalendarNeedUpdate();
    	}
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
		var weekSlot = {
			start: self.startDate,
			end: self.endDate
		};
    	var tempSlots = [];
    	var tempOthers = [];
    	json.forEach(function(slotData) {
    		slotData.constraint = 'businessHours';
    		slotData.color = "#F1A72F";
    		slotData.overlap = shouldOverlap;
    		var newSlot = new Slot(slotData);
    		if (overlap(weekSlot, slotData)) { // Test if event is in the inspection week limits
        		tempSlots.push(newSlot);
    		} else {
    			tempOthers.push(newSlot); // If not for this inspection week, put on the side
    		}
    	});
    	self.slots(tempSlots);
    	self.otherSlots = tempOthers;
    	self.setCalendarNeedUpdate();
    };
    
    
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
    	return Math.round(self.totalTime() / self.maxHours() * 100.0);
    };
    
    self.avaibilityQuota = ko.computed(function() {
    	return self.quota() + "%";
    });
    
    self.avaibilityQuotaLimited = ko.computed(function() {
    	return Math.min(self.quota(), 100) + "%";
    });
    
    self.quotaClass= ko.computed(function() {
    	var quota = self.quota();
    	if (quota <= 80) {
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
    	var weekID = getParameterByName('week');
    	var contentType = 'json';
		$.ajax({
			url: '/PIMS/availability/?type=' + userType + '&id=' + userID +'&content='+ contentType +'&week='+ weekID,
			success: this.handleFetchSlots,
			error: null
		});
    };
    
    self.handleFetchSlots = function(result) {
    	var json = JSON.parse(result);
    	
    	self.startDate = moment(json.defaultDate).format('YYYY-MM-DD');
    	self.endDate = moment(self.startDate).add(4, 'days').format('YYYY-MM-DD');
    	createCalendar(self);
    	
    	self.maxHours(json.maxUnavailability);
    	
    	self.addSlotsFromJSON(json.slots);
    };
    
    self.submitSlots = function() {
    	if (self.quota() > 100) {
    		popError('Too much unavailability');
    	} else {
        	var userType = getParameterByName('type');
        	var userID = getParameterByName('id');
        	var url = '/PIMS/availability/';
        	var slots = self.slots().concat(self.otherSlots); // get the slots we did not used back
        	var data = {
        		userType: userType,
        		userID: userID,
        		slots: JSON.stringify(slots) // send all the slots at once
        	};
        	$.post(url, data, self.generateSubmitHandler());
    	}
    };
    
    self.generateSubmitHandler = function() {
    	var nbKeywords = self.slots().length;
    	var submitHandler = function(data, status) {
        	if (status == "success") {
            	popSuccess('Unavailability saved');
        	} else {
        		popError('Error');
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
    
    self.fetchSlots();
}

var availabilityKnockout = new AvailabilityViewModel();
ko.applyBindings(availabilityKnockout);


function popSuccess(message) {
	var html = '<div class="alert alert-success" role="alert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'+message+'</div>';
	html = html + $("#alert-zone").html();
	$("#alert-zone").html(html);
}

function popError(message) {
	var html = '<div class="alert alert-danger" role="alert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>'+message+'</div>';
	html = html + $("#alert-zone").html();
	$("#alert-zone").html(html);
}