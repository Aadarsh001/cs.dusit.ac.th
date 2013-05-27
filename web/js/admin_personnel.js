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
IncludeJavaScript('js/jquery.wysiwyg.js');
IncludeJavaScript('js/columnRight.js');
IncludeJavaScript('js/flexigrid.js');

IncludeCSS('css/jquery.mobile-1.3.0.css');
IncludeCSS('css/jquery.mobile.pc-1.3.0.css');
IncludeCSS('css/cs.dusit.css');
IncludeCSS('css/jquery-ui-1.8.10.custom.css');
IncludeCSS('css/jquery.nplugins-0.0.1.css');
IncludeCSS('css/jquery.wysiwyg.css');
IncludeCSS('css/style.css');
IncludeCSS('css/flexigrid.css');
IncludeCSS('css/admin.css');

var page = "personnel";

window.onload = function onload(){
    $('.datepicker').nDatepicker();
    $('textarea').wysiwyg({
        controls: {
            insertImage : {
                visible : false
            },
            h4 : {
                visible : true && !( $.browser.mozilla ), 
                className : 'h4', 
                command : 'formatBlock', 
                arguments : ['<H4>'], 
                tags : ['h4'], 
                tooltip : "Header 4"
            },
            h5 : {
                visible : true && !( $.browser.mozilla ), 
                className : 'h5', 
                command : 'formatBlock', 
                arguments : ['<H5>'], 
                tags : ['h5'], 
                tooltip : "Header 5"
            },
            h6 : {
                visible : true && !( $.browser.mozilla ), 
                className : 'h6', 
                command : 'formatBlock', 
                arguments : ['<H6>'], 
                tags : ['h6'], 
                tooltip : "Header 6"
            },
            insertYoutube : {
                exec: function() {
                    $('textarea').wysiwyg('insertHtml',prompt('Embed', ''));
                    return true;
                },
                visible: true
            }
        }
    });
    $('#detail').wysiwyg('setContent','<b>&nbsp;&nbsp;วุฒิการศึกษา :&nbsp;</b><div><b><br>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; อีเมล :&nbsp;</b></div><div><b><br>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; เบอร์โทร :&nbsp;</b></div><div><b><br>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; เว็บไซต์ :&nbsp;</b></div>');
    tab_btn();
    $('#image_file').nPreview('#image',240,320,'images/320x240.png');
    $('#_image_file').nPreview('#_image',240,320,'images/320x240.png');
    personnel.start();
    $('#btn_add_images').click(function(){
        if($('#image').attr("src").substr(0, 4) == "data"){
            $('#image_file').val("");
            $('#detail').wysiwyg('setContent',$('#detail').val()+'<div style="text-align: center;">[IMG'+(img_no)+']</div>');
            img_no++;
            $('#add_images').after('<div class="ui-block-a"><div class="image_frm"><img src="images/640x480.png" id="image" class="image"></div><div class="image_name">[IMG'+img_no+']</div></div>');   
        }
    });
}

