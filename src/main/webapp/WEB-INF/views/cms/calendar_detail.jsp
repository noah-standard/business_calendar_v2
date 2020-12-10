<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}/cms/calendar/detail" />
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

</script>
<div class="uk-flex uk-flex-column uk-margin-medium-left uk-margin-medium-right">
    <table class="uk-table uk-table-hover uk-table-divider uk-table-small uk-table-middle">
        <thead>
        <tr>
            <th class="uk-table-small uk-text-center">번호</th>
            <th class="uk-table-shrink uk-text-center">구분</th>
            <th class="uk-table-shrink uk-text-center">이름(아이디)</th>
            <th class="uk-table-expand uk-text-center">내용</th>
            <th class="uk-table-shrink uk-text-center">등록일</th>
            <th class="uk-table-shrink uk-text-center">상태</th>
            <th class="uk-table-shrink uk-text-center">관리</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${calendarMemberList}" var="item" varStatus="status">
                    <tr>
                        <td class="uk-table-small uk-text-center">${item.rn}</td>
                        <td class="uk-table-small uk-text-center">${item.type}</td>
                        <td class="uk-table-small uk-text-center">${item.name}(${item.userid})</td>
                        <td class="uk-table-expand uk-text-center">
                            <a href="./view?idx=${item.idx}" class="uk-text-bold">${item.content}</a>
                        </td>
                        <td class="uk-table-small uk-text-center"><fmt:parseDate var="regDateStr" value="${item.reg_date}" pattern="yyyy-MM-dd HH:mm:ss" /><fmt:formatDate value="${regDateStr}" pattern="yyyy-MM-dd" /></td>
                        <td class="uk-table-small uk-text-center">
                            <select class="uk-select" onchange="select_state(this,${item.idx})">
                                <option value="0" ${item.state == 0 ? "selected" : ""}>승인대기</option>
                                <option value="1" ${item.state == 1 ? "selected" : ""}>승인</option>
                                <option value="2" ${item.state == 2 ? "selected" : ""}>취소</option>
                            </select>
                        </td>
                        <td class="uk-table-small uk-text-center"><a href="./edit?idx=${item.idx}" class="uk-margin-small-left"><span class="uk-icon-button " uk-icon="file-edit"></span></a><a
                                href="javascript:void(0)" onclick="confirm_data('./delete.do?idx=${item.idx}','삭제')" class="uk-margin-small-left"><span class="uk-icon-button" uk-icon="trash"></span></a></td>
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
