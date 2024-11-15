package br.com.cesarcastro.buscacep.domain.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class CEPResponse {
    @Schema(description = "CEP.", required = true)
    private String cep;
    @Schema(description = "Logradouro associado ao CEP.", required = true)
    private String logradouro;
    @Schema(description = "Complemento do logradouro associado ao CEP.")
    private String complemento;
    @Schema(description = "Unidade do logradouro associado ao CEP.")
    private String unidade;
    @Schema(description = "Região do logradouro associado ao CEP.")
    private String regiao;
    @Schema(description = "Bairro relacionado ao CEP.")
    private String bairro;
    @Schema(description = "Município relacionado ao CEP.", required = true)
    private String localidade;
    @Schema(description = "UF relacionado ao CEP.", required = true)
    private String uf;
    @Schema(description = "Código IBGE do municipio relacionado ao CEP.")
    private Integer ibge;
    @Schema(description = "Código GIA relacionado ao CEP.")
    private String gia;
    @Schema(description = "DDD da região relacionado ao CEP.")
    private Integer ddd;
    @Schema(description = "Código SIAFI do municipio relacionado ao CEP.")
    private String siafi;
    @Schema(description = "Localização aproximada do CEP em Lat e Lon.")
    private LocationResponse localizacaoAproximada;
    @Schema(description = "Link do Google Maps para o CEP.")
    private String gMapLink;
}
