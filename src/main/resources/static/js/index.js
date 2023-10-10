console.log("index.js 파일진입");

var main={
    init: function(){
        console.log("index.js 파일 진입 -init() 함수 진입");

        var _this= this;

        $("#registerBtn").on("click", function(){
            _this.save();
        });

        $("#modifyBtn").on("click", function(){
            _this.update();
        });

        $("#removeBtn").on("click", function(){
            _this.remove();
        });
    },

    save: function(){
    console.log("index.js 파일 진입- save() 함수진입");

        var data={
            title: $("#title").val(),
            author: $("#author").val(),
            content: $("#content").val()
        };

        $.ajax({
            type:'post',
            url:'/api/v1/posts',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data)
        }).done(function(num){
           console.log("index.js 파일 진입 -save() - ajax done() 진입");

            alert(num+"번 게시글이 등록되었습니다.");
            location.replace(`/`);
        }).fail(function(error){
            console.log("index.js 파일 진입 -save() - ajax fail() 진입");
        });
    },//save

    update: function(){
        console.log("index.js 파일 진입 -update() 진입");

        var data={
            title: $("#title").val(),
            content:$("#content").val()
        };

        var id=$("#id").val();

        $.ajax({
            type:'put',
            url:'/api/v1/posts/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data:JSON.stringify(data)
        }).done(function(num){
            console.log("index.js 파일 진입 -update() - ajax done() 진입");
            alert(num+"번 게시글 수정이 완료되었습니다.");
            location.replace(`/`);
        }).fail(function(){
            console.log("index.js 파일 진입 -update() - ajax fail() 진입");

        });
    },//update

    remove: function(){
        console.log("index.js 파일 진입 -delete() 진입");

        var id=$("#id").val();

        $.ajax({
            type:'delete',
            url:'/api/v1/posts/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8'
        }).done(function(num){
            console.log("index.js 파일 진입 -delete() - ajax done() 진입");
            alert(num+"번 게시글이 삭제되었습니다.");
            location.replace(`/`);
        }).fail(function(){
            console.log("index.js 파일 진입 -delete() - ajax fail() 진입");
        });
    },


};//main
main.init();