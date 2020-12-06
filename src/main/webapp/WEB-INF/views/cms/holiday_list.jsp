<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}/cms/holiday/list.do" />
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
    document.addEventListener("DOMContentLoaded",function(){
        search_order();
    });
</script>
<div class="uk-flex uk-width-1-1 uk-flex-between uk-flex-middle uk-margin-small-top uk-margin-small-bottom">
    <div class="uk-margin-medium-left ">
    총 ${listCount}개의 휴일이 있습니다.
    </div>
</div>
<div class="uk-flex uk-flex-column uk-margin-medium-left uk-margin-medium-right">
    <table class="uk-table uk-table-hover uk-table-divider uk-table-small uk-table-middle">
        <thead>
        <tr>
            <th class="uk-width-small uk-text-center">번호</th>
            <th class="uk-table-expand uk-text-center">휴일</th>
            <th class="uk-table-shrink uk-text-center">등록일</th>
            <th class="uk-table-shrink uk-text-center">관리</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${holidayList}" var="item" varStatus="status">
            <tr>
                <td>${listCount - status.index}</td>
                <td class="uk-text-bold">${item.locdate_name} (<fmt:parseDate var="locDateStr" value="${item.locdate}" pattern="yyyy-MM-dd" /><fmt:formatDate value="${locDateStr}" pattern="yyyy-MM-dd" />)</td>
                <td class="uk-width-small uk-text-center"><fmt:parseDate var="regDateStr" value="${item.reg_date}" pattern="yyyy-MM-dd HH:mm:ss" /><fmt:formatDate value="${regDateStr}" pattern="yyyy-MM-dd" /></td>
                <td class="uk-width-small uk-text-center"><a href="./edit.do?idx=${item.idx}" class="uk-margin-small-left"><span class="uk-icon-button " uk-icon="file-edit"></span></a><a
                        href="javascript:void(0)" onclick="confirm_data('./delete.do?idx=${item.idx}','삭제')" class="uk-margin-small-left"><span class="uk-icon-button" uk-icon="trash"></span></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class=" uk-text-right uk-margin-bottom">
        <a href="./write.do" class="uk-button uk-button-primary">등록</a>
    </div>
</div>
