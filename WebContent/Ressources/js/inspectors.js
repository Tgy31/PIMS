var showRender = function ( data, type, full, meta ) {
    return '<a href="./'+data+'/">Show</a>';
};

$(document).ready( function () {
    $('#inspector-table').DataTable( {
        "ajax": '/PIMS/inspectors.json',
        "columnDefs": [{
            "targets": 3,
            "render": showRender
          }]
    });
} );