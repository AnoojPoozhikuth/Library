package com.joona.rest.controller;

import com.joona.rest.request.AddBookRequestDTO;
import com.joona.rest.response.AddBookError;
import com.joona.rest.response.ResponseDTO;
import com.joona.rest.request.RequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class AddBookController {
    @RequestMapping(value = "/library", method = POST)
    public ResponseEntity<ResponseDTO> addBook(@RequestBody AddBookRequestDTO addBookRequestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        List<AddBookError> addBookErrors = RequestValidator.validateBook(addBookRequestDTO);
        if (addBookErrors.isEmpty()) {
            responseDTO.setStatus("success");
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } else {
            responseDTO.setStatus("failure");
            responseDTO.setErrors(addBookErrors);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }
}
