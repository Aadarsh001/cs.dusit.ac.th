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

function clock(){
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

    $('#date').empty();
    $('#date').append(date +" "+time);
    
    setTimeout("clock()", 1000);
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