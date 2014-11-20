var activeRender = function ( data, type, full, meta ) {
	if (data == true) {
		return "<span class=\"glyphicon glyphicon-ok\"></span>";
	} else {
		return "";
	}
};

$(document).ready( function () {
    $('#module-table').DataTable( {
        "ajax": '/PIMS/Ressources/fake/modules.html',
        "columnDefs": [{
            "targets": 2,
            "render": activeRender
          }]
    });
} );