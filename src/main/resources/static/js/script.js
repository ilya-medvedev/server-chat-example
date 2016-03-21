var socket = new SockJS('/sockjs');
var stompClient = Stomp.over(socket);

stompClient.connect({}, subscribe);

function subscribe() {
    stompClient.subscribe('/topic/message', showMessage);
}

function send() {
    var name = document.getElementById('name').value;
    var message = document.getElementById('message').value;

    var json = JSON.stringify({
        'name' : name,
        'message' : message
    });

    stompClient.send("/app/message", {}, json);
}

function showMessage(response) {
    var json = response.body;
    var message = JSON.parse(json);

    var name = message.name;
    var text = message.message;

    var messages = document.getElementById('messages');
    var div = document.createElement('div');

    div.appendChild(document.createTextNode(name + ': ' + text));

    messages.appendChild(div);
}