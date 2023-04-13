package ru.netology;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserRegistration {
    private final String login;
    private final String password;
    private final String status;
}
