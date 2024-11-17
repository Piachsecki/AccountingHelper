//package com.piasecki;
//
//import com.piasecki.repository.UserRepository;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.MySQLContainer;
//
//public class UserControllerTest {
//    @LocalServerPort
//    private Integer port;
//
//    static MySQLContainer<?> postgres = new MySQLContainer<>(
//            "mysql:latest"
//    );
//
//    @BeforeAll
//    static void beforeAll() {
//        postgres.start();
//    }
//
//    @AfterAll
//    static void afterAll() {
//        postgres.stop();
//    }
//
//    @DynamicPropertySource
//    static void configureProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgres::getJdbcUrl);
//        registry.add("spring.datasource.username", postgres::getUsername);
//        registry.add("spring.datasource.password", postgres::getPassword);
//    }
//
//    @Autowired
//    UserRepository userRepository;
//
//    @BeforeEach
//    void setUp() {
//        RestAssured.baseURI = "http://localhost:" + port;
//        customerRepository.deleteAll();
//    }
//
//    @Test
//    void shouldGetAllCustomers() {
//        List<Customer> customers = List.of(
//                new Customer(null, "John", "john@mail.com"),
//                new Customer(null, "Dennis", "dennis@mail.com")
//        );
//        customerRepository.saveAll(customers);
//
//        given()
//                .contentType(ContentType.JSON)
//                .when()
//                .get("/api/customers")
//                .then()
//                .statusCode(200)
//                .body(".", hasSize(2));
//    }
//
//
//}
