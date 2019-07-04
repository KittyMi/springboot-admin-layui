var Browser = new Object();

Browser.isMozilla = (typeof document.implementation != 'undefined') && (typeof document.implementation.createDocument != 'undefined') && (typeof HTMLDocument != 'undefined');
Browser.isIE = window.ActiveXObject ? true : false;
Browser.isFirefox = (navigator.userAgent.toLowerCase().indexOf("firefox") != - 1);
Browser.isSafari = (navigator.userAgent.toLowerCase().indexOf("safari") != - 1);
Browser.isOpera = (navigator.userAgent.toLowerCase().indexOf("opera") != - 1);


var Utils = new Object();


Utils.htmlEncode = function(text)
{
    return text.replace(/&/g, '&').replace(/"/g, '"').replace(/</g, '<').replace(/>/g, '>');
}

/**
 * 去除最前、最后空格
 */
Utils.trim = function( text )
{
    if (typeof(text) == "string")
    {
        return text.replace(/^\s*|\s*$/g, "");
    }
    else
    {
        return text;
    }
}

/**
 * 是否为空
 */
Utils.isEmpty = function( val )
{
    switch (typeof(val))
    {
        case 'string':
            return Utils.trim(val).length == 0 ? true : false;
            break;
        case 'number':
            return val == 0;
            break;
        case 'object':
            return val == null;
            break;
        case 'array':
            return val.length == 0;
            break;
        default:
            return true;
    }
}

/**
 * 数值
 */
Utils.isNumber = function(val)
{
    var reg = /^[\d|\.|,]+$/;
    return reg.test(val);
}

/**
 * 整形
 */
Utils.isInt = function(val)
{
    if (val == "")
    {
        return false;
    }
    var reg = /\D+/;
    return !reg.test(val);
}

/**
 * 邮件验证
 * @param email:邮件
 * @returns：若是邮件，返回true，反之，返回false！
 */
Utils.isEmail = function(email)
{
    var reg1 = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)/;

    return reg1.test( email );
}

/**
 * 邮编验证
 * @param postalCode:邮编
 * @returns：若是邮编，返回true，反之，返回false！
 */
Utils.isPostalCode = function isPostalCode(postalCode)
{
    // var reg=/^[1-9]\d{5}(?!\d)$/;
    var reg=/^[0-9]\d{5}(?!\d)$/;
    return reg.test(postalCode);
}


/**
 * 电话
 */
Utils.isTel = function ( tel )
{
    var reg = /^[\d|\-|\s|\_]+$/; //只允许使用数字-空格等

    return reg.test( tel );
}

Utils.fixEvent = function(e)
{
    var evt = (typeof e == "undefined") ? window.event : e;
    return evt;
}

Utils.srcElement = function(e)
{
    if (typeof e == "undefined") e = window.event;
    var src = document.all ? e.srcElement : e.target;

    return src;
}

/**
 * time时间
 */
Utils.isTime = function(val)
{
    var reg = /^\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}$/;

    return reg.test(val);
}

Utils.x = function(e)
{ //当前鼠标X坐标
    return Browser.isIE?event.x + document.documentElement.scrollLeft - 2:e.pageX;
}

Utils.y = function(e)
{ //当前鼠标Y坐标
    return Browser.isIE?event.y + document.documentElement.scrollTop - 2:e.pageY;
}

Utils.request = function(url, item)
{
    var sValue=url.match(new RegExp("[\?\&]"+item+"=([^\&]*)(\&?)","i"));
    return sValue?sValue[1]:sValue;
}

Utils.$ = function(name)
{
    return document.getElementById(name);
}



/**
 * 保留两位小数
 * @param {Object} number
 * @param {Object} decimals
 */
