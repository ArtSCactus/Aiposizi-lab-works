<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<html>
<head>
    <title>Teachers</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <%--@elvariable id="foundTeacher" type="org.bsuir.dto.Teacher"--%>
    <c:set var="foundTeacher" value="${pageContent.attributes.get('foundTeacher')}"/>
</head>
<body>
<form id="add-teacher-from" class="add-form" method="POST" action="controller">
    <input type="text" name="name" placeholder="Name..." required="required">
    <input type="text" name="surname" placeholder="Surname..." required="required">
    <input type="hidden" name="command" value="add_teacher">
    <input type="submit" value="submit">
</form>
<form id="delete-teacher-form" class="delete-form" method="POST" action="controller">
    <input type="text" name="id" placeholder="Id..." required="required">
    <input type="hidden" name="command" value="delete_teacher">
    <input type="submit" value="delete">
</form>
<form id="find-teacher-form" class="find-form" method="get" action="controller">
    <input type="text" name="id" placeholder="Id..." required="required">
    <input type="hidden" name="command" value="get_teacher">
    <input class="find-btn" type="submit" value="find">
</form>
<c:choose>
    <c:when test="${empty foundTeacher}">
        <h3>No teacher found</h3>
    </c:when>
    <c:otherwise>
        <form id="teacher-edit-form" class="edit-from" method="POST" action="controller">
            <input type="hidden" name="command" value="update_teacher">
            <input type="hidden" name="id" value="${foundTeacher.id}">
            <input type="text" name="name" placeholder="Name..." value="${foundTeacher.name}">
            <input type="text" name="surname" placeholder="Surname..." value="${foundTeacher.surname}">
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
        </tr>
            <%--@elvariable id="teacher" type="org.bsuir.dto.Teacher"--%>
        <c:forEach var="teacher" items="${requestScope.PageContent.tableContent}">
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
