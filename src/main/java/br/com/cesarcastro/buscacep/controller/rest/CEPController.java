package br.com.cesarcastro.buscacep.controller.rest;

import br.com.cesarcastro.buscacep.controller.api.ICEPController;
import br.com.cesarcastro.buscacep.domain.model.response.CEPResponse;
import br.com.cesarcastro.buscacep.domain.service.CEPService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CEPController implements ICEPController {
    private final CEPService service;
    @Override
    public ResponseEntity<CEPResponse> buscarCEP(String cep) {
        return ResponseEntity.ok(service.buscarCEP(cep));
    }
}
