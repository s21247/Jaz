package pl.edu.pjwstk.jaz;


import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@IntegrationTest
public class AverageIT {
    @Test
    public void when_no_parameter_supplied_should_print_a_message() {
            //@formatter:off
        given()
        .when()
                 .get("/api/average")
        .then()
                .contentType(ContentType.JSON)
                .body("result", equalTo("Please put the parameters."));
                //@formatter:on
        }

    @Test
    public void should_give_average_case_1() {
        //@formatter:off
        given()
                .param("numbers", 2,1,5,6)
        .when()
                .get("/api/average")
        .then()
                .contentType(ContentType.JSON)
                .body("result", equalTo("Average equals to: 3.5"));
        //@formatter:on
    }

    @Test
    public void should_give_average_case_2() {
        //@formatter:off
        given()
                .param("numbers", 4,3,1,7,5)
        .when()
                .get("/api/average")
        .then()
                .contentType(ContentType.JSON)
                .body("result", equalTo("Average equals to: 4"));
        //@formatter:on
    }

    @Test
    public void should_give_average_case_3() {
        //@formatter:off
        given()
                .param("numbers", 2,1,1)
        .when()
                .get("/api/average")
        .then()
                .contentType(ContentType.JSON)
                .body("result", equalTo("Average equals to: 1.33"));
        //@formatter:on
    }



}

