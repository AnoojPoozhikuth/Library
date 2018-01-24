package com.joona.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookError {
    private String errorMessage;
    private String errorCode;
}
