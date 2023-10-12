package br.com.fipetable.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleDetails(@JsonAlias("Modelo") String model,
                             @JsonAlias("AnoModelo") int yearModel,
                             @JsonAlias("Valor") String value,
                             @JsonAlias("Combustivel") String fuel) {
}
