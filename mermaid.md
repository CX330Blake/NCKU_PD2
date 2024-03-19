classDiagram
    class Person 
    Person : +introduceSelf(String name) void

    class Student {
        +String studentID
        +study() void
    }
    
    class Teacher {
        +String teacherID
        +teach() void
    }
    Person : -int age
    Person : -String name

    class Student {
        int studClass;
        Teacher coorespondingTeacher;
    }