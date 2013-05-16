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
IncludeCSS('css/index.css');

window.onload = function(){
    slideshow();
    news();
    event();
    knowledge();
    link();
}

function slideshow(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'slideshow',
            'option' : 'show'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            alert('Error');
        },
        success : function (data){
//              $('#slideshow').append('<img src="">');
            $('#slideshow').prepend('<img src="'+data.data[0].image+'"/>');
        }
    });
}

function news(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'news',
            'option' : 'pin'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert('Error');
        },
        success : function (data){
            for(var i=0;i<data.data.length;i++){
                var date = data.data[i].startdate.substr(6, 2)+"/"+data.data[i].startdate.substr(4, 2)+"/"+data.data[i].startdate.substr(2, 2);
                $('.titlenews.ui-grid-b').append('<div class="ui-block-a">*</div><div class="ui-block-b">'+date+' : </div><div class="ui-block-c"><a href="'+data.data[i].file+'">'+data.data[i].title+'</a></div>');
            }
            $.ajax({
                url : 'content',
                data : {
                    'content' : 'news',
                    'option' : 'show'
                },
                dataType : 'json',
                type : 'get',
                error : function(XMLHttpRequest, textStatus, errorThrown){
                    alert('Error');
                },
                success : function (data){
                    for(var i=0;i<data.data.length;i++){
                        var date = data.data[i].startdate.substr(6, 2)+"/"+data.data[i].startdate.substr(4, 2)+"/"+data.data[i].startdate.substr(2, 2);
                        $('.titlenews.ui-grid-b').append('<div class="ui-block-a"></div><div class="ui-block-b">'+date+' : </div><div class="ui-block-c"><a href="'+data.data[i].file+'">'+data.data[i].title+'</a></div>');
                    }
                }
            });
        }
    });
}

function event(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'event',
            'option' : 'show'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert('Error');
        },
        success : function (data){
            var heading = 0;
            var image = data.data[heading].image.split('[,]');
            var title = data.data[heading].title.substr(0, 57)+"...";
            var detail = data.data[heading].detail.substr(0, 450)+"...";
            $('.event.ui-grid-a').append('<div class="ui-block-a"><a href="#"><img src="'+image[0]+'" class="image"/></a></div><div class="ui-block-b"><p class="firstevent"><a href="#">'+title+'</a></p><p class="detailfirstevent">'+detail+'</p></div>');
            for(var i=1;i<data.data.length;i++){
                $('.subevent.ui-grid-a').append('<div class="ui-block-a"><a href="#"><img src="'+image[0]+'" class="image"/></a></div><div class="ui-block-b"><p class="title"><a href="#">'+data.data[i].title+'</a></p><p class="detail">'+data.data[i].detail+'</p></div>');
            }
        }
    });
}

function knowledge(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'knowledge',
            'option' : 'show'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert('Error');
        },
        success : function (data){
            for(var i=0;i<data.data.length;i++){
                $('.knowledge.ui-grid-a').append('<div class="ui-block-a"><a href="#"><img src="'+data.data[i].image+'"/></a></div><div class="ui-block-b"><p class="title"><a href="#">'+data.data[i].title+'</a></p><p class="detail"><a href="#">'+data.data[i].detail+'</a></p></div>');
            }
        }
    });
}

function link(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'link',
            'option' : 'show'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown){
            alert('Error');
        },
        success : function (data){
            for(var i=0;i<data.data.length;i++){
                $('#link').append('<li class="link"><a href="'+data.data[i].link+'" target="blank">-  '+data.data[i].title+'</a></li>');
            }
        }
    });
}