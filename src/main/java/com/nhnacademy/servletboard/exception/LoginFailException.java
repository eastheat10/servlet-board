package com.nhnacademy.servletboard.exception;

public class LoginFailException extends IllegalArgumentException {

    public LoginFailException() {
        super("아이디 혹은 비밀번호가 다릅니다.");
    }
}
