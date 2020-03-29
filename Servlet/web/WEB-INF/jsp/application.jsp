<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <c:set var="pageContent" value="${requestScope.PageContent}"/>
    <script type="text/javascript" src="script/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="script/applicationBeans.js"></script>
    <script type="text/javascript" src="script/application.js"></script>
    <script type="text/javascript" src="script/ui-scripts.js"></script>
    <link rel="stylesheet" type="text/css" href="css/application.css"/>
</head>
<body>
<button id="teacher-header-btn">Teachers</button>
<button id="student-header-btn">Students</button>
<button id="lesson-header-btn">Lessons</button>
<button id="subject-header-btn">Subjects</button>
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
   <%-- <c:choose>
        <c:when test="${empty foundSubject}">--%>
            <h3 id="subject-not-found-msg" class="not-found-msg">No subject found</h3>
       <%-- </c:when>
        <c:otherwise>--%>
            <form id="edit-subject-form" class="edit-from" method="POST" action="controller">
                <input type="hidden" name="command" value="update_subject">
                <input type="hidden" name="id" value="${foundSubject.id}">
                <input type="text" name="name" placeholder="Name..." value="${foundSubject.name}">
                <input type="text" name="hours" placeholder="Hours..." value="${foundSubject.hours}">
                <input type="submit" value="edit">
            </form>
       <%-- </c:otherwise>
    </c:choose>--%>
    <c:if test="${not empty pageContent.attributes.get('subjects')}">
        <table id="subjects-table">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>hours</th>
            </tr>
            </thead>
            <tbody>
                <%--@elvariable id="subject" type="org.bsuir.dto.Subject--%>
            <c:forEach var="lesson" items="${pageContent.attributes.get('subjects')}">
                <tr>
                    <td>${lesson.id}</td>
                    <td>${lesson.name}</td>
                    <td>${lesson.hours}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<div id="teachers-page">
    <%--<form id="add-teacher-from" class="add-form" method="POST" action="controller">--%>
        <input id="teacher-name-input-field" type="text" name="name" placeholder="Name..." required="required">
        <input id="teacher-surname-input-field" type="text" name="surname" placeholder="Surname..." required="required">
        <button id="add-teacher-btn">Add</button>
    <%--</form>--%>
    <form id="delete-teacher-form" class="delete-form">
        <input id="delete-teacher-id-input" type="text" placeholder="Id..." required="required">
        <input id="delete-teacher-btn" type="submit" value="Delete">
    </form>
    <form id="find-teacher-form" class="find-form">
        <input id="find-teacher-id-input" type="text" name="id" placeholder="Id..." required="required">
        <input id="find-teacher-btn" class="find-btn" type="submit" value="find">
    </form>
    <%--<c:choose>
        <c:when test="${empty foundTeacher}">--%>
            <h3 id="teacher-not-found-msg" class="not-found-msg">No teacher found</h3>
       <%-- </c:when>
        <c:otherwise>--%>
            <form id="edit-teacher-form" class="edit-from">
                <input id="edit-teacher-id-input" type="hidden" value="">
                <input id="edit-teacher-name-input" type="text" value="" placeholder="Name...">
                <input id="edit-teacher-surname-input" type="text" value="" placeholder="Surname...">
                <input id="edit-teacher-btn" type="submit" value="Edit">
            </form>
        <%--</c:otherwise>
    </c:choose>--%>
    <c:if test="${not empty pageContent.attributes.get('teachers')}">
        <table id="teachers-table">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>surname</th>
            </tr>
            </thead>
            <tbody>
                <%--@elvariable id="teacher" type="org.bsuir.dto.Teacher"--%>
            <c:forEach var="teacher" items="${pageContent.attributes.get('teachers')}">
                <tr>
                    <td>${teacher.id}</td>
                    <td>${teacher.name}</td>
                    <td>${teacher.surname}</td>
                </tr>
            </c:forEach>
            </tbody>
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
    <%--<c:choose>
        <c:when test="${pageContent.attributes.get('foundLesson') eq null}">
        </c:when>
        <c:when test="${empty pageContent.attributes.get('foundLesson')}">--%>
            <h3 id="lesson-not-found-msg" clas="not-found-msg">No lesson found</h3>
       <%-- </c:when>
        <c:otherwise>--%>
            <form id="edit-lessons-form" class="edit-from" method="POST" action="controller">
                <input type="hidden" name="command" value="update_lesson">
                <input type="hidden" name="id" value="${pageContent.attributes.get('foundStudent').id}">
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
      <%--  </c:otherwise>
    </c:choose>--%>
    <c:if test="${not empty pageContent.attributes.get('lessons')}">
        <table id="lessons-table">
            <thead>
            <tr>
                <th>id</th>
                <th>teacher id</th>
                <th>group id</th>
                <th>subject</th>
            </tr>
            </thead>
            <tbody>
                <%--@elvariable id="lesson" type="org.bsuir.dto.Lesson--%>
            <c:forEach var="lesson" items="${pageContent.attributes.get('lessons')}">
                <tr>
                    <td>${lesson.id}</td>
                    <td>${lesson.teacherId}</td>
                    <td>${lesson.groupId}</td>
                    <td>${lesson.subjectId}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<div id="students-page">
    <form id="add-student-form" class="add-form" method="POST" action="controller">
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
    <form id="students-delete-form" class="delete-form" method="POST" action="controller">
        <input type="text" name="id" placeholder="Id..." required="required">
        <input type="hidden" name="command" value="delete_student">
        <input type="submit" value="delete">
    </form>
    <form id=students-find-form class="find-by-id-form" method="get" action="controller">
        <input type="text" name="id" placeholder="Id..." required="required">
        <input type="hidden" name="command" value="get_student">
        <input class="find-btn" type="submit" value="find">
    </form>
    <%--<c:choose>
        <c:when test="${empty foundStudent}">
        </c:when>
        <c:otherwise>--%>
    <h3 id="student-not-found-msg" class="not-found-msg">No student found</h3>
    <form id="students-edit-form" class="edit-from" method="POST" action="controller">
                <input type="hidden" name="command" value="update_student">
                <input type="hidden" name="id" value="${foundStudent.id}">
                <input type="text" name="name" placeholder="Name..." value="${foundStudent.name}">
                <input type="text" name="surname" placeholder="Surname..." value="${foundStudent.surname}">
                <input type="text" name="rating" placeholder="rating..." value="${foundStudent.rating}">
                <input type="text" name="group" placeholder="Group..." value="${foundStudent.group}">
                <input type="submit" value="edit">
            </form>
      <%--  </c:otherwise>
    </c:choose>--%>
    <c:if test="${not empty requestScope.PageContent.attributes.get('students')}">
        <table id="students-table">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>surname</th>
                <th>rating</th>
                <th>group</th>
            </tr>
            </thead>
            <tbody>
                <%--@elvariable id="student" type="org.bsuir.dto.Student"--%>
            <c:forEach var="student" items="${requestScope.PageContent.attributes.get('students')}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.surname}</td>
                    <td>${student.rating}</td>
                    <td>${student.group}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
