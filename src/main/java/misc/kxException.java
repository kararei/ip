package misc;

/**
 * Defines how to handle errors in the application.
 */
public class kxException extends Exception {

    /**
     * Constructs a new kxException with the specified error message.
     * @param message The specified error message.
     */
    public kxException(String message) {
        super(message);
    }
}
