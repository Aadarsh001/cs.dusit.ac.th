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

IncludeCSS('css/jquery.mobile-1.3.0.css');
IncludeCSS('css/jquery.mobile.pc-1.3.0.css');
IncludeCSS('css/cs.dusit.css');
IncludeCSS('css/jquery-ui-1.8.10.custom.css');
IncludeCSS('css/jquery.nplugins-0.0.1.css');
IncludeCSS('css/style.css');
IncludeCSS('css/schedule.css');

window.onload = function() {
    $('.headcontent').attr('style', 'background-image: url(images/headschedule.png);');
    var content = getUrlVars()["id"];
    if (content === "calendar") {
        setTimeout(function() {
            $('.headcalendar_teacher')[0].scrollIntoView(true);
        }, 100);
    }
    table.start();
    teacher.start();
    student.start();
};

var table = {
    start: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': 'schedule',
                'option': 'show',
                'category': '1'
            },
            dataType: 'json',
            type: 'get',
            success: function(data) {
                for (var i = 0; i < data.data.length; i++) {
                    $('#contenttable').append('<a target="_blank" href="' + data.data[i].file + '">- ' + data.data[i].title + '</a><br/>');
                }
            }
        });
    }
};

var teacher = {
    start: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': 'schedule',
                'option': 'show',
                'category': '0'
            },
            dataType: 'json',
            type: 'get',
            success: function(data) {
                for (var i = 0; i < data.data.length; i++) {
                    $('#contentteacher').append('<a target="_blank" href="' + data.data[i].file + '">- ' + data.data[i].title + '</a><br/>');
                }
            }
        });
    }
};

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=]*)=([^&]*)/gi, function(m, key, value) {
        vars[key] = value;
    });
    return vars;
}

var student = {
    start: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': 'schedule',
                'option': 'show',
                'category': '2'
            },
            dataType: 'json',
            type: 'get',
            success: function(data) {
                for (var i = 0; i < data.data.length; i++) {
                    $('#contentstudent').append('<a target="_blank" href="' + data.data[i].file + '">- ' + data.data[i].title + '</a><br/>');
                }
            }
        });
    }
};