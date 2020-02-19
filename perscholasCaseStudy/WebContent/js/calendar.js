/* 
This is a javascript plugin for calendars
called fullcalendar.io

*/

document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('calendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    plugins: [ 'timeGrid', 'dayGrid', 'interaction', 'bootstrap' ],
    themeSystem: 'bootstrap',
    defaultView: 'dayGridMonth',
    height: 550,
    nowIndicator: true,
    selectable: true,
    editable: true,
    eventLimit: true,
    header: {
      left: 'prev,next today,addEventButton',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    customButtons: {
      addEventButton: {
        text: 'Add Event',
        click: function() {
          var dateStr = prompt('Enter a date in YYYY-MM-DD format');
          var date = new Date(dateStr + 'T00:00:00'); // will be in local time

          if (!isNaN(date.valueOf())) { // valid?
            calendar.addEvent({
              title: 'test event',
              start: date,
              allDay: false
            });
          } else {
            alert('Invalid date.');
          }
        }
      }
    },
    select: function(info) {
      var eventName = prompt("Enter a event name:");
      calendar.addEvent({
        title: eventName,
        start: info.startStr,
        end: info.endStr,
        allDay: false
      });
    }
  });

  calendar.render();
});

//dashboard
document.addEventListener('DOMContentLoaded', function() {
  var calendarEl = document.getElementById('dashboardCalendar');

  var calendar = new FullCalendar.Calendar(calendarEl, {
    plugins: [ 'timeGrid', 'dayGrid', 'interaction', 'bootstrap' ],
    themeSystem: 'bootstrap',
    defaultView: 'dayGridMonth',
    height: 300,
    nowIndicator: true,
    selectable: true,
    editable: true,
    eventLimit: true,
    header: {
      left: 'prev,next today,addEventButton',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    customButtons: {
      addEventButton: {
        text: 'Add Event',
        click: function() {
          var dateStr = prompt('Enter a date in YYYY-MM-DD format');
          var date = new Date(dateStr + 'T00:00:00'); // will be in local time

          if (!isNaN(date.valueOf())) { // valid?
            calendar.addEvent({
              title: 'test event',
              start: date,
              allDay: false
            });
          } else {
            alert('Invalid date.');
          }
        }
      }
    },
    select: function(info) {
      var eventName = prompt("Enter a event name:");
      calendar.addEvent({
        title: eventName,
        start: info.startStr,
        end: info.endStr,
        allDay: false
      });
    }
  });

  calendar.render();
});