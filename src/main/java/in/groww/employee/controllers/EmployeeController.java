package in.groww.employee.controllers;

import in.groww.employee.dtos.EmployeeDto;
import in.groww.employee.dtos.ResponseMessage;
import in.groww.employee.exceptions.BadRequestException;
import in.groww.employee.exceptions.InternalServerErrorException;
import in.groww.employee.services.serviceImpl.EmployeeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    public EmployeeController(final EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/employees")
    List<EmployeeDto> all() throws InternalServerErrorException {
        return employeeService.getAllEmployees();
    }


    @PostMapping("/addEmployee")
    ResponseEntity<ResponseMessage> addEmployee(@RequestBody EmployeeDto employeeDto)
            throws InternalServerErrorException {

        employeeService.addOrUpdateEmployee(employeeDto);
        return new ResponseEntity<>(new ResponseMessage("Successfully Added"),HttpStatus.OK);
    }


    @GetMapping("/employees/{id}")
    ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) throws BadRequestException,
            InternalServerErrorException {

        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    @PutMapping("/updateEmployee")
    ResponseEntity<ResponseMessage> replaceEmployee(@RequestBody EmployeeDto employeeDto)
            throws InternalServerErrorException {

        employeeService.addOrUpdateEmployee(employeeDto);
        return new ResponseEntity<>(new ResponseMessage("Successfully Updated"),HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    ResponseEntity<ResponseMessage> deleteEmployee(@PathVariable Long id) throws BadRequestException, InternalServerErrorException {

        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(new ResponseMessage("Successfully Deleted"), HttpStatus.OK);
    }
}

