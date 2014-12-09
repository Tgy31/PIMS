var overlap = function(slot1, slot2) {
	var slot1Start = moment(slot1.start);
	var slot1End = moment(slot1.end);
	var slot2Start = moment(slot2.start);
	var slot2End = moment(slot2.end);
	var isBefore = slot2End.isBefore(slot1Start, 'seconde');
	var isAfter = slot1End.isBefore(slot2Start, 'seconde');
	return !isBefore && !isAfter;
};

var slotsOverlap = function(slots, referenceSlot) {
	var overlapFound = false;
	slots.forEach(function(slot) {
		if (overlap(slot, referenceSlot)) {
			overlapFound = true;
			return;
		}
	});
	return overlapFound;
};

var createCalendar = function(model) {

	$('#calendar').fullCalendar({
		header: {
			left: '',
			center: '',
			right: ''
		},
		height: 542,
		defaultView: 'agendaWeek',
		defaultDate: model.defaultDate,
		slotDuration: '00:30:00',
		snapDuration: '00:30:00',
		minTime: '08:00:00',
		maxTime: '19:00:00',
		allDaySlot: false,
		weekends: false,
		businessHours: {
		    start: '9:00', // a start time (9am in this example)
		    end: '18:00', // an end time (6pm in this example)

		    dow: [ 1, 2, 3, 4, 5 ]
		    // days of week. an array of zero-based day of week integers (0=Sunday)
		    // (Monday-Friday in this example)
		},
		editable: true,
		events: model.getSlots,
		eventDrop: model.slotChanged,
		eventResize: model.slotChanged
	});
	
};

$(document).ready(function() {

});