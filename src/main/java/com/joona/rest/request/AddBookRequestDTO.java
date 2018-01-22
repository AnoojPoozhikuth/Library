package com.joona.rest.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddBookRequestDTO {
    String book;
    String author;
    String category;
    String language;
}
