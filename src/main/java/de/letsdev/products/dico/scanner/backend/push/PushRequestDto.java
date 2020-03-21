package de.letsdev.products.dico.scanner.backend.push;

import java.util.Objects;

public class PushRequestDto {

    /* mandatory, Eindeutige ID der Nachricht. An die ID wird in der Server Response der Übertragungsstatus angehängt*/
    private String id;

    /*  "message": "", /* mandatory, Text der Push-Nachricht*/
    private String message;

    private String title;

    /* optional, Filter zur Auswahl der Zielgeräte. Beispiel: (alias = [user.name] or location = [latitude = 49.005259, longitude = 8.429107, distance = 0.5]) and resolution = [width = 768, height = 1024]*/
    private String deviceQuery;

    /* optional, Beliebige Schlüssel-/Wert-Paare in JSON, die an die Geräte übertragen werden. Beispiel: {'chatMessage': 'Hey Pusher', 'chatMessageSender': 'lets PUSH'}*/
    private String pushData;

    /* optional, Flags zur Konfiguration der Push-Nachricht in JSON Beispiel: {'contentAvailable': true, 'launchImage': 'start.png'}*/
    private String pushFlags;

    private boolean sandboxMode = false;

    /* optional, Datum der Ausführung, Default: aktuelles Datum*/
    private String executionDate = "";

    //optional, UTC Timestamp in s. Bei 0 wird nur einmalig versucht, die Nachricht an das Gerät zuzustellen. Bei keiner Angabe des Werts, wird der jeweilige Betriebssystem-Standard gesetzt
    private long timeToLive = 0;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeviceQuery() {
        return deviceQuery;
    }

    public void setDeviceQuery(String deviceQuery) {
        this.deviceQuery = deviceQuery;
    }

    public String getPushData() {
        return pushData;
    }

    public void setPushData(String pushData) {
        this.pushData = pushData;
    }

    public String getPushFlags() {
        return pushFlags;
    }

    public void setPushFlags(String pushFlags) {
        this.pushFlags = pushFlags;
    }

    public boolean isSandboxMode() {
        return sandboxMode;
    }

    public void setSandboxMode(boolean sandboxMode) {
        this.sandboxMode = sandboxMode;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public long getTimeToLive() {
        return timeToLive;
    }

    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PushRequestDto pushRequestDto = (PushRequestDto) o;
        return sandboxMode == pushRequestDto.sandboxMode &&
                timeToLive == pushRequestDto.timeToLive &&
                Objects.equals(id, pushRequestDto.id) &&
                Objects.equals(message, pushRequestDto.message) &&
                Objects.equals(title, pushRequestDto.title) &&
                Objects.equals(deviceQuery, pushRequestDto.deviceQuery) &&
                Objects.equals(pushData, pushRequestDto.pushData) &&
                Objects.equals(pushFlags, pushRequestDto.pushFlags) &&
                Objects.equals(executionDate, pushRequestDto.executionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, title, deviceQuery, pushData, pushFlags, sandboxMode, executionDate, timeToLive);
    }

    @Override
    public String toString() {
        return "PushDto{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", title='" + title + '\'' +
                ", deviceQuery='" + deviceQuery + '\'' +
                ", pushData='" + pushData + '\'' +
                ", pushFlags='" + pushFlags + '\'' +
                ", sandboxMode=" + sandboxMode +
                ", executionDate='" + executionDate + '\'' +
                ", timeToLive=" + timeToLive +
                '}';
    }
}
