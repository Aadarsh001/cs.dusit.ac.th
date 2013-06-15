<%-- 
    Document   : frmOfacademic
    Created on : May 23, 2013, 2:20:59 PM
    Author     : Note
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/icon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=9" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>หลักสูตรวิทยาการคอมพิวเตอร์ มหาวิทยาลัยราชภัฏสวนดุสิต ศูนย์การเรียนรางน้ำ</title>
        <script type="text/javascript" src="js/schdule.js"></script>
    </head>
    <body>
        <div data-role="page">
            <jsp:include page="jsp/header.jsp"/>
            <div data-role="content">      
                <div class="headcontent"></div>
                <div class="showcontent">
                    <div id="frm" class="frmcontent">
                        <div class="headtable"></div>
                        <div id="contenttable" class="content"></div>
                    </div>
                    <div id="frm" class="frmcontent">
                        <div class="headcalendar_teacher"></div>
                        <div id="contentteacher" class="content"></div>
                    </div>
                    <div id="frm" class="frmcontent">
                        <div class="headcalendar_student"></div>
                        <div id="contentstudent" class="content"></div>
                    </div>
                </div>
                <!--                <div class="ui-grid-a column">
                                    <div class="ui-block-a column">
                                        <div class="headcontent"></div>
                                        <div class="showcontent">
                                            <div id="frm" class="frmcontent">
                                                <div class="headtable"></div>
                                                <div id="contenttable" class="content"></div>
                                            </div>
                                            <div id="frm" class="frmcontent">
                                                <div class="headcalendar"></div>
                                                <div id="contentcalendar" class="content"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="ui-block-b column">
                <%--<jsp:include page="jsp/columnRight.jsp" />--%>
            </div>
        </div>-->
            </div>
            <jsp:include page="jsp/footer.jsp" />
        </div>
        <br/>
    </body>
</html>