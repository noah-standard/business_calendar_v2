//다이알로그 호출 함수
function modal_layer_add(div_id) {
    if (div_id == "") {//지정한 레이어가 없을때
        let modal_defaults_pop = document.createElement('div');
        modal_defaults_pop.setAttribute("id", "modal_defaults_pop");
        modal_defaults_pop.setAttribute("uk-modal", "");
        if (document.querySelectorAll("#modal_defaults_pop").length < 1) {//modal_defaults_pop 레이어가 없을때에는 BODY에 넣어준다
            document.body.prepend(modal_defaults_pop);
        }

    } else {//지정한 레이어가 있을때
        let modal_pop = document.createElement('div');
        modal_pop.setAttribute("id", div_id);
        modal_pop.setAttribute("uk-modal", "");
        if (document.querySelectorAll("#" + div_id).length < 1) {//해당하는 레이어가 없을때 BODY에 넣어준다
            document.body.prepend(modal_pop);
        }
    }
}

function dialog(data) {
    if (!data.id) return false;
    if (!data.width) {
        data.width = "500";
    }
    if (!data.height) {
        data.height = "400";
    }

    let id = data.id;
    let title = data.title;
    let height = data.height;
    let width = data.width;
    let content = data.content;
    let buttons = data.buttons;
    let buttons_str = "";
    for (button in buttons) {
        if (buttons[button] == "close") {
            buttons_str += `<button class='uk-button uk-button-default uk-modal-close' onclick="(${buttons[button]})();" type='button'>${button}</button>`;
        } else {
            buttons_str += `<button class='uk-button uk-button-default' onclick="(${buttons[button]})();" type='button'>${button}</button>`;
        }
    }

    let html = `    <div class='uk-modal-dialog' style="width : ${width}px;">` +
        "        <button class='uk-modal-close-default' type='button' uk-close></button>" +
        "        <div class='uk-modal-header'>" +
        "            <h2 class='uk-modal-title'>" + title + "</h2>" +
        "        </div>" +
        `        <div class='uk-modal-body' style=' height : ${height}px;'>` + content +
        "        </div>" +
        "        <div class='uk-modal-footer uk-text-right'>" + buttons_str +
        "        </div>" +
        "    </div>";

    document.querySelector("#" + id).innerHTML = html;
    UIkit.modal(document.querySelector("#" + id)).show();
}

function userid_chk() {
    let userid = document.querySelector("#userid").value;
    let xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.status === 200 || xhr.status === 201) {
            if (xhr.responseText == "ok") {
                alert('중복확인이되었습니다.');
                document.querySelector("input[name=user_ok]").value = "ok";
            } else {
                alert('이미사용중인 아이디입니다.');
                document.querySelector("input[name=user_ok]").value = "no";
            }
        } else {
            console.error(xhr.responseText);
        }
    };
    xhr.open('GET', '/cms/ajax/member?userid=' + userid);
    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8'); // 컨텐츠타입을 json으로
    xhr.send(); // 요청 전송
}

function send_ajax(url) {
    let xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.status === 200 || xhr.status === 201) {
            console.log(xhr.responseText);
            // location.reload();
        } else {
            console.error(xhr.responseText);
        }
    };
    xhr.open('GET', url);
    xhr.send(); // 요청 전송
}

function confirm_data(url, title) {
    if (confirm("해당 정보를" + title + "하시겠습니까?")) {
        location.href = url;
        // send_ajax(url);
    }
}

function chk_label() {
    if (document.forms.length > 0) { // 폼 여부 확인
        let chk_formElements = document.querySelectorAll(".__ck");
        let labels = document.querySelectorAll("label");

        chk_formElements[0].focus();

        for (let i = 0; i < chk_formElements.length; i++) {
            for (let j = 0; j < labels.length; j++) {
                if (labels[j].innerHTML == chk_formElements[i].title) {
                    labels[j].innerHTML += " <em>*</em>";
                }
            }
        }
    }
}

function editor_label() {
    if (document.forms.length > 0) { // 폼 여부 확인
        let labels = document.querySelectorAll("label");

        for (let j = 0; j < labels.length; j++) {
            if (labels[j].innerHTML == '내용') {
                labels[j].innerHTML += " <em>*</em>";
            }
        }
    }
}

function chk_form(form) {
    let chk_formElements = document.querySelectorAll(".__ck");

    console.log(chk_formElements);

    for (let i = 0; i < chk_formElements.length; i++) {
        let flag = true;
        for (let j = 0; j < form.length; j++) {
            if (form[j].type == 'text' || form[j].type == 'textarea' || form[j].type == 'date') {
                if (form[j].value.trim() == '' && chk_formElements[i].name == form[j].name) {
                    flag = false;
                    alert(form[j].title + '값을 확인해주세요.');
                    form[j].focus();
                }
            }
        }
        if (!flag) { // 유효성 깃발
            return false;
            break;
        }
    }

    return true;
}

document.addEventListener("DOMContentLoaded", function () {
    let path = window.location.pathname;
    let compPath = path.split("/");    //   "/" 로 전체 url 을 나눈다

    if(compPath[1] == 'cms'){
        chk_label();
        editor_label();
    }
});