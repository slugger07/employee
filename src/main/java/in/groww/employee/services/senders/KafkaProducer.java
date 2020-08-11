package in.groww.employee.services.senders;
import in.groww.employee.constants.KafkaConstants;
import in.groww.employee.dtos.EmployeeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    final private KafkaTemplate<String, EmployeeDto> kafkaTemplate;

    public KafkaProducer(final KafkaTemplate<String, EmployeeDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void updateEmployee(EmployeeDto employeeDto) {
        logger.info("kafka message sent");
        kafkaTemplate.send(KafkaConstants.TOPIC, employeeDto);
    }

}