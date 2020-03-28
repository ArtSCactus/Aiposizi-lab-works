class TeacherBean {

    constructor(id, name, surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }


    get _id() {
        return this.id;
    }

    set _id(value) {
        this.id = value;
    }

    get _name() {
        return this.name;
    }

    set _name(value) {
        this.name = value;
    }

    get _surname() {
        return this.surname;
    }

    set _surname(value) {
        this.surname = value;
    }

    /** Returns a TeacherBean object from non JSON.
     *
     * @param json
     * @returns TeacherBean
     */
    static from(json) {
        return Object.assign(new TeacherBean(), json);
    }

    /** Returns an array of TeacherBean objects from JSON array.
     * @param json
     * @returns [TeacherBean]
     * */
    static fromArray(json) {
        var teachersJSONArray = JSON.parse(json);
        var objects = [];
        for (var index = 0; index < teachersJSONArray.length; index++) {
            console.log(teachersJSONArray[index]);
            objects.push(TeacherBean.from(teachersJSONArray[index]));
        }
        return objects;
    }
}

class StudentBean {
    constructor(id, name, surname, rating, group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.rating = rating;
        this.group = group;
    }


    get _id() {
        return this.id;
    }

    set _id(value) {
        this.id = value;
    }

    get _name() {
        return this.name;
    }

    set _name(value) {
        this.name = value;
    }

    get _surname() {
        return this.surname;
    }

    set _surname(value) {
        this.surname = value;
    }

    get _rating() {
        return this.rating;
    }

    set _rating(value) {
        this.rating = value;
    }

    get _group() {
        return this.group;
    }

    set _group(value) {
        this.group = value;
    }

    /** Returns a StudentBean object from non JSON.
     *
     * @param json
     * @returns StudentBean
     */
    static from(json) {
        return Object.assign(new StudentBean(), json);
    }

    /** Returns an array of StudentBean objects from JSON array.
     * @param json
     * @returns [StudentBean]
     * */
    static fromArray(json) {
        var teachersJSONArray = JSON.parse(json);
        var objects = [];
        for (var index = 0; index < teachersJSONArray.length; index++) {
            console.log(teachersJSONArray[index]);
            objects.push(StudentBean.from(teachersJSONArray[index]));
        }
        return objects;
    }
}

class SubjectBean {
    constructor(id, name, hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
    }


    get _id() {
        return this.id;
    }

    set _id(value) {
        this.id = value;
    }

    get _name() {
        return this.name;
    }

    set _name(value) {
        this.name = value;
    }

    get _hours() {
        return this.hours;
    }

    set _hours(value) {
        this.hours = value;
    }

    /** Returns a SubjectBean object from non JSON.
     *
     * @param json
     * @returns SubjectBean
     */
    static from(json) {
        return Object.assign(new SubjectBean(), json);
    }

    /** Returns an array of SubjectBean objects from JSON array.
     * @param json
     * @returns [SubjectBean]
     * */
    static fromArray(json) {
        var teachersJSONArray = JSON.parse(json);
        var objects = [];
        for (var index = 0; index < teachersJSONArray.length; index++) {
            console.log(teachersJSONArray[index]);
            objects.push(SubjectBean.from(teachersJSONArray[index]));
        }
        return objects;
    }
}

class LessonBean {
    /*   private Long id;
    private Long groupId;
    private Long subjectId;
    private Long teacherId;*/
    constructor(id, groupId, subjectId, teacherId) {
        this.id = id;
        this.groupId = groupId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
    }


    get _id() {
        return this.id;
    }

    set _id(value) {
        this.id = value;
    }

    get _groupId() {
        return this.groupId;
    }

    set _groupId(value) {
        this.groupId = value;
    }

    get _subjectId() {
        return this.subjectId;
    }

    set _subjectId(value) {
        this.subjectId = value;
    }

    get _teacherId() {
        return this.teacherId;
    }

    set _teacherId(value) {
        this.teacherId = value;
    }

    /** Returns a LessonBean object from non JSON.
     *
     * @param json
     * @returns SubjectBean
     */
    static from(json) {
        return Object.assign(new LessonBean(), json);
    }

    /** Returns an array of LessonBean objects from JSON array.
     * @param json
     * @returns [SubjectBean]
     * */
    static fromArray(json) {
        var teachersJSONArray = JSON.parse(json);
        var objects = [];
        for (var index = 0; index < teachersJSONArray.length; index++) {
            console.log(teachersJSONArray[index]);
            objects.push(LessonBean.from(teachersJSONArray[index]));
        }
        return objects;
    }
}