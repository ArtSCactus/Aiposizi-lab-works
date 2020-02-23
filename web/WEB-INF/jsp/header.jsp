<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:set var="pageContent" value="${requestScope.PageContent}"/>
</head>
<body>
<a href="controller?command=show_teachers">Show teachers</a>
<a href="controller?command=show_students">Show students</a>
<a href="controller?command=show_lessons">Show lessons</a>
</body>
</html>
