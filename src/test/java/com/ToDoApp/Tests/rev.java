package com.ToDoApp.Tests;

import com.ToDoApp.Models.UserData;
import com.ToDoApp.apis.UserApi;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class rev {


    @Test
    public void revision() {
        //** make serialization **//
        // create model "pojo" class for each property with setter and getter and constructor
        // take object of model class and pass values in constructor  and pass it as body

        Response res = given()
                .baseUri("")
                .basePath("")
                .header("", "")
                .auth().oauth2("")
                .contentType("")
                .body("")
                .when().post()
                .then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("", equalTo(""))
                .extract().response();

    }


    @Test
    public void revision2() {
        // spearte req from res
        Response res = given()
                .baseUri("")
                .basePath("")
                .header("", "")
                .auth().oauth2("")
                .contentType("")
                .body("")
                .when().post()
                .then().log().all()
                .extract().response();

        //hard assert
        assertThat(res.statusCode(), equalTo(200));
        assertThat(res.path("hh"), equalTo(""));

    }

    @Test
    public void revision3() {

        //** de-serialization **//
        // for dynamic assert
        Response res = given()
                .baseUri("")
                .basePath("")
                .header("", "")
                .auth().oauth2("")
                .contentType("")
                .body("")
                .when().post()
                .then().log().all()
                .extract().response();
// 1 - de-serialization
        // create model "pojo" class for each property with setter and getter and constructor
        // take object of model class like :
        // Users user = res.body().as(Users.class);
        //pass object to assert

        //hard assert
    //    assertThat(res.statusCode(), equalTo(200));
      //  assertThat(res.path(user.hh), equalTo(""));

    }


    @Test
    public void revision4() {
                           //** move request to other class"api" **//
        /*
    public Response login(UserData userData )
    {
        return given()
                .baseUri("https://qacart-todo.herokuapp.com")
                .basePath("/api/v1")
                .contentType("application/json")
                .body(userData)
                .when().post("/users/login")
                .then().log().all().extract().response();
    }
         */
                                          // final test case //
      // make state of class and call method as req
        UserData userData = new UserData("test121Emaidwl20025@gmail.com", "testPassword123");

        //request
        Response res = new UserApi().login(userData);

        //de-serialization
        UserData returnedUser = res.body().as(UserData.class);

        //assert
        assertThat(res.statusCode(), equalTo(200));
        assertThat(returnedUser.getAccess_token(), not(equalTo((null))));
        assertThat(returnedUser.getUserID(), not(equalTo((null))));
        assertThat(returnedUser.getFirstName(), equalTo(userData.getFirstName()));

    }


}
