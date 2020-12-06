<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}/calendar/list"/>
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
        document.querySelector("input[search_order]").value = order_value;
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
<div class="uk-flex uk-width-1-1 uk-flex-between uk-flex-middle uk-margin-small-top uk-margin-small-bottom">
    <div class="uk-margin-medium-left ">
        총 ${listCount}개의 일정이 있습니다.
        <div class="uk-alert-primary uk-margin-small-top" uk-alert>
            <a class="uk-alert-close" uk-close></a>
            <p>승인전에 취소할 수 있습니다.</p>
        </div>
    </div>

</div>
<div class="uk-flex uk-flex-column uk-margin-medium-left uk-margin-medium-right">
    <table class="uk-table uk-table-hover uk-table-divider uk-table-small uk-table-middle">
        <thead>
        <tr>
            <th class="uk-table-small uk-text-center">번호</th>
            <th class="uk-table-small uk-text-center">구분</th>
            <th class="uk-table-expand uk-text-center">내용</th>
            <th class="uk-table-small uk-text-center">신청일</th>
            <th class="uk-table-small uk-text-center">등록일</th>
            <th class="uk-table-small uk-text-center">상태</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${calendarMemberList}" var="item" varStatus="status">
            <tr>
                <td class="uk-table-small uk-text-center">${item.rn}</td>
                <td class="uk-table-small uk-text-center">${item.type}</td>
                <td class="uk-table-expand uk-text-center">
                    ${item.content}
                </td>
                <td class="uk-table-small uk-text-center"><fmt:parseDate var="startDateStr" value="${item.s_date}" pattern="yyyy-MM-dd HH:mm:ss" /><fmt:formatDate value="${startDateStr}" pattern="yyyy-MM-dd" /></td>
                <td class="uk-table-small uk-text-center"><fmt:parseDate var="regDateStr" value="${item.reg_date}" pattern="yyyy-MM-dd HH:mm:ss" /><fmt:formatDate value="${regDateStr}" pattern="yyyy-MM-dd" /></td>
                <td class="uk-table-small uk-text-center">
                    <c:choose>
                        <c:when test="${item.state eq 0}">
                            <span>승인대기</span><br>
                        </c:when>
                        <c:when test="${item.state eq 1}">
                            <span>승인</span><br>
                        </c:when>
                        <c:when test="${item.state eq 2}">
                            <span>취소</span><br>
                        </c:when>
                    </c:choose>
                    <c:if test="${item.state == 0}">
                        <a class="uk-button uk-button-default" href="javascript:send_ajax('/cms/ajax/state?idx=${item.idx}&state=2')">취소</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <ul class="uk-pagination uk-flex-center uk-flex-middle" uk-margin>
        <li><a href="javascript:list('${pager.prevPage}')"><span uk-pagination-previous></span></a></li>
        <c:forEach var="num" begin="${pager.blockBegin}" end="${pager.blockEnd}">
            <li><a href="javascript:list('${num}')">${num}</a></li>
        </c:forEach>
        <li><a href="javascript:list('${pager.nextPage}')"><span uk-pagination-next></span></a></li>
    </ul>
</div>
