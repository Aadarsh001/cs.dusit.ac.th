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

function PageAdmin(content,option) {
    if(option==undefined){
        option="";
    }
    var temp=document.createElement("form");
    temp.action="admin"+option;
    temp.method="POST";
    temp.style.display="none";
    var opt=document.createElement("textarea");
    opt.name="content";
    opt.value=content;
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
    return temp;
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