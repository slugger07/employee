package in.groww.employee.mappers;

import in.groww.employee.dtos.EmployeeDto;
import in.groww.employee.models.Employee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto convertEmployeeModelIntoDto(
            final Employee employee);

    Employee convertEmployeeDtoIntoModel(
            final EmployeeDto employeeDto);

    List<EmployeeDto> convertEmployeeListModelIntoDto(
            final List<Employee> employeeList);


}
