/* 
This is a javascript plugin for calendars
called fullcalendar.io

*/

document.addEventListener('DOMContentLoaded', function() {
	
	  var calendarEl = document.getElementById('scheduleCalendar');
	
	  var calendar = new FullCalendar.Calendar(calendarEl, {
	    plugins: [ 'timeGrid', 'dayGrid', 'interaction', 'bootstrap', 'moment' ],
	    themeSystem: 'bootstrap',
	    defaultView: 'dayGridMonth',
	    height: 550,
	    nowIndicator: true,
	    selectable: true,
	    editable: true,
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
	      var eventName = prompt("Enter a event name:");
	      if(eventName){
	    	  var start = calendar.formatIso(info.start);
	    	  var end = calendar.formatIso(info.end);
	    	  $.ajax({
	    		  url: '/perscholasCaseStudy/CreateEventServlet',
	    		  type: "POST",
	    		  data:{ title:eventName, start:start, end:end },
				  success:function(){
					  calendar.refetchEvents()
					  alert("added successfully");
				  }
			  })
		  	}
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
					  alert("updated successfully");
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
					  alert("updated successfully");
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
						  alert("deleted successfully");
					  }
				  })
	    	}
	    }
	    
	});

  calendar.render();
  
});