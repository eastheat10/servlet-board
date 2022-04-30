<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${applicationScope.lang}"/>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>BOARD</title>
</head>
<body>
<fmt:bundle basename="language">
    <h1> PROFILE </h1>
    <p>
        <fmt:message key="id"/>: ${requestScope.findUser.id}
    </p>
    <p>
        <fmt:message key="name"/>: ${requestScope.findUser.name}
    </p>
    <div>
        <fmt:message key="profile"/>
        <img src="img.do?img=${findUser.profileFileName}"
             alt="${requestScope.findUser.id}" style="width: 100px;"/>
    </div>

    <a href="update.do?id=${requestScope.findUser.id}">
        <fmt:message key="updateUser"/>
    </a>
    <a href="delete.do?id=${requestScope.findUser.id}">
        <fmt:message key="removeUser"/>
    </a>


</fmt:bundle>
</body>
</html>
