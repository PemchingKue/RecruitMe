$(document).ready(function() {

    $.PostItAll.load();
    
    $('#postItAll').click(function() {
        $.PostItAll.new();
    });

});