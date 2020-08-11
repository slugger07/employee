package in.groww.employee.dtos;

public class ResponseMessageWithId {

    private String message;
    private String Id;

    public ResponseMessageWithId(final String message, final String id) {
        this.message = message;
        Id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getId() {
        return Id;
    }

    public void setId(final String id) {
        Id = id;
    }
}
