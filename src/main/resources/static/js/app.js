var stompClient = null;



function connect() {
  var socket = new SockJS('/gs-guide-websocket');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function(frame) {
    setConnected(true);
    console.log('Stomp: ' + frame);
    stompClient.subscribe('/api/message/receive', function() {
      location.reload();
    });
  });
}

function start() {
  if (stompClient != null) {
    stompClient.connect();
  }

  function disconnect() {
    if (stompClient != null) {
      stompClient.disconnect();
    }
    console.log("Disconnected");
  }

  connect()
}


// function sendMessage() {
//   var from = document.getElementById('from').value;
//   var text = document.getElementById('text').value;
//   stompClient.
//   stompClient.send("/app/chat", {},
//       JSON.stringify({'from':from, 'text':text}));
// }
//
// function showMessageOutput(messageOutput) {
//   var response = document.getElementById('response');
//   var p = document.createElement('p');
//   p.style.wordWrap = 'break-word';
//   p.appendChild(document.createTextNode(messageOutput.from + ": "
//       + messageOutput.text + " (" + messageOutput.time + ")"));
//   response.appendChild(p);
// }