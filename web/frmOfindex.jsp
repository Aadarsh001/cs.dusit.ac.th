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
                            <div class="ui-grid-a event">
                                <div class="ui-block-a"><a href="#"><img src=""/></a></div>
                                <div class="ui-block-b"><a href="#"><p class="firstevent">มสด. ร่วมกับ สพฐ. จัดโครงการอบรมเชิงปฏิบัติการ หลักสูตร การพัฒนาสมรรถนะเทคโนโลยีสารสนเทศขั้นสูง สำหรับข้าราชการ สพฐ. สู่อาเซียน Movie Maker รุ่นที่ 1</p><p class="detailfirstevent">โดยจัดโครงการส่งเสริมศักยภาพอาจารย์ในการเขียนผลงานทางวิชาการ (โครงการ 12) กิจกรรมที่ 4 ระหว่างวันที่ 14-16 พฤษภาคม 2556 ณ โรงแรม Kantary Bay Hotel จ.ระยอง โดยได้รับเกียรติจาก รศ.ดร.สุขุม เฉลยทรัพย์ ประธานที่ปรึกษาอธิการบดี มหาวิทยาลัยราชภัฏสวนดุสิต ร่วมบรรยาย โดยมีผู้บริหาร และอาจารย์จากหลักสูตรต่าง ๆ เข้าร่วมโครงการกว่า 120 คน</p></a></div>
                            </div>
                            <div class="ui-grid-a subevent">
                                <div class="ui-block-a"></div>
                                <div class="ui-block-b"><p class="titleevent">มสด. ร่วมกับ สพฐ. จัดโครงการอบรมเชิงปฏิบัติการ หลักสูตร การพัฒนาสมรรถนะเทคโนโลยีสารสนเทศขั้นสูง สำหรับข้าราชการ สพฐ. สู่อาเซียน Movie Maker รุ่นที่ </p><p class="detailevent">โดยจัดโครงการส่งเสริมศักยภาพอาจารย์ในการเขียนผลงานทางวิชาการ (โครงการ 12) กิจกรรมที่ 4 ระหว่างวันที่ 14-16 พฤษภาคม 2556 ณ โรงแรม Kantary Bay Hotel จ.ระยอง โดยได้รับเกียรติจาก รศ.ดร.สุขุม เฉลยทรัพย์ ประธานที่ปรึกษาอธิการบดี มหาวิทยาลัยราชภัฏสวนดุสิต ร่วมบรรยาย โดยมีผู้บริหาร และอาจารย์จากหลักสูตรต่าง ๆ เข้าร่วมโครงการกว่า 120 คน</p></div>
                            </div>
                        </div>
                        <div id="frmknowledge" class="frmcontent">
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
