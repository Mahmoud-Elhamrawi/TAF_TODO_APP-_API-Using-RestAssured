package com.ToDoApp.apis;

import com.ToDoApp.BaseSpecs.Specs;
import com.ToDoApp.Models.taskData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static com.ToDoApp.Routs.Routs.TASKS_ROUTE;
import static io.restassured.RestAssured.given;

public class TaskApi {

    public Response createTask(String token, taskData taskDataaa) {
        return given()
                .spec(Specs.getSpecs())
                .auth().oauth2(token)
                .body(taskDataaa)
                .when().post(TASKS_ROUTE)
                .then().log().all().extract().response();


    }

    public Response getTaskById(String token, String taskID) {
        return given()
                .spec(Specs.getSpecs())
                .auth().oauth2(token)
                .when().get(TASKS_ROUTE+"/" + taskID)
                .then().log().all().extract().response();
    }

    public Response markTaskTodo(String token, String taskID, taskData taskDataaa) {
        return given()
                .spec(Specs.getSpecs())
                .body(taskDataaa)
                .auth().oauth2(token)
                .when().put(TASKS_ROUTE+"/" + taskID)
                .then().log().all().extract().response();
    }

    public Response deleteTask(String token, String taskID) {
        return given()
                .spec(Specs.getSpecs())
                .auth().oauth2(token)
                .when().delete(TASKS_ROUTE+"/" + taskID)
                .then().log().all().extract().response();
    }


}