package in.groww.employee.mappers;

import in.groww.employee.dtos.EmployeeDto;
import in.groww.employee.models.Employee;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The interface Employee mapper.
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    /**
     * Convert employee model into dto employee dto.
     *
     * @param employee the employee
     * @return the employee dto
     */
    EmployeeDto convertEmployeeModelIntoDto(
            final Employee employee);


    /**
     * Convert employee dto into model employee.
     *
     * @param employeeDto the employee dto
     * @return the employee
     */
    Employee convertEmployeeDtoIntoModel(
            final EmployeeDto employeeDto);

    /**
     * Convert employee list model into dto list.
     *
     * @param employeeList the employee list
     * @return the list
     */
    List<EmployeeDto> convertEmployeeListModelIntoDto(
            final List<Employee> employeeList);


}
