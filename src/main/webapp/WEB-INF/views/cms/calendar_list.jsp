<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}/cms/calendar/list" />
<script>
    function list(page){
        location.href = '${path}?curPage='+page
        +'&search_order=${search_order}'
        +'&keyword=${keyword}'
        +'&list_order=${param.list_order}'
        +'&list_scale=${param.list_scale}';
    }

    function search_order(){
        let search_order = document.querySelector("#search_order");
        let order_value = search_order.options[search_order.selectedIndex].value;
        document.querySelector("input[name=search_order]").value = order_value;
    }

    function list_order(){
        let list_order = document.querySelector("#list_order");
        let order_value = list_order.options[list_order.selectedIndex].value;
        location.href = '${path}?curPage=${curPage}'
            +'&search_order=${search_order}'
            +'&keyword=${keyword}'
            +'&list_order='+order_value
            +'&list_scale=${param.list_scale}';
    }

    function list_scale(){
        let list_scale = document.querySelector("#list_scale");
        let scale_value = list_scale.options[list_scale.selectedIndex].value;
        location.href = '${path}?curPage=${curPage}'
            +'&search_order=${search_order}'
            +'&keyword=${keyword}'
            +'&list_order=${param.list_order}'
            +'&list_scale='+scale_value;
    }

    function select_state(state,idx) {
        let url = "/cms/ajax/state?idx="+idx+"&state="+state.value;
        send_ajax(url);
    }

    document.addEventListener("DOMContentLoaded",function(){
        search_order();
    });

    function getVacationCnt(idx) {
        let xhr = new XMLHttpRequest();
        xhr.onload = function () {
            if (xhr.status === 200 || xhr.status === 201) {
                vacation_cnt = xhr.responseText;
                document.querySelector("#vacation_cnt_" + idx).innerHTML += "" + xhr.responseText + " 개";
            } else {
                console.error(xhr.responseText);
            }
        };
        xhr.open('GET', "/cms/ajax/getvacation?idx=" + idx);
        xhr.send(); // 요청 전송
    }

    function getVacationApplyCnt(idx) {
        let xhr = new XMLHttpRequest();
        xhr.onload = function () {
            if (xhr.status === 200 || xhr.status === 201) {
                vacationapply_cnt = xhr.responseText;
                document.querySelector("#vacationapply_cnt_" + idx).innerHTML += "" + xhr.responseText + " 개";
            } else {
                console.error(xhr.responseText);
            }
        };
        xhr.open('GET', "/cms/ajax/getvacationapply?idx=" + idx);
        xhr.send(); // 요청 전송
    }


    function getVacationCalCnt(idx) {
        let xhr = new XMLHttpRequest();
        xhr.onload = function () {
            if (xhr.status === 200 || xhr.status === 201) {
                document.querySelector("#vacationcal_cnt_"+idx).innerHTML += xhr.responseText + " 개";
            } else {
                console.error(xhr.responseText);
            }
        };
        xhr.open('GET', "/cms/ajax/getvacationrest?idx=" + idx);
        xhr.send(); // 요청 전송
    }

</script>
<div class="uk-card uk-card-default uk-flex uk-margin-medium-left uk-margin-medium-right uk-padding-small uk-margin">
    <div class="uk-card uk-card-default uk-padding uk-width-1-4 uk-text-center uk-margin-right">
        <h3 class="uk-margin-remove-top">총 휴가</h3>
        <span>${vacationTotal} 개</span>
    </div>
    <div class="uk-card uk-card-default uk-padding uk-width-1-4 uk-text-center uk-margin-right">
        <h3 class="uk-margin-remove-top">사용일</h3>
        <span>${vacationApplyTotal} 개</span>
    </div>
    <div class="uk-card uk-card-primary uk-padding uk-width-1-2 uk-text-center uk-margin-right">
        <h3 class="uk-margin-remove-top">남은휴가</h3>
        <span>${vacationTotal-vacationApplyTotal < 0 ? "0" : vacationTotal-vacationApplyTotal} 개</span>
    </div>
