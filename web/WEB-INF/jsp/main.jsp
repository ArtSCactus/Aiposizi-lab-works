<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <c:set var="pageContent" value="${requestScope.PageContent}"/>
    <%--@elvariable id="foundStudent" type="org.bsuir.dto.Student"--%>
    <c:set var="foundStudent" value="${pageContent.attributes.get('found student')}"/>
</head>
<body>
<a href="controller?command=show_teachers">Show teachers</a>
<a href="controller?command=show_students">Show students</a>
<form method="POST" action="controller">
    <input type="text" name="name" placeholder="Name..." required="required">
    <input type="text" name="surname" placeholder="Surname..." required="required">
    <input type="text" name="rating" placeholder="Rating..." required="required">
    <input type="text" name="groupId" placeholder="Group id..." required="required">
    <input type="hidden" name="command" value="add_student">
    <input type="submit" value="submit">
</form>
<form method="POST" action="controller">
    <input type="text" name="id" placeholder="Id..." required="required">
    <input type="hidden" name="command" value="delete_student">
    <input type="submit" value="delete">
</form>
<form class="find-by-id-form" method="get" action="controller">
    <input type="text" name="id" placeholder="Id..." required="required">
    <input type="hidden" name="command" value="get_student">
    <input class="find-btn" type="submit" value="find">
</form>
<c:choose>
    <c:when test="${empty pageContent.attributes.get('found student')}">
        <h3>No student found</h3>
    </c:when>
    <c:otherwise>
        <form class="edit-from" method="POST" action="controller">
            <input type="hidden" name="command" value="update_student">
            <input type="hidden" name="id" value="${foundStudent.id}">
            <input type="text" name="name" placeholder="Name..." value="${foundStudent.name}">
            <input type="text" name="surname" placeholder="Surname..." value="${foundStudent.surname}">
            <input type="text" name="rating" placeholder="rating..." value="${foundStudent.rating}">
            <input type="text" name="group" placeholder="Group..." value="${foundStudent.group}">
            <input type="submit" value="edit">
        </form>
    </c:otherwise>
</c:choose>
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
