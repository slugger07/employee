package in.groww.employee.services.serviceImpl;

import in.groww.employee.services.RedisOperation;
import in.groww.employee.services.Transaction;
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

/**
 * The type Employee service.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final Transaction transaction;

    private final RedisOperation redisOperation;


    /**
     * The constant employeeMapper.
     */
    final static EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    /**
     * Instantiates a new Employee service.
     * @param transaction the employee repo
     * @param redisOperation
     */
    public EmployeeServiceImpl(final Transaction transaction, final RedisOperation redisOperation) {

        this.transaction = transaction;
        this.redisOperation = redisOperation;
    }


    @Override
    public List<EmployeeDto> getAllEmployees() throws InternalServerErrorException {

        LOGGER.info("Getting all Employees");
        return getAllEmployeesFromDbAndUpdateRedis();
    }


    @Override
    public EmployeeDto getEmployee(final String id) throws BadRequestException,
            InternalServerErrorException {

        LOGGER.info("Getting an Employee using Id");

        return  redisOperation.containsKey(id) ? redisOperation.getObjectFromRedis(id)
                : getEmployeeFromDbAndUpdateRedis(id);
    }


    @Override
    public String addOrUpdateEmployee(final EmployeeDto employeeDto) throws
            InternalServerErrorException {

        LOGGER.info("Adding or Updating an Employee");

        final String employeeId = transaction.addOrUpdateEmployee(
                employeeMapper.convertEmployeeDtoIntoModel(employeeDto));

        employeeDto.setId(employeeId);
        redisOperation.save(employeeDto);

        return employeeId;
    }


    @Override
    public void deleteEmployeeById(final String id) throws
            BadRequestException, InternalServerErrorException {

        final Employee employee = transaction.getEmployeeById(id);
        redisOperation.remove(id);
        transaction.deleteEmployee(employee);
    }

    private EmployeeDto getEmployeeFromDbAndUpdateRedis(final String id) throws
            BadRequestException, InternalServerErrorException {

        LOGGER.info("Employee not found in Redis.. retrieving from database");

        final EmployeeDto employeeDto = employeeMapper.convertEmployeeModelIntoDto(
                transaction.getEmployeeById(id));

        redisOperation.save(employeeDto);
        return employeeDto;
    }

    private List<EmployeeDto> getAllEmployeesFromDbAndUpdateRedis() throws
            InternalServerErrorException {

        LOGGER.info("Employees not found in Redis.. retrieving from database");

        final List<EmployeeDto> employeeDto = employeeMapper.convertEmployeeListModelIntoDto(
                transaction.getEmployees());

        redisOperation.saveAll(employeeDto);
        return employeeDto;
    }


}
