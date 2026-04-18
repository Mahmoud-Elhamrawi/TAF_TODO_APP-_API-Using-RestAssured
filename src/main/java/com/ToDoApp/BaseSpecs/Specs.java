package com.ToDoApp.BaseSpecs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.ToDoApp.Routs.Routs.BASE_URL;
import static io.restassured.RestAssured.given;

public class Specs {

    public static String getEnv(){
        String env = System.getProperty("env", "PRODUCTION");
        String URL ;
        switch (env.toUpperCase()){
            case "PRODUCTION":
                URL = "https://qacart-todo.herokuapp.com";
                break;
            case "STAGING":
                URL = "http://localhost:3000";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + env);
        }
        return URL;
    }



    public static RequestSpecification getSpecs()
    {
        return new RequestSpecBuilder()
                .setBaseUri(getEnv())
                .setBasePath("/api/v1")
                .setContentType("application/json")
                .build();


    }



}
