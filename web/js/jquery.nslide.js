(function($) {
    var max_slide = 0;
    $.fn.nSlide = function(width,height){
        $(this).find('a').each(function() {
            $(this).attr("class","nslide_img");
        }); 
        $(this).attr("style","width:"+width+"px;height:"+height+"px");
        $(this).find('a').each(function() {
            max_slide++;
            $(this).attr("id","nslide"+max_slide);
        });
        $('#nslide1').fadeIn();
        for(var i=max_slide;i>0;i--){
            $(this).append('<div class="nslide_btn" style="margin-top:'+(height-32)+'px;">'+i+'</div>');
        }
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
                        nslide.sequence++;
                        $('#nslide'+(nslide.sequence)).fadeIn('100');
                    });
                }else{
                    $('#nslide'+nslide.sequence).fadeOut('400',function(){
                        nslide.sequence = 1;
                        $('#nslide'+nslide.sequence).fadeIn('100');
                    });
                }
            },10000);
        },
        move : function(num){
            if(num!=nslide.sequence){
                clearInterval(nslide.slide);
                $('#nslide'+nslide.sequence).fadeOut('400',function(){
                    nslide.sequence=num;
                    nslide.run();
                    $('#nslide'+nslide.sequence).fadeIn('100');
                });
            }
        }
    }
})(jQuery);