var stompClient = null;

function connect() {
  var socket = new SockJS('/server');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function(frame) {
    console.log('Stomp: ' + frame);
    stompClient.subscribe('/socket', function() {
      location.reload();
    });
  });
}

  connect();

// Auto-refresh
//
// setTimeout(function() {
//   location.reload();
// }, 5000);
