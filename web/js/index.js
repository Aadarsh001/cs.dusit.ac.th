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
IncludeCSS('css/index.css');

window.onload = function(){
    slideshow();
    news();
    event();
    knowledge();
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
//            alert('Error');
        },
        success : function (data){
            for(i=0;i<data.data.length;i++){
                $('#slideshow').append('<a href="'+data.data[i].link+'" title="'+data.data[i].title+'" rel="external" target="_blank"><img src="'+data.data[i].image+'" /></a>');
            }
            $('#slideshow').nSlide(720,300);
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
//            alert('Error');
        },
        success : function (data){
            for(var i=0;i<data.data.length;i++){
                var title = data.data[i].title.substr(0,73);
                var date = data.data[i].startdate.substr(6, 2)+"/"+data.data[i].startdate.substr(4, 2)+"/"+data.data[i].startdate.substr(2, 2);
                $('.titlenews.ui-grid-b').append('<div class="ui-block-a pin"></div><div class="ui-block-b">'+date+' : </div><div class="ui-block-c"><a href="showcontent?content=news&id='+data.data[i].id_new+'"title="'+data.data[i].title+'" rel="external" target="_blank">'+title+'</a></div>');
            }
            $.ajax({
                url : 'content',
                data : {
                    'content' : 'news',
                    'option' : 'show',
                    'rp'    : '8'
                },
                dataType : 'json',
                type : 'get',
                error : function(XMLHttpRequest, textStatus, errorThrown){
//                    alert('Error');
                },
                success : function (data){
                    for(var i=0;i<data.data.length;i++){
                        var title = data.data[i].title.substr(0,73);
                        var date = data.data[i].startdate.substr(6, 2)+"/"+data.data[i].startdate.substr(4, 2)+"/"+data.data[i].startdate.substr(2, 2);
                        $('.titlenews.ui-grid-b').append('<div class="ui-block-a"></div><div class="ui-block-b">'+date+' : </div><div class="ui-block-c"><a href="showcontent?content=news&id='+data.data[i].id_new+'"title="'+data.data[i].title+'" rel="external" target="_blank">'+title+'</a></div>');
                    }
                    $('.titlenews.ui-grid-b').append('<div class="ui-block-a"></div><div class="ui-block-b more"></div><a href="allcontent?content=news&option=all" rel="external" target="_blank"><div class="ui-block-c more">ดูข่าวย้อนหลัง></div></a>');
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
            'option' : 'show',
            'rp'    : '5'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown){
//            alert('Error');
        },
        success : function (data){
            var heading = 0;
            var image = data.data[heading].image.split(',');
            var title = data.data[heading].title.substr(0, 59);
            var detail = data.data[heading].detail.substr(0, 467);
            var detail_split;
            for(i=0;i<image.length;i++){
                detail_split = detail.split('[IMG'+i+']');
                detail = detail_split[0];
                for(j=1;j<detail_split.length;j++){
                    detail += detail_split[j];
                }
            }
            $('.event.ui-grid-a').append('<div class="ui-block-a"><a href="showcontent?content=event&id='+data.data[0].id_eve+'" rel="external" target="_blank"><img src="'+image[0]+'" class="image"/></a></div><div class="ui-block-b"><p class="firstevent"><a href="showcontent?content=event&id='+data.data[0].id_eve+'" title="'+data.data[0].title+'" rel="external" target="_blank">'+title+'</a></p><p class="detailfirstevent">'+detail+'</p></div>');
            for(var i=1;i<data.data.length;i++){
                image = data.data[i].image.split(',');
                title = data.data[i].title.substr(0, 86);
                detail = data.data[i].detail.substr(0, 314);
                for(k=0;k<image.length;k++){
                    detail_split = detail.split('[IMG'+k+']');
                    detail = detail_split[0];
                    for(j=1;j<detail_split.length;j++){
                        detail += detail_split[j];
                    }
                }
                $('.subevent.ui-grid-a').append('<div class="ui-block-a"><a href="showcontent?content=event&id='+data.data[i].id_eve+'" rel="external" target="_blank"><img src="'+image[0]+'" class="image"/></a></div><div class="ui-block-b"><p class="title"><a href="showcontent?content=event&id='+data.data[i].id_eve+'"title="'+data.data[i].title+'" rel="external" target="_blank">'+title+'</a></p><p class="detail">'+detail+'</p></div>');
            }
            $('.subevent.ui-grid-a').append('<div class="ui-block-a more"></div><a href="allcontent?content=event&option=all" rel="external" target="_blank"><div class="ui-block-b more">รวมกิจกรรม></div></a>');
        }
    });
}

function knowledge(){
    $.ajax({
        url : 'content',
        data : {
            'content' : 'knowledge',
            'option' : 'show',
            'rp'    : '5'
        },
        dataType : 'json',
        type : 'get',
        error : function(XMLHttpRequest, textStatus, errorThrown){
//            alert('Error');
        },
        success : function (data){
            for(var i=0;i<data.data.length;i++){
                var image = data.data[i].image.split(',');
                var title = data.data[i].title.substr(0, 86);
                var detail = data.data[i].detail.substr(0, 323);
                var detail_split;
                for(j=0;j<image.length;j++){
                    detail_split = detail.split('[IMG'+j+']');
                    detail = detail_split[0];
                    for(k=1;k<detail_split.length;k++){
                        detail += detail_split[k];
                    }
                }
                $('.knowledge.ui-grid-a').append('<div class="ui-block-a"><a href="showcontent?content=knowledge&id='+data.data[i].id_kno+'" rel="external" target="_blank"><img src="'+image[0]+'" class="image"/></a></div><div class="ui-block-b"><p class="title"><a href="showcontent?content=knowledge&id='+data.data[i].id_kno+'" title="'+data.data[i].title+'" rel="external" target="_blank">'+title+'</a></p><p class="detail">'+detail+'</p></div>');
            }
            $('.knowledge.ui-grid-a').append('<div class="ui-block-a more"></div><a href="allcontent?content=knowledge&option=all" rel="external" target="_blank"><div class="ui-block-b more">รวมสาระน่ารู้></div></a>');
        }
    });
}