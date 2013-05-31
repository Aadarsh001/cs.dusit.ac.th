<%-- 
    Document   : calendar
    Created on : May 25, 2013, 7:08:04 PM
    Author     : NewSuppamit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=9">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="js/admin_main.js"></script>
    </head>
    <body>
        <div data-role="page">
            <jsp:include page="../jsp/header.jsp"/>
            <div data-role="content">
                <div class="ui-grid-a column">
                    <div class="ui-block-a column">
                        <div class="headcontent"></div>
                        <a href="#" class="admin_btn" id="slideshow">- เพิ่ม/แก้ไข สไลด์โชว์</a><br/>
                        <a href="#" class="admin_btn" id="news">- เพิ่ม/แก้ไข ข่าวประชาสัมพันธ์</a><br/>
                        <a href="#" class="admin_btn" id="event">- เพิ่ม/แก้ไข กิจกรรมที่น่าสนใจ</a><br/>
                        <a href="#" class="admin_btn" id="knowledge">- เพิ่ม/แก้ไข สาระน่ารู้</a><br/>
                        <a href="#" class="admin_btn" id="link">- เพิ่ม/แก้ไข เว็บไซต์ที่เกี่ยวข้อง</a><br/>
                        <a href="#" class="admin_btn" id="calendar">- เพิ่ม/แก้ไข ปฏิทินกิจกรรม</a><br/>
                        <a href="#" class="admin_btn" id="personnel">- เพิ่ม/แก้ไข ข้อมูลบุคลากร</a><br/>
                        <a href="#" class="admin_btn" id="student">- เพิ่ม/แก้ไข ข้อมูลนักศึกษา</a><br/>
                        <a href="#" class="admin_btn" id="course">- เพิ่ม/แก้ไข โครงสร้างหลักสูตร</a><br/>
                        <a href="#" class="admin_btn" id="academic">- เพิ่ม/แก้ไข ผลงานวิชาการ</a><br/>
                        <a href="#" class="admin_btn" id="research">- เพิ่ม/แก้ไข ผลงานวิจัย</a><br/>
                        <a href="#" class="admin_btn" id="project">- เพิ่ม/แก้ไข ผลงานนักศึกษา</a><br/>
                        <a href="#" class="admin_btn" id="qassurance">- เพิ่ม/แก้ไข ประกันคุณภาพ</a><br/>
                        <a href="#" class="admin_btn" id="download">- เพิ่ม/แก้ไข เอกสารดาวน์โหลด</a><br/>
                    </div>
                    <div class="ui-block-b column">
                        <jsp:include page="../jsp/columnRight.jsp" />
                    </div>
                </div>
            </div>
            <div data-role="footer" data-theme="a"><jsp:include page="../jsp/footer.jsp" /></div>
        </div><br/>
    </body>
</html>