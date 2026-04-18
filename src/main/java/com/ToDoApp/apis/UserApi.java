package com.ToDoApp.apis;

import com.ToDoApp.BaseSpecs.Specs;
import com.ToDoApp.Models.UserData;
import io.restassured.response.Response;

import static com.ToDoApp.Routs.Routs.LOGIN_ROUTE;
import static com.ToDoApp.Routs.Routs.REGISTER_ROUTE;
import static io.restassured.RestAssured.given;

public class UserApi {

    public Response register(UserData dataa) {
        return given()
                .spec(Specs.getSpecs())
                .body(dataa)
                .when().post(REGISTER_ROUTE)
                .then().log().all().extract().response();


    }


    public Response login(UserData userData) {
        return given()
                .spec(Specs.getSpecs())
                .body(userData)
                .when().post(LOGIN_ROUTE)
                .then().log().all().extract().response();
    }


}
