(function($) {
    var max_slide = 0;
    var m_t = 0;
    $.fn.nSlide = function(width, height) {
        m_t = (height - 32);
        $(this).find('a').each(function() {
            $(this).attr("class", "nslide_link");
            $(this).children("img").attr("class", "nslide_img");
        });
        $(this).attr("style", "width:" + width + "px;height:" + height + "px");
        $(this).attr("class", "nslide_frm");
        $(this).find('a').each(function() {
            max_slide++;
            $(this).attr("id", "nslide" + max_slide);
        });
        $('#nslide1').fadeIn();
        for (var i = max_slide; i > 0; i--) {
            $(this).append('<div class="nslide_btn" id="nslide_btn' + i + '" style="margin-top:' + m_t + 'px;">' + i + '</div>');
        }
        $('#nslide_btn1').attr("style", "margin-top:" + m_t + "px;opacity:.99;");
        nslide.start();
    };
    $.fn.nSlide.move = function(page) {
        nslide.move(page);
    };
    var nslide = {
        sequence: 1,
        slide: null,
        start: function() {
            $('.nslide_btn').click(function() {
                $(this).nSlide.move($(this).text());
            });
            this.run();
        },
        run: function() {
            nslide.slide = setInterval(function() {
                if (parseInt(nslide.sequence) !== parseInt(max_slide)) {
                    $('#nslide' + nslide.sequence).fadeOut(1500);
                    $('#nslide_btn' + nslide.sequence).attr("style", "margin-top:" + m_t + "px;");
                    nslide.sequence++;
                    $('#nslide_btn' + nslide.sequence).attr("style", "margin-top:" + m_t + "px;opacity: .99;");
                    $('#nslide' + nslide.sequence).fadeIn(1500);
                } else {
                    $('#nslide' + nslide.sequence).fadeOut(1500);
                    $('#nslide_btn' + nslide.sequence).attr("style", "margin-top:" + m_t + "px;");
                    nslide.sequence = 1;
                    $('#nslide_btn' + nslide.sequence).attr("style", "margin-top:" + m_t + "px;opacity: .99;");
                    $('#nslide' + nslide.sequence).fadeIn(1500);
                }
            }, 10000);
        },
        move: function(num) {
            if (parseInt(num) !== nslide.sequence) {
                clearInterval(nslide.slide);
                $('#nslide' + nslide.sequence).fadeOut(1500);
                $('#nslide_btn' + nslide.sequence).attr("style", "margin-top:" + m_t + "px;");
                nslide.sequence = num;
                $('#nslide_btn' + nslide.sequence).attr("style", "margin-top:" + m_t + "px;opacity: .99;");
                $('#nslide' + nslide.sequence).fadeIn(1500);
                nslide.run();
            }
        }
    };
})(jQuery);

(function($) {
    $.fn.nPreview = function(id, w, h, demo, m) {
        $(this).change(function() {
            $(id).attr('src', demo);
            $(id).removeAttr('style');
            $(id).attr('src', this.value);
            if (this.files && this.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $(id).attr('src', e.target.result);
                };
                reader.readAsDataURL(this.files[0]);
            }
            if (m === "ASC") {
                if (h !== undefined && w !== undefined) {
                    var timerId = setInterval(function() {
                        if ($(id).width() <= w && $(id).height() <= h) {
                            $(id).css({
                                'width': '100%',
                                'height': '100%'
                            });
                            clearInterval(timerId);
                        } else {
                            $(this).attr("src", "");
                            $(id).attr('src', demo);
                            alert("ไฟล์ภาพขนาดไม่เกิน " + w + " x " + h + " เท่านั้น");
                            clearInterval(timerId);
                        }
                    }, 500);
                }
            } else {
                if (h !== undefined && w !== undefined) {
                    var timerId = setInterval(function() {
                        if ($(id).width() === w && $(id).height() === h) {
                            $(id).css({
                                'width': '100%',
                                'height': '100%'
                            });
                            clearInterval(timerId);
                        } else {
                            $(this).attr("src", "");
                            $(id).attr('src', demo);
                            alert("ไฟล์ภาพต้องเป็นขนาด " + w + " x " + h + " เท่านั้น");
                            clearInterval(timerId);
                        }
                    }, 500);
                }
            }
        });
    };
})(jQuery);

(function($) {
    $.fn.nUpload = function() {
        $(this).change(function() {
            var id = $(this);
            if (this.files && this.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    id.attr('data', e.target.result);
                };
                reader.readAsDataURL(this.files[0]);
            } else {
                id.removeAttr('data');
            }
        });
    };
})(jQuery);

