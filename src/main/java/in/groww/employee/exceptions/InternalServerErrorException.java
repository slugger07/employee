package in.groww.employee.exceptions;


/**
 * The type Internal server error exception.
 */
public class InternalServerErrorException extends Exception {

    private static final long serialVersionUID = 1L;

    private String message;

    /**
     * Instantiates a new Internal server error exception.
     */
    public InternalServerErrorException() {
        super();
    }

    /**
     * Instantiates a new Internal server error exception.
     *
     * @param message the message
     */
    public InternalServerErrorException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
