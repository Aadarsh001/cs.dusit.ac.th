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
IncludeCSS('css/download.css');

window.onload = function() {
    $('.headcontent').attr('style', 'background-image: url(images/headdownload.png);');
    download.start();
};

var download = {
    start: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': 'groupdownload',
                'option': 'show'
            },
            dataType: 'json',
            type: 'get',
            success: function(data) {
                for (i = 0; i < data.data.length; i++) {
                    $('.showcontent').append('<h3 id="'+data.data[i].id_gro+'">' + data.data[i].title + '</h3>');
                    $.ajax({
                        url: 'content',
                        data: {
                            'content': 'download',
                            'option': 'show',
                            'id_gro': data.data[i].id_gro
                        },
                        dataType: 'json',
                        type: 'get',
                        success: function(data) {
                            for (i = 0; i < data.data.length; i++) {
                                $('#'+data.data[i].id_gro).after('<dd><a rel="external" target="_blank" href="' + data.data[i].file + '">- ' + data.data[i].title + '</a></dd>');
                            }
                        }
                    });
                }
            }
        });
    }
};