</div>
<div class="uk-margin-small-top uk-margin-small-bottom uk-margin-medium-left uk-flex">
    <select id="search_order" class="uk-select uk-width-1-6" onchange="search_order()">
        <option value="all" selected>전체검색</option>
        <option value="subject">제목</option>
        <option value="writer">작성자</option>
        <option value="content">내용</option>
    </select>
    <form class="uk-search uk-search-default">
        <input type="hidden" name="search_order">
        <input type="hidden" name="list_order" value="${param.list_order}">
        <input type="hidden" name="list_scale" value="${param.list_scale}">
        <input type="hidden" name="code" value="${mst_bbs.code}">
        <span class="uk-margin-small-left" uk-search-icon></span>
        <input class="uk-search-input uk-margin-small-left" type="search" name="keyword" placeholder="">
    </form>
</div>
<div class="uk-flex uk-width-1-1 uk-flex-between uk-flex-middle uk-margin-small-top uk-margin-small-bottom">
    <div class="uk-margin-medium-left ">
        총 ${listCount}개의 사원이 있습니다.
    </div>
    <div class="uk-text-right uk-flex  uk-width-1-4 uk-margin-medium-right">
        <select class="uk-select" id="list_order" onchange="list_order()">
            <option value="0" ${param.list_order eq 0 ? 'selected' : list_order eq '' ? 'selected' : ''}>최근등록순</option>
            <option value="1" ${param.list_order eq 1 ? 'selected' : ''}>과거등록순</option>
        </select>
        <select class="uk-select uk-margin-small-left" id="list_scale" onchange="list_scale()">
            <option value="10" ${param.list_scale eq '10' ? 'selected' : list_scale eq '' ? 'selected' : '' }>10개식</option>
            <option value="20" ${param.list_scale eq '20' ? 'selected' : ''}>20개식</option>
            <option value="50" ${param.list_scale eq '50' ? 'selected' : ''}>50개식</option>
        </select>
    </div>
</div>
<div class="uk-flex uk-flex-column uk-margin-medium-left uk-margin-medium-right">
    <table class="uk-table uk-table-hover uk-table-divider uk-table-small uk-table-middle">
        <thead>
        <tr>
            <th class="uk-table-small uk-text-center">번호</th>
            <th class="uk-table-small uk-text-center">이름(아이디)</th>
            <th class="uk-table-small uk-text-center">총휴가일</th>
            <th class="uk-table-small uk-text-center">사용일</th>
            <th class="uk-table-small uk-text-center">남은일</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${VacationMemberList}" var="item" varStatus="status">
            <tr>
                <td class="uk-table-small uk-text-center">${item.rn}</td>
                <td class="uk-table-small uk-text-center"><a class="uk-text-bold" href="./detail?idx=${item.mem_idx}">${item.name}(${item.userid})</a></td>
                <td class="uk-table-small uk-text-center" id="vacation_cnt_${item.mem_idx}">
                    <script>
                        getVacationCnt('${item.mem_idx}');
                    </script>
                </td>
                <td class="uk-table-small uk-text-center" id="vacationapply_cnt_${item.mem_idx}">
                    <script>
                        getVacationApplyCnt('${item.mem_idx}');
                    </script>
                </td>
                <td class="uk-table-small uk-text-center" id="vacationcal_cnt_${item.mem_idx}">
                    <script>
                        getVacationCalCnt('${item.mem_idx}');
                    </script>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class=" uk-text-right uk-margin-bottom">
        <a href="./write" class="uk-button uk-button-primary">등록</a>
    </div>
    <ul class="uk-pagination uk-flex-center uk-flex-middle" uk-margin>
        <li><a href="javascript:list('${pager.prevPage}')"><span uk-pagination-previous></span></a></li>
        <c:forEach var="num" begin="${pager.blockBegin}" end="${pager.blockEnd}">
            <li><a href="javascript:list('${num}')">${num}</a></li>
        </c:forEach>
        <li><a href="javascript:list('${pager.nextPage}')"><span uk-pagination-next></span></a></li>
    </ul>
</div>
