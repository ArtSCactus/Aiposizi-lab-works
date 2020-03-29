function appendToStudentTable(studentBean) {
    let tbody = document.getElementById('students-table').getElementsByTagName("tbody")[0];
    let row = document.createElement('tr');
    let id_col = document.createElement('td');
    id_col.appendChild(document.createTextNode(studentBean.id));
    let name_col = document.createElement('td');
    name_col.appendChild(document.createTextNode(studentBean.surname));
    let surname_col = document.createElement('td');
    surname_col.appendChild(document.createTextNode(studentBean.surname));
    let rating_col = document.createElement('td');
    rating_col.appendChild(document.createTextNode(studentBean.rating));
    let group_col = document.createElement('td');
    group_col.appendChild(document.createTextNode(studentBean.group));
    row.appendChild(id_col);
    row.appendChild(name_col);
    row.appendChild(surname_col);
    row.appendChild(rating_col);
    row.appendChild(group_col);
    tbody.appendChild(row);
}


function appendToLessonsTable(lessonBean) {
    let tbody = document.getElementById('lessons-table').getElementsByTagName("tbody")[0];
    let row = document.createElement('tr');
    let id_col = document.createElement('td');
    id_col.appendChild(document.createTextNode(lessonBean.id));
    let group_id_col = document.createElement('td');
    group_id_col.appendChild(document.createTextNode(lessonBean.groupId));
    let subject_id_col = document.createElement('td');
    subject_id_col.appendChild(document.createTextNode(lessonBean.subjectId));
    let teacher_id_col = document.createElement('td');
    teacher_id_col.appendChild(document.createTextNode(lessonBean.teacherId));
    row.appendChild(id_col);
    row.appendChild(group_id_col);
    row.appendChild(subject_id_col);
    row.appendChild(teacher_id_col);
    tbody.appendChild(row);
}

function appendToSubjectsTable(subjectBean) {
    let tbody = document.getElementById('subjects-table').getElementsByTagName("tbody")[0];
    let row = document.createElement('tr');
    let id_col = document.createElement('td');
    id_col.appendChild(document.createTextNode(subjectBean.id));
    let name_col = document.createElement('td');
    name_col.appendChild(document.createTextNode(subjectBean.name));
    let hours_col = document.createElement('td');
    hours_col.appendChild(document.createTextNode(subjectBean.hours));
    row.appendChild(id_col);
    row.appendChild(name_col);
    row.appendChild(hours_col);
    tbody.appendChild(row);
}

function appendToTeacherTable(teacherBean) {
    let tbody = document.getElementById('teachers-table').getElementsByTagName("tbody")[0];
    let row = document.createElement('tr');
    let id_col = document.createElement('td');
    id_col.appendChild(document.createTextNode(teacherBean.id));
    let name_col = document.createElement('td');
    name_col.appendChild(document.createTextNode(teacherBean.name));
    let surname_col = document.createElement('td');
    surname_col.appendChild(document.createTextNode(teacherBean.surname));
    row.appendChild(id_col);
    row.appendChild(name_col);
    row.appendChild(surname_col);
    tbody.appendChild(row);
}

function clearTable(tableId) {
    let old_body = document.getElementById(tableId).getElementsByTagName('tbody')[0];
    let new_body = document.createElement('tbody');
    old_body.parentNode.replaceChild(new_body, old_body);
}

function updateTeacherTable() {
    clearTable('teachers-table');
    var manager = new DataManager();
    var teacherArray = manager.loadAllTeachers();
    for (const teacherObj of teacherArray) {
        appendToTeacherTable(teacherObj);
    }
    console.log('Teacher table successfully updated');
}

function updateStudentTable() {
    clearTable('students-table');
    var manager = new DataManager();
    var studentArray = manager.loadAllStudents();
    for (const studentObj of studentArray) {
        appendToTeacherTable(studentObj);
    }
    console.log('Student table successfully updated');
}

function updateSubjectTable() {
    clearTable('subjects-table');
    var manager = new DataManager();
    var array = manager.loadAllSubjects();
    for (const item of array) {
        appendToTeacherTable(item);
    }
    console.log('Subject table successfully updated');
}

function updateLessonTable() {
    clearTable('lessons-table');
    var manager = new DataManager();
    var array = manager.loadAllLessons();
    for (const item of array) {
        appendToTeacherTable(item);
    }
    console.log('Lesson table successfully updated');
}

$(document).on("click", "#teacher-header-btn", function () {
    updateTeacherTable();
    showTeachersPage();
});

$(document).on("click", "#subject-header-btn", function () {
    updateSubjectTable();
    showSubjectsPage();
});

$(document).on("click", "#lesson-header-btn", function () {
    updateLessonTable();
    showLessonsPage();
});

$(document).on("click", "#student-header-btn", function () {
    updateStudentTable();
    showStudentsPage();
});

$(document).on("click", '#add-teacher-btn', function () {
    let name = document.getElementById('teacher-name-input-field').value;
    let surname = document.getElementById('teacher-surname-input-field').value;
    let bean = new TeacherBean(null, name, surname);
    let data = new DataManager();
    data.saveTeacher(bean);
    updateTeacherTable()
});

$(document).on("click", '#find-teacher-btn', function (event) {
    event.preventDefault();
let id = $('#find-teacher-id-input').val();
let dataManager = new DataManager();
let foundTeacher = dataManager.loadTeacherById(id);
if (foundTeacher !== null){
    $('#edit-teacher-id-input').val(foundTeacher.id);
    $('#edit-teacher-name-input').val(foundTeacher.name);
    $('#edit-teacher-surname-input').val(foundTeacher.surname);
    $('#teacher-not-found-msg').hide();
    $('#edit-teacher-form').show();
} else {
    $('#edit-teacher-form').hide();
    $('#teacher-not-found-msg').show();
}
});

$(document).on("click", '#edit-teacher-btn', function (event) {
event.preventDefault();
let dataManager = new DataManager();
let obj = new TeacherBean($('#edit-teacher-id-input').val(),
    $('#edit-teacher-name-input').val(),
    $('#edit-teacher-surname-input').val());
dataManager.saveTeacher(obj);
updateTeacherTable();
//TODO: Notification about success or fail
});

$(document).on("click", '#delete-teacher-btn', function (event) {
event.preventDefault();
    let dataManager = new DataManager();
    dataManager.deleteTeacher($('#delete-teacher-id-input').val());
    updateTeacherTable();
    //TODO: Notification about success or fail
});