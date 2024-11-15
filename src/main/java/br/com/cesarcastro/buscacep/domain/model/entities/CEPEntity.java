package br.com.cesarcastro.buscacep.domain.model.entities;

import br.com.cesarcastro.buscacep.domain.model.response.LocationResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_cep")
public class CEPEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String cep;
    private String logradouro;
    private String complemento;
    private String unidade;
    private String regiao;
    private String bairro;
    private String localidade;
    private String uf;
    private Integer ibge;
    private String gia;
    private Integer ddd;
    private String siafi;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private LocationEntity localizacaoAproximada;
    private String gMapLink;
}
