package in.groww.employee.services;

import in.groww.employee.dtos.EmployeeDto;
import in.groww.employee.exceptions.BadRequestException;
import in.groww.employee.exceptions.InternalServerErrorException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    List<EmployeeDto> getAllEmployees() throws InternalServerErrorException;

    EmployeeDto getEmployee(final Long id) throws BadRequestException, InternalServerErrorException;

    void addOrUpdateEmployee(final EmployeeDto employeeDto) throws InternalServerErrorException;

    void deleteEmployeeById(final Long Id) throws BadRequestException, InternalServerErrorException;
}
