package in.groww.employee.exceptions;


public class InternalServerErrorException extends Exception {

    private static final long serialVersionUID = 1L;

    private String message;

    public InternalServerErrorException() {
        super();
    }

    public InternalServerErrorException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
