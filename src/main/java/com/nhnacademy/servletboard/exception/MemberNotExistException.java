package com.nhnacademy.servletboard.exception;

public class MemberNotExistException extends IllegalArgumentException {

    public MemberNotExistException() {
        super("해당 사용자가 존재하지 않습니다.");
    }
}