Utils.roundNumber = function(number,decimals) {
    var newString;// The new rounded number
    decimals = Number(decimals);
    if (decimals < 1) {
        newString = (Math.round(number)).toString();
    } else {
        var numString = number.toString();
        if (numString.lastIndexOf(".") == -1) {// If there is no decimal point
            numString += ".";// give it one at the end
        }
        var cutoff = numString.lastIndexOf(".") + decimals;// The point at which to truncate the number
        var d1 = Number(numString.substring(cutoff,cutoff+1));// The value of the last decimal place that we'll end up with
        var d2 = Number(numString.substring(cutoff+1,cutoff+2));// The next decimal, after the last one we want
        if (d2 >= 5) {// Do we need to round up at all? If not, the string will just be truncated
            if (d1 == 9 && cutoff > 0) {// If the last digit is 9, find a new cutoff point
                while (cutoff > 0 && (d1 == 9 || isNaN(d1))) {
                    if (d1 != ".") {
                        cutoff -= 1;
                        d1 = Number(numString.substring(cutoff,cutoff+1));
                    } else {
                        cutoff -= 1;
                    }
                }
            }
            d1 += 1;
        }
        if (d1 == 10) {
            numString = numString.substring(0, numString.lastIndexOf("."));
            var roundedNum = Number(numString) + 1;
            newString = roundedNum.toString() + '.';
        } else {
            newString = numString.substring(0,cutoff) + d1.toString();
        }
    }
    if (newString.lastIndexOf(".") == -1) {// Do this again, to the new string
        newString += ".";
    }
    var decs = (newString.substring(newString.lastIndexOf(".")+1)).length;
    for(var i=0;i<decimals-decs;i++) newString += "0";
//var newNumber = Number(newString);// make it a number if you like
    return newString; // Output the result to the form field (change for your purposes)
}

function rowindex(tr)
{
    if (Browser.isIE)
    {
        return tr.rowIndex;
    }
    else
    {
        table = tr.parentNode.parentNode;
        for (i = 0; i < table.rows.length; i ++ )
        {
            if (table.rows[i] == tr)
            {
                return i;
            }
        }
    }
}

document.getCookie = function(sName)
{
    // cookies are separated by semicolons
    var aCookie = document.cookie.split("; ");
    for (var i=0; i < aCookie.length; i++)
    {
        // a name/value pair (a crumb) is separated by an equal sign
        var aCrumb = aCookie[i].split("=");
        if (sName == aCrumb[0])
            return decodeURIComponent(aCrumb[1]);
    }

    // a cookie with the requested name does not exist
    return null;
}

document.setCookie = function(sName, sValue, sExpires)
{
    var sCookie = sName + "=" + encodeURIComponent(sValue);
    if (sExpires != null)
    {
        sCookie += "; expires=" + sExpires;
    }

    document.cookie = sCookie;
}

document.removeCookie = function(sName,sValue)
{
    document.cookie = sName + "=; expires=Fri, 31 Dec 1999 23:59:59 GMT;";
}

function getPosition(o)
{
    var t = o.offsetTop;
    var l = o.offsetLeft;
    while(o = o.offsetParent)
    {
        t += o.offsetTop;
        l += o.offsetLeft;
    }
    var pos = {top:t,left:l};
    return pos;
}

function cleanWhitespace(element)
{
    var element = element;
    for (var i = 0; i < element.childNodes.length; i++) {
        var node = element.childNodes[i];
        if (node.nodeType == 3 && !/\S/.test(node.nodeValue))
            element.removeChild(node);
    }
}

Utils.string ={
    trim: function (str) {
        return str.replace(/^(\s|\xA0)+|(\s|\xA0)+$/g, '');
    },
    isEmpty: function (str) {
        return (!str || 0 === str.length);
    },
    isNotEmpty: function (str) {
        return !this.isEmpty(str);
    },
    isBlank: function (str) {
        return (!str || 0 === this.trim(str).length);
    },
    isNotBlank: function (str) {
        return !this.isBlank(str);
    },
    startWith:function(data,str){
        if(str==null||str==""||data == null ||data.length==0||str.length>data.length)
            return false;
        if(data.substr(0,str.length)==str)
            return true;
        else
            return false;
        return true;

    },
    endWith:function(data,str){
        if(str==null||str==""||data == null ||data.length==0||str.length>data.length)
            return false;
        if(data.substring(data.length-str.length)==str)
            return true;
        else
            return false;
        return true;
    },
    isUndefined:function (str) {
        return (typeof str=='undefined');
    }
};


