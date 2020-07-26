import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleWeatherTest {
    //api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}
    String apiKey = "af0b4076b9c58d2580128bd53ae70c74";
    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("https://api.openweathermap.org")
            .build();


    @Test
    public void SimpleTestOne() {
        String city = "Saint Petersburg";

        given()
                    .spec(requestSpec)
                .when()
                    .get("/data/2.5/weather?q="+city+"&appid="+apiKey)
                .then()
                    .log().body()
                    .assertThat()
                    .statusCode(200)
                    .body("name", equalTo(city));
    }

    @Test
    public void SimpleTestTwo() {
        String city = "Moscow";

        String name =
                given()
                            .spec(requestSpec)
                        .when()
                            .get("https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+apiKey)
                        .then()
                            .statusCode(200)
                            .log().body()
                            .extract().path("name");
        assertEquals(city, name);
    }

    @Test
    public void SimpleTestThree() {
        String city = "London";

        CityWeather w =

                given()
                            .spec(requestSpec)
                        .when()
                            .get("/data/2.5/weather?q="+city+"&appid="+apiKey)
                        .then()
                            .statusCode(200)
                            .log().body()
                            .extract().as(CityWeather.class);

        assertEquals(city, w.getName());
        System.out.printf("=======Today in %s=======%n", w.getName());
        System.out.printf("Temperature: %.0f%n",w.getMain().getTemp());
        System.out.printf("Feels like: %.0f%n", w.getMain().getFeels_like());
        System.out.printf("Pressure: %d%n", w.getMain().getPressure());
        System.out.printf("Humidity: %d%n", w.getMain().getHumidity());
    }


}
