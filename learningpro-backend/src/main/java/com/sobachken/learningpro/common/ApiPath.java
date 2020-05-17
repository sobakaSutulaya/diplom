package com.sobachken.learningpro.common;

public class ApiPath {

    private static final String APP_NAME = "learning-pro",
    USERS = "users",
    ME = "me",
    V1 = "v1",
    API = "api",
    ID = "id",
    STUDENTS = "students",
    TEACHERS = "teachers",
    TASKS = "tasks",
    ALL = "all",
    SUBJECT = "subject",
    COMPLETED_TASK = "completed-task",
    MARK_AS_COMPLETE = "mark-as-complete";

    public static final String APP_PATH = "/" + APP_NAME,
    USERS_PATH = APP_PATH + "/" + USERS,
    ME_PATH = "/" + ME,
    ID_PATH = "/" + ID,
    ALL_PATH = "/" + ALL,
    SUBJECT_PATH = "/" + SUBJECT,
    API_V1_PATH = "/" + API + "/" + V1,
    APP_V1_PATH = APP_PATH + API_V1_PATH,
    STUDENTS_V1_PATH = API_V1_PATH + "/" + STUDENTS,
    TEACHERS_V1_PATH = API_V1_PATH + "/" + TEACHERS,
    TASKS_V1_PATH = API_V1_PATH + "/" + TASKS,
    COMPLETED_TASK_V1_PATH = API_V1_PATH + "/" + COMPLETED_TASK,
    MARK_AS_COMPLETE_PATH = "/" + MARK_AS_COMPLETE;


}
