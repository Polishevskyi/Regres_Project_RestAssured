package ReqresTests;

import ReqresPojos.UserData;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static Base.BaseApiSpecifications.configureSpec;
import static Base.BaseApiSpecifications.requestSpecification;
import static Base.BaseApiSpecifications.responseSpecification;
import static io.restassured.RestAssured.given;

public class GetListUsersTest {

    private final String BASE_URL = "https://reqres.in/";

    @Test
    @Owner("Polishevskyi")
    @Description("The test verifies that user data contains correct e-mail addresses and that their IDs match the URL links to their avatars")
    public void listUsersTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        List<UserData> userDataList = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        Assert.assertTrue(userDataList.stream().allMatch(x -> x.getEmail().endsWith("reqres.in")));

        List<String> ids = userDataList.stream().map(x -> x.getId().toString()).toList();
        List<String> avatars = userDataList.stream().map(UserData::getAvatar).toList();

        for (int i = 0; i < ids.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }
}
