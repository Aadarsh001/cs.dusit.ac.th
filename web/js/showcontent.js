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

IncludeCSS('css/jquery.mobile-1.3.0.css');
//IncludeCSS('css/jquery-1.3.0-theme-mod.css');
IncludeCSS('css/jquery.mobile.pc-1.3.0.css');
IncludeCSS('css/style.css');
IncludeCSS('css/showcontent.css');

window.onload = function(){
    getUrlVars();
    shownews();
//    showevent();
//    showknow();
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=]*)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function shownews(){
    var content = getUrlVars()["content"];
    var id = getUrlVars()["id_new"];

    $.ajax({
        url : 'content',
        data : {
            'content' : content,
            'option'  : 'some',
            'id_new' : id
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('ERROR');
        },
        success : function (data){
            alert('id = '+id);
        }
    });
}

function showevent(){
    var content = getUrlVars()["content"];
    var id = getUrlVars()["id_eve"];
    
    $.ajax({
        url : 'content',
        data : {
            'content' : content,
            'option'  : 'some',
            'id_eve' : id
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('ERROR');
        },
        success : function (data){
            alert('id = ' + id);
        }
    });
}

function showknow(){
    var content = getUrlVars()["content"];
    var id = getUrlVars()["id_kno"];
    
    $.ajax({
        url : 'content',
        data : {
            'content' : content,
            'option'  : 'some',
            'id_eve' : id
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('ERROR');
        },
        success : function (data){
            alert('id = ' + id);
        }
    });
}