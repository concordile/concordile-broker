package io.github.leopold.exception.v1;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BaseNotFoundExceptionV1 extends IllegalStateException {

    public BaseNotFoundExceptionV1(String message) {
        super(message);
    }

}
