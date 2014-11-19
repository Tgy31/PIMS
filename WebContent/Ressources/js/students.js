$(document).ready( function () {
    $('#student-table').DataTable( {
        "ajax": '/PIMS/students.json'
    });
} );