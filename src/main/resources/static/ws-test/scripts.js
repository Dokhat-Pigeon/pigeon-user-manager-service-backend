import { Client } from '@stomp/stompjs';

document.querySelector('#connect').addEventListener('click', connect);
document.querySelector('#disconnect').addEventListener('click', disconnect);
document.querySelector('#subscribe').addEventListener('click', subscribe);
document.querySelector('#unsubscribe').addEventListener('click', unsubscribe);

//Перед запуском вставить актуальный токен без приставки "Bearer"
let auth_token = '';

setConnected(false);
setSubscribe(false);
console.log("Opened");
let onlineClient = null;
let subscribeEvent = null;

function setConnected(connected) {
    console.log("setConnected: " + connected);
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#form-send").show();
        $("#table-chat").show();
    } else {
        $("#form-send").hide();
        $("#table-chat").hide();
    }
    $("#table-msg-response").html("");
}

function setSubscribe(connected) {
    $("#subscribe").prop("disabled", connected);
    $("#unsubscribe").prop("disabled", !connected);
}

function connect() {
    onlineClient = new Client(
        {
            brokerURL: 'ws://localhost:8081/api/ws',
            connectHeaders: {
                Authorization: `Bearer ${auth_token}`
            },
            reconnectDelay: 0,
            onConnect: function(frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
            }
        }
    );
    onlineClient.activate()
}

function subscribe() {
    let userName = document.querySelector('#login-for-subscribe').value;
    subscribeEvent = onlineClient.subscribe(`/user/online/${userName}`, function (msgResponse) {
        showMsgResponse(msgResponse.body);
    })
    console.log(`Subscribe on /user/online/${userName}`);
    setSubscribe(true);
}

function showMsgResponse(msgResponse) {
    $("#table-msg-response").append("<tr><td>" + msgResponse + "</td></tr>");
}

function disconnect() {
    if (onlineClient !== null) {
        onlineClient.deactivate();
    }
    setConnected(false);
    console.log("Disconnected");
}

function unsubscribe() {
    if (subscribeEvent !== null) {
        subscribeEvent.unsubscribe()
    }
    setSubscribe(false);
    console.log("Unsubscribe");
}