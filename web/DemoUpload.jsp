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
        <script type="text/javascript" src="js/jquery.npreview.js"></script>
        <script type="text/javascript">
            window.onload = function(){
                $('#image').nUpload();
                $('#test').click(function(){
                    $('#sss').empty();
                    $('#sss').append('<embed width="600px" height="800px" name="plugin" src="'+$('#image').attr('data')+'" type="application/pdf"></div>');
                });
            }
        </script>
    </head>
    <body>
        <!--<input type="file" accept="image/jpeg,image/png" id="image"><br/>-->
        <input type="file" accept="application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-powerpoint,application/vnd.openxmlformats-officedocument.presentationml.presentation" id="image"><br/>
        <input type="button" id="test" value="test"><br/>
        <div id="sss"></div>
    </body>
</html>