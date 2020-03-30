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
    <form id="subject-add-form" class="add-form">
        <input id="subject-add-name-input" type="text" placeholder="Name..." required="required">
        <input id="subject-add-hours-input" type="text" placeholder="Hours..." required="required">
        <input id="subject-add-btn" type="submit" value="submit">
    </form>
    <form id="subject-delete-form" class="delete-form">
        <input id="subject-delete-id-input" type="text" placeholder="Id..." required="required">
        <input id="subject-delete-btn" type="submit" value="delete">
    </form>
    <form id="subject-find-form" class="find-form" method="get" action="controller">
        <input id="subject-find-id-input" type="text" name="id" placeholder="Id..." required="required">
        <input id="subject-find-btn" class="find-btn" type="submit" value="find">
    </form>
            <h3 id="subject-not-found-msg" class="not-found-msg">No subject found</h3>
            <form id="subject-edit-form" class="edit-from">
                <input id="subject-edit-id-input" type="hidden" name="id" value="">
                <input id="subject-edit-name-input" type="text" name="name" placeholder="Name..." value="">
                <input id="subject-edit-hours-input" type="text" name="hours" placeholder="Hours..." value="">
                <input id="subject-edit-btn" type="submit" value="edit">
            </form>
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
            <c:forEach var="subject" items="${pageContent.attributes.get('subjects')}">
                <tr>
                    <td>${subject.id}</td>
                    <td>${subject.name}</td>
                    <td>${subject.hours}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
<div id="teachers-page">
    <form id="add-teacher-from" class="add-form" method="POST" action="controller">
        <input id="teacher-name-input-field" type="text" name="name" placeholder="Name..." required="required">
        <input id="teacher-surname-input-field" type="text" name="surname" placeholder="Surname..." required="required">
        <input id="add-teacher-btn" type="submit" value="Add">
    </form>
    <form id="delete-teacher-form" class="delete-form">
        <input id="delete-teacher-id-input" type="text" placeholder="Id..." required="required">
        <input id="delete-teacher-btn" type="submit" value="Delete">
    </form>
    <form id="find-teacher-form" class="find-form">
        <input id="find-teacher-id-input" type="text" name="id" placeholder="Id..." required="required">
        <input id="find-teacher-btn" class="find-btn" type="submit" value="find">
    </form>
            <h3 id="teacher-not-found-msg" class="not-found-msg">No teacher found</h3>
            <form id="teacher-edit-form" class="edit-from">
                <input id="edit-teacher-id-input" type="hidden" value="">
                <input id="edit-teacher-name-input" type="text" value="" placeholder="Name...">
                <input id="edit-teacher-surname-input" type="text" value="" placeholder="Surname...">
                <input id="edit-teacher-btn" type="submit" value="Edit">
            </form>
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
        <label for="lesson-add-teacher-id-select">
            Teacher id:
            <select id="lesson-add-teacher-id-select" name="teacherId">
                <%--@elvariable id="teacher" type="org.bsuir.dto.Teacher"--%>
                <c:forEach var="teacher" items="${pageContent.attributes.get('teachers')}">
                    <option>${teacher.id}</option>
                </c:forEach>
            </select>
        </label>
        <label for="lesson-add-group-id-select">
            Group id:
            <select id="lesson-add-group-id-select" name="groupId">
                <%--@elvariable id="group" type="org.bsuir.dto.Group"--%>
                <c:forEach var="group" items="${pageContent.attributes.get('groups')}">
                    <option>${group.id}</option>
                </c:forEach>
            </select>
        </label>
        <label for="lesson-add-subject-id-select">
            Subject id:
            <select id="lesson-add-subject-id-select" name="groupId">
                <%--@elvariable id="group" type="org.bsuir.dto.Group"--%>
                <c:forEach var="group" items="${pageContent.attributes.get('subjects')}">
                    <option>${group.id}</option>
                </c:forEach>
            </select>
        </label>
        <input id="lesson-add-btn" type="submit" value="submit">
    </form>
    <form id="delete-lesson-form" class="delete-form">
        <input id="lesson-delete-id-input" type="text" name="id" placeholder="Id..." required="required">
        <input id="lesson-delete-btn" type="submit" value="delete">
    </form>
    <form id="find-lesson-form" class="find-form">
        <input id="lesson-find-id-input" type="text" name="id" placeholder="Id..." required="required">
        <input id="lesson-find-btn" class="find-btn" type="submit" value="">
    </form>
            <h3 id="lesson-not-found-msg" class="not-found-msg">No lesson found</h3>
            <form id="lesson-edit-form" class="edit-from">
                <input id="lesson-edit-id-input" type="hidden" name="id" value="">
                <select id="lesson-edit-teacher-id-select">
                        <%--@elvariable id="teacher" type="org.bsuir.dto.Teacher"--%>
                    <c:forEach var="teacher" items="${pageContent.attributes.get('teachers')}">
                        <option>${teacher.id}</option>
                    </c:forEach>
                </select>
                <select id="lesson-edit-group-select">
                        <%--@elvariable id="group" type="org.bsuir.dto.Group"--%>
                    <c:forEach var="group" items="${pageContent.attributes.get('groups')}">
                        <option>${group.id}</option>
                    </c:forEach>
                </select>
                <input id="lesson-edit-subject-input" type="text" name="subjectId" placeholder="Subject id..." value="">
                <input type="submit" value="edit">
            </form>
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
            <tbody id="lessons-tbody">
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
        <input id="add-student-name-input" type="text" name="name" placeholder="Name..." required="required">
        <input id="add-student-surname-input" type="text" name="surname" placeholder="Surname..." required="required">
        <input id="add-student-rating-input" type="text" name="rating" placeholder="Rating..." required="required">
        <select id="add-student-group-select">
            <%--@elvariable id="group" type="org.bsuir.dto.Group"--%>
            <c:forEach var="group" items="${requestScope.PageContent.attributes.get('groups')}">
                <option>${group.id}</option>
            </c:forEach>
        </select>
        <%--<input type="text" name="groupId" placeholder="Group id..." required="required">--%>
        <input id="add-student-btn" type="submit" value="submit">
    </form>
    <form id="students-delete-form" class="delete-form">
        <input id="delete-student-id-input" type="text" name="id" placeholder="Id..." required="required">
        <input id="delete-student-btn" type="submit" value="delete">
    </form>
    <form id="student-edit-form" class="find-by-id-form">
        <input id="find-student-id-input" type="text" name="id" placeholder="Id..." required="required">
        <input id="find-student-btn" class="find-btn" type="submit" value="find">
    </form>
    <h3 id="student-not-found-msg" class="not-found-msg">No student found</h3>
    <form id="student-edit-form">
                <input id="student-edit-id-input" type="hidden" name="id" value="">
                <input id="student-edit-name-input" type="text" name="name" placeholder="Name..." value="">
                <input id="student-edit-surname-input" type="text" name="surname" placeholder="Surname..." value="">
                <input id="student-edit-rating-input" type="text" name="rating" placeholder="rating..." value="">
        <select id="student-edit-group-select">
            <%--@elvariable id="group" type="org.bsuir.dto.Group"--%>
            <c:forEach var="group" items="${requestScope.PageContent.attributes.get('groups')}">
                <option>${group.id}</option>
            </c:forEach>
        </select>
                <input id="student-edit-btn" type="submit" value="edit">
            </form>
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
