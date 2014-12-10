$(document).ready( function () {
	var jsonDiv = $('#json-variables');
	var json = JSON.parse(jsonDiv.text());
	
	var actionRender = function ( data, type, full, meta ) {
	    if (full[2] == true) {
		    return '<a href="/PIMS/inspections/' + json.moduleID + '/' + data + '/">Edit</a>';
	    } else {
		    return '<a href="/PIMS/inspections/' + json.moduleID + '/' + data + '/">Create</a>';
	    }
	};
	
	var inspectionRender = function ( data, type, full, meta ) {
	    if (data == true) {
	    	return '<span class="glyphicon glyphicon-ok text-success" title="The inspection has been set"><span>';
	    } else {
	    	return '';
	    }
	};
	
	if (json.inspectionWeekID !== null) {
	
	    $('#inspection-table').DataTable( {
	        "ajax": '/PIMS/inspections/'+ json.inspectionWeekID +'/?content=json',
	        "columnDefs": [{
	            "targets": 3,
	            "render": actionRender
	          },{
	            "targets": 2,
	            "render": inspectionRender
	          }]
	    });
	    
	}
	

    $( "#inputStartDate" ).datepicker({
    	dateFormat: "yy-mm-dd"
    });
    
    $("#buttonStartDate").on("click", function() {
    	$("#inputStartDate").datepicker("show");
    });
} );