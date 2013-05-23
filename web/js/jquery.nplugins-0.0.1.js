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

(function($) {
    $.fn.nPreview = function(id,w,h){
        $(this).change(function(){
            $(id).attr('src', this.value);
            if (this.files && this.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $(id).attr('src', e.target.result);
                }
                reader.readAsDataURL(this.files[0]);
            }
            var timerId = setInterval(function () {
                if($(id).width()==w&&$(id).height()==h){
                    clearInterval(timerId);
                }else{
                    $(this).attr("src","");
                    $(id).attr('src','images/demo_image.png');
                    alert("ไฟล์ภาพต้องเป็นขนาด "+w+" x "+h+" เท่านั้น");
                    clearInterval(timerId);
                }
            }, 500);
        });
    }
})(jQuery);

(function($) {
    $.fn.nUpload = function(){
        var id = $(this).attr("id");
        $(this).change(function(){
            if (this.files && this.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    $('#'+id).attr('data', e.target.result);
                }
                reader.readAsDataURL(this.files[0]);
            }else{
                $('#'+id).removeAttr('data');
            }
        });
    }
})(jQuery);

(function($) {
    $.fn.nClock = function(){
        clock(this);
        function clock(id){
            now=new Date();
            hour=now.getHours();
            min=now.getMinutes();
            sec=now.getSeconds();
            day=now.getDate();
            month=now.getMonth();
            monthno=now.getMonth();
            monthno+=1;
            year=now.getFullYear();
            //    year+=543;
            if (monthno<=9) {
                monthno="0"+monthno;
            }
            if (hour<=9) {
                hour="0"+hour;
            }
            if (min<=9) {
                min="0"+min;
            }
            if (sec<=9) {
                sec="0"+sec;
            }
            if (month == 0) print2='Jan';
            if (month == 1) month='Feb';
            if (month == 2) month='Mar';
            if (month == 3) month='Apr';
            if (month == 4) month='May';
            if (month == 5) month='Jun';
            if (month == 6) month='July';
            if (month == 7) month='Aug';
            if (month == 8) month='Sep';
            if (month == 9) month='Oct';
            if (month == 10) month='Nov';
            if (month == 11) month='Dec';
            time = hour + ":" + min + ":" + sec;
            date = day + " " + month + " " + year;
            $(id).empty();
            $(id).append(date +" "+time);
            setTimeout(function(){
                clock(id);
            },1000);
        }
    }
})(jQuery);

(function($) {
    $.fn.nDatepicker = function(){
        
        var d = new Date();
        var toDay = d.getDate() + '/' + (d.getMonth() + 1) + '/'
        + (d.getFullYear() + 543);
        $(this).datepicker({
            changeMonth : true,
            changeYear : true,
            dateFormat : 'dd/mm/yy',
            isBuddhist : true,
            defaultDate : toDay,
            dayNames : [ 'อาทิตย์', 'จันทร์', 'อังคาร', 'พุธ',
            'พฤหัสบดี', 'ศุกร์', 'เสาร์' ],
            dayNamesMin : [ 'อา.', 'จ.', 'อ.', 'พ.', 'พฤ.', 'ศ.',
            'ส.' ],
            monthNames : [ 'มกราคม', 'กุมภาพันธ์', 'มีนาคม',
            'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม',
            'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน',
            'ธันวาคม' ],
            monthNamesShort : [ 'ม.ค.', 'ก.พ.', 'มี.ค.', 'เม.ย.',
            'พ.ค.', 'มิ.ย.', 'ก.ค.', 'ส.ค.', 'ก.ย.',
            'ต.ค.', 'พ.ย.', 'ธ.ค.' ]
        });
    }
})(jQuery);