package in.groww.employee.controllers;

import in.groww.employee.dtos.EmployeeDto;
import in.groww.employee.dtos.ResponseMessage;
import in.groww.employee.dtos.ResponseMessageWithId;
import in.groww.employee.exceptions.BadRequestException;
import in.groww.employee.exceptions.InternalServerErrorException;
import in.groww.employee.services.senders.KafkaProducer;
import in.groww.employee.services.senders.RabbitMQSender;
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

    private final RabbitMQSender rabbitMQSender;

    private final KafkaProducer kafkaProducer;

    /**
     * Instantiates a new Employee controller.
     *  @param employeeService the employee service
     * @param rabbitMQSender
     * @param kafkaProducer
     */
    public EmployeeController(final EmployeeServiceImpl employeeService, final RabbitMQSender rabbitMQSender,
                              final KafkaProducer kafkaProducer) {
        this.employeeService = employeeService;
        this.rabbitMQSender = rabbitMQSender;
        this.kafkaProducer = kafkaProducer;
    }


    /**
     * All list.
     *
     * @return the list
     * @throws InternalServerErrorException the internal server error exception
     */
    @GetMapping("/employees")
    public List<EmployeeDto> all() throws InternalServerErrorException {
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
    public ResponseEntity<ResponseMessageWithId> addEmployee(@RequestBody EmployeeDto employeeDto)
            throws InternalServerErrorException {

        final String id = employeeService.addOrUpdateEmployee(employeeDto);
        return new ResponseEntity<>(new ResponseMessageWithId(
                "Successfully Added the Employee", id),HttpStatus.OK);
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
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable String id) throws BadRequestException,
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
    ResponseEntity<ResponseMessageWithId> replaceEmployee(@RequestBody EmployeeDto employeeDto)
            throws InternalServerErrorException {

        final String id = employeeService.addOrUpdateEmployee(employeeDto);
        return new ResponseEntity<>(new ResponseMessageWithId(
                "Successfully Updated Employee", id),HttpStatus.OK);
    }

    /**
     * Update employee by rmq response entity.
     *
     * @param employeeDto the employee dto
     * @return the response entity
     */
    @PutMapping("/updateEmployeeData")
    public ResponseEntity<ResponseMessage> updateEmployeeByRMQ(@RequestBody EmployeeDto employeeDto) {

        rabbitMQSender.send(employeeDto);
        return new ResponseEntity<>(new ResponseMessage("Successfully Updated Using RMQ"),HttpStatus.OK);
    }

    @PutMapping("/publishEmployee")
    public ResponseEntity<ResponseMessage> updateEmployeeByKafka(@RequestBody EmployeeDto employeeDataDto) {

        kafkaProducer.updateEmployee(employeeDataDto);
        return new ResponseEntity<>(new ResponseMessage("Successfully Updated Using kafka"),HttpStatus.OK);
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
    ResponseEntity<ResponseMessageWithId> deleteEmployee(@PathVariable String id) throws BadRequestException, InternalServerErrorException {

        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(new ResponseMessageWithId("Successfully Deleted the Item", id), HttpStatus.OK);
    }
}

