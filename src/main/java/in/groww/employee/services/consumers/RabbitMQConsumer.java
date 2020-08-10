package in.groww.employee.services.consumers;

import in.groww.employee.dtos.EmployeeDto;
import in.groww.employee.exceptions.InternalServerErrorException;
import in.groww.employee.services.EmployeeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer{

    private final EmployeeService employeeService;

    public RabbitMQConsumer(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RabbitListener(queues = "${dev.rabbitmq.queue}")
    public void receivedMessage(EmployeeDto employeeDto) throws InternalServerErrorException {
        employeeService.addOrUpdateEmployee(employeeDto);
    }
}
