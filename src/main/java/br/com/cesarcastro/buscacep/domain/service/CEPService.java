package br.com.cesarcastro.buscacep.domain.service;

import br.com.cesarcastro.buscacep.domain.client.ViaCEPClient;
import br.com.cesarcastro.buscacep.domain.model.entities.CEPEntity;
import br.com.cesarcastro.buscacep.domain.model.response.CEPResponse;
import br.com.cesarcastro.buscacep.domain.model.response.LocationResponse;
import br.com.cesarcastro.buscacep.domain.repositories.CEPRepository;
import br.com.cesarcastro.buscacep.mappers.CEPMapper;
import br.com.cesarcastro.buscacep.support.utils.MapaCepCrowler;
import br.com.cesarcastro.buscacep.support.utils.MapaEnderecoCrowler;
import br.com.cesarcastro.buscacep.support.utils.Others;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

import static br.com.cesarcastro.buscacep.support.Constants.MAPS_URL;

@Service
@RequiredArgsConstructor
@Transactional
public class CEPService {
    private final CEPRepository repository;
    private final CEPMapper mapper;
    private final ViaCEPClient viaCEPClient;

    @Cacheable("ceps")
    public CEPResponse buscarCEP(String cep) {
        var entity = repository.findByCep(cep);
        if (Objects.isNull(entity)) {
            entity = buscarDados(cep);
        }
        return mapper.toCEPResponse(entity);
    }

    private CEPEntity buscarDados(String cep) {
        CEPResponse cepResponse = viaCEPClient.buscarUsuarioPorCpf(cep);
        try {
            obterLocationByCEP(cepResponse);
            if (Objects.isNull(cepResponse.getLocalizacaoAproximada()) || Objects.isNull(cepResponse.getLocalizacaoAproximada().getLatitude())) {
                obterLocationByEndereco(cepResponse);
            }
        } catch (IOException e) {
            //TODO: Logar erro e tratar com excecao certa
        }
        if (Objects.nonNull(cepResponse.getLocalizacaoAproximada())
                && Objects.nonNull(cepResponse.getLocalizacaoAproximada().getLatitude())
                && Objects.nonNull(cepResponse.getLocalizacaoAproximada().getLongitude()))
            getMapsLink(cepResponse);
        normalizaCep(cepResponse);
        var entity =  mapper.toEntity(cepResponse);

        repository.save(entity);
        repository.flush();

        return entity;
    }

    private void normalizaCep(CEPResponse cepResponse) {
        cepResponse.setCep(cepResponse.getCep().replaceAll("[^\\d.]", ""));
    }

    private void obterLocationByCEP(CEPResponse logradouro) throws IOException {
        LocationResponse locationResponse = MapaCepCrowler.obterLocation(logradouro.getCep());
        logradouro.setLocalizacaoAproximada(locationResponse);
    }

    private void obterLocationByEndereco(CEPResponse logradouro) throws IOException {
        LocationResponse location =
                MapaEnderecoCrowler.obterLocation(Others.deAccent(
                        logradouro.getLogradouro() + " " +
                                logradouro.getBairro() + " " +
                                logradouro.getLocalidade() + " " +
                                logradouro.getUf()));
        logradouro.setLocalizacaoAproximada(location);
    }

    private void getMapsLink(CEPResponse cepResponse) {
        cepResponse.setGMapLink(MAPS_URL.replace("{latitude}", cepResponse.getLocalizacaoAproximada().getLatitude().toString())
                .replace("{longitude}", cepResponse.getLocalizacaoAproximada().getLongitude().toString()));
    }
}
