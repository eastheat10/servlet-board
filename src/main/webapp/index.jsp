<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${lang}"/>
<html>
<head>
    <title>BOARD</title>
</head>
<body>
<fmt:bundle basename="language">
    <h1>BOARD</h1>

    <div>
        <a href="<c:url value="/change-lang.do?lang=ko" />">
            <fmt:message key="korean" />
        </a>
        <a href="<c:url value="/change-lang.do?lang=en" />">
            <fmt:message key="english" />
        </a>
    </div>

    <c:choose>
        <c:when test="${sessionScope.user eq null}">
            <a href="/login.do">
                <fmt:message key="login"/>
            </a>
        </c:when>
        <c:otherwise>
            <h3>
                <fmt:message key="welcome" />, ${sessionScope.user.name}
            </h3>
            <a href="/logout.do">
                <fmt:message key="logout"/>
            </a>
        </c:otherwise>
    </c:choose>
<c:choose>
    <c:when test="${sessionScope.user.id eq 'admin'}">
        사용자 목록
        사용자 추가
    </c:when>
    <c:otherwise>
        게시물 목록
        게시물 등록
    </c:otherwise>
</c:choose>
</fmt:bundle>
</body>
</html>
