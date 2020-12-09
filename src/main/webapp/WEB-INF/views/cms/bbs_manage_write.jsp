<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="uk-heading-divider uk-padding-small">게시판관리</h3>
<h4 class="uk-margin-medium-left uk-margin-remove-top uk-margin-remove-bottom">게시판 ${bbsObject.idx ne null ? "수정" : "등록"}</h4>
<form method="post" action="${action}" class="uk-form-horizontal uk-margin-medium-left uk-margin-medium-right uk-margin-small-top" onsubmit="return chk_form(this); ">
    <div class="uk-margin">
        <label class="uk-form-label" for="form-horizontal-select">게시판타입</label>
        <div class="uk-form-controls">
            <select class="uk-select" name="bbs_type" id="form-horizontal-select">
                <option value="notice" ${bbsObject.bbs_type ne null && bbsObject.bbs_type eq "notice" ? "selected" : "selected"}>공지사항</option>
                <option value="reply" ${bbsObject.bbs_type eq "reply" ? "selected" : ""}>답변형</option>
                <option value="gallery" ${bbsObject.bbs_type eq "gallery" ? "selected" : ""}>갤러리</option>
            </select>
        </div>
    </div>
    <style>
        .uk-form-label {
            font-size: 18px;
        }
    </style>
    <div class="uk-margin">
        <label class="uk-form-label" for="form-horizontal-text">게시판이름</label>
        <div class="uk-form-controls">
            <input class="uk-input __ck" title="게시판이름" name="name" id="form-horizontal-text" type="text" value="${bbsObject.name ne null ? bbsObject.name : ""}">
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="form-horizontal-text">페이지갯수</label>
        <div class="uk-form-controls">
            <input class="uk-input uk-width-1-5 __ck" title="페이지갯수" name="list_page" type="text" value="${bbsObject.list_page ne null ? bbsObject.list_page : "10"}">
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="form-horizontal-text">리스트갯수</label>
        <div class="uk-form-controls">
            <input class="uk-input uk-width-1-5 __ck" title="리스트갯수" name="list_height" type="text" value="${bbsObject.list_height ne null ? bbsObject.list_height : "10"}">
        </div>
    </div>
    <div class="uk-margin">
        <div class="uk-form-label">첨부기능</div>
        <div class="uk-form-controls uk-form-controls-text">
            <label><input class="uk-radio" type="radio" name="bbs_pds" value="0" ${bbsObject.bbs_pds ne null && bbsObject.bbs_pds eq "0" ? "checked" : "checked"}>미허용</label><br>
            <label><input class="uk-radio" type="radio" name="bbs_pds" value="1" ${bbsObject.bbs_pds eq "1" ? "checked" : ""}>허용</label>
        </div>
    </div>

    <div class="uk-margin">
        <div class="uk-form-label">게시판 권한</div>
        <input type="hidden" id="id">
        <div class="uk-form-controls uk-form-controls-text uk-width-1-2">
            <div class="uk-child-width-expand@s uk-text-center" uk-grid>
                <div>
                    <div class="uk-card uk-card-small uk-card-default">
                        <div class="uk-card-header">
                            <div class="uk-grid-small uk-flex-middle">
                                <div class="uk-width-expand">
                                    <h3 class="uk-card-title uk-margin-remove-bottom">쓰기</h3>
                                </div>
                            </div>
                        </div>
                        <div class="uk-card-body">
                            <ul class="uk-list">
                                <input type="hidden" name="bbs_write" value="${bbsObject.bbs_write ne null ? bbsObject.bbs_write : "nobody"}">
                                <li><label for="bbs_write0"><input type="radio" class="uk-radio" name="bbs_radio1" id="bbs_write0" value="0" onclick="click_write(this,'bbs_write')" ${bbsObject.bbs_write ne null && bbsObject.bbs_write eq "nobody" ? "checked" : "checked"}>미허용</label></li>
                                <li><label for="bbs_write1"><input type="radio" class="uk-radio" name="bbs_radio1" id="bbs_write1" onclick="click_write(this,'bbs_write','쓰기권한')" ${bbsObject.bbs_write ne "nobody" ? "checked" : ""}>허용</label></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="uk-card uk-card-small uk-card-default">
                        <div class="uk-card-header">
                            <div class="uk-grid-small uk-flex-middle">
                                <div class="uk-width-expand">
                                    <h3 class="uk-card-title uk-margin-remove-bottom">읽기</h3>
                                </div>
                            </div>
                        </div>
                        <div class="uk-card-body">
                            <ul class="uk-list">
                                <input type="hidden" name="bbs_read" value="${bbsObject.bbs_read ne null ? bbsObject.bbs_read : "nobody"}">
                                <li><label for="bbs_read0"><input type="radio" class="uk-radio" name="bbs_radio2" id="bbs_read0" value="0" onclick="click_write(this,'bbs_read')" ${bbsObject.bbs_read ne null && bbsObject.bbs_read eq "nobody" ? "checked" : "checked"}>미허용</label></li>
                                <li><label for="bbs_read1"><input type="radio" class="uk-radio" name="bbs_radio2" id="bbs_read1" onclick="click_write(this,'bbs_read','읽기권한')" ${bbsObject.bbs_read ne "nobody" ? "checked" : ""}>허용</label></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="uk-card uk-card-small uk-card-default">
                        <div class="uk-card-header">
                            <div class="uk-grid-small uk-flex-middle">
                                <div class="uk-width-expand">
                                    <h3 class="uk-card-title uk-margin-remove-bottom">수정/삭제</h3>
                                </div>
                            </div>
                        </div>
                        <div class="uk-card-body">
                            <ul class="uk-list">
                                <input type="hidden" name="bbs_modify" value="${bbsObject.bbs_modify ne null ? bbsObject.bbs_modify : "nobody"}">
                                <li><label for="bbs_modify0"><input type="radio" class="uk-radio" name="bbs_radio3" id="bbs_modify0" value="0" onclick="click_write(this,'bbs_modify')" ${bbsObject.bbs_modify ne null && bbsObject.bbs_modify eq "nobody" ? "checked" : "checked"}>미허용</label></li>
                                <li><label for="bbs_modify1"><input type="radio" class="uk-radio" name="bbs_radio3" id="bbs_modify1" onclick="click_write(this,'bbs_modify','수정/삭제권한')" ${bbsObject.bbs_modify ne "nobody" ? "checked" : ""}>허용</label></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <p class="uk-text-center">
        <a class="uk-button uk-button-default uk-modal-close" href="javascript:void(0)" onclick="history.back();">취소</a>
        <button class="uk-button uk-button-primary" ${bbsObject.idx ne null ? "onclick=\"return confirm(\'수정하시겠습니까?\');\"" : ""} type="submit">${bbsObject.idx ne null ? "수정" : "등록"}</button>
    </p>
