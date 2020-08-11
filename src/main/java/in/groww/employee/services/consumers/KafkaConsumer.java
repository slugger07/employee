package in.groww.employee.services.consumers;

import in.groww.employee.dtos.EmployeeDto;
import in.groww.employee.exceptions.InternalServerErrorException;
import in.groww.employee.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private static final String TOPIC = "employees";

    final private EmployeeService employeeService;

    public KafkaConsumer(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @KafkaListener(topics = TOPIC)
    public void updateEmployee(EmployeeDto employeeDto) throws InternalServerErrorException {

        LOGGER.info("kafka consumer : message received" + employeeDto.getName());
        employeeService.addOrUpdateEmployee(employeeDto);
    }
}