Utils.date={
    patterns: {
        PATTERN_ERA: 'G', //Era 标志符 Era strings. For example: "AD" and "BC"
        PATTERN_YEAR: 'y', //年
        PATTERN_MONTH: 'M', //月份
        PATTERN_DAY_OF_MONTH: 'd', //月份的天数
        PATTERN_HOUR_OF_DAY1: 'k', //一天中的小时数（1-24）
        PATTERN_HOUR_OF_DAY0: 'H', //24小时制，一天中的小时数（0-23）
        PATTERN_MINUTE: 'm', //小时中的分钟数
        PATTERN_SECOND: 's', //秒
        PATTERN_MILLISECOND: 'S', //毫秒
        PATTERN_DAY_OF_WEEK: 'E', //一周中对应的星期，如星期一，周一
        PATTERN_DAY_OF_YEAR: 'D', //一年中的第几天
        PATTERN_DAY_OF_WEEK_IN_MONTH: 'F', //一月中的第几个星期(会把这个月总共过的天数除以7,不够准确，推荐用W)
        PATTERN_WEEK_OF_YEAR: 'w', //一年中的第几个星期
        PATTERN_WEEK_OF_MONTH: 'W', //一月中的第几星期(会根据实际情况来算)
        PATTERN_AM_PM: 'a', //上下午标识
        PATTERN_HOUR1: 'h', //12小时制 ，am/pm 中的小时数（1-12）
        PATTERN_HOUR0: 'K', //和h类型
        PATTERN_ZONE_NAME: 'z', //时区名
        PATTERN_ZONE_VALUE: 'Z', //时区值
        PATTERN_WEEK_YEAR: 'Y', //和y类型
        PATTERN_ISO_DAY_OF_WEEK: 'u',
        PATTERN_ISO_ZONE: 'X'
    },
    week: {
        'ch': {
            "0": "\u65e5",
            "1": "\u4e00",
            "2": "\u4e8c",
            "3": "\u4e09",
            "4": "\u56db",
            "5": "\u4e94",
            "6": "\u516d"
        },
        'en': {
            "0": "Sunday",
            "1": "Monday",
            "2": "Tuesday",
            "3": "Wednesday",
            "4": "Thursday",
            "5": "Friday",
            "6": "Saturday"
        }
    },
    //获取当前时间
    getCurrentTime: function() {
        var today = new Date();
        var year = today.getFullYear();
        var month = today.getMonth() + 1;
        var day = today.getDate();
        var hours = today.getHours();
        var minutes = today.getMinutes();
        var seconds = today.getSeconds();
        var timeString = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
        return timeString;
    },
    /*
     * 比较时间大小
     * time1>time2 return 1
     * time1<time2 return -1
     * time1==time2 return 0
     */
    compareTime: function(time1, time2) {
        if(Date.parse(time1.replace(/-/g, "/")) > Date.parse(time2.replace(/-/g, "/"))) {
            return 1;
        } else if(Date.parse(time1.replace(/-/g, "/")) < Date.parse(time2.replace(/-/g, "/"))) {
            return -1;
        } else if(Date.parse(time1.replace(/-/g, "/")) == Date.parse(time2.replace(/-/g, "/"))) {
            return 0;
        }
    },
    //是否闰年
    isLeapYear: function(year) {
        return((year % 4 === 0 && year % 100 !== 0) || year % 400 === 0);
    },
    //获取某个月的天数，从0开始
    getDaysOfMonth: function(year, month) {
        return [31, (this.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
    },
    getDaysOfMonth2: function(year, month) {
        // 将天置为0，会获取其上个月的最后一天
        month = parseInt(month) + 1;
        var date = new Date(year, month, 0);
        return date.getDate();
    },
    /*距离现在几天的日期：负数表示今天之前的日期，0表示今天，整数表示未来的日期
     * 如-1表示昨天的日期，0表示今天，2表示后天
     */
    fromToday: function(days) {
        var today = new Date();
        today.setDate(today.getDate() + days);
        var date = today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate();
        return date;
    },
    /**
     * 日期时间格式化
     * @param {Object} dateTime 需要格式化的日期时间
     * @param {String} pattern  格式化的模式，如yyyy-MM-dd hh(HH):mm:ss.S a k K E D F w W z Z
     */
    format: function(dateTime, pattern) {
        var date = new Date(dateTime);
        if(Utils.string.isBlank(pattern)) {
            return date.toLocaleString();
        }
        return pattern.replace(/([a-z])\1*/ig, function(matchStr, group1) {
            var replacement = "";
            switch(group1) {
                case Utils.date.patterns.PATTERN_ERA: //G
                    break;
                case Utils.date.patterns.PATTERN_WEEK_YEAR: //Y
                case Utils.date.patterns.PATTERN_YEAR: //y
                    replacement = date.getFullYear();
                    break;
                case Utils.date.patterns.PATTERN_MONTH: //M
                    var month = date.getMonth() + 1;
                    replacement = (month < 10 && matchStr.length >= 2) ? "0" + month : month;
                    break;
                case Utils.date.patterns.PATTERN_DAY_OF_MONTH: //d
                    var days = date.getDate();
                    replacement = (days < 10 && matchStr.length >= 2) ? "0" + days : days;
                    break;
                case Utils.date.patterns.PATTERN_HOUR_OF_DAY1: //k(1~24)
                    var hours24 = date.getHours();
                    replacement = hours24;
                    break;
                case Utils.date.patterns.PATTERN_HOUR_OF_DAY0: //H(0~23)
                    var hours24 = date.getHours();
                    replacement = (hours24 < 10 && matchStr.length >= 2) ? "0" + hours24 : hours24;
                    break;
                case Utils.date.patterns.PATTERN_MINUTE: //m
                    var minutes = date.getMinutes();
                    replacement = (minutes < 10 && matchStr.length >= 2) ? "0" + minutes : minutes;
                    break;
                case Utils.date.patterns.PATTERN_SECOND: //s
                    var seconds = date.getSeconds();
                    replacement = (seconds < 10 && matchStr.length >= 2) ? "0" + seconds : seconds;
                    break;
                case Utils.date.patterns.PATTERN_MILLISECOND: //S
                    var milliSeconds = date.getMilliseconds();
                    replacement = milliSeconds;
                    break;
                case Utils.date.patterns.PATTERN_DAY_OF_WEEK: //E
                    var day = date.getDay();
                    replacement = Utils.date.week['ch'][day];
                    break;
                case Utils.date.patterns.PATTERN_DAY_OF_YEAR: //D
                    replacement = Utils.date.dayOfTheYear(date);
                    break;
                case Utils.date.patterns.PATTERN_DAY_OF_WEEK_IN_MONTH: //F
                    var days = date.getDate();
                    replacement = Math.floor(days / 7);
                    break;
                case Utils.date.patterns.PATTERN_WEEK_OF_YEAR: //w
                    var days = Utils.date.dayOfTheYear(date);
                    replacement = Math.ceil(days / 7);
                    break;
                case Utils.date.patterns.PATTERN_WEEK_OF_MONTH: //W
                    var days = date.getDate();
                    replacement = Math.ceil(days / 7);
                    break;
                case Utils.date.patterns.PATTERN_AM_PM: //a
                    var hours24 = date.getHours();
                    replacement = hours24 < 12 ? "\u4e0a\u5348" : "\u4e0b\u5348";
                    break;
                case Utils.date.patterns.PATTERN_HOUR1: //h(1~12)
                    var hours12 = date.getHours() % 12 || 12; //0转为12
                    replacement = (hours12 < 10 && matchStr.length >= 2) ? "0" + hours12 : hours12;
                    break;
                case Utils.date.patterns.PATTERN_HOUR0: //K(0~11)
                    var hours12 = date.getHours() % 12;
                    replacement = hours12;
                    break;
                case Utils.date.patterns.PATTERN_ZONE_NAME: //z
                    replacement = Utils.date.getZoneNameValue(date)['name'];
                    break;
                case Utils.date.patterns.PATTERN_ZONE_VALUE: //Z
                    replacement = Utils.date.getZoneNameValue(date)['value'];
                    break;
                case Utils.date.patterns.PATTERN_ISO_DAY_OF_WEEK: //u
                    break;
                case Utils.date.patterns.PATTERN_ISO_ZONE: //X
                    break;
                default:
                    break;
            }
            return replacement;
        });
    },
    /**
     * 计算一个日期是当年的第几天
     * @param {Object} date
     */
    dayOfTheYear: function(date) {
        var obj = new Date(date);
        var year = obj.getFullYear();
        var month = obj.getMonth(); //从0开始
        var days = obj.getDate();
        var daysArr = [31, (this.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        for(var i = 0; i < month; i++) {
            days += daysArr[i];
        }
        return days;
    },
    firstDay:function (){
        var date=new Date();
        date.setDate(1);
        return date;
    },
    lastDay:function(){
        var date=new Date();
        var currentMonth=date.getMonth();
        var nextMonth=++currentMonth;
        var nextMonthFirstDay=new Date(date.getFullYear(),nextMonth,1);
        var oneDay=1000*60*60*24;
        return new Date(nextMonthFirstDay-oneDay);
    },
    diffDay:function(d1,d2){
        var oDate1  =  new  Date(d1.substring(0,4)  +  '/'  +  d1.substring(4,6)  +  '/'  +  d1.substring(6,8))
        var oDate2  =  new  Date(d2.substring(0,4)  +  '/'  +  d2.substring(4,6)  +  '/'  +  d2.substring(6,8))
        return parseInt(Math.abs(oDate1  -  oDate2)  /  1000  /  60  /  60  /24);
    },
    diffMonth:function(d1,d2){
        var date1 = parseInt(d1.substring(0,4)) * 12 + parseInt(d1.substring(4,6));
        var date2 = parseInt(d2.substring(0,4)) * 12 + parseInt(d2.substring(4,6));
        var m = Math.abs(date1 - date2);
        return m;
    },
    day_31:function(){
        return Utils.date.format(new Date(Utils.date.fromToday(-31)),"yyyyMMdd")+" - "+Utils.date.format(new Date(),"yyyyMMdd");
    },
    month_12:function(){
        var d=new Date();
        var end=Utils.date.format(new Date(),'yyyyMM');
        d.setMonth(d.getMonth()-12);
        var begin=Utils.date.format(d,'yyyyMM');
        return begin+" - "+end;
    },
    year_10:function(){
        var d=new Date();
        var end=Utils.date.format(new Date(),'yyyy');
        d.setFullYear(d.getFullYear()-10)
        var begin=Utils.date.format(d,'yyyy');
        return begin+" - "+end;
    },
    //获得时区名和值
    getZoneNameValue: function(dateObj) {
        var date = new Date(dateObj);
        date = new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()));
        var arr = date.toString().match(/([A-Z]+)([-+]\d+:?\d+)/);
        var obj = {
            'name': arr[1],
            'value': arr[2]
        };
        return obj;
    }
};

