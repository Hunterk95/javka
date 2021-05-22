package scanner;

/**
 * Throws to indicate that command line parameter key must have a value
 */
public class NotFoundArgumentKeyValueException extends IllegalArgumentException {
    /**
     * @param key for which command line parameter key not found argument
     */
    public NotFoundArgumentKeyValueException(String key) {
        super("Not found any arguments after key: " + key);
    }
}
