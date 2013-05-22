<%-- 
    Document   : slideshow
    Created on : May 21, 2013, 10:30:15 PM
    Author     : NewSuppamit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=9">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="js/admin_slideshow.js"></script>
    </head>
    <body>
        <div data-role="page">
            <jsp:include page="../jsp/header.jsp"/>
            <div data-role="content">          
                <div class="ui-grid-a column">
                    <div class="ui-block-a">
                        <div data-role="collapsible" data-collapsed="false" data-mini="true" data-collapsed-icon="arrow-d" data-expanded-icon="arrow-r">
                            <h3>เพิ่ม Slide</h3>
                            <fieldset class="ui-grid-a default">
                                <div class="ui-block-a">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">title : </label> <input
                                            type="text" data-mini="true" maxlength="1000" placeholder="title">
                                    </div>
                                </div>
                                <div class="ui-block-b">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">title : </label> <input
                                            type="text" data-mini="true" maxlength="1000" placeholder="title">
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                        <div data-role="collapsible" data-collapsed="false" data-mini="true" data-collapsed-icon="arrow-d" data-expanded-icon="arrow-r">
                            <h3>แก้ไข/ลบ Slide</h3>
                            <fieldset class="ui-grid-b">
                                <div class="ui-block-a">

                                </div>
                                <div class="ui-block-b">

                                </div>
                                <div class="ui-block-c">

                                </div>
                            </fieldset>
                        </div>
                    </div>
                    <div class="ui-block-b">
                        <jsp:include page="../jsp/columnRight.jsp" />
                    </div>
                </div>
            </div>
            <div data-role="footer" data-theme="a"><jsp:include page="../jsp/footer.jsp" /></div>
        </div><br/>
    </body>
</html>
