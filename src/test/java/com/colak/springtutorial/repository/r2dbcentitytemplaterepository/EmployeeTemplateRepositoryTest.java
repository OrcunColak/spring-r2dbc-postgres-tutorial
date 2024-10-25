package com.colak.springtutorial.repository.r2dbcentitytemplaterepository;

import com.colak.springtutorial.jpa.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class EmployeeTemplateRepositoryTest {

    @Autowired
    private EmployeeTemplateRepository employeeTemplateRepository;


    @Test
    void findAll() {
        Flux<Employee> employeeFlux = employeeTemplateRepository.findAll();

        StepVerifier.create(employeeFlux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void findById() {
        Mono<Employee> employeeMono = employeeTemplateRepository.findById(1);

        StepVerifier.create(employeeMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findByFirstNameContains() {
        Flux<Employee> employeeFlux = employeeTemplateRepository.findByFirstNameContains("employee");

        StepVerifier.create(employeeFlux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void deleteById() {
        Mono<Long> numberOfRows = employeeTemplateRepository.deleteById(1);

        StepVerifier.create(numberOfRows)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void save() {
        Employee employee = new Employee();
        employee.setId(5L);
        employee.setFirstName("employee5");
        employee.setLastName("lastname5");
        employee.setNew(true);
        Mono<Long> id = employeeTemplateRepository.save(employee);

        StepVerifier.create(id)
                .expectNext(5L)
                .verifyComplete();
    }

    @Test
    void update() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("employee11");
        employee.setLastName("lastname11");
        Mono<Long> numberOfRows = employeeTemplateRepository.update(employee);

        StepVerifier.create(numberOfRows)
                .expectNextCount(1)
                .verifyComplete();
    }
}
