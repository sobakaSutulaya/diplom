export abstract class ApiPath {
    private static readonly SERVER_HOST = "http://localhost:8080";
    private static readonly APP_PATH = ApiPath.SERVER_HOST + "/learning-pro";
    private static readonly APP_V1_PATH = ApiPath.APP_PATH + "/api/v1";
    static readonly OAUTH_TOKEN = ApiPath.SERVER_HOST + "/oauth/token"; //POST 
    static readonly GET_PRINCIPAL = ApiPath.APP_PATH + "/users/me";
    static readonly GET_USER_ID = ApiPath.APP_PATH + "/users/id";
    static readonly GET_ALL_EVENTS = ApiPath.APP_V1_PATH + "/events";
    static readonly GET_ALL_TASKS = ApiPath.APP_V1_PATH + "/tasks";
    static readonly BEARER = 'Bearer ';
}