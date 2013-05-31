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
IncludeCSS('css/academic.css');

window.onload = function() {
    $('.headcontent').attr('style', 'background-image: url(images/headacademic.png);');
    academic.start();
    research.start();
    project.start();
};

var academic = {
    start: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': 'academic',
                'option': 'show'
            },
            dataType: 'json',
            type: 'get',
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                //            alert('Error');
            },
            success: function(data) {
                for (var i = 0; i < data.data.length; i++) {
                    $('#contentacademic').append('<a target="_blank" href="' + data.data[i].file + '">- ' + data.data[i].title + '</a><br/>\n\
                                            <dd>โดย ' + data.data[i].owner + '</dd><br/>');
                }
            }
        });
    }
};

var research = {
    start: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': 'research',
                'option': 'show'
            },
            dataType: 'json',
            type: 'get',
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                //            alert('Error');
            },
            success: function(data) {
                for (var i = 0; i < data.data.length; i++) {
                    $('#contentresearch').append('<a target="_blank" href="' + data.data[i].file + '">- ' + data.data[i].title + '</a><br/>\n\
                                            <dd>โดย ' + data.data[i].owner + '</dd><br/>');
                }
            }
        });
    }
};

var project = {
    start: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': 'project',
                'option': 'show'
            },
            dataType: 'json',
            type: 'get',
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                //            alert('Error');
            },
            success: function(data) {
                for (var i = 0; i < data.data.length; i++) {
                    if(data.data[i].adviser!=""){
                        $('#contentproject').append('<a target="_blank" href="' + data.data[i].link + '">- ' + data.data[i].title + '</a><br/>\n\
                                            <dd>โดย ' + data.data[i].owner + ' (อาจารย์ที่ปรึกษา : '+data.data[i].adviser+')</dd><br/>');
                    }else{
                        $('#contentproject').append('<a target="_blank" href="' + data.data[i].link + '">- ' + data.data[i].title + '</a><br/>\n\
                                            <dd>โดย ' + data.data[i].owner + '</dd><br/>');
                    }
                    
                }
            }
        });
    }
};