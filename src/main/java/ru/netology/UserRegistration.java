package ru.netology;

import lombok.Data;

@Data

public class UserRegistration {
    private final String login;
    private final String password;
    private final String status;
}
