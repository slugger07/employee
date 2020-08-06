package in.groww.employee.components;

import in.groww.employee.exceptions.BadRequestException;
import in.groww.employee.exceptions.InternalServerErrorException;
import in.groww.employee.models.Employee;
import in.groww.employee.repositories.EmployeeRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeRepo {

    private final EmployeeRepository employeeRepository;

    public EmployeeRepo(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public void addOrUpdateEmployee(final Employee employee)
            throws InternalServerErrorException {
        try {
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw new InternalServerErrorException("Cannot add or update Employee");
        }
    }


    @Transactional
    public List<Employee> getEmployees() throws InternalServerErrorException {
        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException("Cannot fetch employees");
        }
    }

    @Transactional
    public Employee getEmployeeById(final Long id) throws InternalServerErrorException, BadRequestException {
        Optional<Employee> employeeOptional;

        try {
            employeeOptional = employeeRepository.findById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Cannot fetch employees");
        }

        if(employeeOptional.isPresent()) {

            return employeeOptional.get();
        } else {
            throw new BadRequestException("Employee with the given Id does not exist");
        }
    }


    @Transactional
    public void deleteEmployee(final Employee employee) throws InternalServerErrorException {

        try {
            employeeRepository.delete(employee);
        } catch (Exception e) {
            throw new InternalServerErrorException("Cannot delete employees");
        }
    }

}
