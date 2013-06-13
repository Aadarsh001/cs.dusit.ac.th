$(function(){
    $('#date').nClock();
    login();
    check_login();
    calendar();
    link();
});

function login(){
    $('#login').click(function(){
        _login();
    });
    $('#email').keydown(function(e){
        if(e.keyCode==13){
            $('#pass').focus();
        }
    });
    $('#pass').keydown(function(e){
        if(e.keyCode==13){
            _login();
        }
    });
    
    function _login(){
        if(($('#email').val()!="")&&($('#pass').val()!="")){
            $.ajax({
                url : 'content',
                data : {
                    'content' : 'user',
                    'option' : 'login',
                    'email' : $('#email').val(),
                    'password' : $('#pass').val()
                },
                dataType : 'json',
                type : 'get',
                error : function(XMLHttpRequest, textStatus, errorThrown){
//                    alert('Error');
                },
                success : function (data){
                    if(data.pname!=undefined){
                        $.ajax({
                            url : 'content',
                            data : {
                                'content' : 'session',
                                'option' : 'set',
                                'email' : data.email,
                                'pname' : data.pname,
                                'fname' : data.fname,
                                'lname' : data.lname,
                                'status' : data.status
                            },
                            dataType : 'json',
                            type : 'post',
                            error : function(XMLHttpRequest, textStatus, errorThrown){
//                                alert('Error');
                            },
                            success : function (data){
                                check_login();
                            }
                        });
                    }else{
                        $('#pass').val("");
                        alert("Email หรือ รหัสผ่าน ไม่ถูกต้อง");
                    }
                }
            });
        }
    }
}

function check_login(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'session',
            'option' : 'get'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown){
//            alert('Error');
        },
        success : function (data){
            var content = '<div class="ui-block-a login"></div><div class="ui-block-b login"><div class="frmemail">ยินดีต้อนรับ<br/>\n\
คุณ '+data.fname+' '+data.lname+'<br/><br/>\n\
<div class="user_menu"><a  href="#">- แก้ไขข้อมูลส่วนตัว</a><br/>';
            if(data.status == "2"){
                content += '<a href="admin" rel="external">- ระบบจัดการเนื้อหาเว็บ</a><br/>';
            }
            content += '<a href="#" onclick="logout();">- ออกจากระบบ</a><br/></div></div></div><div class="ui-block-c login"></div>';
            
            $('.login.ui-grid-b').empty();
            $('.login.ui-grid-b').attr('style','text-align: center;');
            $('.login.ui-grid-b').append(content);
        }
    });
}

function logout(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'session',
            'option' : 'logout'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown){
//            alert('Error');
        },
        success : function (data){
            window.location.reload();
        }
    });
}

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
//            alert('Error');
        },
        success : function (data){
            var date = [];
            for(var i=0;i<data.data.length;i++){
                date.push(data.data[i].date);
            }
            $('.calendar').
            nCalendar(date,function(dateText,inst){
                window.open('showcontent?content=calendar&id='+dateText,'_blank');
            });
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
//            alert('Error');
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