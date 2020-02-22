<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teachers</title>
    <c:set var="pageContent" value="${requestScope.PageContent}"/>
    <%--@elvariable id="foundTeacher" type="org.bsuir.dto.Teacher"--%>
    <c:set var="foundTeacher" value="${pageContent.attributes.get('foundTeacher')}"/>
</head>
<body>
<a href="controller?command=show_teachers">Show teachers</a>
<a href="controller?command=show_students">Show students</a>
<form method="POST" action="controller">
    <input type="text" name="name" placeholder="Name..." required="required">
    <input type="text" name="surname" placeholder="Surname..." required="required">
    <input type="hidden" name="command" value="add_teacher">
    <input type="submit" value="submit">
</form>
<form method="POST" action="controller">
    <input type="text" name="id" placeholder="Id..." required="required">
    <input type="hidden" name="command" value="delete_teacher">
    <input type="submit" value="delete">
</form>
<form class="find-by-id-form" method="get" action="controller">
    <input type="text" name="id" placeholder="Id..." required="required">
    <input type="hidden" name="command" value="get_teacher">
    <input class="find-btn" type="submit" value="find">
</form>
<c:choose>
    <c:when test="${empty foundTeacher}">
        <h3>No teacher found</h3>
    </c:when>
    <c:otherwise>
        <form class="edit-from" method="POST" action="controller">
            <input type="hidden" name="command" value="update_teacher">
            <input type="hidden" name="id" value="${foundTeacher.id}">
            <input type="text" name="name" placeholder="Name..." value="${foundTeacher.name}">
            <input type="text" name="surname" placeholder="Surname..." value="${foundTeacher.surname}">
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
