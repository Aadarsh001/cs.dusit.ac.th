$(function(){
    $('#date').nClock();
    calendar();
    link();
});

function calendar(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'calendar',
            'option' : 'show'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert('Error');
        },
        success : function (data){
            var date = [];
            for(var i=0;i<data.data.length;i++){
                date.push(data.data[i].date);
            }
            setTimeout(function(){
                $('.calendar').
                nCalendar(date,function(dateText,inst){
                    window.open('showcontent?content=calendar&id='+dateText,'_blank');
                });
            }, 500);
        }
    });
}

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
                $('#frm_link').append('<li class="link"><a href="'+data.data[i].link+'" target="blank">-  '+data.data[i].title+'</a></li>');
            }
        }
    });
}

function PageAdmin(content,option) {
    if(option==undefined){
        option="";
    }
    var temp=document.createElement("form");
    temp.action="admin"+option;
    temp.method="POST";
    temp.style.display="none";
    var opt=document.createElement("textarea");
    opt.name="content";
    opt.value=content;
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=]*)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function tab_btn(){
    $('#page_edit').slideUp();
    $('#page_add').slideUp();
    $('#tab_add').click(function(){
        $('#page_edit').slideUp("",function(){
            $('#page_add').slideDown();
        });
    });
    $('#tab_edit').click(function(){
        $('#page_add').slideUp("",function(){
            $('#page_edit').slideDown();
        });
    });
    setTimeout(function(){
        var tab = getUrlVars()["tab"];
        if(tab=="edit"){
            $('#tab_edit').trigger('click');
        }else{
            $('#page_add').slideDown();
        }
    }, 500);
}