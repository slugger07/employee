package in.groww.employee.services;

import in.groww.employee.constants.RedisConstants;
import in.groww.employee.dtos.EmployeeDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisOperation {

    private final RedisTemplate<String, EmployeeDto> redisTemplate;

    private final ValueOperations<String, EmployeeDto> valueOperations;

    public RedisOperation(final RedisTemplate<String, EmployeeDto> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    public boolean containsKey(final String key) {
        return redisTemplate.hasKey(key);
    }

    public EmployeeDto getObjectFromRedis(final String id) {
        return valueOperations.get(id);
    }


    public void saveAll(final List<EmployeeDto> employeeDtoList) {
        for (EmployeeDto employeeDto : employeeDtoList) {
            valueOperations.set(employeeDto.getId(), employeeDto, RedisConstants.TIME_TO_LIVE, TimeUnit.MINUTES);
        }
    }

    public void save(final EmployeeDto employeeDto) {
        valueOperations.set(employeeDto.getId(), employeeDto, RedisConstants.TIME_TO_LIVE, TimeUnit.MINUTES);
    }

    public void remove(final String id) {
        redisTemplate.delete(id);
    }
}
