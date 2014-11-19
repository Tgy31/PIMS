$(document).ready( function () {
    $('#inspector-table').DataTable( {
        "ajax": '/PIMS/inspectors.json'
    });
} );