<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Lessos</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <%--@elvariable id="foundLesson" type="org.bsuir.dto.Lesson"--%>
    <c:set var="foundLesson" value="${pageContent.attributes.get('foundLesson')}"/>
</head>
<body>
<form id="add-lesson-form" class="add-form" method="POST" action="controller">
    <input type="text" name="teacherId" placeholder="Teacher id..." required="required">
    <input type="text" name="groupId" placeholder="Group id..." required="required">
    <input type="text" name="subjectId" placeholder="Subject id..." required>
    <input type="hidden" name="command" value="add_lesson">
    <input type="submit" value="submit">
</form>
<form id="delete-lesson-form" class="delete-form" method="POST" action="controller">
    <input type="text" name="id" placeholder="Id..." required="required">
    <input type="hidden" name="command" value="delete_lesson">
    <input type="submit" value="delete">
</form>
<form id="find-lesson-form" class="find-form" method="get" action="controller">
    <input type="text" name="id" placeholder="Id..." required="required">
    <input type="hidden" name="command" value="get_lesson">
    <input class="find-btn" type="submit" value="find">
</form>
<c:choose>
    <c:when test="${foundLesson eq null}">
    </c:when>
    <c:when test="${empty foundLesson}">
        <h3>No lesson found</h3>
    </c:when>
    <c:otherwise>
        <form id="edit-lessons-form" class="edit-from" method="POST" action="controller">
            <input type="hidden" name="command" value="update_lesson">
            <input type="hidden" name="id" value="${foundLesson.id}">
            <input type="text" name="teacherId" placeholder="Teacher id..." value="${foundLesson.teacherId}">
            <input type="text" name="groupId" placeholder="Group id..." value="${foundLesson.groupId}">
            <input type="text" name="subjectId" placeholder="Subject id..." value="${foundLesson.subjectId}">
            <input type="submit" value="edit">
        </form>
    </c:otherwise>
</c:choose>
<c:if test="${not empty pageContent.tableContent}">
    <table>
        <tr>
            <th>id</th>
            <th>teacher id</th>
            <th>group id</th>
            <th>subject</th>
        </tr>
            <%--@elvariable id="lesson" type="org.bsuir.dto.Lesson--%>
        <c:forEach var="subject" items="${pageContent.tableContent}">
            <tr>
                <td>${subject.id}</td>
                <td>${subject.teacherId}</td>
                <td>${subject.groupId}</td>
                <td>${subject.subjectId}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
