<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${applicationScope.lang}"/>
<jsp:useBean id="users" scope="request"
             type="java.util.List<com.nhnacademy.servletboard.domain.user.User>"
             class="java.util.ArrayList"/>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Title</title>
</head>
<body>
<fmt:bundle basename="language">
    <h1>
        <fmt:message key="userList"/>
    </h1>
    <table>

        <thead>
        <tr>
            <th>
                <fmt:message key="id"/>
            </th>
            <th>
                <fmt:message key="name"/>
            </th>
        </tr>
        </thead>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>
                        ${user.id}
                </td>

                <td>
                    <a href="user.do?id=${user.id}">
                            ${user.name}
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>

</fmt:bundle>
</body>
</html>
