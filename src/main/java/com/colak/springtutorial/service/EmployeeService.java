package com.colak.springtutorial.service;

import com.colak.springtutorial.jpa.Employee;
import com.colak.springtutorial.repository.springrepository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public Flux<Employee> saveAllStudents(Flux<Employee> employeeFlux) {
        return repository.saveAll(employeeFlux);
    }

    public Mono<Employee> saveSingleEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Mono<Employee> findStudentById(Long id) {
        return repository.findById(id);
    }

    public Flux<Employee> findAllStudents() {
        return repository.findAll();
    }

    public Mono<Employee> updateEmployee(Employee employee, Long id) {
        return repository.findById(id)
                .flatMap(existingEmployee -> {
                    // update employee new values here
                    existingEmployee.setFirstName(employee.getFirstName());
                    existingEmployee.setLastName(employee.getLastName());

                    // now we save the existing employee with the new values
                    return repository.save(existingEmployee);
                });
    }

    public Mono<Void> deleteById(Long id) {
        return repository.deleteById(id);
    }
}
