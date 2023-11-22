package br.com.nekocat.security.contract.response;

import org.springframework.http.ResponseEntity;

public interface ResponseInterface {
    ResponseEntity<?> badRequest();

    ResponseEntity<?> badRequest(Object body);

    ResponseEntity<?> internalServerError();

    ResponseEntity<?> internalServerError(Object body);

    ResponseEntity<?> ok();

    ResponseEntity<?> ok(Object body);

    ResponseEntity<?> created();
}
