<%-- 
    Document   : news
    Created on : May 23, 2013, 2:05:51 PM
    Author     : NewSuppamit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="shortcut icon" href="images/icon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=9" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ระบบจัดการเนื้อหาเว็บ</title>
        <script type="text/javascript" src="js/admin_personnel.js"></script>
    </head>
    <body>
        <div data-role="page">
            <jsp:include page="../jsp/header.jsp"/>
            <div data-role="content">
                <div class="ui-grid-a column">
                    <div class="ui-block-a column">
                        <div class="headcontent"></div>
                        <div data-role="navbar" data-iconpos="left" >
                            <ul>
                                <li><a href="#" id="tab_add" data-icon="plus"  class="ui-btn-active">เพิ่มข้อมูลบุคลากร</a></li>
                                <li><a href="#" id="tab_edit" data-icon="gear">แก้ไข/ลบข้อมูลบุคลากร</a></li>
                            </ul>
                        </div>
                        <div id="page_add">
                            <fieldset class="ui-grid-a default">
                                <div class="ui-block-a">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">ชื่อ : </label>
                                        <input type="text" id="name" data-mini="true" maxlength="1000" placeholder="ชื่อ">
                                    </div>
                                </div>
                                <div class="ui-block-b">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">ตำแหน่ง : </label>
                                        <input type="text" id="position" data-mini="true" maxlength="1000" placeholder="ตำแหน่ง">
                                    </div>

                                </div>
                                <div class="ui-block-a">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-select">ลำดับ : </label>
                                    </div>
                                    <input type="range" readonly id="sequence" value="1" min="1" max="20" />
                                </div>
                                <div class="ui-block-b">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">สถานะ : </label>
                                        <fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
                                            <input type="radio" name="status" id="1"  value="1" checked/>
                                            <label for="1">แสดง</label>
                                            <input type="radio" name="status" id="0" value="0" />
                                            <label for="0">ซ่อน</label>
                                        </fieldset>
                                    </div>
                                </div>
                                <div class="ui-block-a">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">ข้อมูลอื่นๆ : </label>
                                    </div>
                                </div>
                            </fieldset>
                            <textarea name="textarea" id="detail" maxlength="10000"></textarea>
                            <fieldset class="ui-grid-a default" >
                                <div class="ui-block-a">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">รูปภาพ : </label>
                                        <input type="file" id="image_file" accept="image/jpeg,image/png" data-mini="true">
                                    </div>
                                </div>
                                <div class="ui-block-b">
                                    <div class="image_per"><img src="images/320x240.png" id="image" class="image"/></div>
                                </div>
                            </fieldset>

                            <fieldset class="ui-grid-d">
                                <div class="ui-block-a"></div>
                                <div class="ui-block-b">
                                    <input type="button" id="submit_add" value="เพิ่ม" data-mini="true" data-icon="plus">
                                </div>
                                <div class="ui-block-c"></div>
                                <div class="ui-block-d">
                                    <input type="button" class="reset" value="ยกเลิก" data-mini="true" data-icon="refresh">
                                </div>
                                <div class="ui-block-e"></div>
                            </fieldset>
                        </div>
                        <div id="page_edit">
                            <table id="showAll">
                                <thead>
                                    <tr>
                                        <th width="320">ชื่อ</th>
                                        <th width="160">ตำแหน่ง</th>
                                        <th width="80">ลำดับ</th>
                                        <th width="80">สถานะ</th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                            <div class="comment">*คลิกที่ข้อมูลในตารางเพื่อแก้ไข<br />**ดับเบิลคลิกที่ข้อมูลในตารางเพื่อลบ</div>

                            <fieldset class="ui-grid-a default">
                                <div class="ui-block-a">
                                    <input type="text" id="_id_per" class="hidden" data-role="none">
                                    <input type="text" id="_filename" class="hidden" data-role="none">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">ชื่อ : </label> <input
                                            type="text" id="_name" data-mini="true" maxlength="1000" placeholder="ชื่อ">
                                    </div>
                                </div>
                                <div class="ui-block-b">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">ตำแหน่ง : </label> <input
                                            type="text" id="_position" data-mini="true" maxlength="1000" placeholder="ตำแหน่ง">
                                    </div>
                                </div>
                                <div class="ui-block-a">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-select">ลำดับ : </label>
                                    </div>
                                    <input type="range" readonly id="_sequence" value="1" min="1" max="20" />
                                </div>
                                <div class="ui-block-b">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">สถานะ : </label> 
                                        <fieldset data-role="controlgroup" data-type="horizontal" data-mini="true">
                                            <input type="radio" name="_status" id="_status-1"  value="1"/>
                                            <label for="_status-1">แสดง</label>
                                            <input type="radio" name="_status" id="_status-0" value="0" />
                                            <label for="_status-0">ซ่อน</label>
                                        </fieldset>
                                    </div>
                                </div>
                                <div class="ui-block-a">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">ข้อมูลอื่นๆ : </label>
                                    </div>
                                </div>
                            </fieldset>
                            <textarea name="textarea" id="_detail"></textarea>
                            <fieldset class="ui-grid-a default" >
                                <div class="ui-block-a">
                                    <div data-role="fieldcontain" class="ui-field-contain ui-body ui-br">
                                        <label for="foo" class="ui-input-text">รูปภาพ : </label>
                                        <input type="file" id="_image_file" accept="image/jpeg,image/png" data-mini="true">
                                    </div>
                                </div>
                                <div class="ui-block-b">
                                    <div class="image_per"><img src="images/320x240.png" id="_image" class="image"/></div>
                                </div>
                            </fieldset>

                            <fieldset class="ui-grid-d">
                                <div class="ui-block-a"></div>
                                <div class="ui-block-b">
                                    <input type="button" id="submit_edit" value="แก้ไข" data-mini="true" data-icon="check">
                                </div>
                                <div class="ui-block-c"></div>
                                <div class="ui-block-d">
                                    <input type="button" class="reset" value="ยกเลิก" data-mini="true" data-icon="refresh">
                                </div>
                                <div class="ui-block-e"></div>
                            </fieldset>
                        </div>
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
