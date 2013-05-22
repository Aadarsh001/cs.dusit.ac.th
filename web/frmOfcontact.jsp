<%-- 
    Document   : frmOfcontact
    Created on : May 15, 2013, 12:55:58 AM
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
        <link rel="stylesheet" href="css/contact.css" type="text/css" media="all"/>
    </head>
    <body>
        <div data-role="page">
            <jsp:include page="jsp/header.jsp"/>
            <div data-role="content">          
                <div class="ui-grid-a contact">
                    <div class="ui-block-a contact">
                        <div class="headcontact"></div>
                        <div class="detailcontact">
                            <span class="cont" >หลักสูตรวิทยาการคอมพิวเตอร์</span><br/>
                            <span class="cont">ที่ตั้ง : 107 ถนนรางน้ำ แขวงพญาไท เขตราชเทวี กรุงเทพมหานคร 10400</span><br/>
                            <span class="cont">โทรศัพท์ : โทร. 02-6425596 ต่อ 6295</span><br/>
                            <span class="cont">โทรสาร : 02-6425596</span><br/>
                            <span class="cont">เว็บไซต์ : <a href="" >www.cs.dusit.ac.th</a></span><br/>
                            <span class="cont">Facebook : <a href="http://www.facebook.com" >www.facebook.com/csdusit</a></span><br/>
                            <span class="cont">แผนที่ </span>
                            <div class="map"></div>
                        </div>
                    </div>
                    <div class="ui-block-b contact">
                        <jsp:include page="jsp/columnRight.jsp" />
                    </div>
                </div>
            </div>
            <jsp:include page="jsp/footer.jsp" />
        </div>
    </body>
</html>
