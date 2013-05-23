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
IncludeJavaScript('js/jquery.npreview.js');
IncludeJavaScript('js/columnRight.js');
IncludeJavaScript('js/flexigrid.js');

IncludeCSS('css/jquery.mobile-1.3.0.css');
IncludeCSS('css/jquery.mobile.pc-1.3.0.css');
IncludeCSS('css/cs.dusit.css');
IncludeCSS('css/jquery-ui-1.8.10.custom.css');
IncludeCSS('css/style.css');
IncludeCSS('css/flexigrid.css');
IncludeCSS('css/admin_slideshow.css');

var page = "slideshow";

window.onload = function onload(){
    tab_btn();
    clock();
    datepicker();
    $('#image_file').nPreview('#image');
    slideshow.all();
    $('#submit_add').click(function(){
        if(($('#title').val() != "")
            &&($('#startdate').val() != "")
            &&($('#enddate').val() != "")
            &&($('#image').attr('src').substr(0, 4) == "data")){
            if(confirm('กด “ตกลง” เพื่อยืนยันการเพิ่มข้อมูล!')){
                if($('#link').val()==""){
                    $('#link').val("#");
                }
                slideshow.add();
            }
        }else{
            alert('กรุณาระบุข้อมูลทั้งหมด');
        }
    });
    $('#submit_edit').click(function(){
        if(($('#_title').val() != "")
            &&($('#_id_sli').val() != "")
            &&($('#_startdate').val() != "")
            &&($('#_enddate').val() != "")){
            if(confirm('กด “ตกลง” เพื่อยืนยันการเพิ่มข้อมูล!')){
                if($('#_link').val()==""){
                    $('#_link').val("#");
                }
                slideshow.edit();
            }
        }else{
            alert('กรุณาระบุข้อมูลทั้งหมด');
        }
    });
    $('.reset').click(function(){
        PageAdmin('slideshow');
    });
}

function tab_btn(){
    $('#page_edit').slideUp();
    $('#tab_add').click(function(){
        $('#page_edit').slideUp("",function(){
            $('#page_add').slideDown();
        });
    });
    $('#tab_edit').click(function(){
        $('#page_add').slideUp("",function(){
            $('#page_edit').slideDown();
        });
    });
    setTimeout(function(){
        var tab = getUrlVars()["tab"];
        if(tab=="edit"){
            $('#tab_edit').trigger('click');
        }
    }, 500);
}

var slideshow = {
    all : function(){
        $.ajax({
            url : 'content',
            data : {
                'content' : 'slideshow',
                'option' : 'all'
            },
            dataType : 'json',
            type : 'get',
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert('Error');
            },
            success : function (data){
                for(var i =0;i<data.data.length;i++){
                    $('#showAll').children("tbody").append("<tr id="+data.data[i].id_sli+"><td>"
                        +data.data[i].title+"</td><td>"
                        +data.data[i].startdate+"</td><td>"
                        +data.data[i].enddate+"</td><td>"
                        +data.data[i].sequence+"</td><td>"
                        +data.data[i].status+"</td></tr>");
                }
                $('#showAll tr').click(function(){
                    slideshow.some($(this).attr("id"));
                });
                $('#showAll tr').dblclick(function(){
                    if(confirm('กด “ตกลง” เพื่อยืนยันการลบข้อมูล!')){
                        slideshow.remove($(this).attr("id"));
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
                'content' : 'slideshow',
                'option' : 'some',
                'id_sli' : id
            },
            dataType : 'json',
            type : 'get',
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error : 0x01");
            },
            success : function (data){
                $('#_id_sli').val(data.id_sli);
                $('#_title').val(data.title);
                $('#_link').val(data.link);
                $('#_startdate').val(data.startdate.substr(6, 2)+"/"+data.startdate.substr(4, 2)+"/"+data.startdate.substr(0, 4));
                $('#_enddate').val(data.enddate.substr(6, 2)+"/"+data.enddate.substr(4, 2)+"/"+data.enddate.substr(0, 4));
                $('#_sequence').val(data.sequence).slider( "refresh" );
                if (data.status == "1") {
                    $('#_status-1').attr("checked", true).checkboxradio("refresh");
                } else {
                    $('#_status-0').attr("checked", true).checkboxradio("refresh");
                }
                $('#_image').attr("src",data.image);
            }
        });
    },
    add : function(){
        $.ajax({
            url : 'content',
            data : {
                'content' : 'slideshow',
                'option' : 'add',
                'title' : $('#title').val(),
                'image' : $('#image').attr('src'),
                'link' : $('#link').val(),
                'sequence' : $('#sequence').val(),
                'startdate' : $('#startdate').val().substr(6, 4)+$('#startdate').val().substr(3, 2)+$('#startdate').val().substr(0, 2),
                'enddate' : $('#enddate').val().substr(6, 4)+$('#enddate').val().substr(3, 2)+$('#enddate').val().substr(0, 2),
                'status' : $('input[name="status"]:checked').val()
            },
            dataType : 'json',
            type : 'post',
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error : 0111");
            },
            success : function (data){
                if(data.result != "fail"){
                    PageAdmin(page);
                }else{
                    alert("Error : 0101");
                }
            }
        });
    },
    edit : function(){
        $.ajax({
            url : 'content',
            data : {
                'content' : 'slideshow',
                'option' : 'edit',
                'id_sli' : $('#_id_sli').val(),
                'title' : $('#_title').val(),
                'link' : $('#_link').val(),
                'sequence' : $('#_sequence').val(),
                'startdate' : $('#_startdate').val().substr(6, 4)+$('#_startdate').val().substr(3, 2)+$('#_startdate').val().substr(0, 2),
                'enddate' : $('#_enddate').val().substr(6, 4)+$('#_enddate').val().substr(3, 2)+$('#_enddate').val().substr(0, 2),
                'status' : $('input[name="_status"]:checked').val()
            },
            dataType : 'json',
            type : 'post',
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                alert("Error : 0112");
            },
            success : function (data){
                if(data.result != "fail"){
                    PageAdmin(page,"?tab=edit");
                }else{
                    alert("Error : 0102");
                }
            }
        });
    },
    remove : function(id){
        $.ajax({
            url : 'content',
            data : {
                'content' : 'slideshow',
                'option' : 'remove',
                'id_sli' : id
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

function datepicker() {
    var d = new Date();
    var toDay = d.getDate() + '/' + (d.getMonth() + 1) + '/'
    + (d.getFullYear() + 543);
    $(".datepicker")
    .datepicker(
    {
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

function getUrlVars() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=]*)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}