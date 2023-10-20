package reqresTests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;

import static base.BaseApiSpecifications.configureSpec;
import static base.BaseApiSpecifications.requestSpecification;
import static base.BaseApiSpecifications.responseSpecification;
import static io.restassured.RestAssured.given;

public class DeleteUserDeleteTest {

    private final String BASE_URL = "https://reqres.in/";

    @Test
    @Owner("Polishevskyi")
    @Description("Test for deleting a user")
    public void userDeleteTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(204));
        given()
                .when()
                .delete("api/users/2")
                .then().log().all();
    }
}
