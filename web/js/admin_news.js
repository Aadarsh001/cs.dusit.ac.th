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

var page = "news";

window.onload = function onload() {
    $('.headcontent').attr('style', 'background-image: url(images/head' + page + '.png);');
    $('textarea').wysiwyg({
        controls: {
            insertImage: {visible: false},
            h4: {visible: true && !($.browser.mozilla), className: 'h4', command: 'formatBlock', arguments: ['<H4>'], tags: ['h4'], tooltip: "Header 4"},
            h5: {visible: true && !($.browser.mozilla), className: 'h5', command: 'formatBlock', arguments: ['<H5>'], tags: ['h5'], tooltip: "Header 5"},
            h6: {visible: true && !($.browser.mozilla), className: 'h6', command: 'formatBlock', arguments: ['<H6>'], tags: ['h6'], tooltip: "Header 6"},
            insertYoutube: {
                exec: function() {
                    $('textarea').wysiwyg('insertHtml', prompt('Embed', ''));
                    return true;
                },
                visible: true
            }
        }
    });
    tab_btn();
    $('.datepicker').nDatepicker();
    $('.file').nUpload();
    news.start();
};

var news = {
    start: function() {
        news.all();
        $('#submit_add').click(function() {
            if (($('#title').val() !== "")
                    && ($('#startdate').val() !== "")) {
                if (confirm('กด “ตกลง” เพื่อยืนยันการเพิ่มข้อมูล!')) {
                    news.add();
                }
            } else {
                alert('กรุณาระบุข้อมูลทั้งหมด');
            }
        });
        $('#submit_edit').click(function() {
            if (($('#_title').val() !== "")
                    && ($('#_startdate').val() !== "")) {
                if (confirm('กด “ตกลง” เพื่อยืนยันการแก้ไขข้อมูล!')) {
                    news.edit();
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
            }, success: function(data) {
                for (var i = 0; i < data.data.length; i++) {
                    var status;
                    if (data.data[i].status === "1") {
                        status = "แสดง";
                    } else if (data.data[i].status === "2") {
                        status = "ปักหมุด";
                    } else {
                        status = "ซ่อน";
                    }
                    $('#showAll').children("tbody").append("<tr id=" + data.data[i].id_new + "><td>"
                            + data.data[i].title + "</td><td>"
                            + data.data[i].startdate.substr(6, 2) + "/" + data.data[i].startdate.substr(4, 2) + "/" + data.data[i].startdate.substr(0, 4) + "</td><td>"
                            + status + "</td></tr>");
                }
                $('#showAll tr').click(function() {
                    news.some($(this).attr("id"));
                });
                $('#showAll tr').dblclick(function() {
                    if (confirm('กด “ตกลง” เพื่อยืนยันการลบข้อมูล!')) {
                        news.remove($(this).attr("id"));
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
                'id_new': id
            },
            dataType: 'json',
            type: 'get',
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error : 0x01");
            },
            success: function(data) {
                $('#_id_new').val(data.id_new);
                $('#_title').val(data.title);
                $('#_detail').wysiwyg('setContent', data.detail);
                $('#_startdate').val(data.startdate.substr(6, 2) + "/" + data.startdate.substr(4, 2) + "/" + data.startdate.substr(0, 4));
                $('#_status-0').removeAttr("checked").checkboxradio("refresh");
                $('#_status-1').removeAttr("checked").checkboxradio("refresh");
                $('#_status-2').removeAttr("checked").checkboxradio("refresh");
                if (data.status === "1") {
                    $('#_status-1').attr("checked", true).checkboxradio("refresh");
                } else if (data.status === "2") {
                    $('#_status-2').attr("checked", true).checkboxradio("refresh");
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
                'detail': $('#detail').val(),
                'file': $('#file').attr('data'),
                'filename': $('#file').val(),
                'startdate': $('#startdate').val().substr(6, 4) + $('#startdate').val().substr(3, 2) + $('#startdate').val().substr(0, 2),
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
                'id_new': $('#_id_new').val(),
                'title': $('#_title').val(),
                'detail': $('#_detail').val(),
                'file': $('#_file').attr('data'),
                'filename': $('#_file').val(),
                'startdate': $('#_startdate').val().substr(6, 4) + $('#_startdate').val().substr(3, 2) + $('#_startdate').val().substr(0, 2),
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
                'id_new': id
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