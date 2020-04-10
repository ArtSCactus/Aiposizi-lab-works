function showTeachersPage() {
    var teacherPage = document.getElementById('teachers-page');
    var studentPage = document.getElementById('students-page');
    var lessonPage = document.getElementById('lessons-page');
    var subjectPage = document.getElementById('subjects-page');
    teacherPage.style.display = 'block';
    subjectPage.style.display = 'none';
    studentPage.style.display = 'none';
    lessonPage.style.display = 'none';
}

function showStudentsPage() {
    var teacherPage = document.getElementById('teachers-page');
    var studentPage = document.getElementById('students-page');
    var lessonPage = document.getElementById('lessons-page');
    var subjectPage = document.getElementById('subjects-page');
    teacherPage.style.display = 'none';
    subjectPage.style.display = 'none';
    studentPage.style.display = 'block';
    lessonPage.style.display = 'none';
}

function showLessonsPage() {
    var teacherPage = document.getElementById('teachers-page');
    var studentPage = document.getElementById('students-page');
    var lessonPage = document.getElementById('lessons-page');
    var subjectPage = document.getElementById('subjects-page');
    teacherPage.style.display = 'none';
    subjectPage.style.display = 'none';
    studentPage.style.display = 'none';
    lessonPage.style.display = 'block';
}

function showSubjectsPage() {
    var teacherPage = document.getElementById('teachers-page');
    var studentPage = document.getElementById('students-page');
    var lessonPage = document.getElementById('lessons-page');
    var subjectPage = document.getElementById('subjects-page');
    teacherPage.style.display = 'none';
    subjectPage.style.display = 'block';
    studentPage.style.display = 'none';
    lessonPage.style.display = 'none';
}

class DataManager {

    constructor() {
        this.allTeachersURL = 'http://localhost:8081/teachers/all';
        this.allStudentsURL = 'http://localhost:8081/students/all';
        this.allLessonsURL = 'http://localhost:8081/lessons/all';
        this.allSubjectsURL = 'http://localhost:8081/subjects/all';
        this.teacherByIdURL = 'http://localhost:8081/teachers/'; //+id required
        this.subjectByIdURL = 'http://localhost:8081/subjects/'; //+id required
        this.lessonByIdURL = 'http://localhost:8081/lessons/'; //+id required
        this.studentByIdURL = 'http://localhost:8081/students/'; //+id required
        this.updateTeacherURL = 'http://localhost:8081/teachers/update';
        this.updateSubjectURL = 'http://localhost:8081/subjects/update';
        this.updateStudentURL = 'http://localhost:8081/students/update';
        this.updateLessonURL = 'http://localhost:8081/lessons/update';
    }

    loadAllTeachers() {
        let json = this.executeGetRequestToREST(this.allTeachersURL);
        return TeacherBean.fromArray(json);
    }

    loadAllStudents() {
        let json = this.executeGetRequestToREST(this.allStudentsURL);
        return StudentBean.fromArray(json);
    }

    loadAllSubjects() {
        let json = this.executeGetRequestToREST(this.allSubjectsURL);
        return SubjectBean.fromArray(json);
    }

    loadAllLessons() {
        let json = this.executeGetRequestToREST(this.allLessonsURL);
        return LessonBean.fromArray(json);
    }

    loadTeacherById(id) {
        let json = this.executeGetRequestToREST(this.teacherByIdURL + id);
        return TeacherBean.from(json);
    }

    loadStudentById(id) {
        let json = this.executeGetRequestToREST(this.studentByIdURL + id);
        return StudentBean.from(json);
    }

    loadSubjectById(id) {
        let json = this.executeGetRequestToREST(this.subjectByIdURL + id);
        return SubjectBean.from(json);
    }

    loadLessonById(id) {
        let json = this.executeGetRequestToREST(this.lessonByIdURL + id);
        return LessonBean.from(json);
    }

    saveTeacher(teacherBean) {
        let json = JSON.stringify(teacherBean);
        return this.executePostToRest(this.updateTeacherURL, json);
    }

    saveStudent(studentBean) {
        let json = JSON.stringify(studentBean);
        return this.executePostToRest(this.updateStudentURL, json);
    }

    saveLesson(lessonBean) {
        let json = JSON.stringify(lessonBean);
        return this.executePostToRest(this.updateLessonURL, json);
    }

    saveSubject(subjectBean) {
        let json = JSON.stringify(subjectBean);
        return this.executePostToRest(this.updateSubjectURL, json);
    }

    deleteTeacher(id) {
        return this.executeDeleteToRest(this.teacherByIdURL + id);
    }

    deleteSubject(id) {
        return this.executeDeleteToRest(this.subjectByIdURL + id);
    }

    deleteLesson(id) {
        return this.executeDeleteToRest(this.lessonByIdURL + id);
    }

    deleteStudent(id) {
        return this.executeDeleteToRest(this.studentByIdURL + id);
    }

    /** Executes GET request by url and returns *.responseText.
     *
     * @param url (like http://localhost:8081/teachers/all), only ports: 8081, 8080 is allowed
     */
    executeGetRequestToREST(url) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, false);

        // use xhr.onload and xhr.onerror if any troubles

        xhr.send();

        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            console.log("JSON from url " + url + ": " + xhr.responseText);
            return xhr.responseText;

        }
    }


    executePostToRest(url, json) {
        let xhr = new XMLHttpRequest();
        xhr.open('POST', url, false);
        xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xhr.send(json);
        return xhr.status;
    }

    executeDeleteToRest(url) {
        let xhr = new XMLHttpRequest();
        xhr.open('DELETE', url, false);
        xhr.send();
        return xhr.status;
    }
}

