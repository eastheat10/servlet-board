<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${applicationScope.lang}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>BOARD</title>
</head>
<body>
<fmt:bundle basename="language">
    <h1>
        <fmt:message key="writePost"/>
    </h1>

    <form method="POST" action="create.do">
        <label>
            <fmt:message key="title"/><br />
            <fmt:message key="writeTitle" var="writeTitle" />
            <input type="text" name="title" style="width: 200px; height: 20px" placeholder="${writeTitle}">
        </label><br /><br />

        <label>
            <fmt:message key="content"/><br />
            <fmt:message key="writeContent" var="writeContent" />
            <textArea style="width: 200px; height: 400px;" name="content" placeholder="${writeContent}"></textArea>
        </label><br /><br />

        <label>
            <fmt:message key="add" var="add"/>
            <input type="submit" value="${add}">
        </label>
    </form>
</fmt:bundle>
</body>
</html>
