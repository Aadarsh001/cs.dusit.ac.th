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
        <script type="text/javascript" src="js/columnRight.js"></script>
        <script type="text/javascript">
            window.onload = function(){
                $('.admin_btn').click(function(){
                    PageAdmin($(this).attr('id'));
                });      
            }
        </script>
    </head>
    <body>
        <input type="button" class="admin_btn" id="slideshow" value="slideshow"><br/>
        <input type="button" class="admin_btn" id="news" value="news"><br/>
    </body>
</html>
