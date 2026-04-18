package com.ToDoApp.Steps;

import com.ToDoApp.Models.taskData;
import com.ToDoApp.apis.TaskApi;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class TodoSteps {

    public static taskData generateDataTask()
    {
        String item = new Faker().name().title();
        Boolean completed = false;

        return new taskData(item, completed);
    }




public static  String getIDTask(String token, taskData taskdataa)
{
    //taskData taskdataa = generateDataTask();
    Response ress =new TaskApi().createTask(token, taskdataa);
    return ress.body().path("_id");

}





}
