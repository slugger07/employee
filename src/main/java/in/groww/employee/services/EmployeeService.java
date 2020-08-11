package in.groww.employee.services;

import in.groww.employee.dtos.EmployeeDto;
import in.groww.employee.exceptions.BadRequestException;
import in.groww.employee.exceptions.InternalServerErrorException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The interface Employee service.
 */
@Service
public interface EmployeeService {

    /**
     * Gets all employees.
     *
     * @return the all employees
     * @throws InternalServerErrorException the internal server error exception
     */
    List<EmployeeDto> getAllEmployees() throws InternalServerErrorException;

    /**
     * Gets employee.
     *
     * @param id the id
     * @return the employee
     * @throws BadRequestException          the bad request exception
     * @throws InternalServerErrorException the internal server error exception
     */
    EmployeeDto getEmployee(final String id) throws BadRequestException, InternalServerErrorException;

    /**
     * Add or update employee.
     *
     * @param employeeDto the employee dto
     * @throws InternalServerErrorException the internal server error exception
     */
    EmployeeDto addOrUpdateEmployee(final EmployeeDto employeeDto) throws InternalServerErrorException;

    /**
     * Delete employee by id.
     *
     * @param Id the id
     * @throws BadRequestException          the bad request exception
     * @throws InternalServerErrorException the internal server error exception
     */
     void deleteEmployeeById(final String Id) throws BadRequestException, InternalServerErrorException;
}
