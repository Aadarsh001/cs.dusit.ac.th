<%-- 
    Document   : frmOfpersonnal
    Created on : May 23, 2013, 3:12:26 AM
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
        <script type="text/javascript" src="js/personnal.js"></script>
    </head>
    <body>
        <div data-role="page">
            <jsp:include page="jsp/header.jsp"/>
            <div data-role="content">          
                <div class="ui-grid-a column">
                    <div class="ui-block-a column">
                        <!--<div class="headcontent"></div>-->
                        <div data-role="collapsible-set" id="results">
                            <div data-role="collapsible" data-mini="true" data-collapsed="false" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d">
                                <h2><img src="images/manager.png" /></h4>
                                    <div class="showcontent" id="manager"></div>
                            </div>
                            <div data-role="collapsible" data-mini="true" data-collapsed="true" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d">
                                <h2><img src="images/teacher.png" /></h2>
                                <div class="showcontent" id="teacher"></div>
                            </div>
                            <div data-role="collapsible" data-mini="true" data-collapsed="true" data-collapsed-icon="arrow-r" data-expanded-icon="arrow-d">
                                <h2><img src="images/officer.png" /></h2>
                                <div class="showcontent" id="officer"></div>
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
        <br />
    </body>
</html>