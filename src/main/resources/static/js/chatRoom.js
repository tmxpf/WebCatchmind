$(document).ready(function() {
    chatSock.init();
    chatSock.data.roomId = localStorage.getItem('wschat.roomId');
    chatSock.data.sender = localStorage.getItem('wschat.sender');
    chatSock.findRoom();

    connect();
});

var sock = new SockJS("/ws-chat");
var ws = Stomp.over(sock);
var reconnect = 0;

var chatSock = {
    init : function() {
       var _this = this;

       $('#msg_push').on('click', function () {
            _this.data.message = $('#msg_text').val();
           $('#msg_text').val('');
            _this.sendMessage();
       })
    },
    data : {
        roomId : '',
        room : {},
        sender : '',
        users : [],
        message : '',
        messages : []
    },
    findRoom : function () {
        var _this = this;

        $.ajax({
            type : 'GET',
            url : '/chat/room/' + _this.data.roomId,
            dataType : 'json'
        }).done(function(result) {
            _this.data.room = result.name;
        }).fail(function(error) {
            console.log(error);
        });
    },
    getMSG : function() {
        var messages = this.data.messages;
        var innerHTML = '';

        messages.forEach(function (element) {
            var msg = element.sender + ' - ' + element.message;
            innerHTML += '<li class="list-group-item">'
                             + msg
                             + '</li>';
        });

        return innerHTML;
    },
    setUser : function() {
        var _this = this;

        $.ajax({
            type : 'GET',
            url : '/chat/room/' + _this.data.roomId,
            dataType : 'json'
        }).done(function(result) {
            _this.data.room = result.name;
        }).fail(function(error) {
            console.log(error);
        });
    },
    sendMessage : function () {
        ws.send("/pub/chat/message", {}, JSON.stringify({type : 'TALK', roomId : this.data.roomId, sender : this.data.sender, message : this.data.message}));
        this.data.message = '';
    },
    recvMessage : function (recv) {
        this.data.messages.unshift({"type" : recv.type, "sender" : ((recv.type == "ENTER") ? '[알림]' : recv.sender), "message" : recv.message});

        $('#msg_list').children().remove();
        $('#msg_list').append(this.getMSG());
    }
};

function connect() {
    ws.connect({}, function(frame) {

        ws.subscribe("/sub/chat/room/" + chatSock.data.roomId, function(message) {
            var recv = JSON.parse(message.body);
            chatSock.recvMessage(recv);
        });

        ws.send("/pub/chat/message", {}, JSON.stringify({type : 'ENTER', roomId : chatSock.data.roomId, sender : chatSock.data.sender}));
    }, function (error) {
        if(reconnect++ <= 5) {
            setTimeout(function () {
                console.log("connection reconnect");
                sock = new SockJS("/ws-stomp");
                ws = Stomp.over(sock);
                connect();
            }, 10*1000);
        }
    })
};