var stompClient = null;

function connect() {
  var socket = new SockJS('/messages');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function(frame) {
    // setConnected(true);
    console.log('Stomp: ' + frame);
    stompClient.subscribe('/api/message/receive', function() {
      location.reload();
    });
  });
}

  function disconnect() {
    if (stompClient != null) {
      stompClient.disconnect();
    }
    console.log("Disconnected");
  }

  connect()
