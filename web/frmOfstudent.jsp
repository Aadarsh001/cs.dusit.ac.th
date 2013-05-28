<%-- 
    Document   : frmOfstudent
    Created on : May 23, 2013, 2:13:52 PM
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
        <script type="text/javascript" src="js/student.js"></script>
    </head>
    <body>
        <div data-role="page">
            <jsp:include page="jsp/header.jsp"/>
            <div data-role="content">          
                <div class="ui-grid-a column">
                    <div class="ui-block-a column">
                        <div class="headcontent"></div>
                        <div class="showcontent">
                            <div class="ui-grid-b">
                                <div class="ui-block-a"></div>
                                <div class="ui-block-b">
                                    <div data-role="fieldcontain">
                                        <label for="select-choice-month">รุ่น : </label>
                                            <select name="select-choice-month" id="year" data-mini="true">
                                            </select>
                                    </div>
                                </div>
                                <div class="ui-block-c"></div>
                            </div>
                        </div>
                    </div>
                    <div class="ui-block-b column">
                        <jsp:include page="jsp/columnRight.jsp" />
                    </div>
                </div>
            </div>
            <jsp:include page="jsp/footer.jsp" />
        </div>
    </body>
</html>