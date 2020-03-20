package de.letsdev.products.dico.scanner.backend;

public class APIResponse {
    public APIResponse(String message, boolean error) {
        this.message = message;
        this.error = error;
    }

    private String message;
    private boolean error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
