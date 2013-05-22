(function($) {
    $.fn.nPreview = function(id){
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
                if($(id).width()==720&&$(id).height()==300){
                    clearInterval(timerId);
                }else{
                    $(this).attr("src","");
                    $(id).attr('src','images/demo_image.png');
                    alert("ไฟล์ภาพต้องเป็นขนาด 720 x 300 เท่านั้น");
                    clearInterval(timerId);
                }
            }, 500);
        });
    }
})(jQuery);