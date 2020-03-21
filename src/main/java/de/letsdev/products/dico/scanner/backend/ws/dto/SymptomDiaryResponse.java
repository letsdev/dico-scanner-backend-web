package de.letsdev.products.dico.scanner.backend.ws.dto;

import java.util.Objects;

public class SymptomDiaryResponse {

    private boolean maybeInfected = false;

    private String message;

    private int code;

    public boolean isMaybeInfected() {
        return maybeInfected;
    }

    public void setMaybeInfected(boolean maybeInfected) {
        this.maybeInfected = maybeInfected;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SymptomDiaryResponse that = (SymptomDiaryResponse) o;
        return maybeInfected == that.maybeInfected &&
                code == that.code &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maybeInfected, message, code);
    }

    @Override
    public String toString() {
        return "SymptomDiaryResponse{" +
                "maybeInfected=" + maybeInfected +
                ", message='" + message + '\'' +
                ", code=" + code +
                '}';
    }
}
