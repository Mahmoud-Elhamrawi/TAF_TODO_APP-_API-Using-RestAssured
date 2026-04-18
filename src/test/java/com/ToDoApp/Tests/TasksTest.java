package com.ToDoApp.Tests;

import com.ToDoApp.Models.Error;
import com.ToDoApp.Models.taskData;
import com.ToDoApp.Steps.TodoSteps;
import com.ToDoApp.Steps.UserSteps;
import com.ToDoApp.apis.TaskApi;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import tools.jackson.databind.ObjectMapper;

import static com.ToDoApp.Routs.ErrorMsg.IS_COMPLETED_REQUIRED;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@Feature("Tasks API")
public class TasksTest {

    @Story("Create Task")
    @Test(description = "verify user able to create task")
    public void verifyUserAbleToCreateTask() {

        String token = UserSteps.getToken();
        taskData taskDataaa = TodoSteps.generateDataTask();

        System.out.println(new ObjectMapper().writeValueAsString(taskDataaa));


        //request
        Response res = new TaskApi().createTask(token, taskDataaa);

        //deserialization
        taskData returnedTask = res.body().as(taskData.class);

        //assert
        assertThat(res.statusCode(), equalTo(201));
        assertThat(returnedTask.getItem(), equalTo(taskDataaa.getItem()));
        assertThat(returnedTask.getCompleted(), equalTo(taskDataaa.getCompleted()));
        assertThat(returnedTask.getTaskID(), not(equalTo((null))));

    }

    @Story("InValid Create Task")
    @Test(description = "verify user not able to create task without paramater")
    public void verifyUserNotAbleToCreateTaskWithoutParamater() {

        String token = UserSteps.getToken();
        taskData taskDataa = new taskData("appium");

        taskData taskDataaa = new taskData(taskDataa.getItem());

        System.out.println(new ObjectMapper().writeValueAsString(taskDataaa));
        Response res = given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .basePath("/api/v1")
                .auth().oauth2(token)
                .contentType(ContentType.JSON)
                .body(taskDataaa)
                .when().post("/tasks")
                .then().log().all().extract().response();

        com.ToDoApp.Models.Error returnedError = res.body().as(Error.class);

        assertThat(res.statusCode(), equalTo(400));
        assertThat(returnedError.getMessage(), equalTo(IS_COMPLETED_REQUIRED));
    }

    @Story("Get Task ID")
    @Test(description = "get task id")
    public void getTaskById() {
        taskData taskDataaa = TodoSteps.generateDataTask();

        String token = UserSteps.getToken();
        String taskID = TodoSteps.getIDTask(token, taskDataaa);

        //req
        Response res = new TaskApi().getTaskById(token, taskID);

        //de-serialization
        taskData returnedTask = res.body().as(taskData.class);

        //assert
        assertThat(res.statusCode(), equalTo(200));
        assertThat(returnedTask.getItem(), equalTo(taskDataaa.getItem()));
        assertThat(returnedTask.getTaskID(), equalTo(taskID));


    }

    @Story("Mark Task Todo")
    @Test(description = "mark task todo")
    public void markTaskTodo() {

        taskData taskDataaa = TodoSteps.generateDataTask();

        String token = UserSteps.getToken();
        String taskID = TodoSteps.getIDTask(token, taskDataaa);


        //req
        Response res = new TaskApi().markTaskTodo(token, taskID, taskDataaa);

        //de-serialization
        taskData returnedTask = res.body().as(taskData.class);

        //assert
        assertThat(res.statusCode(), equalTo(200));
        assertThat(returnedTask.getCompleted(), equalTo(taskDataaa.getCompleted()));
        assertThat(returnedTask.getTaskID(), equalTo(taskID));
        assertThat(returnedTask.getItem(), equalTo(taskDataaa.getItem()));
    }

    @Story("Delete Task")
    @Test(description = "delete task")
    public void deleteTask() {

        taskData taskDataaa = TodoSteps.generateDataTask();

        String token = UserSteps.getToken();
        String taskID = TodoSteps.getIDTask(token, taskDataaa);

        //req
        Response res = new TaskApi().deleteTask(token, taskID);

        //de-serialization
        taskData returnedTask = res.body().as(taskData.class);

        //assert
        assertThat(res.statusCode(), equalTo(200));
        assertThat(returnedTask.getItem(), equalTo(taskDataaa.getItem()));


    }

}
