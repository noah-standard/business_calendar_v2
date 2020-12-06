 function Postcode() {
    var pwidth = 500; //팝업의 너비
    var pheight = 600; //팝업의 높이
    new daum.Postcode({
    oncomplete: function(data) {
    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
    var fullAddr = ''; // 최종 주소 변수
    var extraAddr = ''; // 조합형 주소 변수
    data.userSelectedType = 'R';
    // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
    fullAddr = data.roadAddress;

} else { // 사용자가 지번 주소를 선택했을 경우(J)
    fullAddr = data.jibunAddress;
}

    // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
    if(data.userSelectedType === 'R'){
    //법정동명이 있을 경우 추가한다.
    if(data.bname !== ''){
    extraAddr += data.bname;
}
    // 건물명이 있을 경우 추가한다.
    if(data.buildingName !== ''){
    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
}
    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
}

    // 우편번호와 주소 정보를 해당 필드에 넣는다.
    document.querySelector("#zip").value = data.zonecode;
    document.querySelector("#add1").value = fullAddr;

}
}).open(
{
    left: (window.screen.width / 2) - (pwidth / 2),
    top: (window.screen.height / 2) - (pheight / 2),
}
    );
}

    function foldDaumPostcode(id) {
    // iframe을 넣은 element를 안보이게 한다.
    document.querySelector("#"+id).style.display = "none";
}

    function sample3_execDaumPostcode(zip_code_id,add_id,div_id) {
    var element_wrap = document.getElementById(div_id);
    // 현재 scroll 위치를 저장해놓는다.
    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
    oncomplete: function(data) {
    // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
//                var fullAddr = data.address; // 최종 주소 변수
    var fullAddr = data.roadAddress; // 최종 주소 변수(무조건 도로명을 찍히도록 함)
    var extraAddr = ''; // 조합형 주소 변수

    // 기본 주소가 도로명 타입일때 조합한다.
    if(data.addressType === 'R'){
    //법정동명이 있을 경우 추가한다.
    if(data.bname !== ''){
    extraAddr += data.bname;
}
    // 건물명이 있을 경우 추가한다.
    if(data.buildingName !== ''){
    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
}
    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
}

    if(data.userLanguageType=="K"){
//					var fullAddr = data.address; // 최종 주소 변수
    var fullAddr = data.roadAddress; // 최종 주소 변수(무조건 도로명을 찍히도록 함)
    var extraAddr = ''; // 조합형 주소 변수
}else if(data.userLanguageType=="E"){
    var fullAddr = (data.addressType=="R")?data.roadAddressEnglish:data.jibunAddressEnglish; // 최종 주소 변수
    var extraAddr = ''; // 조합형 주소 변수
}

    // 기본 주소가 도로명 타입일때 조합한다.
    if(data.addressType === 'R'){
    //법정동명이 있을 경우 추가한다.
    if(data.bname !== ''){
    extraAddr += data.bname;
}
    // 건물명이 있을 경우 추가한다.
    if(data.buildingName !== ''){
    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
}
    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
}

    // 우편번호와 주소 정보를 해당 필드에 넣는다.
    document.querySelector("#"+zip_code_id).value = data.zonecode;
    document.querySelector("#"+add_id).value =fullAddr;

    //document.getElementById('zip_code').value = data.zonecode; //5자리 새우편번호 사용
    //document.getElementById('add').value = fullAddr;

    // iframe을 넣은 element를 안보이게 한다.
    // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
    document.querySelector("#"+div_id).style.display = "none";

    // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
    document.body.scrollTop = currentScroll;


    document.querySelector("#"+div_id).focus();
},
    // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
    onresize : function(size) {
    //element_wrap.style.height = size.height+'px';
},
    width : '100%',
    height : "500"
}).embed(element_wrap);

    // iframe을 넣은 element를 보이게 한다.
    document.querySelector("#"+div_id).style.display = "block";
}