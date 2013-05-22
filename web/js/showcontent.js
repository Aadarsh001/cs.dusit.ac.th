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
    sel_content();
}

function sel_content(){
    var content = getUrlVars()["content"];
    var id = getUrlVars()["id"];
    if(content == "news"){
        shownews(id);
    }else if(content == "event"){
        showevent(id);
    }else if(content == "knowledge"){
        showknowledge(id);
    }
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=]*)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function shownews(id){

    $.ajax({
        url : 'content',
        data : {
            'content' : 'news',
            'option'  : 'some',
            'id_new' : id
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('Error');
        },
        success : function (data){
            var date = data.startdate.substr(6, 2)+"/"+data.startdate.substr(4, 2)+"/"+data.startdate.substr(2, 2);
            $('.showcontent.ui-grid-b').append('<div class="ui-block-a"></div><div class="ui-block-b">'+date+' : </div><div class="ui-block-c">'+data.title+'</div>');
            
        }
    });
}

function showevent(id){    
    $.ajax({
        url : 'content',
        data : {
            'content' : 'event',
            'option'  : 'some',
            'id_eve' : id
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('Error');
        },
        success : function (data){
            var date = data.startdate.substr(6, 2)+"/"+data.startdate.substr(4, 2)+"/"+data.startdate.substr(2, 2);
            $('.showcontent.ui-grid-b').append('<div class="ui-block-a"></div><div class="ui-block-b">'+date+' : </div><div class="ui-block-c">'+data.title+'</div>');
            
        }
    });
}

function showknowledge(id){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'knowledge',
            'option'  : 'some',
            'id_kno' : id
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('Error');
        },
        success : function (data){
            var date = data.startdate.substr(6, 2)+"/"+data.startdate.substr(4, 2)+"/"+data.startdate.substr(2, 2);
            $('.showcontent.ui-grid-b').append('<div class="ui-block-a"></div><div class="ui-block-b">'+date+' : </div><div class="ui-block-c">'+data.title+'</div>');
            
        }
    });
}