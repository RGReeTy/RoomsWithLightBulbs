package net.itspartner.rooms_with_light_bulbs.service;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "ServiceException: " + this.getMessage();
    }

}
