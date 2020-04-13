class TeacherBean {
    id;
    name;
    surname;

    constructor(id, name, surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }


    get id() {
        return this.id;
    }

    set id(value) {
        this.id = value;
    }

    get name() {
        return this.name;
    }

    set name(value) {
        this.name = value;
    }

    get surname() {
        return this.surname;
    }

    set surname(value) {
        this.surname = value;
    }

    /** Returns a TeacherBean object from non JSON.
     *
     * @param json
     * @returns TeacherBean
     */
    static from(json) {
        let parsedJson;
        if ((typeof json) === "string"){
          parsedJson  = JSON.parse(json);

        } else {
            parsedJson = json;
        }
        return new TeacherBean(parsedJson.id, parsedJson.name, parsedJson.surname);
    }

    /** Returns an array of TeacherBean objects from JSON array.
     * @param json
     * @returns [TeacherBean]
     * */
    static fromArray(json) {
        let teachersJSONArray = JSON.parse(json);
        let objects = [];
        for (let index = 0; index < teachersJSONArray.length; index++) {
            console.log(teachersJSONArray[index]);
            objects.push(TeacherBean.from(teachersJSONArray[index]));
        }
        return objects;
    }
}

class StudentBean {
    id;
    name;
    surname;
    rating;
    group;

    constructor(id, name, surname, rating, group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.rating = rating;
        this.group = group;
    }


    get id() {
        return this.id;
    }

    set id(value) {
        this.id = value;
    }

    get name() {
        return this.name;
    }

    set name(value) {
        this.name = value;
    }

    get surname() {
        return this.surname;
    }

    set surname(value) {
        this.surname = value;
    }

    get rating() {
        return this.rating;
    }

    set rating(value) {
        this.rating = value;
    }

    get group() {
        return this.group;
    }

    set group(value) {
        this.group = value;
    }

    /** Returns a StudentBean object from non JSON.
     *
     * @param json
     * @returns StudentBean
     */
    static from(json) {
        let parsedJson;
        if ((typeof json) === "string"){
            parsedJson  = JSON.parse(json);

        } else {
            parsedJson = json;
        }
        return new StudentBean(parsedJson.id, parsedJson.name, parsedJson.surname, parsedJson.rating, parsedJson.group);
    }

    /** Returns an array of StudentBean objects from JSON array.
     * @param json
     * @returns [StudentBean]
     * */
    static fromArray(json) {
        let teachersJSONArray = JSON.parse(json);
        let objects = [];
        for (let index = 0; index < teachersJSONArray.length; index++) {
            console.log(teachersJSONArray[index]);
            objects.push(StudentBean.from(teachersJSONArray[index]));
        }
        return objects;
    }
}

class SubjectBean {
    id;
    name;
    hours;
    constructor(id, name, hours) {
        this.id = id;
        this.name = name;
        this.hours = hours;
    }


    get id() {
        return this.id;
    }

    set id(value) {
        this.id = value;
    }

    get name() {
        return this.name;
    }

    set name(value) {
        this.name = value;
    }

    get hours() {
        return this.hours;
    }

    set hours(value) {
        this.hours = value;
    }

    /** Returns a SubjectBean object from non JSON.
     *
     * @param json
     * @returns SubjectBean
     */
    static from(json) {
        let parsedJson;
        if ((typeof json) === "string"){
            parsedJson  = JSON.parse(json);

        } else {
            parsedJson = json;
        }
        return new SubjectBean(parsedJson.id, parsedJson.name, parsedJson.hours);
    }

    /** Returns an array of SubjectBean objects from JSON array.
     * @param json
     * @returns [SubjectBean]
     * */
    static fromArray(json) {
        let teachersJSONArray = JSON.parse(json);
        let objects = [];
        for (let index = 0; index < teachersJSONArray.length; index++) {
            console.log(teachersJSONArray[index]);
            objects.push(SubjectBean.from(teachersJSONArray[index]));
        }
        return objects;
    }
}

class LessonBean {
    id;
    groupId;
    subjectId;
    teacherId;
    constructor(id, groupId, subjectId, teacherId) {
        this.id = id;
        this.groupId = groupId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
    }


    get id() {
        return this.id;
    }

    set id(value) {
        this.id = value;
    }

    get groupId() {
        return this.groupId;
    }

    set groupId(value) {
        this.groupId = value;
    }

    get subjectId() {
        return this.subjectId;
    }

    set subjectId(value) {
        this.subjectId = value;
    }

    get teacherId() {
        return this.teacherId;
    }

    set teacherId(value) {
        this.teacherId = value;
    }

    /** Returns a LessonBean object from non JSON.
     *
     * @param json
     * @returns LessonBean
     */
    static from(json) {
        let parsedJson;
        if ((typeof json) === "string"){
            parsedJson  = JSON.parse(json);

        } else {
            parsedJson = json;
        }
        return new LessonBean(parsedJson.id, parsedJson.groupId, parsedJson.subjectId, parsedJson.teacherId);
    }

    /** Returns an array of LessonBean objects from JSON array.
     * @param json
     * @returns [SubjectBean]
     * */
    static fromArray(json) {
        let teachersJSONArray = JSON.parse(json);
        let objects = [];
        for (let index = 0; index < teachersJSONArray.length; index++) {
            console.log(teachersJSONArray[index]);
            objects.push(LessonBean.from(teachersJSONArray[index]));
        }
        return objects;
    }
}