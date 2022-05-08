<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${applicationScope.lang}"/>
<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <style>
        .page li {
            margin: 3px;
            list-style: none;
        }

        .page a {
            text-decoration-line: none;
            text-decoration: none;
            cursor: default;
        }

        .page a:hover {
            cursor: pointer;
            color: aqua;
        }
    </style>
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
        <c:forEach var="post" items="${postList.getList()}">
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
                    <javatime:format value="${post.getWriteTime()}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
            </tr>
        </c:forEach>
    </table>

    <ul class="page" style="display: flex">
        <c:forEach var="i" begin="1" end="${postList.getTotalPageCount()}" step="1">
            <li>
                <a href="list.do?size=10&page=${i}">
                    <c:choose>
                        <c:when test="${postList.getPageNumber() eq i}">
                            <b>${i}</b>
                        </c:when>
                        <c:otherwise>
                            ${i}
                        </c:otherwise>
                    </c:choose>
                </a>
            </li>
        </c:forEach>
    </ul>
</fmt:bundle>
</body>
</html>
