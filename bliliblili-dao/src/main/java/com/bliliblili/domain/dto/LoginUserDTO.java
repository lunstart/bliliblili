package com.bliliblili.domain.dto;

import lombok.Data;
@Data
public class LoginUserDTO {

    //手机号/账号
    private String phone;

    //邮箱/账号
    private String email;

    private String password;

}