var personnel = {
    start : function(){
        personnel.all();
        $('#submit_add').click(function(){
            if(($('#name').val() != "")
                &&($('#position').val() != "")){
                if(confirm('กด “ตกลง” เพื่อยืนยันการเพิ่มข้อมูล!')){
                    $.mobile.loading( 'show');
                    personnel.add();
                }
            }else{
                alert('กรุณาระบุข้อมูลทั้งหมด');
            }
        });
        $('#submit_edit').click(function(){
            if(($('#_title').val() != "")
                &&($('#_startdate').val() != "")){
                if(confirm('กด “ตกลง” เพื่อยืนยันการแก้ไขข้อมูล!')){
                    $.mobile.loading( 'show');
                    personnel.edit();
                }
            }else{
                alert('กรุณาระบุข้อมูลทั้งหมด');
            }
        });
        $('.reset').click(function(){
            PageAdmin(page);
        });
    },
    all : function(){
        $.ajax({
            url : 'content',
            data : {
                'content' : page,
                'option' : 'all'
            },
            dataType : 'json',
            type : 'get',
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert('Error');
            },
            success : function (data){
                for(var i =0;i<data.data.length;i++){
                    var status;
                    if(data.data[i].status=="1"){
                        status = "แสดง";
                    }else{
                        status = "ซ่อน";
                    }
                    $('#showAll').children("tbody").append("<tr id="+data.data[i].id_per+"><td>"
                        +data.data[i].name+"</td><td>"
                        +data.data[i].position+"</td><td>"
                        +data.data[i].sequence+"</td><td>"
                        +status+"</td></tr>");
                }
                $('#showAll tr').click(function(){
                    personnel.some($(this).attr("id"));
                });
                $('#showAll tr').dblclick(function(){
                    if(confirm('กด “ตกลง” เพื่อยืนยันการลบข้อมูล!')){
                        personnel.remove($(this).attr("id"));
                    }
                });
                $('#showAll').flexigrid({
                    width:"720",
                    height : "200",
                    singleSelect:true,
                    resizable: false
                });
            }
        });
    },
    some : function(id){
        $.ajax({
            url : 'content',
            data : {
                'content' : page,
                'option' : 'some',
                'id_per' : id
            },
            dataType : 'json',
            type : 'get',
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error : 0x01");
            },
            success : function (data){
                $('#_id_per').val(data.id_per);
                $('#_name').val(data.name);
                $('#_position').val(data.position);
                $('#_sequence').val(data.sequence).slider( "refresh" );;
                $('#_detail').wysiwyg('setContent',data.detail);
                $('#_filename').val(data.image);
                $('#_image').attr("style","width:100%;height:100%");
                $('#_image').attr("src",data.image);
                $('#_status-0').removeAttr("checked").checkboxradio("refresh");
                $('#_status-1').removeAttr("checked").checkboxradio("refresh");
                if (data.status == "1") {
                    $('#_status-1').attr("checked", true).checkboxradio("refresh");
                } else {
                    $('#_status-0').attr("checked", true).checkboxradio("refresh");
                }
            }
        });
    },
    add : function(){
        var image = "";
        if($('#image').attr('src').substr(0,4) == "data"){
            image = $('#image').attr('src');
        }
        $.ajax({
            url : 'content',
            data : {
                'content' : page,
                'option' : 'add',
                'name' : $('#name').val(),
                'position' : $('#position').val(),
                'sequence' : $('#sequence').val(),
                'detail' : $('#detail').val(),
                'image' : image,
                'status' : $('input[name="status"]:checked').val()
            },
            dataType : 'json',
            type : 'post',
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error : 0111");
                $.mobile.loading( 'hide');
            },
            success : function (data){
                if(data.result != "fail"){
                    PageAdmin(page);
                }else{
                    alert("Error : 0101");
                    $.mobile.loading( 'hide');
                }
            }
        });
    },
    edit : function(){
        var image = $('#_image').attr('src');
        if(image.substr(0, 4) != "data"){
            image = null;
        }
        $.ajax({
            url : 'content',
            data : {
                'content' : page,
                'option' : 'edit',
                'id_per' : $('#_id_per').val(),
                'name' : $('#_name').val(),
                'position' : $('#_position').val(),
                'sequence' : $('#_sequence').val(),
                'detail' : $('#_detail').val(),
                'image' : image,
                'filename' : $('#_filename').val(),
                'status' : $('input[name="_status"]:checked').val()
            },
            dataType : 'json',
            type : 'post',
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error : 0112");
                $.mobile.loading( 'hide');
            },
            success : function (data){
                if(data.result != "fail"){
                    PageAdmin(page,"?tab=edit");
                }else{
                    alert("Error : 0102");
                    $.mobile.loading( 'hide');
                }
            }
        });
    },
    remove : function(id){
        $.ajax({
            url : 'content',
            data : {
                'content' : page,
                'option' : 'remove',
                'id_per' : id
            },
            dataType : 'json',
            type : 'post',
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error : 0113");
            },
            success : function (data){
                if(data.result != "fail"){
                    PageAdmin(page,"?tab=edit");
                }else{
                    alert("Error : 0103");
                }
            }
        });
    }
}