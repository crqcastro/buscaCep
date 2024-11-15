package br.com.cesarcastro.buscacep.domain.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LocationResponse {
    @Schema(description = "Lat em graus.", required = true)
    private BigDecimal latitude;
    @Schema(description = "Lon em graus.", required = true)
    private BigDecimal longitude;
}
