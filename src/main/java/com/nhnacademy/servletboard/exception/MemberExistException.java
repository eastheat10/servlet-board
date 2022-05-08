package com.nhnacademy.servletboard.exception;

public class MemberExistException extends IllegalArgumentException {

    public MemberExistException(String id) {
        super(id + "는 이미 존재하는 아이디 입니다.");
    }
}
