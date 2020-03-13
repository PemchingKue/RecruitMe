/*
* Filename: postitall.js
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
* 
* This is a jquery plug-in for dynamic notes called postitall
* 
*/
$(document).ready(function() {

    $.PostItAll.load();
    
    $('#postItAll').click(function() {
        $.PostItAll.new();
    });

});