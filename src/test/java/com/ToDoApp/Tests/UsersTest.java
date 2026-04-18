package com.ToDoApp.Tests;

import com.ToDoApp.Models.Error;
import com.ToDoApp.Models.UserData;
import com.ToDoApp.Steps.UserSteps;
import com.ToDoApp.apis.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import tools.jackson.databind.ObjectMapper;

import static com.ToDoApp.Routs.ErrorMsg.EMAIL_ALREADY_EXISTS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@Feature("User API")
public class UsersTest {


    @Story("Valid Register")
    @Test(description = "Verify user able to register with valid data")
    public void verifyUserAbleToRegisterWithValidData() {

        UserData userdata = UserSteps.registerNewData();

        //request
        Response res = new UserApi().register(userdata);


        //de-serialization
        UserData returnedUser = res.body().as(UserData.class);

        //assert
        assertThat(res.statusCode(), equalTo(201));
        assertThat(returnedUser.getFirstName(), equalTo(userdata.getFirstName()));
        assertThat(returnedUser.getAccess_token(), not(equalTo((null))));


    }

    @Story("InValid Register")
    @Test(description = "Verify user not able to register with same email")
    public void verifyUserNotAbleToRegisterWithSameEmail() {
        UserData userdata = UserSteps.registeredData();
        System.out.println(new ObjectMapper().writeValueAsString(userdata));
        //request
        Response res = new UserApi().register(userdata);

        //de-serialization
        Error error = res.body().as(Error.class);

        //assert
        assertThat(res.statusCode(), equalTo(400));
        assertThat(error.getMessage(), equalTo(EMAIL_ALREADY_EXISTS));
    }

    @Story("Valid Login")
    @Test(description = "Verify user able to login")
    public void verifyUserAbleToLoginWithValidData() {
        UserData userdata = UserSteps.registeredData();
        UserData loginData = new UserData(userdata.getEmail(), userdata.getPassword());

        //request
        Response res = new UserApi().login(loginData);

        //de-serialization
        UserData returnedUser = res.body().as(UserData.class);

        //assert
        assertThat(res.statusCode(), equalTo(200));
        assertThat(returnedUser.getAccess_token(), not(equalTo((null))));
        assertThat(returnedUser.getUserID(), not(equalTo((null))));
        assertThat(returnedUser.getFirstName(), equalTo(userdata.getFirstName()));

    }

}
