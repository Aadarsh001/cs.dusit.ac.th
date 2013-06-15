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
IncludeCSS('css/jquery.mobile.pc-1.3.0.css');
IncludeCSS('css/style.css');
IncludeCSS('css/allcontent.css');

window.onload = function(){
    getUrlVars();
    sel_content();
}

function sel_content(){
    var content = getUrlVars()["content"];
    
    if(content == "news"){
        news();
    }else if(content == "event"){
        event();
    }else if(content == "knowledge"){
        knowledge();
    }
}

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=]*)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}

function news(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'news',
            'option' : 'show',
            'rp'    : '20'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('Error');
        },
        success : function (data){
            if(data.data.length > 5){
                $('.loadmore').show();
                
            }
            for(var i = 0; i<data.data.length; i++){
                $('.headcontent').css('background', 'url("images/headnews.png") no-repeat');
                var title = data.data[i].title.substr(0,68)+"..";
                var date = data.data[i].startdate.substr(6, 2)+"/"+data.data[i].startdate.substr(4, 2)+"/"+data.data[i].startdate.substr(2, 2);
                $('.allcontent.ui-grid-a').append('<div class="ui-block-a">'+date+' : </div><div class="ui-block-b"><a href="showcontent?content=news&id='+data.data[i].id_new+'"title="'+data.data[i].title+'" rel="external">'+title+'</a></div>');
            }
        }
    });
}

function event(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'event',
            'option' : 'show',
            'rp'    : '20'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('Error');
        },
        success : function (data){
            if(data.data.length > 5){
                $('.loadmore').show();
                
            }
            $('.headcontent').css('background', 'url("images/headevent.png") no-repeat');
            for(var i = 0; i<data.data.length; i++){
                var title = data.data[i].title.substr(0,68)+"..";
                var date = data.data[i].startdate.substr(6, 2)+"/"+data.data[i].startdate.substr(4, 2)+"/"+data.data[i].startdate.substr(2, 2);
                $('.allcontent.ui-grid-a').append('<div class="ui-block-a">'+date+' : </div><div class="ui-block-b"><a href="showcontent?content=event&id='+data.data[i].id_eve+'"title="'+data.data[i].title+'" rel="external">'+title+'</a></div>');
            }
        }
    });
}

function knowledge(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'knowledge',
            'option' : 'show',
            'rp'    : '20'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('Error');
        },
        success : function (data){
            if(data.data.length > 5){
                $('.loadmore').show();
                
            }
            $('.headcontent').css('background', 'url("images/headknowledge.png") no-repeat');
            for(var i = 0; i<data.data.length; i++){
                var title = data.data[i].title.substr(0,68)+"..";
                var date = data.data[i].startdate.substr(6, 2)+"/"+data.data[i].startdate.substr(4, 2)+"/"+data.data[i].startdate.substr(2, 2);
                $('.allcontent.ui-grid-a').append('<div class="ui-block-a">'+date+' : </div><div class="ui-block-b"><a href="showcontent?content=knowledge&id='+data.data[i].id_kno+'"title="'+data.data[i].title+'" rel="external">'+title+'</a></div>');
            }
        }
    });
}