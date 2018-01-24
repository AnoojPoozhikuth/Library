package com.joona.rest.base;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class BaseTest {
    protected String getContentFromFile(String fileName) throws IOException {
        return IOUtils.toString(BaseTest.class.getResourceAsStream("/".concat(fileName)), "UTF-8");
    }
}
