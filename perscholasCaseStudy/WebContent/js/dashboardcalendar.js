document.addEventListener('DOMContentLoaded', function() {
	
	  var calendarEl = document.getElementById('dashboardCalendar');
	
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
	    select: function(start, end, allDay) {
	      var eventName = prompt("Enter a event name TEST:");
	      if(eventName){
	    	  var start = info.startStr;
	    	  var end = info.endStr;
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
	    	  var start = info.event.startStr;
	    	  var end = info.event.endStr;
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
	    	  var start = moment(info.event.start);
	    	  var end = moment(info.event.end);
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
	    }
	    
	});

  calendar.render();
  
});