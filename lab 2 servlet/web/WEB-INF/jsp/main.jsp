<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="controller?command=show_students">Hey there</a>
<c:if test="${not empty requestScope.PageContent.objectsList}">
    <table>
        <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>rating</th>
        <th>group</th>
        </tr>
            <%--@elvariable id="student" type="org.bsuir.dto.Student"--%>
        <c:forEach var="student" items="${requestScope.PageContent.objectsList}">
            <tr>
            <td>${student.id}</td>
            <td>${student.name}</td>
            <td>${student.surname}</td>
            <td>${student.rating}</td>
            <td>${student.group}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
