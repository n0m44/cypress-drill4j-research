package com.github.n0m44.service;

/**
 * Пользователь не найден
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
