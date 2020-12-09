<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="./include/inc_head.jsp" %>
</head>
<body>
<nav class="top-nav">
    <div class="fixed-menu">
        <ul class="uk-iconnav uk-iconnav-vertical">
            <li><a href="javascript:void(0)" class=" uk-align-center uk-text-center" uk-icon="icon: home;ratio:1.8"></a></li>
            <li><a href="javascript:void(0)" class=" uk-align-center uk-text-center" uk-icon="icon: menu;ratio:1.8"></a></li>
            <li><a href="javascript:void(0)" class=" uk-align-center uk-text-center" uk-icon="icon: cog;ratio:1.8"></a></li>
        </ul>
    </div>
</nav>
<section class="top-banner" style="transform: translateX(60px);width: calc(100% - 60px);">
    <div class="uk-position-large">
    <h2 class="logo">VacationWare</h2>
    </div>
    <div class="uk-margin-auto uk-width-1-2" style="margin:auto 0;">
    <form method="post">
        <fieldset class="uk-fieldset">

            <legend class="uk-legend">로그인</legend>

            <div class="uk-margin">
                <input class="uk-input" type="text" name="userid" placeholder="userid">
            </div>
            <div class="uk-margin">
                <input class="uk-input" type="password" name="password" placeholder="password">
            </div>

        </fieldset>
        <p class="uk-text-right" uk-margin>
            <button class="uk-button uk-button-primary">로그인</button>
        </p>
    </form>
    </div>
</section>
</body>
</html>
