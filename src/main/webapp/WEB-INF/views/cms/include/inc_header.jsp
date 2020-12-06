<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}/cms" />
<style>
    /* -- Top navigation -- */
    #top-head {
        position:relative;
        z-index: 9;
        top: 0;
        left:0;
        right:0;
    }
    /* Smaller Header */
    .uk-navbar-nav > li > a,
    .uk-navbar-item,
    .uk-navbar-toggle {
        /* navbar height */
        min-height: 52px;
        padding: 0 8px;
        font-size: 0.85rem;
    }
    .navbar-logo {
        background-color: #222A30;
        margin-left: -12px;
    }
</style>
<header id="top-head" class="uk-position-fixed">
    <div class="uk-container uk-container-expand uk-background-primary">
        <nav class="uk-navbar uk-light " data-uk-navbar="mode:click; duration: 250">
            <div class="uk-navbar-left">
                <ul class="uk-navbar-nav uk-visible@m">
                    <li><a href="#">현재 사용자: <c:out value="${sessionScope.adminUserid}"></c:out></a></li>
                </ul>
            </div>
            <div class="uk-navbar-right">
                <ul class="uk-navbar-nav">
                    <li><a href="${path}/logout.do" data-uk-icon="icon:unlock" title="로그아웃" data-uk-tooltip="" class="uk-icon" aria-expanded="false"></a></li>
                </ul>
            </div>
        </nav>
    </div>
</header>