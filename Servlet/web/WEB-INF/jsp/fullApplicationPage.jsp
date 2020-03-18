<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 18.03.2020
  Time: 19:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="subjects-page">
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
    <c:if test="${not empty pageContent.attributes.get('subjects')}">
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>hours</th>
            </tr>
                <%--@elvariable id="subject" type="org.bsuir.dto.Subject--%>
            <c:forEach var="lesson" items="${pageContent.attributes.get('subjects')}">
                <tr>
                    <td>${lesson.id}</td>
                    <td>${lesson.name}</td>
                    <td>${lesson.hours}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<div id="teachers-page">
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
    <form id="find-subject-form" class="find-form" method="get" action="controller">
        <input type="text" name="id" placeholder="Id..." required="required">
        <input type="hidden" name="command" value="get_teacher">
        <input class="find-btn" type="submit" value="find">
    </form>
    <c:choose>
        <c:when test="${empty foundTeacher}">
            <h3>No teacher found</h3>
        </c:when>
        <c:otherwise>
            <form id="edit-teacher-form" class="edit-from" method="POST" action="controller">
                <input type="hidden" name="command" value="update_teacher">
                <input type="hidden" name="id" value="${foundTeacher.id}">
                <input type="text" name="name" placeholder="Name..." value="${foundTeacher.name}">
                <input type="text" name="surname" placeholder="Surname..." value="${foundTeacher.surname}">
                <input type="submit" value="edit">
            </form>
        </c:otherwise>
    </c:choose>
    <c:if test="${not empty pageContent.attributes.get('teachers')}">
        <table>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>surname</th>
            </tr>
                <%--@elvariable id="teacher" type="org.bsuir.dto.Teacher"--%>
            <c:forEach var="teacher" items="${pageContent.attributes.get('teachers')}">
                <tr>
                    <td>${teacher.id}</td>
                    <td>${teacher.name}</td>
                    <td>${teacher.surname}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
<div id="lessons-page">
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
    <c:if test="${not empty pageContent.attributes.get('lessons')}">
        <table>
            <tr>
                <th>id</th>
                <th>teacher id</th>
                <th>group id</th>
                <th>subject</th>
            </tr>
                <%--@elvariable id="lesson" type="org.bsuir.dto.Lesson--%>
            <c:forEach var="lesson" items="${pageContent.attributes.get('lessons')}">
                <tr>
                    <td>${lesson.id}</td>
                    <td>${lesson.teacherId}</td>
                    <td>${lesson.groupId}</td>
                    <td>${lesson.subjectId}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
