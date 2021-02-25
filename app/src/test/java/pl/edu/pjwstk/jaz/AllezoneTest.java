package pl.edu.pjwstk.jaz;


import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.request.CategoryRequest;
import pl.edu.pjwstk.jaz.request.LoginRequest;
import pl.edu.pjwstk.jaz.request.RegisterRequest;
import io.restassured.response.Response;
import pl.edu.pjwstk.jaz.request.SectionRequest;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AllezoneTest {
    private static Response adminResponse;
    private static Response userResponse;

    @BeforeClass
    public static void beforeClass() throws Exception {

        given()
                .body(new RegisterRequest("wojtek", "wojtek2", "Wojtek", "Piorecki")) //admin user
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
        adminResponse = given()
                .body(new LoginRequest("wojtek", "wojtek2"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/login")
                .thenReturn();
    }

    @Test
    public void createSectionShouldResponseStatus200() {
        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("ogrod"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .then()
                .statusCode(200);
    }

    @Test
    public void createSectionWithAlreadyExistingSectionShouldResponse500() {
        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("elektronika"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("elektronika"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .then()
                .statusCode(500);

    }

    @Test
    public void createCategoryShouldResponse200() {
        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("lozka","Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .then()
                .statusCode(200);
    }

    @Test
    public void createCategoryWithAlreadyExistingNameShouldResponse500() {
        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("elektronika"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("dyski","elektronika"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("dyski","elektronika"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .then()
                .statusCode(500);
    }

    @Test
    public void updateSectionNameShouldResponseStatus200() {
        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("elektronika"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("dom"))
                .contentType(ContentType.JSON)
                .post("/api/updateSection/elektronika")
                .then()
                .statusCode(200);
    }

    @Test
    public void updateCategoryNameShouldResponse200() {
        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("elektronika"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("Sport","elektronika"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("cos","elektronika"))
                .contentType(ContentType.JSON)
                .post("/api/updateCategory/sport")
                .then()
                .statusCode(200);
    }
}
