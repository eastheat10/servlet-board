<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <title>BOARD</title>
</head>
<body>
<fmt:bundle basename="language">
    <h1>PROFILE</h1>

    <form method="POST" action="profile.do" enctype="multipart/form-data">

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

        <label>
            <fmt:message key="name"/>
            <input type="text" name="name">
        </label>
        <br/><br/>

        <label>
            <input type="file" name="profile" />
        </label>
        <br/><br/>

        <fmt:message key="add" var="add"/>
        <input type="submit" value="${add}">
    </form>

</fmt:bundle>
</body>
</html>
