package ReqresTests;

import ReqresPojos.SuccessCreateUserResp;
import ReqresPojos.UserForCreate;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Base.BaseApiSpecifications.configureSpec;
import static Base.BaseApiSpecifications.requestSpecification;
import static Base.BaseApiSpecifications.responseSpecification;
import static io.restassured.RestAssured.given;

public class PostCreateTest {

    private final String BASE_URL = "https://reqres.in/";

    @Test
    @Owner("Polishevskyi")
    @Description("The test creates a new user and checks the correctness of the returned data about the created user, including name and job")
    public void createTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(201));

        UserForCreate userForCreate = new UserForCreate("morpheus", "leader");
        SuccessCreateUserResp successCreateUserResp = given()
                .body(userForCreate)
                .when()
                .post("api/users")
                .then().log().all()
                .extract().as(SuccessCreateUserResp.class);

        Assert.assertEquals(successCreateUserResp.getName(), "morpheus");
        Assert.assertEquals(successCreateUserResp.getJob(), "leader");
    }
}
