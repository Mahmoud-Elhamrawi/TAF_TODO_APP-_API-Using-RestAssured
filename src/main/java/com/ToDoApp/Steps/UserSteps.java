package com.ToDoApp.Steps;

import com.ToDoApp.Models.UserData;
import com.ToDoApp.apis.UserApi;
import com.github.javafaker.Faker;
import io.restassured.response.Response;

public class UserSteps {

    public static UserData registerNewData() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password = "P@ssw0rd";
        return new UserData(firstName, lastName, email, password);
    }


    public static UserData registeredData() {
        UserData userData = registerNewData();
        new UserApi().register(userData);
        return userData;
    }

    public static String getToken() {
        UserData userData = registerNewData();
        Response res = new UserApi().register(userData);
        return res.body().path("access_token");

    }


}
