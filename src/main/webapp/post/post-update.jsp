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

    <form method="POST" action="update.do?id=${requestScope.updatePost.id}">
        <label>
            <fmt:message key="title"/><br />
            <input type="text" name="title" style="width: 200px; height: 20px" value="${requestScope.updatePost.title}">
        </label><br /><br />
        <label>
            <fmt:message key="content" var="writeContent"/><br />
            <textArea style="width: 200px; height: 400px" name="content">
                    ${requestScope.updatePost.content}
            </textArea>
        </label><br /><br />
        <label>
            <fmt:message key="update" var="update"/>
            <input type="submit" value="${update}">
        </label>
    </form>
</fmt:bundle>
</body>
</html>
