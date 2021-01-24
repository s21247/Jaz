package pl.edu.pjwstk.jaz;

import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.request.LoginRequest;
import pl.edu.pjwstk.jaz.request.RegisterRequest;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class LoginTest {

    @BeforeClass
    public static void beforeClass()  {
        given()
                .body(new RegisterRequest("wojtek", "wojtek2","wojtek","Piorecki"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
        given()
                .body(new RegisterRequest("adam", "adam2","adam","Kaluza"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
        given()
                .body(new RegisterRequest("dominik", "dominik2","dominik","Pasymowski"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
    }

    @Test
    public void tryToLogInWhenUserAndPasswordIsCorrectReturns200() {
        given()
                .when()
                .body(new LoginRequest("wojtek", "wojtek2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .then()
                .statusCode(200);
    }

    @Test
    public void tryToLogInWhenUserIsCorrectAndPasswordIncorrectReturns401() {
        given()
                .when()
                .body(new LoginRequest("wojtek", "asdsdgssadagf"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void tryToLogInWhenUserIsIncorrectAndPasswordIsCorrectReturns401() {
        given()
                .when()
                .body(new LoginRequest("sadsgg", "wojtek2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    public void tryToLogInNotRegisteredUserReturns500() {
        given()
                .when()
                .body(new LoginRequest("michal", "pawelczak"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    public void tryToLogInAdminUserToEndPointIsReadyOnlyAccessForAdmin() {
        var response = given()
                .when()
                .body(new LoginRequest("wojtek", "wojtek2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/auth0/is-ready")
                .then()
                .statusCode(200);
    }

    @Test
    public void tryToLogInADefaultUserToEndPointIsReadyOnlyAccessForAdmin() {
        var response = given()
                .when()
                .body(new LoginRequest("adam", "adam2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/auth0/is-ready")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void tryToLogInAdminUserToEndPointGrantedAuthorityOnlyAccessForAdmin() {
        var response = given()
                .when()
                .body(new LoginRequest("wojtek", "wojtek2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new RegisterRequest("adam", "adam2","adam","Kaluza"))
                .contentType(ContentType.JSON)
                .post("/api/Granted")
                .then()
                .statusCode(200);

    }

    @Test
    public void tryToLogInDefaultUserToEndPointGrantedAuthorityOnlyAccessForAdmin() {
        var response = given()
                .when()
                .body(new LoginRequest("adam", "adam2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new RegisterRequest("adam", "adam2","adam","Kaluza"))
                .contentType(ContentType.JSON)
                .post("/api/Granted")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void tryToLogInDefaultOrAdminUserToEndPointManagerOnlyAccessForManager() {
        var response = given()
                .when()
                .body(new LoginRequest("dominik", "dominik2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .get("/api/Menager")
                .then()
                .statusCode(HttpStatus.FORBIDDEN.value());
    }

    @Test
    public void logInManagerAuthorityToEndPointManagerOnlyAccessForManager() {
        var response = given()
                .when()
                .body(new LoginRequest("wojtek", "wojtek2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new RegisterRequest("adam", "adam2","adam","Kaluza"))
                .contentType(ContentType.JSON)
                .post("/api/Granted")
                .thenReturn();
        var response2 = given()
                .body(new LoginRequest("adam", "adam2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response2.getCookies())
                .body(new LoginRequest("adam", "adam2"))
                .get("/api/Menager")
                .then()
                .statusCode(200);
    }

    @Test
    public void logInToEndPointGrantedWithAdminButGrantedAuthorityRegisterIsEmpty() {
        var response = given()
                .when()
                .body(new LoginRequest("wojtek", "wojtek2"))
                .contentType(ContentType.JSON)
                .post("/api/login")
                .thenReturn();
        given()
                .cookies(response.getCookies())
                .body(new RegisterRequest("", "","",""))
                .contentType(ContentType.JSON)
                .post("/api/Granted")
                .then()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

    }
}
