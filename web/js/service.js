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
IncludeCSS('css/service.css');

window.onload = function() {
    $('#headtraining').attr('style', 'background-image: url(images/headtraining.png);');
    $('#headcooperation').attr('style', 'background-image: url(images/headcooperation.png);');
    service.start();
};

var service = {
    start: function() {
        $.ajax({
            url: 'content',
            data: {
                'content': 'service',
                'option': 'show'
            },
            dataType: 'json',
            type: 'get',
            success: function(data) {
                for (var i = 0; i < data.data.length; i++) {
                    if (data.data[i].status === "1") {
                        $('#contenttraining').append('<li><a rel="external" href="' + data.data[i].link + '">' + data.data[i].title + '</a></li>');
                    } else {
                        $('#contentcooperation').append('<li><a rel="external" href="' + data.data[i].link + '">' + data.data[i].title + '</a></li>');
                    }
                }
            }
        });
    }
};