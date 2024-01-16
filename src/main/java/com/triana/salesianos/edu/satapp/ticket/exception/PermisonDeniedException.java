package com.triana.salesianos.edu.satapp.ticket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

import java.net.URI;
import java.time.Instant;

public class PermisonDeniedException extends ErrorResponseException {

    public PermisonDeniedException() {

        super(HttpStatus.UNAUTHORIZED, of("you don't have permisons to edit/delete this ticket"), null);
    }

    public static ProblemDetail of(String message) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, message);
        problemDetail.setTitle("you don't have permisons to edit/delete this ticket");
        problemDetail.setType(URI.create("https://api.midominio.com/errors/user-not-found"));
        problemDetail.setProperty("entityType", "Note");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
