package com.merchant.portal.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class NoRecordFoundException extends Exception {

    public NoRecordFoundException() {
        super("No Record found ");
        log.warn("no record found");
    }

    public NoRecordFoundException(String message) {
        super(message);
    }
}
