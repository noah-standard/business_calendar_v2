<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}/cms/ajax/search"/>
<script>
    function search_member(obj, id, id_title) {
        modal_layer_add(id);
        let xhr = new XMLHttpRequest();
        xhr.onload = function () {
            if (xhr.status === 200 || xhr.status === 201) {
                document.querySelector("#id").value=id;
                let data = {
                    id: id,
                    title: id_title,
                    content: xhr.responseText,
                    width: "800",
                    height: "600",
                    buttons: {
                        "취소": "close",
                    }
                };
                dialog(data);
            } else {
                console.error(xhr.responseText);
            }
        };
        xhr.open('POST', '/cms/ajax/search');
        xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8'); // 컨텐츠타입을 json으로
        xhr.send(); // 요청 전송
    }

    function select_member(idx){
        let idxs = document.querySelectorAll('.uk-checkbox.member_idx');
        document.querySelector('input[name=member_idx]').value = idx;
        let name = document.querySelector('#name_' + idx).innerHTML;
        document.querySelector('input[name=name]').value = name;
        let id = document.querySelector('#id').value;
        let element = document.querySelector('#' + id);
        UIkit.modal(element).hide();
    }


    function list(page) {
        location.href = '${path}?curPage=' + page
            + '&search_order=${search_order}'
            + '&keyword=${keyword}'
            + '&list_order=${param.list_order}'
            + '&list_scale=${param.list_scale}';
    }

    function search_order() {
        if (event.keyCode == 13) {
            let keyword = document.querySelector("#keyword").value;
            document.querySelector("input[name=keyword]").value = keyword;

            let formData = new FormData(document.getElementById("ajax_search"));
            let url = '${path}';

            let xhr = new XMLHttpRequest();
            xhr.onload = function () {
                if (xhr.status === 200 || xhr.status === 201) {
                    document.querySelector("#member_search .uk-modal-body").innerHTML = xhr.responseText;
                } else {
                    console.error(xhr.responseText);
                }
            };
            xhr.open('POST', url);
            xhr.send(formData); // 요청 전송
        }
    }


</script>
<style>
    .uk-form-label {
        font-size: 18px;
    }
</style>
<input type="hidden" id="id">
<form method="post" name="calendar_form" action="${action}"
      class="uk-form-horizontal uk-margin-medium-left uk-margin-medium-right uk-margin-small-top" onsubmit="return chk_form(this);">
    <input type="hidden" name="idx" value="${calendarObject.idx}">
    <div class="uk-margin">
        <label class="uk-form-label" for="type">사원검색</label>
        <div class="uk-form-controls">
            <input type="hidden" name="member_idx" value="${calendarObject.member_idx}">
            <input class="uk-input uk-width-1-6 __ck" title="사원검색" name="name" type="text" value="${calendarObject.name}" readonly><a href="javascript:void(0)"
                                                                                            onclick="search_member(this,'member_search','사원검색')"
            class="uk-button uk-button-default uk-margin-small-left">사원검색</a>
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="type">구분</label>
        <div class="uk-form-controls">
            <select class="uk-select uk-width-1-6 __ck" title="구분" name="type" id="type">
                <option value="">----선택----</option>
                <option value="연차" ${calendarObject.type == '연차' ? "selected" : ""}>연차</option>
                <option value="반차" ${calendarObject.type == '반차' ? "selected" : ""}>반차</option>
            </select>
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="s_date">일자</label>
        <div class="uk-form-controls">
            <input class="uk-input uk-width-1-6 __ck" title="일자" type="date" id="s_date" name="s_date" value="${calendarObject.s_date}">
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="content">내용</label>
        <div class="uk-form-controls">
            <textarea class="uk-textarea" title="내용" name="content" id="content" cols="30" rows="10">${calendarObject.content}</textarea>
        </div>
    </div>
    <p class="uk-text-center">
        <a class="uk-button uk-button-default uk-modal-close" href="javascript:void(0)" onclick="history.back();">취소</a>
        <c:choose>
            <c:when test="${calendarObject.idx == null}">
                <button class="uk-button uk-button-primary" type="submit">등록</button>
            </c:when>
            <c:when test="${calendarObject.idx != ''}">
                <button class="uk-button uk-button-primary" type="submit">수정</button>
            </c:when>
        </c:choose>
    </p>
</form>
