package br.com.cesarcastro.buscacep.domain.client;

import br.com.cesarcastro.buscacep.domain.model.response.CEPResponse;
import br.com.cesarcastro.buscacep.support.exceptions.InternalErrorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;

@Component
public class ViaCEPClient {
    private final Client client;
    private final ObjectMapper om;
    private final String urlBase;

    public ViaCEPClient(Client client,
                        ObjectMapper om,
                        @Value("${VIACEP_URL_TEMPLATE}")
                         String urlBase) {
        this.client = client;
        this.om = om;
        this.urlBase = urlBase;
    }

    public CEPResponse buscarUsuarioPorCpf(String cep) {
        String url = String.format("https://viacep.com.br/ws/%s/json", cep);
        HttpHeaders headers = obterHeaders();
        String response = client.get(url, headers);
        try {
            return om.readValue(response, CEPResponse.class);
        } catch (IOException e) {
            throw new InternalErrorException("Erro ao buscar CEP: " + e.getMessage());
        }
    }

    private HttpHeaders obterHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }
}
