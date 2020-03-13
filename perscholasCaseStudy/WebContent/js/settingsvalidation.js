/*
* Filename: settingsvalidation.js
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
* 
* This is a jquery plug-in for validation called jquery validation
* 
*/
$(document).ready(function () {
	
	//Name and Email change form
	$("#infoform").validate({
        //SPECIFY RULES
        rules: {
            firstName: "required",
            lastName: "required",
            email: {
                required: true,
                email: true
            }
        },
        // Specify validation error messages
        messages: {
            firstName: "<div id='infoerror'>*Please provide a firstname</div>",
            lastName: "<div id='infoerror'>*Please provide a lastname</div>",
            email: "<div id='infoerror'>*Please enter a valid email address</div>"
        }
	});
	
	//Password change form
	$("#passwordform").validate({
        //SPECIFY RULES
        rules: {
            oldpassword: {
                required: true
            },
            newpassword: {
                required: true,
                minlength: 5
            },
            cnewpassword: {
                equalTo: "#newpass"
            }
        },
        // Specify validation error messages
        messages: {
            oldpassword: {
                required: "<div id='passworderror'>*Please provide your current password</div>"
            },
            newpassword: {
                required: "<div id='passworderror'>*Please provide a new password</div>",
                minlength: "<div id='passworderror'>*Your password must be at least 5 characters long</div>"
            },
            cnewpassword: {
                equalTo: "<div id='passworderror'>*Please enter the same password</div>"
            },
        }
	});
	
});