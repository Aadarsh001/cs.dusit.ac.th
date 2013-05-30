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
IncludeCSS('css/personnal.css');

window.onload = function() {
    $('.headcontent').attr('style', 'background-image: url(images/headpersonnel.png);');
    personnal();
};

function personnal() {
    $.ajax({
        url: 'content',
        data: {
            'content': 'personnel',
            'option': 'show'
        },
        dataType: 'json',
        type: 'get',
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            //            alert('Error');
        },
        success: function(data) {
            for (i = 0; i < data.data.length; i++) {
                var image = data.data[i].image;
                if (image === "null") {
                    image = "images/per_demo.png";
                }
                var content = '<h3>' + data.data[i].name + ' ( ' + data.data[i].position + ' )</h3>\n\
                            <div class="ui-grid-a personnal">\n\
                                <div class="ui-block-a">\n\
                                    <div class="per_frm_images"><img src="' + image + '" class="per_image"></div>\n\
                                </div>\n\
                                <div class="ui-block-b"><br/>\n\
                                    <div class="detail">' + data.data[i].detail + '</div>\n\
                                </div>\n\
                            </div>';
                $('.showcontent').append(content);
            }
        }
    });
}