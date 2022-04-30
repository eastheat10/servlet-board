<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>BOARD ERROR</title>
</head>
<body>
<h1 style="color: red">BOARD ERROR</h1>

<p>
    ${ requestScope.exception }
</p>

<a href="/">HOME</a>

</body>
</html>
