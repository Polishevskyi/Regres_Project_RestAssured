package reqresTests;

import reqresPojos.ErrorPojo;
import reqresPojos.UserForReg;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static base.BaseApiSpecifications.configureSpec;
import static base.BaseApiSpecifications.requestSpecification;
import static base.BaseApiSpecifications.responseSpecification;
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
