<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${applicationScope.lang}"/>
<jsp:useBean id="postList" scope="request"
             type="java.util.List<com.nhnacademy.servletboard.domain.post.Post>"
             class="java.util.ArrayList"/>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>BOARD</title>
</head>
<body>
<fmt:bundle basename="language">
    <h1>
        <fmt:message key="postList"/>
    </h1>
    <table>

        <thead>
        <tr>
            <th>
                <fmt:message key="number"/>
            </th>
            <th>
                <fmt:message key="title"/>
            </th>
            <th>
                <fmt:message key="writer"/>
            </th>
            <th>
                <fmt:message key="writeDate"/>
            </th>
        </tr>
        </thead>
        <c:forEach var="post" items="${postList}">
            <tr>
                <td>
                        ${post.id}
                </td>

                <td>
                    <a href="post.do?id=${post.id}">
                            ${post.title}
                    </a>
                </td>

                <td>
                        ${post.writerUserId}
                </td>
                <td>
                        ${post.writeTime}
                </td>
            </tr>
        </c:forEach>
    </table>
</fmt:bundle>
</body>
</html>
