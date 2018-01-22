package com.joona.rest.service;

import com.joona.rest.request.AddBookRequestDTO;
import com.joona.rest.response.AddBookError;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.joona.rest.enums.Language.ENGLISH;
import static com.joona.rest.enums.Language.MALAYALAM;
import static java.util.regex.Pattern.*;

public class RequestValidator {
    public static List<AddBookError> validateBook(AddBookRequestDTO addBookRequestDTO) {
        String bookName = addBookRequestDTO.getBook();
        String author = addBookRequestDTO.getAuthor();
        String language = addBookRequestDTO.getLanguage();
        List<AddBookError> errorList = new ArrayList<>();
        validateBookName(bookName, errorList);
        validateAuthor(author, errorList);
        validateLanguage(language, errorList);
        return errorList;
    }

    private static void validateLanguage(String language, List<AddBookError> errorList) {
        if (StringUtils.isBlank(language)) {
            AddBookError addBookErrorLanguage = new AddBookError();
            addBookErrorLanguage.setErrorMessage("Language is mandatory");
            addBookErrorLanguage.setErrorCode("_ERR_SPECIFY_LANGUAGE");
            errorList.add(addBookErrorLanguage);
        } else if (!(MALAYALAM.name().equalsIgnoreCase(language) || ENGLISH.name().equalsIgnoreCase(language))) {
            AddBookError addBookErrorLanguageDetails = new AddBookError();
            addBookErrorLanguageDetails.setErrorMessage("Language name should be correct");
            addBookErrorLanguageDetails.setErrorCode("_ERR_UNKNWON_LANGUAGE");
            errorList.add(addBookErrorLanguageDetails);
        }
    }

    private static void validateAuthor(String author, List<AddBookError> errorList) {

        if (StringUtils.isBlank(author)) {
            AddBookError addBookErrorAuthor = new AddBookError();
            addBookErrorAuthor.setErrorMessage("Author name is mandatory");
            addBookErrorAuthor.setErrorCode("_ERR_REQUIRED_AUTHOR_NAME");
            errorList.add(addBookErrorAuthor);
        }
        else {
            String regexAuthorName = "^[A-Za-z\\s\\.]+$";
            Pattern pattern = compile(regexAuthorName, CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(author);
            if(!(matcher.matches()) || author.startsWith(".") || author.startsWith(" ")){
                AddBookError addBookErrorAuthor = new AddBookError();
                addBookErrorAuthor.setErrorMessage("Author name should not contain numerics and special charachers other than space and dot");
                addBookErrorAuthor.setErrorCode("_ERR_INVALID_CHARACTERS_IN_AUTHOR_NAME");
                errorList.add(addBookErrorAuthor);
            }

        }
    }

    private static void validateBookName(String bookName, List<AddBookError> errorList) {
        if (StringUtils.isBlank(bookName)) {
            AddBookError addBookError = new AddBookError();
            addBookError.setErrorMessage("Book name is mandatory");
            addBookError.setErrorCode("_ERR_REQUIRED_BOOK_NAME");
            errorList.add(addBookError);
        }
    }
}

