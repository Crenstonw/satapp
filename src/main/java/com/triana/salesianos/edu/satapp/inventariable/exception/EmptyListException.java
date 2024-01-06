package com.triana.salesianos.edu.satapp.inventariable.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.Instant;

public class EmptyListException extends ErrorResponseException {

    public EmptyListException() {

        super(HttpStatus.NO_CONTENT, of("List is empty"), null);
    }

    public static ProblemDetail of(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
        problemDetail.setTitle("List is empty");
        problemDetail.setType(URI.create("https://api.midominio.com/errors/user-not-found"));
        problemDetail.setProperty("entityType", "Note");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
