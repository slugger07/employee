package in.groww.employee.exceptions;

import in.groww.employee.dtos.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler({InternalServerErrorException.class})
    @ResponseBody
    public ResponseEntity<ResponseMessage> internalServerErrorException(HttpServletRequest request, Exception e) {
        return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseBody
    public ResponseEntity<ResponseMessage> badRequestException(HttpServletRequest request, Exception e) {
        return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
