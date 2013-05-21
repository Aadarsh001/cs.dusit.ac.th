(function($) {
    var max_slide = 0;
    var m_t = 0;
    $.fn.nSlide = function(width,height){
        m_t = (height-32);
        $(this).find('a').each(function() {
            $(this).attr("class","nslide_link");
            $(this).children("img").attr("class","nslide_img");
        }); 
        $(this).attr("style","width:"+width+"px;height:"+height+"px");
        $(this).attr("class","nslide_frm");
        $(this).find('a').each(function() {
            max_slide++;
            $(this).attr("id","nslide"+max_slide);
        });
        $('#nslide1').fadeIn();
        for(var i=max_slide;i>0;i--){
            $(this).append('<div class="nslide_btn" id="nslide_btn'+i+'" style="margin-top:'+m_t+'px;">'+i+'</div>');
        }
        $('#nslide_btn1').attr("style","margin-top:"+m_t+"px;opacity:.99;");
        nslide.start();
    }
    $.fn.nSlide.move = function(page){
        nslide.move(page);
    }
    var nslide = {
        sequence : 1,
        slide : null,
        start : function(){
            $('.nslide_btn').click(function(){
                $(this).nSlide.move($(this).text());
            });
            this.run();
        },
        run : function(){
            nslide.slide = setInterval(function(){
                if(nslide.sequence!=max_slide){
                    $('#nslide'+nslide.sequence).fadeOut('400',function(){
                        $('#nslide_btn'+nslide.sequence).attr("style","margin-top:"+m_t+"px;");
                        nslide.sequence++;
                        $('#nslide'+(nslide.sequence)).fadeIn('100');
                        $('#nslide_btn'+nslide.sequence).attr("style","margin-top:"+m_t+"px;opacity: .99;");
                    });
                }else{
                    $('#nslide'+nslide.sequence).fadeOut('400',function(){
                        $('#nslide_btn'+nslide.sequence).attr("style","margin-top:"+m_t+"px;");
                        nslide.sequence = 1;
                        $('#nslide_btn'+nslide.sequence).attr("style","margin-top:"+m_t+"px;opacity: .99;");
                        $('#nslide'+nslide.sequence).fadeIn('100');
                    });
                }
            },10000);
        },
        move : function(num){
            if(num!=nslide.sequence){
                clearInterval(nslide.slide);
                $('#nslide'+nslide.sequence).fadeOut('400',function(){
                    $('#nslide_btn'+nslide.sequence).attr("style","margin-top:"+m_t+"px;");
                    nslide.sequence=num;
                    $('#nslide_btn'+nslide.sequence).attr("style","margin-top:"+m_t+"px;opacity: .99;");
                    nslide.run();
                    $('#nslide'+nslide.sequence).fadeIn('100');
                });
            }
        }
    }
})(jQuery);