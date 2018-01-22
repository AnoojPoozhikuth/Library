package com.joona.rest.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookError {
    String errorMessage;
    String errorCode;
}