</form>
<script>

    function click_write(obj,id,id_title){
        let value= obj.value;
        if(value == "0"){
            document.querySelector('input[name='+id+']').value="nobody";
        }else{
            document.querySelector("#id").value=id;
            modal_layer_add(id);
            let xhr = new XMLHttpRequest();
            xhr.onload = function() {
                if (xhr.status === 200 || xhr.status === 201) {
                    let data = {
                        id:id,
                        title:id_title,
                        content:xhr.responseText,
                        width:"300",
                        height:"200",
                        buttons : {
                            "등록":function (){
                                let id = document.querySelector('#id').value;
                                let values=  document.getElementsByName('allow_'+id);
                                let check_count = values.length;
                                let check_str = '';

                                for (let i=0; i<check_count; i++) {
                                    if (values[i].checked == true) {
                                        check_str += values[i].value+',';
                                    }
                                }
                                check_str = check_str.substring(0,check_str.length-1);
                                document.querySelector('input[name='+id+']').value =check_str;
                                UIkit.modal(document.querySelector('#'+id)).hide();
                            },
                            "취소":"close",
                        }
                    }
                    dialog(data);
                } else {
                    console.error(xhr.responseText);
                }
            };
            xhr.open('GET', '/cms/ajax/bbs_manage?allow='+id);
            xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8'); // 컨텐츠타입을 json으로
            xhr.send(); // 요청 전송
        }
    }

    function check_chk(){
        let any_element = document.querySelector("#anybody");
        let check_all="";
        check_all = document.querySelectorAll(".member");
        let check_count = check_all.length;


        if(any_element.checked == false) {
            for(let i=0; i<check_count; i++){
                check_all[i].removeAttribute("disabled");
            }
        }
        if(any_element.checked == true){
            console.log(any_element.checked);
            for(let i=0; i<check_count; i++){
                check_all[i].setAttribute("disabled","true");
            }
        }
    }
</script>