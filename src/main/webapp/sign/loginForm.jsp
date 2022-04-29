<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${lang}"/>
<html>
<head>
    <title>BOARD</title>
</head>
<body>
<fmt:bundle basename="language">

    <h1>LOGIN</h1>

    <form method="POST" action="/login.do">

        <label>
            <fmt:message key="id"/>
            <input type="text" name="id">
        </label>
        <br/><br/>
        <label>
            <fmt:message key="password"/>
            <input type="text" name="password">
        </label>
        <br/><br/>
        <fmt:message key="login" var="login"/>
        <br/>
        <input type="submit" value="${login}">
    </form>

</fmt:bundle>
</body>
</html>
