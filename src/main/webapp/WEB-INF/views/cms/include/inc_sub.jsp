<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}/cms" />
<script>
    document.addEventListener("DOMContentLoaded",function (){
        <c:forEach items="${menuList}" var="item">
            <c:if test="${item.ref_step == 0}">
            menu_chk('${item.idx}');
            </c:if>
        </c:forEach>
    });

    function menu_chk(menu_idx){
        let xhr = new XMLHttpRequest();
        xhr.onload = function() {
            if (xhr.status === 200 || xhr.status === 201) {
                document.querySelector("#menu").innerHTML += xhr.responseText;
            } else {
                console.error(xhr.responseText);
            }
        };
        xhr.open('GET', '/cms/ajax/admin_config/home?menu='+menu_idx);
        xhr.send(); // 요청 전송
    }
</script>
<nav class="top-nav">
    <div class="fixed-menu">
        <ul class="uk-iconnav uk-iconnav-vertical">
            <li><a href="${path}/calendar/list.do" class=" uk-align-center uk-text-center" uk-icon="icon: home;ratio:1.8"></a></li>
            <li><a href="${path}/bbs_manage" class=" uk-align-center uk-text-center" uk-icon="icon: menu;ratio:1.8"></a></li>
            <li><a href="${path}/admin_config/menu" class=" uk-align-center uk-text-center" uk-icon="icon: cog;ratio:1.8"></a></li>
        </ul>
    </div>
    <div class="menu-wrapper">
        <div class="uk-width-1-1 uk-text-center"><h2  style="color:#fff;">VacationWare</h2></div>
        <div class="uk-width-1-1">
            <ul class="uk-nav-parent uk-text-center" id="menu" uk-nav="multiple: true">
                <c:choose>
                    <c:when test="${menu == 'home'}">
                    </c:when>
                    <c:when test="${menu == 'user'}">
                        <li class="uk-active"><a href="${path}/bbs_manage" class="uk-button uk-button-default" style="color:#fff;opacity:0.8;"><span style="font-size:20px;opacity:0.8;" class="uk-text-bold">게시판관리</span></a></li>
                        <li class="uk-active"><a href="${path}/page_manage" class="uk-button uk-button-default" style="color:#fff;opacity:0.8;"><span style="font-size:20px;opacity:0.8;" class="uk-text-bold">사용자메뉴관리</span></a></li>
                    </c:when>
                    <c:when test="${menu == 'admin_config'}">
                        <li class="uk-active"><a href="${path}/admin_config/menu" class="uk-button uk-button-default" style="color:#fff;opacity:0.8;"><span style="font-size:20px;opacity:0.8;" class="uk-text-bold">관리자게시판관리</span></a></li>
                    </c:when>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>
