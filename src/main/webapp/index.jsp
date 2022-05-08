<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${applicationScope.lang}"/>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>BOARD</title>
</head>
<body>
<fmt:bundle basename="language">
    <h1>BOARD</h1>

    <div>
        <a href="<c:url value="/change-lang.do?lang=ko" />">
            <fmt:message key="korean"/>
        </a>
        <a href="<c:url value="/change-lang.do?lang=en" />">
            <fmt:message key="english"/>
        </a>
    </div>

    <div>
        <p>
            <fmt:message key="loginCounter" />: ${applicationScope.sessionCount}
        </p>
        <p>
            <fmt:message key="visitor" />: ${applicationScope.counter}
        </p>
    </div>

    <c:choose>
        <c:when test="${sessionScope.user eq null}">
            <a href="login.do">
                <fmt:message key="login"/>
            </a>
        </c:when>
        <c:otherwise>
            <h3>
                <fmt:message key="welcome"/>, ${sessionScope.user.name}
            </h3>
            <a href="logout.do">
                <fmt:message key="logout"/>
            </a>
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${sessionScope.user.id eq 'admin'}">
            <a href="user/profiles.do"> <fmt:message key="userList"/></a>
            <a href="user/profile.do"><fmt:message key="addUser"/></a>
        </c:when>
        <c:otherwise>
            <a href="post/list.do?size=10&page=1">
                <fmt:message key="postList"/>
            </a>
            <a href="post/create.do">
                <fmt:message key="addPost"/>
            </a>
        </c:otherwise>
    </c:choose>
</fmt:bundle>
</body>
</html>
