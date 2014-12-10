$(document).ready( function () {
	var jsonDiv = $('#json-variables');
	var json = JSON.parse(jsonDiv.text());
	
	var actionRender = function ( data, type, full, meta ) {
	    if (full[3] == true) {
		    return '<a href="/PIMS/inspections/' + json.moduleID + '/' + data + '/">Edit</a>';
	    } else {
		    return '<a href="/PIMS/inspections/' + json.moduleID + '/' + data + '/">Create</a>';
	    }
	};
	
	var inspectionRender = function ( data, type, full, meta ) {
	    if (data == true) {
	    	return '<span class="glyphicon glyphicon-ok"><span>';
	    } else {
	    	return '';
	    }
	};
	
	if (json.inspectionWeekID !== null) {
	
	    $('#inspection-table').DataTable( {
	        "ajax": '/PIMS/inspections/'+ json.inspectionWeekID +'/?content=json',
	        "columnDefs": [{
	            "targets": 4,
	            "render": actionRender
	          },{
	            "targets": 3,
	            "render": inspectionRender
	          }]
	    });
	    
	}
} );