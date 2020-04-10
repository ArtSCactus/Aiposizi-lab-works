<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ include file="header.jsp"%>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <%--@elvariable id="foundStudent" type="org.bsuir.dto.Student"--%>
    <c:set var="foundStudent" value="${pageContent.attributes.get('foundStudent')}"/>
</head>
<body>
<form method="POST" action="controller">
    <input type="text" name="name" placeholder="Name..." required="required">
    <input type="text" name="surname" placeholder="Surname..." required="required">
    <input type="text" name="rating" placeholder="Rating..." required="required">
    <select>
        <%--@elvariable id="group" type="org.bsuir.dto.Group"--%>
        <c:forEach var="group" items="${requestScope.PageContent.attributes.get('groups')}">
            <option>${group.id}</option>
        </c:forEach>
    </select>
    <%--<input type="text" name="groupId" placeholder="Group id..." required="required">--%>
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
    <c:when test="${empty foundStudent}">
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
<c:if test="${not empty requestScope.PageContent.tableContent}">
    <table>
        <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>rating</th>
        <th>group</th>
        </tr>
            <%--@elvariable id="student" type="org.bsuir.dto.Student"--%>
        <c:forEach var="student" items="${requestScope.PageContent.tableContent}">
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
