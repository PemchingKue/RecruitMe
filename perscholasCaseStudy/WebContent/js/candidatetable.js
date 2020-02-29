$(document).ready( function () {

	//EDITOR
	var editor = new $.fn.dataTable.Editor( {
		ajax: {
	        create: {
	            url: '/perscholasCaseStudy/CreateCandidateServlet'
	        },
	        edit: {
	            type: 'POST',
	            url: '/perscholasCaseStudy/UpdateCandidateServlet'
	        },
	        remove: {
	            url: '/perscholasCaseStudy/DeleteCandidateServlet'
	        }
	    },
	    legacyAjax: true,
	    table: '#candidateTable',
	    idSrc: 'candidateId',
	    fields: [
	        { label: 'First Name', name: 'firstName' },
	        { label: 'Last Name',  name: 'lastName'  },
	        { label: 'Company',  name: 'client.clientName'  },
	        { label: 'Position',  name: 'client.position'  },
	        { label: 'Email',  name: 'email'  },
	        { label: 'Phone',  name: 'phone'  },
	        {
                label: "Resume",
                name: "resume.resumeId",
                type: "upload",
                ajax: {
                	type: 'POST',
                	url: "/perscholasCaseStudy/UploadResumeServlet"
                },
                display: function ( id ) {
//                	return '<a href="functions/document_get.php?id='+data+'">Download</a>';
                    return editor.file('resume', id).fileName;
                },
                clearText: "Remove File"
            }
	    ]
	} );
	
    // Edit record
    $('#candidateTable').on('click', 'a.editor_edit', function (e) {
        e.preventDefault();

        editor.edit( $(this).closest('tr'), {
            title: 'Edit Candidate',
            buttons: 'Update'
        } );
    } );
 
    // Delete a record
    $('#candidateTable').on('click', 'a.editor_remove', function (e) {
        e.preventDefault();

        editor.remove( $(this).closest('tr'), {
            title: 'Delete Candidate',
            message: 'Are you sure you wish to remove this candidate?',
            buttons: 'Delete'
        } );
    } );
	
	//DATATABLES
$('#candidateTable').DataTable({
		processing: true,
		serverSide: true,
		dom: 
			"<'row'<'col-sm-12'B>>" +
			"<'row'<'col-sm-12 col-md-6'l><'col-sm-12 col-md-6'f>>" +
			"<'row'<'col-sm-12'tr>>" +
			"<'row'<'col-sm-12 col-md-5'i><'col-sm-12 col-md-7'p>>",
		ajax : {
			"url" : "/perscholasCaseStudy/FetchCandidateServlet",
			"dataSrc": "data",
			"type" : "GET"
			},
		dataFilter: function(files){
			var obj = JSON.parse(files);
			console.log(JSON.stringify(obj));
			
			return obj;
		},
	    columnDefs: [
	        { 	
	        	className: "titleCase",
	        	targets: [0, 1, 2]
	        }
	    ],
		columns: [
            { "data": null, render: function ( data, type, row ) {
                // Combine the first and last names into a single table field
                return data.firstName + ' ' + data.lastName;
            } },
            { "data": "client.clientName" },
            { "data": "client.position" },
            { "data": "email" },
            { "data": "phone" },
            {
                "data": "resume.resumeId",
                render: function ( id ) {
                	
                    return id ?
                            '<a href="/perscholasCaseStudy/DownloadResumeServlet?id='+id+'">'+editor.file( 'resume', id ).fileName +'</a>' :
                            null;
                },
                defaultContent: "No Resume"
            },
            {
                defaultContent: '<a href="" class="editor_edit">Edit</a> / <a href="" class="editor_remove">Delete</a>'
            }
        ],
        buttons: [
            { extend: 'create', text: 'Add', editor: editor, formTitle: 'Add a Candidate' }
        ]
	});

});