package br.com.fipetable.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleModels(@JsonAlias("codigo") Integer code,
                            @JsonAlias("nome") String name) {
}
