package in.groww.employee.services.serviceImpl;

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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Employee service.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    private final Transaction transaction;


    /**
     * The constant employeeMapper.
     */
    final static EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    /**
     * Instantiates a new Employee service.
     * @param transaction the employee repo
     */
    public EmployeeServiceImpl(final Transaction transaction) {

        this.transaction = transaction;
    }


    @Override
    @Cacheable(value= "allEmployeeCache", unless= "#result.size() == 0")
    public List<EmployeeDto> getAllEmployees() throws InternalServerErrorException {

        LOGGER.info("Getting all Employees");
        return employeeMapper.convertEmployeeListModelIntoDto(
                transaction.getEmployees());
    }


    @Override
    @Cacheable(value= "employeeCache", key= "#id")
    public EmployeeDto getEmployee(final String id) throws BadRequestException,
            InternalServerErrorException {

        LOGGER.info("Getting an Employee using Id");
        return employeeMapper.convertEmployeeModelIntoDto(transaction.getEmployeeById(id));
    }


    @Override
    @Caching(
            put= { @CachePut(value= "employeeCache", key= "#employeeDto.id") },
            evict= { @CacheEvict(value= "allEmployeeCache", allEntries= true) }
    )
    public EmployeeDto addOrUpdateEmployee(final EmployeeDto employeeDto) throws
            InternalServerErrorException {

        LOGGER.info("Adding or Updating an Employee");

        final String employeeId = transaction.addOrUpdateEmployee(
                employeeMapper.convertEmployeeDtoIntoModel(employeeDto));

        employeeDto.setId(employeeId);
        return employeeDto;
    }


    @Override
    @Caching(
            evict= {
                    @CacheEvict(value= "employeeCache", key= "#id"),
                    @CacheEvict(value= "allEmployeeCache", allEntries= true)
            }
    )
    public void deleteEmployeeById(final String id) throws
            BadRequestException, InternalServerErrorException {

        final Employee employee = transaction.getEmployeeById(id);
        transaction.deleteEmployee(employee);
    }

}
