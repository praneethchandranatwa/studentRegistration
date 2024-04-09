package com.awt.signin.signin.entity;

import lombok.Data;

@Data

public class ResetPasswordInputs {

    private Long id;

    private String oldPassword;

    private String newPassword;
}