(function($) {
    $.fn.nClock = function() {
        clock(this);
        function clock(id) {
            now = new Date();
            hour = now.getHours();
            min = now.getMinutes();
            sec = now.getSeconds();
            day = now.getDate();
            month = now.getMonth();
            monthno = now.getMonth();
            monthno += 1;
            year = now.getFullYear();
            //    year+=543;
            if (monthno <= 9) {
                monthno = "0" + monthno;
            }
            if (hour <= 9) {
                hour = "0" + hour;
            }
            if (min <= 9) {
                min = "0" + min;
            }
            if (sec <= 9) {
                sec = "0" + sec;
            }
            if (month === 0)
                print2 = 'Jan';
            if (month === 1)
                month = 'Feb';
            if (month === 2)
                month = 'Mar';
            if (month === 3)
                month = 'Apr';
            if (month === 4)
                month = 'May';
            if (month === 5)
                month = 'Jun';
            if (month === 6)
                month = 'July';
            if (month === 7)
                month = 'Aug';
            if (month === 8)
                month = 'Sep';
            if (month === 9)
                month = 'Oct';
            if (month === 10)
                month = 'Nov';
            if (month === 11)
                month = 'Dec';
            time = hour + ":" + min + ":" + sec;
            date = day + " " + month + " " + year;
            $(id).empty();
            $(id).append(date + " " + time);
            setTimeout(function() {
                clock(id);
            }, 1000);
        }
    };
})(jQuery);

(function($) {
    $.fn.nDatepicker = function() {

        var d = new Date();
        var toDay = d.getDate() + '/' + (d.getMonth() + 1) + '/'
                + (d.getFullYear() + 543);
        $(this).datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: 'dd/mm/yy',
            isBuddhist: true,
            defaultDate: toDay,
            dayNames: ['อาทิตย์', 'จันทร์', 'อังคาร', 'พุธ',
                'พฤหัสบดี', 'ศุกร์', 'เสาร์'],
            dayNamesMin: ['อา.', 'จ.', 'อ.', 'พ.', 'พฤ.', 'ศ.',
                'ส.'],
            monthNamesShort: ['มกราคม', 'กุมภาพันธ์', 'มีนาคม',
                'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม',
                'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน',
                'ธันวาคม'],
            monthNames: ['ม.ค.', 'ก.พ.', 'มี.ค.', 'เม.ย.',
                'พ.ค.', 'มิ.ย.', 'ก.ค.', 'ส.ค.', 'ก.ย.',
                'ต.ค.', 'พ.ย.', 'ธ.ค.']
        });
    };
})(jQuery);

(function($) {
    $.fn.nCalendar = function(hDate, eClick) {
        $(this).datepicker({
            dateFormat: 'dd/mm/yy',
            isBuddhist: true,
            onSelect: eClick,
            beforeShowDay: function(date) {
                var dates = hDate;
                var date_no = date.getDate();
                var month_no = date.getMonth();
                var year_no = date.getFullYear() + 543;
                month_no++;
                if (month_no <= 9) {
                    month_no = "0" + month_no;
                }
                if (date_no <= 9) {
                    date_no = "0" + date_no;
                }
                date = year_no + "" + month_no + "" + date_no;
                for (var i = 0; i < dates.length; i++) {
                    if (dates[i] === date) {
                        return [true, 'highlight'];
                    }
                }
                return [true, "no_event\" onclick=\"return false;"];
            },
            monthNames: ['มกราคม', 'กุมภาพันธ์', 'มีนาคม',
                'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม',
                'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน',
                'ธันวาคม']
        });
    };
})(jQuery);

(function($, window, undefined) {
    //patch ajax settings to call a progress callback
    var oldXHR = $.ajaxSettings.xhr;
    $.ajaxSettings.xhr = function() {
        var xhr = oldXHR();
        if (xhr instanceof window.XMLHttpRequest) {
            xhr.addEventListener('progress', this.progress, false);
        }

        if (xhr.upload) {
            xhr.upload.addEventListener('progress', this.progress, false);
        }

        return xhr;
    };
})(jQuery, window);

function nChartInit() {
    google.load("visualization", "1", {packages: ["corechart"]});
}

(function($) {
    $.fn.nChart = function(type, datas, w, h) {
        if (w === undefined) {
            w = 640;
        }
        if (h === undefined) {
            h = 480;
        }
        var data = [];
        for (var i = 0; i < datas.length; i++) {
            data.push(datas[i]);
        }

        var _data = new google.visualization.arrayToDataTable(data);

        var options = {'title': 'How Much Pizza I Ate Last Night',
            'width': w,
            'height': h};

        var chart;
        if (type === 'pie') {
            chart = new google.visualization.PieChart(document.getElementById($(this).attr('id')));
        } else if (type === 'column') {
            chart = new google.visualization.ColumnChart(document.getElementById($(this).attr('id')));
        } else if (type === 'bar') {
            chart = new google.visualization.BarChart(document.getElementById($(this).attr('id')));
        } else if (type === 'line') {
            chart = new google.visualization.LineChart(document.getElementById($(this).attr('id')));
        }

        chart.draw(_data, options);
    };
})(jQuery);