package br.com.cesarcastro.buscacep.controller.rest;

import br.com.cesarcastro.buscacep.domain.model.response.CEPResponse;
import br.com.cesarcastro.buscacep.domain.model.response.LocationResponse;
import br.com.cesarcastro.buscacep.domain.service.CEPService;
import br.com.cesarcastro.buscacep.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class CEPControllerTest {
    @Mock
    CEPService service;
    @InjectMocks
    CEPController cEPController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBuscarCEP() {
        var response = TestUtils.generateRandom(CEPResponse.class);
        when(this.service.buscarCEP(anyString())).thenReturn(response);
        ResponseEntity<CEPResponse> result = this.cEPController.buscarCEP("cep");
        Assertions.assertEquals(200, result.getStatusCodeValue());
        verify(this.service).buscarCEP(anyString());
    }
}
