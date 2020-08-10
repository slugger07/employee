package in.groww.employee.services.senders;


import in.groww.employee.dtos.EmployeeDto;
import in.groww.employee.models.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    final private AmqpTemplate rabbitTemplate;

    final static private Logger LOGGER = LoggerFactory.getLogger(RabbitMQSender.class);

    @Value("${dev.rabbitmq.exchange}")
    private String exchange;

    @Value("${dev.rabbitmq.routingkey}")
    private String routingKey;


    public RabbitMQSender(final AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(EmployeeDto employeeDto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, employeeDto);
        LOGGER.info("Sent msg via Rabbit MQ= " + employeeDto);
    }

}
