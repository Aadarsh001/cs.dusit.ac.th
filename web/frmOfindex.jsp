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
                        <div id="frmnews">
                            <div class="headnews"></div>
                            <ul id="news">
                                <li class=""><a href="http://google.com" rel="external">หัวข้อข่าว</a></li>
                            </ul>
                        </div>
                        <div id="frmevent">
                            <div class="headevent"></div>
                        </div>
                        <div id="frmknowledge">
                            <div class="headknowledge"></div>
                        </div>
                        
                    </div>
                    <div class="ui-block-b">
                        <jsp:include page="jsp/columnRight.jsp" />
                    </div>
                </div>
            </div>
            <div data-role="footer" data-theme="d"><jsp:include page="jsp/footer.jsp" /></div>
        </div>
    </body>
</html>
