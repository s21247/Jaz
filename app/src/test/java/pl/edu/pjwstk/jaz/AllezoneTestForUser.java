package pl.edu.pjwstk.jaz;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import pl.edu.pjwstk.jaz.request.*;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AllezoneTestForUser {

    private static Response adminResponse;
    private static Response userResponse;
    private static Response notLoggedInUser;

    @BeforeClass
    public static void beforeClass() throws Exception {

        given()
                .body(new RegisterRequest("wojtek", "wojtek2", "Wojtek", "Piorecki")) //admin user
                .contentType(ContentType.JSON)
                .when()
                .post("/api/register")
                .thenReturn();
        given()
                .body(new RegisterRequest("adam", "adam2", "Adam", "Kaluza")) //User
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
        userResponse = given()
                .body(new LoginRequest("adam", "adam2"))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/login")
                .thenReturn();
    }


    @Test
    public void creatingNewAuctionShouldResponse200() {
        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("link1",1));
        photoRequestList.add(new PhotoRequest("link2",2));
        photoRequestList.add(new PhotoRequest("link3",3));

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("170cm","Szerokosc"));
        parameterRequestList.add(new ParameterRequest("100","Wysokosc"));
        parameterRequestList.add(new ParameterRequest("120","Dlugosc"));

        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("meble","Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .then()
                .statusCode(200);
    }

    @Test
    public void editAuctionShouldResponse200() {
        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("link1",1));
        photoRequestList.add(new PhotoRequest("link2",2));
        photoRequestList.add(new PhotoRequest("link3",3));

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("170cm","Szerokosc"));
        parameterRequestList.add(new ParameterRequest("100","Wysokosc"));
        parameterRequestList.add(new ParameterRequest("120","Dlugosc"));

        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("meble","Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("jednak troche bylo smigae",20,"krzeselko",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/updateAuction/26")
                .then()
                .statusCode(200);
    }

    @Test
    public void editAuctionShouldResponse500WrongUserLogged() {
        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("link1",1));
        photoRequestList.add(new PhotoRequest("link2",2));
        photoRequestList.add(new PhotoRequest("link3",3));

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("170cm","Szerokosc"));
        parameterRequestList.add(new ParameterRequest("100","Wysokosc"));
        parameterRequestList.add(new ParameterRequest("120","Dlugosc"));

        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("meble","Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new AuctionRequest("jednak troche bylo smigae",20,"krzeselko",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/updateAuction/26")
                .then()
                .statusCode(500);
    }

    @Test
    public void viewAuctionshouldResponse200() {
        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("link1",1));
        photoRequestList.add(new PhotoRequest("link2",2));
        photoRequestList.add(new PhotoRequest("link3",3));

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("170cm","Szerokosc"));
        parameterRequestList.add(new ParameterRequest("100","Wysokosc"));
        parameterRequestList.add(new ParameterRequest("120","Dlugosc"));

        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("meble","Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .get("/api/viewAuction/26")
                .then()
                .statusCode(200);

    }

    @Test
    public void viewAllAuctionsShouldResponse200() {
        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("link1",1));
        photoRequestList.add(new PhotoRequest("link2",2));
        photoRequestList.add(new PhotoRequest("link3",3));

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("170cm","Szerokosc"));
        parameterRequestList.add(new ParameterRequest("100","Wysokosc"));
        parameterRequestList.add(new ParameterRequest("120","Dlugosc"));

        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("meble","Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .get("/api/viewAllAuction")
                .then()
                .statusCode(200);

    }

    @Test
    public void viewAuctionWithMiniatureShouldResponse200() {
        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("link1",1));
        photoRequestList.add(new PhotoRequest("link2",2));
        photoRequestList.add(new PhotoRequest("link3",3));

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("170cm","Szerokosc"));
        parameterRequestList.add(new ParameterRequest("100","Wysokosc"));
        parameterRequestList.add(new ParameterRequest("120","Dlugosc"));

        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("meble","Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .get("/api/viewAuctionWithMiniature")
                .then()
                .statusCode(200);
    }

    @Test
    public void viewAuctionWithMiniatureButDifferentPersonViewAuctionShouldResponse200() {
        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("link1",1));
        photoRequestList.add(new PhotoRequest("link2",2));
        photoRequestList.add(new PhotoRequest("link3",3));

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("170cm","Szerokosc"));
        parameterRequestList.add(new ParameterRequest("100","Wysokosc"));
        parameterRequestList.add(new ParameterRequest("120","Dlugosc"));

        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("meble","Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .get("/api/viewAuctionWithMiniature")
                .then()
                .statusCode(200);
    }

    @Test
    public void viewAuctionWithMiniatureButPersonIsNotLoggedInShouldResponse403() {
        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("link1",1));
        photoRequestList.add(new PhotoRequest("link2",2));
        photoRequestList.add(new PhotoRequest("link3",3));

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("170cm","Szerokosc"));
        parameterRequestList.add(new ParameterRequest("100","Wysokosc"));
        parameterRequestList.add(new ParameterRequest("120","Dlugosc"));

        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("meble","Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        given()
                .cookies(notLoggedInUser.getCookies())
                .get("/api/viewAuctionWithMiniature")
                .then()
                .statusCode(403);
    }
    @Test
    public void checkingVersionShouldResponse500() {
        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("link1",1));
        photoRequestList.add(new PhotoRequest("link2",2));
        photoRequestList.add(new PhotoRequest("link3",3));

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("170cm","Szerokosc"));
        parameterRequestList.add(new ParameterRequest("100","Wysokosc"));
        parameterRequestList.add(new ParameterRequest("120","Dlugosc"));

        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("meble","Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        2L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/updateAuction/26")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/updateAuction/26")
                .then()
                .statusCode(500);
    }

    @Test
    public void checkingVersionShouldResponse200() {
        List<PhotoRequest> photoRequestList = new ArrayList<>();
        photoRequestList.add(new PhotoRequest("link1",1));
        photoRequestList.add(new PhotoRequest("link2",2));
        photoRequestList.add(new PhotoRequest("link3",3));

        List<ParameterRequest> parameterRequestList = new ArrayList<>();
        parameterRequestList.add(new ParameterRequest("170cm","Szerokosc"));
        parameterRequestList.add(new ParameterRequest("100","Wysokosc"));
        parameterRequestList.add(new ParameterRequest("120","Dlugosc"));

        given()
                .cookies(adminResponse.getCookies())
                .body(new SectionRequest("Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addSection")
                .thenReturn();
        given()
                .cookies(adminResponse.getCookies())
                .body(new CategoryRequest("meble","Dom"))
                .contentType(ContentType.JSON)
                .post("/api/addCategory")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        1L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/addAuction")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        2L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/updateAuction/26")
                .thenReturn();
        given()
                .cookies(userResponse.getCookies())
                .body(new AuctionRequest("nie smigane",100,"krzeslo",
                        3L,photoRequestList,parameterRequestList,"meble"))
                .contentType(ContentType.JSON)
                .post("/api/updateAuction/26")
                .then()
                .statusCode(200);
    }
}
