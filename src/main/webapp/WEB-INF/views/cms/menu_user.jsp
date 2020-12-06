<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="uk-heading-divider uk-padding-small">사용자메뉴관리</h3>
<div class="uk-flex uk-margin-medium-left uk-margin-medium-right">
    <div class="uk-panel uk-panel-scrollable uk-width-1-2 uk-padding" style="height:500px;">
        <p class="uk-text-right">
            <button class="uk-button uk-button-danger" id="delete" type="button">삭제</button>
            <a href="/cms/page_manage"><button class="uk-button uk-button-primary" type="button">메뉴등록</button></a>
        </p>
        <div id="jstree_div">
            <li>테스트</li>
        </div>
    </div>
    <form class="uk-width-1-2 uk-padding uk-form-horizontal" name="user_menu_form" method="post">
        <fieldset class="uk-fieldset">
            <input type="hidden" name="idx" id="idx">
            <div class="uk-margin">
                <label for="cate">메뉴분류</label>
                <select id="cate" name="cate" class="uk-select">
                    <option value="0">-대분류-</option>
                    <c:forEach items="${menu_list}" var="item">
                        <c:choose>
                            <c:when test="${item.ref_step == 0}">
                                <option value="${item.idx}">${item.name}</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <div class="uk-margin">
                <label for="name">메뉴명</label>
                <input type="text" id="name" name="name" class="uk-input">
            </div>
            <div class="uk-margin">
                <label for="link">메뉴링크</label>
                <input type="text" id="link" name="link" class="uk-input">
            </div>
            <div class="uk-margin">
                <label for="menu0">사용</label>
                <input type="radio" class="uk-radio" id="menu0" name="state" value="0" checked>
                <label for="menu1">미사용</label>
                <input type="radio" class="uk-radio" id="menu1" name="state" value="1">
            </div>
        </fieldset>
        <p class="uk-text-right">
            <button class="uk-button uk-button-primary" id="reg" type="submit">등록</button>
            <button class="uk-button uk-button-primary" id="edit" type="button" style="display: none">수정</button>
        </p>
    </form>
</div>
<link rel="stylesheet" href="/static/resources/addon/vakata-jstree/dist/themes/default/style.css">
<script src="/static/resources/js/cms/jquery.min.3.5.1.js"></script>
<script src="/static/resources/addon/vakata-jstree/dist/jstree.js"></script>
<script>

    $(function () {
        document.querySelector("#edit").addEventListener("click",function(e){
            if(confirm('수정하시겠습니까?')){
                let form = document.forms.user_menu_form;
                form.action = "/cms/page_manage/ajax";
                form.submit();
            }
        });

        $('#jstree_div').jstree(
            {
                "core": {
                    "check_callback": (operation, node, node_parent, node_position, more) => {
                        if (operation === "move_node") {
                            if(more.ref == undefined){
                                if(confirm('위치를 바꾸시겠습니까?')){
                                    let xhr = new XMLHttpRequest();
                                    let node_array = new Array();
                                    let jquery_arr =$('#jstree_div').jstree("get_json");
                                    for(let i = 0 ; i< jquery_arr.length; i++){
                                        node_array[i] = jquery_arr[i].id;
                                    }

                                    node_array.splice(node_array.indexOf(node.id),1);
                                    node_array.splice(node_position,0,node.id);

                                    console.log(node_array);
                                    let depth1_data = node_array;
                                    let data = {
                                        node: node.id,
                                        node_parent: node_parent.id,
                                        node_position : ""+node_position,
                                        nodes_depth1 : depth1_data
                                    };

                                    xhr.onload = function() {
                                        if (xhr.status === 200 || xhr.status === 201) {
                                            // console.log(xhr.responseText);
                                        } else {
                                            console.error(xhr.responseText);
                                        }
                                    };
                                    xhr.open('POST', '/cms/menu/edit');
                                    xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8'); // 컨텐츠타입을 json으로
                                    xhr.send(JSON.stringify(data)); // 데이터를 stringify해서 보냄
                                }
                            }
                            return true;
                        }
                        return false;
                    },
                    'data': [
                        <c:forEach items="${menu_list}" var="item">
                        <c:if test="${item.ref_step == 0}">
                        {"id": "${item.idx}", "parent": "#", "text": "${item.name}"},
                        </c:if>
                        <c:if test="${item.ref_step != 0}">
                        {"id": "${item.idx}", "parent": "${item.ref}", "text": "${item.name}"},
                        </c:if>
                        </c:forEach>
                    ]
                },
                "plugins": ["dnd"]
            }
        );



        $("#jstree_div").bind('select_node.jstree', function(event, data){
            let id = data.instance.get_node(data.selected).id;        //id 가져오기
            let xhr = new XMLHttpRequest();
            xhr.onload = function() {
                if (xhr.status === 200 || xhr.status === 201) {
                    let menu_json = JSON.parse(xhr.responseText);
                    document.querySelector("#name").value = menu_json.name;
                    document.querySelector("#link").value = menu_json.link;
                    document.querySelector("#idx").value = menu_json.idx;
                    let menu_select = document.querySelectorAll("#cate option");
                    for(let i = 0; i < menu_select.length ; i++){
                        if(menu_select[i].value == menu_json.ref) {
                            menu_select[i].selected = true;
                        }
                    }
                    let menu_state = document.querySelectorAll("input[name=state]");
                    for(let j = 0; j < menu_state.length ; j++){
                        if(menu_state[j].value == menu_json.state) {
                            menu_state[j].checked = true;
                        }
                    }

                    document.querySelector("#reg").style.display = "none";
                    document.querySelector("#edit").style.display = "inline-block";
                } else {
                    console.error(xhr.responseText);
                }
            };
            xhr.open('GET', '/cms/page_manage/ajax?node='+id);
            xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8'); // 컨텐츠타입을 json으로
            xhr.send(); // 요청 전송
        })

        document.querySelector("#delete").addEventListener("click",function (){
            if(confirm('해당 메뉴를 삭제하시겠습니까?')){
                let idx = document.querySelector("#idx").value;
                if(!idx){
                    alert('선택하신 메뉴가 없습니다.');
                    return false;
                }
                let xhr = new XMLHttpRequest();
                xhr.onload = function() {
                    if (xhr.status === 200 || xhr.status === 201) {
                        alert('삭제했습니다');
                        location.reload();
                    } else {
                        console.error(xhr.responseText);
                    }
                };
                xhr.open('GET', '/cms/page_manage/ajax/delete.do?idx='+idx);
                xhr.setRequestHeader('Content-Type', 'application/json;charset=UTF-8'); // 컨텐츠타입을 json으로
                xhr.send(); // 요청 전송
            }
        });
    });
</script>