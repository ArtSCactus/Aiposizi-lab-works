<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<html>
<head>
    <title>Subjects</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <%--@elvariable id="foundSubject" type="org.bsuir.dto.Subject"--%>
    <c:set var="foundSubject" value="${pageContent.attributes.get('foundSubject')}"/>

</head>
<body>
<form id="add-subject-form" class="add-form" method="POST" action="controller">
    <input type="text" name="name" placeholder="Name..." required="required">
    <input type="text" name="hours" placeholder="Hours..." required="required">
    <input type="hidden" name="command" value="add_subject">
    <input type="submit" value="submit">
</form>
<form id="delete-subject-form" class="delete-form" method="POST" action="controller">
    <input type="text" name="id" placeholder="Id..." required="required">
    <input type="hidden" name="command" value="delete_subject">
    <input type="submit" value="delete">
</form>
<form id="find-subject-form" class="find-form" method="get" action="controller">
    <input type="text" name="id" placeholder="Id..." required="required">
    <input type="hidden" name="command" value="get_subject">
    <input class="find-btn" type="submit" value="find">
</form>
<c:choose>
    <c:when test="${empty foundSubject}">
        <h3>No subject found</h3>
    </c:when>
    <c:otherwise>
        <form id="edit-subject-form" class="edit-from" method="POST" action="controller">
            <input type="hidden" name="command" value="update_subject">
            <input type="hidden" name="id" value="${foundSubject.id}">
            <input type="text" name="name" placeholder="Name..." value="${foundSubject.name}">
            <input type="text" name="hours" placeholder="Hours..." value="${foundSubject.hours}">
            <input type="submit" value="edit">
        </form>
    </c:otherwise>
</c:choose>
<c:if test="${not empty pageContent.tableContent}">
    <table>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>hours</th>
        </tr>
            <%--@elvariable id="subject" type="org.bsuir.dto.Subject--%>
        <c:forEach var="lesson" items="${pageContent.tableContent}">
            <tr>
                <td>${lesson.id}</td>
                <td>${lesson.name}</td>
                <td>${lesson.hours}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
