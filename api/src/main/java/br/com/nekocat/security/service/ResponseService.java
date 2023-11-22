package br.com.nekocat.security.service;

import br.com.nekocat.security.contract.response.ResponseInterface;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseService implements ResponseInterface {
    @Override
    public ResponseEntity<?> badRequest() {
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<?> badRequest(Object body) {
        return ResponseEntity.badRequest().body(body);
    }

    @Override
    public ResponseEntity<?> internalServerError() {
        return ResponseEntity.internalServerError().build();
    }

    @Override
    public ResponseEntity<?> internalServerError(Object body) {
        return ResponseEntity.internalServerError().body(body);
    }

    @Override
    public ResponseEntity<?> ok() {
        return ResponseEntity.ok().build();
    }


    @Override
    public ResponseEntity<?> ok(Object body) {
        return ResponseEntity.ok().body(body);
    }

    @Override
    public ResponseEntity<?> created() {
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }
}
