package com.nhnacademy.servletboard.exception;

public class PostNotExistException extends IllegalStateException  {

    public PostNotExistException() {
        super("해당 게시물이 존재하지 않습니다.");
    }
}
