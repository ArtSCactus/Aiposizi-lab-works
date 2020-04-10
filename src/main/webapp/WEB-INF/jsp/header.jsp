<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:set var="pageContent" value="${requestScope.PageContent}"/>
    <link rel="stylesheet" type="text/css" href="../../css/style.css" />
</head>
<body>
<a href="controller?command=show_teachers">Teachers</a>
<a href="controller?command=show_students">Students</a>
<a href="controller?command=show_lessons">Lessons</a>
<a href="controller?command=show_subjects">Subjects</a>
</body>
</html>
