package com.example.jpaexam.controller;

import com.example.jpaexam.entity.Employee;
import com.example.jpaexam.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.POST)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Employee> findAllEmployees (){
        return employeeRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Employee> detail(@PathVariable int id){
        return employeeRepository.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Employee update(@PathVariable int id, @RequestBody Employee updateEmployee) {
        Optional<Employee> userEdit = employeeRepository.findById(id);
        if (userEdit.isPresent()) {
            Employee employee = userEdit.get();
            employee.setName(updateEmployee.getName());
            employee.setWage(updateEmployee.getWage());

            return employeeRepository.save(employee);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id) {
        employeeRepository.deleteById(id);

        return "ok";
    }
}
