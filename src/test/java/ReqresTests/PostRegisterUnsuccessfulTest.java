package ReqresTests;

import ReqresPojos.ErrorPojo;
import ReqresPojos.UserForReg;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Base.BaseApiSpecifications.configureSpec;
import static Base.BaseApiSpecifications.requestSpecification;
import static Base.BaseApiSpecifications.responseSpecification;
import static io.restassured.RestAssured.given;

public class PostRegisterUnsuccessfulTest {

    private final String BASE_URL = "https://reqres.in/";

    @Test
    @Owner("Polishevskyi")
    @Description("The test verifies the error message when an incorrect user registration is attempted")
    public void registerUnsuccessfulTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(400));

        UserForReg userForReg = new UserForReg("sydney@fife", "");
        ErrorPojo errorPojo = given()
                .body(userForReg)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(ErrorPojo.class);

        Assert.assertEquals(errorPojo.getError(), "Missing password");
    }
}
