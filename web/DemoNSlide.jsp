<%-- 
    Document   : DemoNSlide
    Created on : May 21, 2013, 12:35:40 AM
    Author     : NewSuppamit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/jquery.nslide.css" type="text/css" media="all">
        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="js/jquery.nslide.js"></script>
        <script type="text/javascript">
            window.onload = function(){
                $('#nslide').nSlide(720,300);
            }
        </script>
    </head>
    <body>
         <div id="nslide">
            <a href="#"><img src="images/slideshow/slide1.png" /></a>
            <a href="#"><img src="images/slideshow/slide2.png" /></a>
            <a href="#"><img src="images/slideshow/slide3.png" /></a>
            <a href="#"><img src="images/slideshow/slide4.png" /></a>
            <a href="#"><img src="images/slideshow/slide5.png" /></a>
            <a href="#"><img src="images/slideshow/slide6.png" /></a>
            <a href="#"><img src="images/slideshow/slide7.png" /></a>
            <a href="#"><img src="images/slideshow/slide8.png" /></a>
            <a href="#"><img src="images/slideshow/slide9.png" /></a>
        </div>
    </body>
</html>