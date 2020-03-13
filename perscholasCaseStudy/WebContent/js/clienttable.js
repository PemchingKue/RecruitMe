/*
* Filename: clienttable.js
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
* 
* This is a jquery plug-in tables from dataTables
* 
*/
$(document).ready( function () {
	
	//EDITOR
	var editor = new $.fn.dataTable.Editor( {
		ajax: {
	        create: {
	            url: '/perscholasCaseStudy/CreateClientServlet'
	        },
	        edit: {
	            type: 'POST',
	            url: '/perscholasCaseStudy/UpdateClientServlet'
	        },
	        remove: {
	            url: '/perscholasCaseStudy/DeleteClientServlet'
	        }
	    },
	    legacyAjax: true,
	    table: '#clientTable',
	    idSrc: 'clientId',
	    fields: [
	        { label: 'Company', name: 'clientName' },
	        { label: 'Position',  name: 'position'  },
	        {
                label: "Job Description",
                name: "role.roleId",
                type: "upload",
                ajax: {
                	type: 'POST',
                	url: "/perscholasCaseStudy/UploadJobRoleServlet"
                },
                display: function ( id ) {
                    return editor.file('role', id).fileName;
                },
                clearText: "Remove File"
            }
	    ]
	} );
	
    // Edit record
    $('#clientTable').on('click', 'a.editor_edit', function (e) {
        e.preventDefault();

        editor.edit( $(this).closest('tr'), {
            title: 'Edit Client',
            buttons: 'Update'
        } );
    } );
 
    // Delete a record
    $('#clientTable').on('click', 'a.editor_remove', function (e) {
        e.preventDefault();

        editor.remove( $(this).closest('tr'), {
            title: 'Delete Client',
            message: 'Are you sure you wish to remove this client?',
            buttons: 'Delete'
        } );
    } );
	
	//DATATABLES
$('#clientTable').DataTable({
		processing: true,
		serverSide: true,
		dom: 
			"<'row'<'col-sm-12'B>>" +
			"<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
		ajax : {
			"url" : "/perscholasCaseStudy/FetchClientServlet",
			"dataSrc": "data",
			"type" : "GET"
			},
	    columnDefs: [
	        { 	
	        	className: "titleCase",
	        	targets: [0, 1]
	        }
	    ],
		columns: [
			{ "data": "clientName" },
            { "data": "position" },
            {
                "data": "role.roleId",
                render: function ( id ) {
                	
                    return id ?
                            '<a href="/perscholasCaseStudy/DownloadJobRoleServlet?id='+id+'">'+editor.file( 'role', id ).fileName +'</a>' :
                            null;
                },
                defaultContent: "No Role Des"
            },
            {
                className: "center",
                defaultContent: '<a href="" class="editor_edit">Edit</a> / <a href="" class="editor_remove">Delete</a>'
            }
        ],
        buttons: [
            { extend: 'create', text: 'Add', editor: editor, formTitle: 'Add a Client' }
        ]
	});

});