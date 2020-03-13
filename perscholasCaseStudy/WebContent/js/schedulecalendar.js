/*
* Filename: schedulecalendar.js
* Author: Pemching Kue
* 03/13/2020 
* Modified by: Pemching Kue
* 
* This is a javascript plug-in for calendars called fullcalendar.io
* 
*/
document.addEventListener('DOMContentLoaded', function() {
	
	  var calendarEl = document.getElementById('scheduleCalendar');
	
	  var calendar = new FullCalendar.Calendar(calendarEl, {
	    plugins: [ 'timeGrid', 'dayGrid', 'interaction', 'bootstrap' ],
	    themeSystem: 'bootstrap',
	    defaultView: 'dayGridMonth',
	    height: 550,
	    nowIndicator: true,
	    selectable: true,
	    editable: true,
	    navLinks: true,
	    eventLimit: true,
	    header: {
	      left: 'prev,next today',
	      center: 'title',
	      right: 'dayGridMonth,timeGridWeek,timeGridDay'
	    },
	    eventSources: [
	    	
	    	{
	    		url: '/perscholasCaseStudy/FetchCalendarServlet',
	    		method: 'GET'
	    	}

	    ],
	    select: function(info) {

	      $('#calendarModal').modal('show');
	      
	      $('#calendarForm').on('submit', function(){
	    	  	  
	    	  var eventName = $("#calendarModal #title").val().trim();
	    	  var startValue = $("#calendarModal #starts-at").val().trim();
	    	  var endValue = $("#calendarModal #ends-at").val().trim();

	    	  if(eventName && moment(info.startStr).add(1, 'days').format('YYYY-MM-DD') == moment(info.endStr).format('YYYY-MM-DD')){
		    	  
	    		  var start = info.startStr+'T'+startValue+':00'+'-05:00';
		    	  var end = info.startStr+'T'+endValue+':00'+'-05:00';

		    	  $.ajax({
		    		  url: '/perscholasCaseStudy/CreateEventServlet',
		    		  type: "POST",
		    		  data:{ title:eventName, start:start, end:end },
					  success:function(){
						  calendar.refetchEvents()
						  $("#createModal").modal('show');
					  }
				  })
				  
				$("#calendarForm").unbind();
		    	$("#calendarModal").modal('hide');
		    	return false;
		    	
			  }else if(eventName){
			    	  var start = info.startStr+'T'+startValue+':00'+'-05:00';
			    	  var end = info.endStr+'T'+endValue+':00'+'-05:00';

			    	  $.ajax({
			    		  url: '/perscholasCaseStudy/CreateEventServlet',
			    		  type: "POST",
			    		  data:{ title:eventName, start:start, end:end },
						  success:function(){
							  calendar.refetchEvents()
							  $("#createModal").modal('show');
						  }
					  })
					$("#calendarForm").unbind();
			    	$("#calendarModal").modal('hide');
			    	return false;
			  	}
	    	     	  
	      });
	      
	      $(".calendarCloseButton").click(function(){
	    	    $("#calendarForm").unbind();
	    	  });

	    },
	    eventResize:function(info){
	    	  var start = calendar.formatIso(info.event.start);
	    	  var end = calendar.formatIso(info.event.end);
	    	  var title = info.event.title;
	    	  var id = info.event.id;
	    	  $.ajax({
	    		  url: '/perscholasCaseStudy/UpdateEventServlet',
	    		  type: "POST",
	    		  data:{ title:title, start:start, end:end, id:id },
				  success:function(){
					  calendar.refetchEvents()
					  $("#updateModal").modal('show');
				  }
			  })
	    },
	    eventDrop:function(info){
	    	  var start = calendar.formatIso(info.event.start);
	    	  var end = calendar.formatIso(info.event.end);
	    	  var title = info.event.title;
	    	  var id = info.event.id;
	    	  $.ajax({
	    		  url: '/perscholasCaseStudy/UpdateEventServlet',
	    		  type: "POST",
	    		  data:{ title:title, start:start, end:end, id:id },
				  success:function(){
					  calendar.refetchEvents()
					  $("#updateModal").modal('show');
				  }
			  })
	    },
	    eventClick:function(info){
	    	
	    	if(confirm("Are you sure you want to remove it?")){
	    		var id = info.event.id;
		    	  $.ajax({
		    		  url: '/perscholasCaseStudy/DeleteEventServlet',
		    		  type: "POST",
		    		  data:{ id:id },
					  success:function(){
						  calendar.refetchEvents()
						  $("#deleteModal").modal('show');
					  }
				  })
	    	}
	    }
	    
	});

  calendar.render();
  $("#starts-at, #ends-at").timepicker();
  
  $('#calendarModal').on('hidden.bs.modal', function () {
	    $(this).find('form').trigger('reset');
	})
  
});