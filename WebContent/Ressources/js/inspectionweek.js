$(document).ready( function () {
	var jsonDiv = $('#json-variables');
	var json = JSON.parse(jsonDiv.text());
	
	var showRender = function ( data, type, full, meta ) {
	    return '<a href="/PIMS/inspections/' + json.moduleID + '/' + data + '/">Edit</a>';
	};
	
	if (json.inspectionWeekID !== null) {
	
	    $('#inspection-table').DataTable( {
	        "ajax": '/PIMS/inspectionweeks/'+ json.inspectionWeekID +'/?content=json',
	        "columnDefs": [{
	            "targets": 3,
	            "render": showRender
	          }]
	    });
	    
	}
} );