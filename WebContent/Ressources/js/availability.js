var shouldOverlap = function(fixedEvent, movingEvent) {
    return fixedEvent.title === 'Student not available';
}

$(document).ready(function() {

	$('#calendar').fullCalendar({
		header: {
			left: '',
			center: 'title',
			right: ''
		},
		defaultView: 'agendaWeek',
		defaultDate: '2014-11-12',
		slotDuration: '00:30:00',
		snapDuration: '00:15:00',
		minTime: '07:00:00',
		maxTime: '21:00:00',
		allDaySlot: false,
		weekends: false,
		businessHours: {
		    start: '9:00', // a start time (10am in this example)
		    end: '18:00', // an end time (6pm in this example)

		    dow: [ 1, 2, 3, 4, 5 ]
		    // days of week. an array of zero-based day of week integers (0=Sunday)
		    // (Monday-Friday in this example)
		},
		editable: true,
		events: [
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
		]
	});

});