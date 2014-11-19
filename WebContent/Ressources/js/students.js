var showRender = function ( data, type, full, meta ) {
    return '<a href="./'+data+'/">Show</a>';
};

$(document).ready( function () {
    $('#student-table').DataTable( {
        "ajax": '/PIMS/students.json',
        "columnDefs": [{
            "targets": 3,
            "render": showRender
          }]
    });
    
    $('#student-table').on( 'click', 'tbody tr', function () {
    	window.location.href = $(this).attr('href');
    } );
} );