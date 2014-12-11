$(document).ready( function () {
	var jsonDiv = $('#json-variables');
	var json = JSON.parse(jsonDiv.text());
	
	var showRender = function ( data, type, full, meta ) {
	    return '<a href="/PIMS/inspectionweeks/' + json.moduleID + '/' + data + '/">Edit</a>';
	};
	
	if (json.moduleID !== null) {
		
	    $('#inspection-week-table').DataTable( {
	        "ajax": '/PIMS/inspectionweeks/'+ json.moduleID +'/?content=json',
	        "columnDefs": [{
	            "targets": 2,
	            "render": showRender
	          }]
	    });
		
	}
} );