var shouldOverlap = function(fixedEvent, movingEvent) {
    return fixedEvent.title === 'Student not available';
}

$(document).ready(function() {

	$('#calendar').fullCalendar({
		header: {
			left: '',
			center: '',
			right: ''
		},
		height: 542,
		defaultView: 'agendaWeek',
		defaultDate: '2014-11-12',
		slotDuration: '00:30:00',
		snapDuration: '00:15:00',
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
		events: availabilityKnockout.getSlots
	});

});