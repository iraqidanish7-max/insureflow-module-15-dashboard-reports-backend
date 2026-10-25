package com.insurance.reporting;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest(properties={"spring.datasource.url=jdbc:h2:mem:testdb","spring.datasource.driver-class-name=org.h2.Driver","spring.datasource.username=sa","spring.jpa.hibernate.ddl-auto=create-drop"})
class ReportingServiceApplicationTests {
    @Test void contextLoads() { }
}
