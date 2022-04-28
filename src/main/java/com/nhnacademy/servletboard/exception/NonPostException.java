package com.nhnacademy.servletboard.exception;

public class NonPostException extends IllegalStateException  {
    public NonPostException() {
        super("해당 게시물이 존재하지 않습니다.");
    }
}
