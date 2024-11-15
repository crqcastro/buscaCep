package br.com.cesarcastro.buscacep.controller.api;

import br.com.cesarcastro.buscacep.domain.model.response.CEPResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "CEP", description = "Rotas para consulta de CEP.")
@CrossOrigin
@RequestMapping("/v1/cep")
@Validated
public interface ICEPController {

    @Operation(summary = "Endpoint para Consultar CEP.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados do CEP obtidos com sucesso.",
                    content = @Content(mediaType = APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = CEPResponse.class))),
            @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acesso negado.", content = @Content),
            @ApiResponse(responseCode = "500", description = "Erro inesperado no servidor.", content = @Content),
            @ApiResponse(responseCode = "503", description = "Serviço não está disponível no momento.", content = @Content)
    })
    @GetMapping(produces = APPLICATION_JSON_VALUE, path = "/{cep}")
    ResponseEntity<CEPResponse> buscarCEP(@PathVariable(name = "cep")
                                          @Parameter(description = "CEP Buscado.", required = true)
                                          @NotEmpty(message = "CEP não pode ser vazio.")
                                          String cep);
}
