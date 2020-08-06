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

/**
 * The type Employee controller.
 */
@RestController
public class EmployeeController {

    private final EmployeeServiceImpl employeeService;

    /**
     * Instantiates a new Employee controller.
     *
     * @param employeeService the employee service
     */
    public EmployeeController(final EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }


    /**
     * All list.
     *
     * @return the list
     * @throws InternalServerErrorException the internal server error exception
     */
    @GetMapping("/employees")
    List<EmployeeDto> all() throws InternalServerErrorException {
        return employeeService.getAllEmployees();
    }


    /**
     * Add employee response entity.
     *
     * @param employeeDto the employee dto
     * @return the response entity
     * @throws InternalServerErrorException the internal server error exception
     */
    @PostMapping("/addEmployee")
    ResponseEntity<ResponseMessage> addEmployee(@RequestBody EmployeeDto employeeDto)
            throws InternalServerErrorException {

        employeeService.addOrUpdateEmployee(employeeDto);
        return new ResponseEntity<>(new ResponseMessage("Successfully Added"),HttpStatus.OK);
    }


    /**
     * Gets employee.
     *
     * @param id the id
     * @return the employee
     * @throws BadRequestException          the bad request exception
     * @throws InternalServerErrorException the internal server error exception
     */
    @GetMapping("/employees/{id}")
    ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) throws BadRequestException,
            InternalServerErrorException {

        return new ResponseEntity<>(employeeService.getEmployee(id), HttpStatus.OK);
    }

    /**
     * Replace employee response entity.
     *
     * @param employeeDto the employee dto
     * @return the response entity
     * @throws InternalServerErrorException the internal server error exception
     */
    @PutMapping("/updateEmployee")
    ResponseEntity<ResponseMessage> replaceEmployee(@RequestBody EmployeeDto employeeDto)
            throws InternalServerErrorException {

        employeeService.addOrUpdateEmployee(employeeDto);
        return new ResponseEntity<>(new ResponseMessage("Successfully Updated"),HttpStatus.OK);
    }

    /**
     * Delete employee response entity.
     *
     * @param id the id
     * @return the response entity
     * @throws BadRequestException          the bad request exception
     * @throws InternalServerErrorException the internal server error exception
     */
    @DeleteMapping("/deleteEmployee/{id}")
    ResponseEntity<ResponseMessage> deleteEmployee(@PathVariable Long id) throws BadRequestException, InternalServerErrorException {

        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(new ResponseMessage("Successfully Deleted"), HttpStatus.OK);
    }
}

