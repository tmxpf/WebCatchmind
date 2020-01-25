$(document).ready(function() {
    chatEvent.init();
    chatEvent.findAllRoom();
});

var chatEvent = {
    init : function () {
        var _this = this;

        $('#createRoom').on('click', function() {
            _this.createRoom();
        });

        $('#game_room_list').on('click', function(e) {
            var roomId = e.target.dataset.roomid;

            _this.enterRoom(roomId);
        });
    },
    data : {
        room_name : '',
        chatrooms: []
    },
    findAllRoom : function () {
        var chatrooms = this.data.chatrooms;

        $.ajax({
            type : 'GET',
            url : '/chat/rooms',
            dataType : 'json'
        }).done(function(result) {
            $('#game_room_list').children().remove();
            chatrooms = result;

            chatrooms.forEach(function(item) {
                var innerHTML = '<li class="list-group-item list-group-item-action" data-roomid="' + item.roomId + '">'
                                    + item.name
                                    + '</li>';

                $('#game_room_list').append(innerHTML);
            });

        }).fail(function(error) {
            // alert(JSON.stringify(error));
        });
    },
    createRoom : function() {
        var roomName = $('#room_name').val();
        var _this = this;

        if("" === roomName) {
            alert("방 제목을 입력해 주십시요.");

            return false;
        } else {
            $.ajax({
                type : 'POST',
                url : '/chat/createRoom',
                dataType : 'json',
                data : {name : roomName}
            }).done(function(result) {
                alert(result.name+"방 개설에 성공하였습니다.");

                _this.data.room_name = result.name;
                _this.findAllRoom();
            }).fail(function(error) {
                alert("채팅방 개설에 실패하였습니다.");
            });
        }
    },
    enterRoom : function (roomId) {
        var sender = prompt('대화명을 입력해 주세요.');

        if(sender != "" && sender != null && sender != 'undefined') {
            localStorage.setItem('wschat.sender', sender);
            localStorage.setItem('wschat.roomId', roomId);
            location.href="/chat/room/enter/" + roomId;
        }
    }
};

// var vm = new Vue({
//     el: '#app',
//     data: {
//         room_name : '',
//         chatrooms: [
//         ]
//     },
//     created() {
//         this.findAllRoom();
//     },
//     methods: {
//         findAllRoom: function() {
//             axios.get('/chat/rooms').then(response => { this.chatrooms = response.data; });
//         },
//         createRoom: function() {
//             if("" === this.room_name) {
//                 alert("방 제목을 입력해 주십시요.");
//
//                 return false;
//             } else {
//                 const params = new URLSearchParams();
//                 params.append("name", this.room_name);
//
//                 axios.post('/chat/createRoom', params)
//                     .then(
//                         response => {
//                             alert(response.data.name+"방 개설에 성공하였습니다.");
//                             this.room_name = response.data.name;
//                             this.findAllRoom();
//                         }
//                     )
//                     .catch( response => { alert("채팅방 개설에 실패하였습니다."); } );
//             }
//         },
//         enterRoom: function(roomId) {
//             const sender = prompt('대화명을 입력해 주세요.');
//
//             if(sender != "") {
//                 localStorage.setItem('wschat.sender',sender);
//                 localStorage.setItem('wschat.roomId',roomId);
//                 location.href="/chat/room/enter/"+roomId;
//             }
//         }
//     }
// });