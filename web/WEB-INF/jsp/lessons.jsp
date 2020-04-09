<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Lessons</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <%--@elvariable id="foundLesson" type="org.bsuir.dto.Lesson"--%>
    <c:set var="foundLesson" value="${pageContent.attributes.get('foundLesson')}"/>
</head>
<body>
<form id="add-lesson-form" class="add-form" method="POST" action="controller">
    <label for="teacher-id-select">
        Teacher id:
        <select id="teacher-id-select" name="teacherId">
            <%--@elvariable id="teacher" type="org.bsuir.dto.Teacher"--%>
            <c:forEach var="teacher" items="${pageContent.attributes.get('teachers')}">
                <option>${teacher.id}</option>
            </c:forEach>
        </select>
    </label>
    <label for="group-id-select">
        Group id:
        <select id="group-id-select" name="groupId">
            <%--@elvariable id="group" type="org.bsuir.dto.Group"--%>
            <c:forEach var="group" items="${pageContent.attributes.get('groups')}">
                <option>${group.id}</option>
            </c:forEach>
        </select>
    </label>
    <label for="subject-id-select">
        Subject id:
        <select id="subject-id-select" name="groupId">
            <%--@elvariable id="group" type="org.bsuir.dto.Group"--%>
            <c:forEach var="group" items="${pageContent.attributes.get('subjects')}">
                <option>${group.id}</option>
            </c:forEach>
        </select>
    </label>
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
            <select>
                    <%--@elvariable id="teacher" type="org.bsuir.dto.Teacher"--%>
                <c:forEach var="teacher" items="${pageContent.attributes.get('teachers')}">
                    <option>${teacher.id}</option>
                </c:forEach>
            </select>
            <select>
                    <%--@elvariable id="group" type="org.bsuir.dto.Group"--%>
                <c:forEach var="group" items="${pageContent.attributes.get('groups')}">
                    <option>${group.id}</option>
                </c:forEach>
            </select>
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
        <c:forEach var="lesson" items="${pageContent.tableContent}">
            <tr>
                <td>${lesson.id}</td>
                <td>${lesson.teacherId}</td>
                <td>${lesson.groupId}</td>
                <td>${lesson.subjectId}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
