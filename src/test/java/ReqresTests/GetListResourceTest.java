package ReqresTests;

import ReqresPojos.YearsData;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static Base.BaseApiSpecifications.configureSpec;
import static Base.BaseApiSpecifications.requestSpecification;
import static Base.BaseApiSpecifications.responseSpecification;
import static io.restassured.RestAssured.given;

public class GetListResourceTest {

    private final String BASE_URL = "https://reqres.in/";

    @Test
    @Owner("Polishevskyi")
    @Description("Test the list of 'year' values in the answer sorted in ascending order")
    public void listResourceTest() {
        configureSpec(requestSpecification(BASE_URL), responseSpecification(200));
        List<YearsData> yearsDataList = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", YearsData.class);
        List<Integer> yearActual = yearsDataList.stream().map(YearsData::getYear).toList();
        List<Integer> expectedActual = yearActual.stream().sorted().toList();
        Assert.assertEquals(expectedActual, yearActual);
    }
}
