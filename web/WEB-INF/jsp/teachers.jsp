<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.02.2020
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teachers</title>
</head>
<body>
<a href="controller?command=show_teachers">Show teachers</a>
<a href="controller?command=show_students">Show students</a>
<c:if test="${not empty requestScope.PageContent.objectsList}">
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>surname</th>
            <th>rating</th>
            <th>group</th>
        </tr>
            <%--@elvariable id="teacher" type="org.bsuir.dto.Teacher"--%>
        <c:forEach var="teacher" items="${requestScope.PageContent.objectsList}">
            <tr>
                <td>${teacher.id}</td>
                <td>${teacher.name}</td>
                <td>${teacher.surname}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
