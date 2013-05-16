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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>หลักสูตรวิทยาการคอมพิวเตอร์ มหาวิทยาลัยราชภัฏสวนดุสิต ศูนย์การเรียนรางน้ำ</title>
        <script type="text/javascript" src="js/index.js"></script>
    </head>
    <body>
        <div data-role="page">
            <jsp:include page="jsp/header.jsp"/>
            <div data-role="content">          
                <div class="ui-grid-a column">
                    <div class="ui-block-a">
                        <div id="frmslide">
                            <div id="slideshow"></div>
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
                    <div class="ui-block-b">
                        <jsp:include page="jsp/columnRight.jsp" />
                    </div>
                </div>
            </div>
            <div data-role="footer" data-theme="d"><jsp:include page="jsp/footer.jsp" /></div>
        </div>
        <br/>
    </body>
</html>
