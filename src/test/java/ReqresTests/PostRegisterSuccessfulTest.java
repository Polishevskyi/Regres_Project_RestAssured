package ReqresTests;

import ReqresPojos.SuccessUserResp;
import ReqresPojos.UserForReg;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Base.BaseApiSpecifications.configureSpec;
import static Base.BaseApiSpecifications.requestSpecification;
import static Base.BaseApiSpecifications.responseSpecification;
import static io.restassured.RestAssured.given;

public class PostRegisterSuccessfulTest {

    private final String BASE_URL = "https://reqres.in/";

    @Test
    @Owner("Polishevskyi")
    @Description("The test successfully registers the user and verifies the ID and token data")
    public void registerSuccessfulTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));

        UserForReg userForReg = new UserForReg("eve.holt@reqres.in", "pistol");
        SuccessUserResp successUserResp = given()
                .body(userForReg)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessUserResp.class);

        Assert.assertEquals(successUserResp.getId(), 4);
        Assert.assertEquals(successUserResp.getToken(), "QpwL5tke4Pnpja7X4");
    }
}
