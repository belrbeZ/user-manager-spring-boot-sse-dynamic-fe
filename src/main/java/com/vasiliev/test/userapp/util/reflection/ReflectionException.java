package com.vasiliev.test.userapp.util.reflection;

/**
 * The type Reflection exception.
 *
 * @author Alexandr Vasiliev <alexandrvasilievby@gmail.com>
 */
public class ReflectionException extends RuntimeException {

    /**
     * Creates a new instance of the exception.
     *
     * @param message a descriptive exception message.
     */
    public ReflectionException(final String message) {
        super(message);
    }

    /**
     * Creates a new instance of the exception.
     *
     * @param cause an original cause of this exception.
     */
    public ReflectionException(final Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new instance of the exception.
     *
     * @param message a descriptive exception message.
     * @param cause   an original cause of this exception.
     */
    public ReflectionException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
