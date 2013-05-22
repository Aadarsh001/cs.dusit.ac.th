<%-- 
    Document   : index
    Created on : May 8, 2013, 11:33:04 AM
    Author     : Note
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="icon" type="image/x-icon" href="">
        <meta http-equiv="X-UA-Compatible" content="IE=8" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>หลักสูตรวิทยาการคอมพิวเตอร์ มหาวิทยาลัยราชภัฏสวนดุสิต ศูนย์การเรียนรางน้ำ</title>
        <script type="text/javascript" src="js/index.js"></script>
    </head>
    <body>
        <div data-role="page">
            <jsp:include page="jsp/header.jsp"/>
            <div data-role="content">          
                <div class="ui-grid-a column">
                    <div class="ui-block-a column">
                        <div id="frmslide">
                            <div id="slideshow">
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
                        </div>
                        <div id="frmnews" class="frmcontent">
                            <div class="headnews"></div>
                            <div class="ui-grid-b titlenews" ></div>
                        </div>
                        <div id="frmevent" class="frmcontent">
                            <div class="headevent"></div>
                            <div class="ui-grid-a event"></div>
                            <div class="ui-grid-a subevent"></div>
                        </div>
                        <div id="frmknowledge" class="frmcontent">
                            <div class="headknowledge"></div>
                            <div class="ui-grid-a knowledge"></div>
                        </div>
                    </div>
                    <div class="ui-block-b column">
                        <jsp:include page="jsp/columnRight.jsp" />
                    </div>
                </div>
            </div>
            <div data-role="footer" data-theme="d"><jsp:include page="jsp/footer.jsp" /></div>
        </div>
        <br/>
    </body>
</html>
