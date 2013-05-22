<%-- 
    Document   : test
    Created on : May 11, 2013, 9:10:33 PM
    Author     : NewSuppamit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript">
            window.onload = function(){
                $('#image').change(function(){
                    showPreview(this);
                });
                $('#slideshow').click(function(){
                    post("admin", {content:"slideshow"});
                });
                
                $('#test').click(function(){
                    if($('#image').val()){
                        $('#imagedata').append($('#myimage').attr('src'));
                    }else{
                        alert('กรุณาเลือกรูปภาพ');
                    }
                });
            }
            function showPreview(ele)
            {
                $('#myimage').attr('src', ele.value);
                if (ele.files && ele.files[0]) {
                    var reader = new FileReader();
                    reader.onload = function (e) {
                        $('#myimage').attr('src', e.target.result);
                    }
                    reader.readAsDataURL(ele.files[0]);
                }
                //                var timerId = setInterval(function () {
                //                    if($('#myimage').width()==720&&$('#myimage').height()==300){
                //                        clearInterval(timerId);
                //                    }else{
                //                        $('#image').val('');
                //                        $('#myimage').attr('src','images/header.png');
                //                        alert("ไฟล์ภาพต้องเป็นขนาด 720 x 300 เท่านั้น");
                //                        clearInterval(timerId);
                //                    }
                //                }, 500);
            }
            function post(URL, PARAMS) {
                var temp=document.createElement("form");
                temp.action=URL;
                temp.method="POST";
                temp.style.display="none";
                for(var x in PARAMS) {
                    var opt=document.createElement("textarea");
                    opt.name=x;
                    opt.value=PARAMS[x];
                    temp.appendChild(opt);
                }
                document.body.appendChild(temp);
                temp.submit();
                return temp;
            }
        </script>
    </head>
    <body>
        <input type="file" accept="image/jpeg,image/png" id="image"><br/>
        <div style="width: 720px;height: 300px;"><img src="images/header.png" id="myimage"/></div>
        <input type="button" id="test" value="test"><br/>
        <input type="button" id="slideshow" value="slideshow"><br/>
    </body>
</html>