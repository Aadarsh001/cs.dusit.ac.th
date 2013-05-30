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
IncludeJavaScript('js/columnRight.js');
IncludeJavaScript('js/flexigrid.js');

IncludeCSS('css/jquery.mobile-1.3.0.css');
IncludeCSS('css/jquery.mobile.pc-1.3.0.css');
IncludeCSS('css/cs.dusit.css');
IncludeCSS('css/jquery-ui-1.8.10.custom.css');
IncludeCSS('css/jquery.nplugins-0.0.1.css');
IncludeCSS('css/style.css');
IncludeCSS('css/student.css');
IncludeCSS('css/flexigrid.css');

window.onload = function() {
    $('.headcontent').attr('style', 'background-image: url(images/headstudent.png);');
    student.start();
};

var student = {
    start: function() {
        var date = new Date();
        var year = date.getFullYear() + 543;
        student.year_select(year);
        $('#year').change(function() {
            student.year_select(parseInt($('#year option:selected').val()));
        });
    },
    show: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': 'student',
                'option': 'show',
                'year': $('#year option:selected').val().substr(2, 2)
            },
            dataType: 'json',
            type: 'get',
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                //            alert('Error');
            },
            success: function(data) {
                $('#showAll').empty();
                $('#showAll').append('<table id="showData"><thead><tr>\n\
                        <th align="center" width="50">ลำดับที่</th>\n\
                        <th width="100">รหัสนักศึกษา</th>\n\
                        <th width="240">ชื่อ - สกุล</th>\n\
                        <th width="200">อีเมล</th></tr></thead><tbody></tbody></table>');
                for (var i = 0; i < data.data.length; i++) {
                    $('#showData').children('tbody').append("<tr><td>"
                            + (i+1) + "</td><td>"
                            + data.data[i].id_stu + "</td><td>"
                            + data.data[i].name + "</td><td>"
                            + data.data[i].detail + "</td></tr>");
                }
                $('#showData').flexigrid({
                    width: "640",
                    height: "auto",
                    singleSelect: true,
                    resizable: false
                });
            }
        });
    }, year_select: function(year) {
        $('#year').empty();
        for (i = (year - 7); i <= (year + 7); i++) {
            if (i === year) {
                $('#year').append('<option value="' + i + '" selected>' + i + '</option>');
            } else {
                $('#year').append('<option value="' + i + '">' + i + '</option>');
            }
        }
        $('#year').selectmenu("refresh");
        student.show();
    }
};