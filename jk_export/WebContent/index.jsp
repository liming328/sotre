<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <title>多吃点登录</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

<div class="dwo">
    <div class="panda">
        <div class="ear"></div>
        <div class="face">
            <div class="eye-shade"></div>
            <div class="eye-white">
                <div class="eye-ball"></div>
            </div>
            <div class="eye-shade rgt"></div>
            <div class="eye-white rgt">
                <div class="eye-ball"></div>
            </div>
            <div class="nose"></div>
            <div class="mouth"></div>
        </div>
        <div class="body"> </div>
        <div class="foot">
            <div class="finger"></div>
        </div>
        <div class="foot rgt">
            <div class="finger"></div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/user/login" method="post">
        <div class="hand"></div>
        <div class="hand rgt"></div>
        <h1>用户登录</h1>
        <div class="form-group">
            <input name="username" required="required" class="form-control">
            <label class="form-label">用户名</label>
        </div>
        <div class="form-group">
            <input id="password" name="password" type="password" required="required" class="form-control">
            <label class="form-label">密码</label>
            <p class="alert">验证未通过！</p>
            <button class="btn">登 录</button>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/script.js"></script>

</body>
</html>