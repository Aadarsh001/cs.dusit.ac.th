function IncludeJavaScript(jsFile) {
    document.write('<script type="text/javascript" src="' + jsFile
            + '"></script>');
}
function IncludeCSS(cssFile) {
    document.write('<link rel="stylesheet" href="' + cssFile
            + '" type="text/css" media="all">');
}
IncludeJavaScript('js/jquery-1.8.3.min.js');
IncludeJavaScript('js/jquery.mobile-1.3.0.js');
IncludeJavaScript('js/jquery.html5-placeholder.js');
IncludeJavaScript('js/jquery-ui-1.8.10.offset.datepicker.min.js');
IncludeJavaScript('js/jquery.nplugins-0.0.1.js');
IncludeJavaScript('js/jquery.wysiwyg.js');
IncludeJavaScript('js/columnRight.js');
IncludeJavaScript('js/flexigrid.js');

IncludeCSS('css/jquery.mobile-1.3.0.css');
IncludeCSS('css/jquery.mobile.pc-1.3.0.css');
IncludeCSS('css/cs.dusit.css');
IncludeCSS('css/jquery-ui-1.8.10.custom.css');
IncludeCSS('css/jquery.nplugins-0.0.1.css');
IncludeCSS('css/jquery.wysiwyg.css');
IncludeCSS('css/style.css');
IncludeCSS('css/flexigrid.css');
IncludeCSS('css/admin.css');

var page = "schedule";

window.onload = function onload() {
    $('.headcontent').attr('style', 'background-image: url(images/head' + page + '.png);');
    tab_btn();
    $('.file').nUpload();
    schedule.start();
};

var schedule = {
    start: function() {
        schedule.all();
        $('#submit_add').click(function() {
            if (($('#title').val() !== "")
                    && ($('#file').val() !== "")) {
                if (confirm('กด “ตกลง” เพื่อยืนยันการเพิ่มข้อมูล!')) {
                    $.mobile.loading('show');
                    schedule.add();
                }
            } else {
                alert('กรุณาระบุข้อมูลทั้งหมด');
            }
        });
        $('#submit_edit').click(function() {
            if ($('#_title').val() !== "") {
                if (confirm('กด “ตกลง” เพื่อยืนยันการแก้ไขข้อมูล!')) {
                    $.mobile.loading('show');
                    schedule.edit();
                }
            } else {
                alert('กรุณาระบุข้อมูลทั้งหมด');
            }
        });
        $('.reset').click(function() {
            PageAdmin(page);
        });
    },
    all: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': page,
                'option': 'all'
            },
            dataType: 'json',
            type: 'get',
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert('Error');
            },
            success: function(data) {
                for (var i = 0; i < data.data.length; i++) {
                    var status;
                    var category;
                    if (data.data[i].status === "1") {
                        status = "แสดง";
                    } else {
                        status = "ซ่อน";
                    }
                    if (data.data[i].category === "1") {
                        category = "ตารางเรียน";
                    } else if (data.data[i].category === "2") {
                        category = "ปฏิทินการศึกษา(นักศึกษา)";
                    } else {
                        category = "ปฏิทินการศึกษา(อาจารย์)";
                    }
                    $('#showAll').children("tbody").append("<tr id=" + data.data[i].id_sch + "><td>"
                            + data.data[i].title + "</td><td>"
                            + category + "</td><td>"
                            + status + "</td></tr>");
                }
                $('#showAll tr').click(function() {
                    schedule.some($(this).attr("id"));
                });
                $('#showAll tr').dblclick(function() {
                    if (confirm('กด “ตกลง” เพื่อยืนยันการลบข้อมูล!')) {
                        schedule.remove($(this).attr("id"));
                    }
                });
                $('#showAll').flexigrid({
                    width: "720",
                    height: "200",
                    singleSelect: true,
                    resizable: false
                });
            }
        });
    },
    some: function(id) {
        $.ajax({
            url: 'content',
            data: {
                'content': page,
                'option': 'some',
                'id_sch': id
            },
            dataType: 'json',
            type: 'get',
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error : 0x01");
            },
            success: function(data) {
                $('#_id_sch').val(data.id_sch);
                $('#_title').val(data.title);
                $('#_category-0').removeAttr("checked").checkboxradio("refresh");
                $('#_category-1').removeAttr("checked").checkboxradio("refresh");
                $('#_category-2').removeAttr("checked").checkboxradio("refresh");
                if (data.category === "1") {
                    $('#_category-1').attr("checked", true).checkboxradio("refresh");
                } else if (data.category === "2") {
                    $('#_category-2').attr("checked", true).checkboxradio("refresh");
                } else {
                    $('#_category-0').attr("checked", true).checkboxradio("refresh");
                }
                $('#_status-0').removeAttr("checked").checkboxradio("refresh");
                $('#_status-1').removeAttr("checked").checkboxradio("refresh");
                if (data.status === "1") {
                    $('#_status-1').attr("checked", true).checkboxradio("refresh");
                } else {
                    $('#_status-0').attr("checked", true).checkboxradio("refresh");
                }
            }
        });
    },
    add: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': page,
                'option': 'add',
                'title': $('#title').val(),
                'category': $('input[name="category"]:checked').val(),
                'file': $('#file').attr('data'),
                'filename': $('#file').val(),
                'status': $('input[name="status"]:checked').val()
            },
            dataType: 'json',
            type: 'post',
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error : 0111");
                $.mobile.loading('hide');
            },
            success: function(data) {
                if (data.result !== "fail") {
                    PageAdmin(page);
                } else {
                    alert("Error : 0101");
                    $.mobile.loading('hide');
                }
            },
            progress: function(e) {
                if (e.lengthComputable) {
                    var pct = (e.loaded / e.total) * 100;
                    $.mobile.showPageLoadingMsg("a", "กำลังโหลด " + parseInt(pct) + "%", false);
                }
            }
        });
    },
    edit: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': page,
                'option': 'edit',
                'id_sch': $('#_id_sch').val(),
                'title': $('#_title').val(),
                'category': $('input[name="_category"]:checked').val(),
                'file': $('#_file').attr('data'),
                'filename': $('#_file').val(),
                'status': $('input[name="_status"]:checked').val()
            },
            dataType: 'json',
            type: 'post',
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error : 0112");
                $.mobile.loading('hide');
            },
            success: function(data) {
                if (data.result !== "fail") {
                    PageAdmin(page, "?tab=edit");
                } else {
                    alert("Error : 0102");
                    $.mobile.loading('hide');
                }
            },
            progress: function(e) {
                if (e.lengthComputable) {
                    var pct = (e.loaded / e.total) * 100;
                    $.mobile.showPageLoadingMsg("a", "กำลังโหลด " + parseInt(pct) + "%", false);
                }
            }
        });
    },
    remove: function(id) {
        $.ajax({
            url: 'content',
            data: {
                'content': page,
                'option': 'remove',
                'id_sch': id
            },
            dataType: 'json',
            type: 'post',
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error : 0113");
            },
            success: function(data) {
                if (data.result !== "fail") {
                    PageAdmin(page, "?tab=edit");
                } else {
                    alert("Error : 0103");
                }
            }
        });
    }
};