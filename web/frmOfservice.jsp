<%-- 
    Document   : frmOfservice
    Created on : Jun 14, 2013, 12:29:34 PM
    Author     : NewSuppamit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/icon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=9" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>หลักสูตรวิทยาการคอมพิวเตอร์ มหาวิทยาลัยราชภัฏสวนดุสิต ศูนย์การเรียนรางน้ำ</title>
        <script type="text/javascript" src="js/service.js"></script>
    </head>
    <body>
        <div data-role="page">
            <jsp:include page="jsp/header.jsp"/>
            <div data-role="content">
                <div class="headcontent" id="headtraining"></div>
                <div class="frm"><p>หลักสูตรวิทยาการคอมพิวเตอร์ คณะวิทยาศาสตร์และเทคโนโลยี มหาวิทยาลัยราชภัฏสวนดุสิต 
                        ได้จัดโครงการหลักสูตรอบรมทางวิชาการโดยมีความร่วมมือกับบริษัทชั้นนำทาง ด้านเทคโนโลยีสารสนเทศ 
                        ให้คำแนะนำและถ่ายทอดเทคโนโลยีเฉพาะด้านอันทันสมัย แก่นักศึกษา 
                        ศิษย์เก่าของคณะเทคโนโลยีสารสนเทศและผู้สนใจทั่วไป ปัจจุบันมีหลักสูตรที่เปิดอบรมดังนี้</p>
                    <ul class="showcontent" id="contenttraining"></ul>
                </div>
                <div class="headcontent" id="headcooperation"></div>
                <div class="frm"><p>ปัจจุบันหลักสูตรวิทยาการคอมพิวเตอร์ คณะวิทยาศาสตร์และเทคโนโลยี มหาวิทยาลัยราชภัฏสวนดุสิต 
                        ได้มีความร่วมมือกับหน่วยงานภายนอกทั้งภาครัฐและเอกชน ดังต่อไปนี้</p>
                    <ul class="showcontent" id="contentcooperation" style="list-style: none;margin-left: -20px;"></ul>
                </div>

            </div>
            <jsp:include page="jsp/footer.jsp" />
        </div>
        <br/>
    </body>
</html>
