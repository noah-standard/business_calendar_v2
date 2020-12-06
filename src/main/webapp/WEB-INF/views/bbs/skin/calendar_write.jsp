<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}/cms/ajax/search"/>
<script>
    function search_member(obj, id, id_title) {
        modal_layer_add(id);
        let xhr = new XMLHttpRequest();
        xhr.onload = function () {
            if (xhr.status === 200 || xhr.status === 201) {
                document.querySelector("#id").value = id;
                let data = {
                    id: id,
                    title: id_title,
                    content: xhr.responseText,
                    width: "700",
                    height: "600",
                    buttons: {
                        "등록": function () {
                            let id = document.querySelector('#id').value;
                            let idxs = document.querySelectorAll('.uk-checkbox.member_idx');
                            let check_count = idxs.length;
                            let check_idx = '';
                            let element = document.querySelector('#' + id);

                            for (let i = 0; i < check_count; i++) {
                                if (idxs[i].checked === true) {
                                    check_idx = idxs[i].value;
                                    document.querySelector('input[name=member_idx]').value = check_idx;
                                }
                            }
                            let name = document.querySelector('#name_' + check_idx).innerHTML;
                            document.querySelector('input[name=name]').value = name;
                            UIkit.modal(element).hide();
                        },
                        "취소": "close",
                    }
                };
                dialog(data);
            } else {
                console.error(xhr.responseText);
            }
        };
        xhr.open('GET', '/cms/ajax/search');
        xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8'); // 컨텐츠타입을 json으로
        xhr.send(); // 요청 전송
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
<form method="post" name="calendar_form"
      class="uk-form-horizontal uk-margin-medium-left uk-margin-medium-right uk-margin-small-top" action="./write.do">
    <div class="uk-margin">
        <label class="uk-form-label" for="type">이름</label>
        <div class="uk-form-controls">
            <input type="hidden" name="member_idx" value="${memberObject.idx}">
            <input class="uk-input uk-width-1-4 __ck" title="이름" name="name" type="text" value="${memberObject.name}" readonly>
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="type">구분</label>
        <div class="uk-form-controls">
            <select class="uk-select uk-width-1-4" name="type" id="type">
                <option value="">----선택----</option>
                <option value="연차">연차</option>
                <option value="반차">반차</option>
            </select>
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="s_date">시작일</label>
        <div class="uk-form-controls">
            <input class="uk-input uk-width-1-4 __ck" title="시작일" type="date" id="s_date" name="s_date" value="">
        </div>
    </div>
    <div class="uk-margin">
        <label class="uk-form-label" for="content">내용</label>
        <div class="uk-form-controls">
            <textarea class="uk-textarea" name="content" id="content" cols="30" rows="10"></textarea>
        </div>
    </div>
    <p class="uk-text-center">
        <a class="uk-button uk-button-default uk-modal-close" href="javascript:void(0)" onclick="history.back();">취소</a>
        <button class="uk-button uk-button-primary" type="submit">등록</button>
    </p>
</form>
