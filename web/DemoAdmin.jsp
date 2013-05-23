<%-- 
    Document   : DemoAdmin
    Created on : May 22, 2013, 9:01:33 PM
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
                $('#slideshow').click(function(){
                    PageAdmin("slideshow");
                });      
            }
            function PageAdmin(content) {
                var temp=document.createElement("form");
                temp.action="admin";
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
        </script>
    </head>
    <body>
        <input type="button" id="slideshow" value="slideshow"><br/>
    </body>
</html>
