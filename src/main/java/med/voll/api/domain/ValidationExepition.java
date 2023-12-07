package med.voll.api.domain;

public class ValidationExepition extends RuntimeException {

    public ValidationExepition (String message) {
        super(message);
    }
}
