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
IncludeCSS('css/course.css');

window.onload = function() {
    $('.headcontent').attr('style', 'background-image: url(images/headcourse.png);');
    course.start();
};

var course = {
    start: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': 'course',
                'option': 'show'
            },
            dataType: 'json',
            type: 'get',
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                //            alert('Error');
            },
            success: function(data) {
                for (var i = 0; i < data.data.length; i++) {
                    $('.showcontent').append('<h4>' + data.data[i].title + '</h4>\n\
                                        <dd>- ดาวโหลดเอกสาร <a rel="external" target="_blank" href="' + data.data[i].file + '">คลิกที่นี่</a>');
                }
            }
        });
    }
};