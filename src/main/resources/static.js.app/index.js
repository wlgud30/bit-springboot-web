var main = {
    init : function(){
        var _this = this;
        $('#btn-save').on('click',function(){
            _this.save();
        });
    },
    save : function() {
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };
        $.ajax({
            type : 'POST',
            url : '/api/v1/posts',
            contentType : 'application/json; charset=UTF-8',
            data : JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다');
            window.location.href="/";
        }).fail(function(error){
            alert("에러 발생 : "+JSON.stringify(error));
        });
    }
};

main.init();