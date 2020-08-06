package in.groww.employee.exceptions;

public class BadRequestException extends Exception {
    private static final long serialVersionUID = 1L;

    private String message;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
