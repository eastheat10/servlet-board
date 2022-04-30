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
    <h2>
            ${requestScope.findPost.title}
    </h2>
    <p>
        <fmt:message key="writeDate"/>: ${requestScope.findPost.writeTime}
    </p>
    <p>
        <fmt:message key="hits"/>: ${requestScope.findPost.viewCount}
    </p>
    <p>
        <fmt:message key="writer"/>: ${requestScope.findPost.writerUserId}
    </p>
    <div>
            ${requestScope.findPost.content}
    </div>
    <c:if test="${sessionScope.user.id eq requestScope.findPost.writerUserId}">
        <a href="update.do?id=${requestScope.findPost.id}">
            <fmt:message key="updatePost"/>
        </a>
        <a href="delete.do?id=${requestScope.findPost.id}">
            <fmt:message key="removePost"/>
        </a>
    </c:if>

</fmt:bundle>
</body>
</html>
