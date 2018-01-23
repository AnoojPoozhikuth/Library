package com.joona.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookRequestDTO {
    private String book;
    private String author;
    private String category;
    private String language;
}
