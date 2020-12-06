// �꾪솕踰덊샇
function inputPhoneNumber(obj) {

    var number = obj.value.replace(/[^0-9]/g, "");

    obj.value = number.replace(/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,"$1-$2-$3");
}

//�ъ뾽�먮벑濡앸쾲��
function bizNoFormatter( obj ) {
    obj.value =  PhonNumStr( obj.value );
}

// �좎쭨 �좏슚�� 寃���
function dateFormatCheck(obj) {
    /*
    var pattern = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;

    if(pattern.test(obj.value)) {
    } else {
        alert("�좎쭨�뺤떇�� �섎せ�섏뿀�듬땲��. �ㅼ떆 �낅젰�댁＜�몄슂.");
        obj.focus();
        return;
    }
    */
    if(!isValidDate(obj.value)) {
//		alert("�좎쭨�뺤떇�� �섎せ�섏뿀�듬땲��. �ㅼ떆 �낅젰�댁＜�몄슂.");
//		obj.focus();
    }
}

function isValidDate(param) {
    try
    {
        param = param.replace(/-/g,'');

        // �먮━�섍� 留욎��딆쓣��
        if( isNaN(param) || param.length!=8 ) {
            return false;
        }

        var year = Number(param.substring(0, 4));
        var month = Number(param.substring(4, 6));
        var day = Number(param.substring(6, 8));

        var dd = day / 0;

        if( month<1 || month>12 ) {
            return false;
        }

        var maxDaysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        var maxDay = maxDaysInMonth[month-1];

        // �ㅻ뀈 泥댄겕
        if( month==2 && ( year%4==0 && year%100!=0 || year%400==0 ) ) {
            maxDay = 29;
        }

        if( day<=0 || day>maxDay ) {
            return false;
        }
        return true;

    } catch (err) {
        return false;
    }
}

function PhonNumStr( str ){

    var RegNotNum  = /[^0-9]/g;
    var RegPhonNum = "";
    var DataForm   = "";

    if( str == "" || str == null ) return "";

    str = str.replace(RegNotNum,'');

    if( str.length < 4 ) return str;
    if( str.length > 3 && str.length < 5 ) {
        DataForm = "$1-$2";
        RegPhonNum = /([0-9]{3})([0-9]+)/;
    } else if(str.length == 5 ) {
        DataForm = "$1-$2";
        RegPhonNum = /([0-9]{3})([0-9]+)/;
    } else if(str.length == 10 ) {
        DataForm = "$1-$2-$3";
        RegPhonNum = /([0-9]{3})([0-9]{2})([0-9]+)/;
    } else  if( str.length > 5 && str.length < 10 ) {
        DataForm = "$1-$2-$3";
        RegPhonNum = /([0-9]{3})([0-9]{2})([0-9]+)/;
    } else if(str.length > 10){
        DataForm = "$1-$2-$3";
        RegPhonNum = /([0-9]{3})([0-9]{2})([0-9]+)/;
    }
    str = str.replace(RegPhonNum, DataForm);

    return str;
}

function birthday_bub( obj ) {

    obj.value =  birthday_Str_bub( obj.value );
}

function birthday_Str_bub( str ){

    var RegNotNum  = /[^0-9]/g;
    var RegPhonNum = "";
    var DataForm   = "";

    // return blank

    if( str == "" || str == null ) return "";

    // delete not number

    str = str.replace(RegNotNum,'');

    if( str.length < 4 ) return str;

    if( str.length > 3 && str.length < 6 ) {
        DataForm = "$1-$2";
        RegPhonNum = /([0-9]{4})([0-9]+)/;

    } else if(str.length == 6 ) {
        DataForm = "$1-$2";
        RegPhonNum = /([0-9]{4})([0-9]+)/;

    } else if(str.length > 5 && str.length < 8 ) {
        DataForm = "$1-$2";
        RegPhonNum = /([0-9]{4})([0-9]+)/;

    } else if(str.length == 10 ) {
        DataForm = "$1-$2-$3";
        RegPhonNum = /([0-9]{4})([0-9]{2)([0-9]{2})/;

    } else if(str.length > 7 && str.length < 10){
        DataForm = "$1-$2-$3";
        RegPhonNum = /([0-9]{4})([0-9]{2})([0-9]{2})/;

    }

    while( RegPhonNum.test(str) ) {
        str = str.replace(RegPhonNum, DataForm);

    }
    return str;
}

//踰뺤씤�깅줉踰덊샇
function businessNum_bub( obj ) {
    /*
    var jumin = obj;
    var str1 = jumin.val().substring(0,6);
    var str2 = jumin.val().substring(6,jumin.val().length);

    if(jumin.val().length>6) {
        if(jumin.val().length>=13) {
            str2 = jumin.val().substring(6,13);
            $(jumin).val(str1+'-'+str2);
        }else {
            $(jumin).val(str1+'-'+str2);
        }
    }else {
        str1 = jumin.val().substring(0,jumin.val().length);
        $(jumin).val(str1);
    }*/
    obj.value =  PhonNumStr_bub( obj.value );
}

function PhonNumStr_bub( str ){

    var RegNotNum  = /[^0-9]/g;

    var RegPhonNum = "";

    var DataForm   = "";


    // return blank

    if( str == "" || str == null ) return "";

    // delete not number

    str = str.replace(RegNotNum,'');

    if( str.length < 6 ) return str;

    if( str.length > 6 && str.length < 10 ) {

        DataForm = "$1-$2";

        RegPhonNum = /([0-9]{6})([0-9]+)/;

    } else if(str.length == 6 ) {

        DataForm = "$1-$2";

        RegPhonNum = /([0-9]{6})([0-9]+)/;

    } else if(str.length == 13 ) {

        DataForm = "$1-$2";

        RegPhonNum = /([0-9]{6})([0-9]{7})/;

    } else if(str.length == 13){

        DataForm = "$1-$2";

        RegPhonNum = /([0-9]{6})([0-9]{7})/;

        DataForm = "$1-$2";

        RegPhonNum = /([0-9]{6})([0-9]{7})/;


    } else if(str.length > 13){

        DataForm = "$1-$2";

        RegPhonNum = /([0-9]{6})([0-9]{7})/;

    }

    while( RegPhonNum.test(str) ) {

        str = str.replace(RegPhonNum, DataForm);

    }
    return str;
}