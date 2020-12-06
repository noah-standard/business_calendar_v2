document.addEventListener('DOMContentLoaded', init);
checkFlag = false;


function init() {
    let calendarE1 = document.querySelector('.calendar');
    let login = document.body.querySelector('div.nav-item.layout');
    let acceptCalendar = document.body.querySelector('div.accept_btn');
    let checkBox = document.body.querySelector('table.check-item.checkbox');
    let deletebtn = document.body.querySelector('.check-item.object.object-btn.delete');
    let updatebtn = document.body.querySelector('button.detail-item.updatebtn.object.object-btn');
    let checkTemplate = document.body.querySelector('.templates-item.check');


    window.addEventListener('keydown', keyHandler);
    login.addEventListener('click', clickHandler);
    if (deletebtn) {
        deletebtn.addEventListener('click', deleteHandler)
    }
    if (checkBox) {
        checkBox.addEventListener('click', checkBoxHandler);
    }
    if (acceptCalendar) {
        acceptCalendar.addEventListener('click', clickAcceptHandler);
    }
    if (updatebtn) {
        updatebtn.addEventListener('click', clickUpdateHandler);
    }
    if (checkTemplate) {
        checkTemplate.addEventListener('click', checkTemplateHandler);
    }


    if (calendarE1) {
        let calendar = new FullCalendar.Calendar(calendarE1, {
            plugins: ['dayGrid'],
            header: {
                left: 'prev ,next',
                center: 'title',
                right: 'today'
            },
            navLinks: false, // can click day/week names to navigate views
            selectable: false,
            selectMirror: false,
            events: {
                url: '/accept/xhr',
                method: 'post',
                extraParams: {
                    delete_flag: '0',
                },
                failure: function () {
                    alert('there was an error while fetching events!');
                },
            },
            editable: false,
            eventLimit: true, // allow "more" link when too many events
        });
        calendar.setOption('locale', 'ko');
        calendar.render();
    }

}


function clickHandler(event) {
    let login = document.body.querySelector('div.base-item.login');
    let elem = event.target;
    if (elem.classList.contains('btn-login')) {
        login.classList.add('active');
    }
}

function clickAcceptHandler(event) {
    let acceptCalendar = document.body.querySelector('div.templates-item.modal_box');
    let elem = event.target;
    if (elem.innerText == '일정 신청') {
        acceptCalendar.classList.add('active');
    }
}

function keyHandler(event) {
    let login = document.body.querySelector('div.base-item.login');
    let acceptCalendar = document.body.querySelector('div.templates-item.modal_box');
    if (event.key == 'Escape') {
        login.classList.remove('active');
        acceptCalendar.classList.remove('active');
    }
}


function checkBoxHandler(event) { //체크 박스 선택
    let elem = event.target;
    let allCheck = document.body.querySelectorAll('.object.object-checkbox');// all checkBox

    if (elem.classList.contains('i')) {
        location.href = '/detail?i=' + elem.innerHTML;
    }

    if (elem.classList.contains('allcheck')) { // 전체 선택
        if (checkFlag == false) {
            for (let i = 0; i < allCheck.length; i++) {
                allCheck[i].classList.add('active');
                checkFlag = true;
            }
        } else if (checkFlag == true) {
            for (let i = 0; i < allCheck.length; i++) {
                allCheck[i].classList.remove('active');
                checkFlag = false;
            }
        }
    }

    while (!elem.classList.contains('object-check')) { // 독립 선택
        elem = elem.parentNode;
        if (elem.nodeName == 'BODY') {
            elem = null;
            return;
        }
        elem.classList.toggle('active');
    }


}

function deleteHandler() {
    let allCheck = document.body.querySelectorAll('.object.object-checkbox');
    let sessionItem = document.body.querySelectorAll('.session-item.i');
    let queryString = '';
    let form = document.createElement('form');
    let index = document.body.querySelector('span.page-item.index').innerHTML;

    for (let i = 0; i < allCheck.length; i++) {
        if (allCheck[i].classList.contains('active')) {
            queryString += 'i=' + sessionItem[i].innerHTML + '&';
        }
    }
    queryString = queryString.substr(0, queryString.length - 1);


    form.setAttribute('method', 'post');
    form.setAttribute('action', '/delete?' + queryString + '&page=' + index);
    document.charset = "utf-8";
    document.body.appendChild(form);
    if (confirm('삭제하시겠습까?')) {
        if (queryString.length == 0) {
            alert('입력값이 없습니다.');
            return;
        }
        form.submit();
    }
    return;
}

function clickUpdateHandler() {
    let i = document.body.querySelector('.row-item.i');
    location.href = '/update?i=' + i.lastChild.textContent;
}


function checkTemplateHandler(event) { // check template
    let elem = event.target;

    if (elem.classList.contains('index')) {
        let index = elem.innerHTML;
        if (index != null) {
            location.href = `/check?page=${index}`;
        }
    }

    if (elem.classList.contains('agree')) {
        let allCheck = document.body.querySelectorAll('.object.object-checkbox');
        let sessionItem = document.body.querySelectorAll('.session-item.i');
        let queryString = '';
        let form = document.createElement('form');
        let index = location.search.substr(1);

        for (let i = 0; i < allCheck.length; i++) {
            if (allCheck[i].classList.contains('active')) {
                queryString += 'i=' + sessionItem[i].innerHTML + '&';
            }
        }
        queryString = queryString.substr(0, queryString.length - 1);


        form.setAttribute('method', 'post');
        form.setAttribute('action', '/agree?' + queryString + '&agree_flag=1&' + index);
        document.charset = "utf-8";
        document.body.appendChild(form);
        if (confirm('승인하시겠습까?')) {
            if (queryString.length == 0) {
                alert('입력값이 없습니다.');
                return;
            }
            form.submit();
        }
        return;
    }

    if (elem.classList.contains('disagree')) {
        let allCheck = document.body.querySelectorAll('.object.object-checkbox');
        let sessionItem = document.body.querySelectorAll('.session-item.i');
        let queryString = '';
        let index = location.search.substr(1);

        let form = document.createElement('form');

        for (let i = 0; i < allCheck.length; i++) {
            if (allCheck[i].classList.contains('active')) {
                queryString += 'i=' + sessionItem[i].innerHTML + '&';
            }
        }
        queryString = queryString.substr(0, queryString.length - 1);


        form.setAttribute('method', 'post');
        form.setAttribute('action', '/agree?' + queryString + '&agree_flag=0&' + index);
        document.charset = "utf-8";
        document.body.appendChild(form);
        if (confirm('승인을 취소 하시겠습까?')) {
            if (queryString.length == 0) {
                alert('입력값이 없습니다.');
                return;
            }
            form.submit();
        }
        return;
    }
}

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
            location.reload();
        } else {
            console.error(xhr.responseText);
        }
    };
    xhr.open('GET', url);
    xhr.send(); // 요청 전송
}

function confirm_data(url, title) {
    if (confirm("해당 정보를" + title + "하시겠습니까?")) {
        send_ajax(url);
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
    chk_label();
    editor_label();
});