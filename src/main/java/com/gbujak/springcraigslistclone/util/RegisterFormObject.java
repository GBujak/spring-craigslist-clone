package com.gbujak.springcraigslistclone.util;

import lombok.Data;

import java.util.Optional;

@Data
public class RegisterFormObject {
    private String username;
    private String password;
    private String passwordConfirm;

    public Optional<String> getPasswordIfSame() {
        if (!password.equals(passwordConfirm))
            return Optional.empty();
        else return Optional.of(password);
    }
}
