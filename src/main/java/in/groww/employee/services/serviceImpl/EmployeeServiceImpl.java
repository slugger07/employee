package in.groww.employee.services.serviceImpl;

import in.groww.employee.components.EmployeeRepo;
import in.groww.employee.dtos.EmployeeDto;
import in.groww.employee.exceptions.BadRequestException;
import in.groww.employee.exceptions.InternalServerErrorException;
import in.groww.employee.mappers.EmployeeMapper;
import in.groww.employee.models.Employee;
import in.groww.employee.services.EmployeeService;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final EmployeeRepo employeeRepo;

    final static EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    public EmployeeServiceImpl(final EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }


    @Override
    public List<EmployeeDto> getAllEmployees() throws InternalServerErrorException {

        LOGGER.info("Getting all Employees");
        final List<Employee> employees = employeeRepo.getEmployees();
        return employeeMapper.convertEmployeeListModelIntoDto(employees);
    }


    @Override
    public EmployeeDto getEmployee(final Long id) throws BadRequestException,
            InternalServerErrorException {

        LOGGER.info("Getting an Employee using Id");
        final Employee employee = employeeRepo.getEmployeeById(id);
        return employeeMapper.convertEmployeeModelIntoDto(employee);
    }


    @Override
    public void addOrUpdateEmployee(final EmployeeDto employeeDto) throws InternalServerErrorException {

        LOGGER.info("Adding or Updating an Employee");
        employeeRepo.addOrUpdateEmployee(employeeMapper.convertEmployeeDtoIntoModel(employeeDto));
    }


    @Override
    public void deleteEmployeeById(final Long id) throws BadRequestException, InternalServerErrorException {

        final Employee employee = employeeRepo.getEmployeeById(id);

        employeeRepo.deleteEmployee(employee);
    }
}
