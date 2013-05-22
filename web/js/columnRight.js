function link(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'link',
            'option' : 'show'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert('Error');
        },
        success : function (data){
            for(var i=0;i<data.data.length;i++){
                $('#link').append('<li class="link"><a href="'+data.data[i].link+'" target="blank">-  '+data.data[i].title+'</a></li>');
            }
        }
    });
}

function clock(){
    var Time = new Date();
    alert(Time);
}