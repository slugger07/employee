package in.groww.employee.exceptions;

import in.groww.employee.dtos.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Exception handler.
 */
@RestControllerAdvice
public class ExceptionHandlerImpl {

    /**
     * Internal server error exception response entity.
     *
     * @param request the request
     * @param e       the e
     * @return the response entity
     */
    @ExceptionHandler({InternalServerErrorException.class})
    @ResponseBody
    public ResponseEntity<ResponseMessage> internalServerErrorException(HttpServletRequest request, Exception e) {
        return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Bad request exception response entity.
     *
     * @param request the request
     * @param e       the e
     * @return the response entity
     */
    @ExceptionHandler({BadRequestException.class})
    @ResponseBody
    public ResponseEntity<ResponseMessage> badRequestException(HttpServletRequest request, Exception e) {
        return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
