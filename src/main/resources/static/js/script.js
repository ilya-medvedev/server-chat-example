var socket = new SockJS('/sockjs');
var stompClient = Stomp.over(socket);

stompClient.connect({}, subscribe);

function subscribe() {
    stompClient.subscribe('/topic/message', receiveMessage);
}

function sendMessage() {
    var text = document.getElementById('text').value;

    var json = JSON.stringify({
        'text' : text
    });

    stompClient.send("/app/message", {}, json);

    document.getElementById('message').reset();

    return false;
}

function receiveMessage(response) {
    var json = response.body;
    var message = JSON.parse(json);

    var name = message.name;
    var text = message.text;

    var messages = document.getElementById('messages');
    var div = document.createElement('div');

    div.appendChild(document.createTextNode(name + ': ' + text));

    messages.appendChild(div);
}