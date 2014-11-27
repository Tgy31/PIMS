// Class to present and edit available slots
var staticIndex = 1;
function Slot(id, title, start, end) {
    var self = this;
    self.id = id;
    self.index = staticIndex++;
    self.start = moment(start);
    self.end = moment(end);
    self.title = title;
    
    self.formattedStart = function() {
    	return self.start;
    };
    
    self.formattedEnd = function() {
    	return self.end;
    };
}

function popSuccess(nbKeywords) {
	var html = '<div class="alert alert-success" role="alert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>Save successful. You now have <b>' + nbKeywords + '</b> keywords</div>';
	html = html + $("#alert-zone").html();
	$("#alert-zone").html(html);
}

function popError(nbKeywords) {
	var html = '<div class="alert alert-danger" role="alert"><button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>An error occurred, your keywords have not been saved !</div>';
	$("#alert-zone").html(html);
}

// Overall viewmodel for this screen, along with initial state
function AvailabilityViewModel() {
    var self = this;

    // Non-editable catalog data - would come from the server
    var data = [
                         			{
                        				title: 'Inspection 1',
                        				start: '2014-11-13T11:00:00',
                        				end: '2014-11-13T11:30:00',
                        				constraint: 'businessHours',
                        				color: '#257e4a',
                        				overlap: shouldOverlap
                        			},
                        			{
                        				title: 'Inspection 2',
                        				start: '2014-11-13T15:00:00',
                        				end: '2014-11-13T15:30:00',
                        				constraint: 'businessHours',
                        				color: '#257e4a',
                        				overlap: shouldOverlap
                        			},

                        			// Inspector avaibility
                        			{
                        				title: 'Inspector not available',
                        				start: '2014-11-12T10:00:00',
                        				end: '2014-11-12T15:00:00',
                        				color: 'red',
                        				rendering: 'background',
                        				overlap: false
                        			},

                        			// Student avaibility
                        			{
                        				title: 'Student not available',
                        				start: '2014-11-11T11:00:00',
                        				end: '2014-11-11T12:00:00',
                        				color: 'orange',
                        				rendering: 'background'
                        			},
                        			{
                        				title: 'Student not available',
                        				start: '2014-11-14T14:00:00',
                        				end: '2014-11-14T16:00:00',
                        				color: 'orange',
                        				rendering: 'background'
                        			},
                        		];
    
    self.slots = ko.observableArray([]);
    
    // Operations
    self.addSlot = function() {
    	var slot = new Slot(staticIndex++, "meeting");
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
    		var newSlot = new Slot(0, slotData.title, slotData.start, slotData.end);
    		tempSlots.push(newSlot);
    	});
    	self.slots(tempSlots);
    };
    self.addSlotsFromJSON(data);
    
    
    // Getters
    
    self.slotWithID = function(slotID) {
    	var slot = null;
    	this.slots.forEach(function(slotIte) {
    	    if (slotIte.id == slotID) {
    	    	slot = slotIte;
    	    }
    	});
    	return slot;
    };
    
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
    	console.log('slots requested');
    	callback(self.slots());
    };
    
    self.setCalendarNeedUpdate = function() {
    	console.log('need update');
    	$('#calendar').fullCalendar( 'refetchEvents' );
    };
    
}

var availabilityKnockout = new AvailabilityViewModel();
ko.applyBindings(availabilityKnockout);