var showRender = function ( data, type, full, meta ) {
    return '<a href="./'+data+'/">Edit</a>';
};

$(document).ready( function () {
	var jsonDiv = $('#json-variables');
	var json = JSON.parse(jsonDiv.text());
	
    $('#inspection-week-table').DataTable( {
        "ajax": '/PIMS/inspectionweeks/'+ json.moduleID +'/?content=json',
        "columnDefs": [{
            "targets": 3,
            "render": showRender
          }]
    });
} );