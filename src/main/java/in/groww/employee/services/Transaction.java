package in.groww.employee.services;

import in.groww.employee.exceptions.BadRequestException;
import in.groww.employee.exceptions.InternalServerErrorException;
import in.groww.employee.models.Employee;
import in.groww.employee.repositories.EmployeeRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * The type Employee repo.
 */
@Component
public class Transaction {

    private final EmployeeRepository employeeRepository;

    /**
     * Instantiates a new Employee repo.
     *
     * @param employeeRepository the employee repository
     */
    public Transaction(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Add or update employee.
     *
     * @param employee the employee
     * @throws InternalServerErrorException the internal server error exception
     */
    @Transactional
    public void addOrUpdateEmployee(final Employee employee)
            throws InternalServerErrorException {

        try {
            employeeRepository.save(employee);
        } catch (Exception e) {
            throw new InternalServerErrorException("Cannot add or update Employee");
        }
    }


    /**
     * Gets employees.
     *
     * @return the employees
     * @throws InternalServerErrorException the internal server error exception
     */
    @Transactional
    public List<Employee> getEmployees() throws InternalServerErrorException {

        try {
            return employeeRepository.findAll();
        } catch (Exception e) {
            throw new InternalServerErrorException("Cannot fetch employees");
        }
    }

    /**
     * Gets employee by id.
     *
     * @param id the id
     * @return the employee by id
     * @throws InternalServerErrorException the internal server error exception
     * @throws BadRequestException          the bad request exception
     */
    @Transactional
    public Employee getEmployeeById(final Long id) throws InternalServerErrorException, BadRequestException {
        Optional<Employee> employeeOptional;

        try {
            employeeOptional = employeeRepository.findById(id);
        } catch (Exception e) {
            throw new InternalServerErrorException("Couldn't fetch employee");
        }

        if(employeeOptional.isPresent()) {

            return employeeOptional.get();
        } else {
            throw new BadRequestException("Employee with the given Id does not exist");
        }
    }


    /**
     * Delete employee.
     *
     * @param employee the employee
     * @throws InternalServerErrorException the internal server error exception
     */
    @Transactional
    public void deleteEmployee(final Employee employee) throws InternalServerErrorException {

        try {
            employeeRepository.delete(employee);
        } catch (Exception e) {
            throw new InternalServerErrorException("Cannot delete Employee");
        }
    }

}
