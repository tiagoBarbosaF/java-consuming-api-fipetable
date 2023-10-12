package br.com.fipetable.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleBrands(@JsonAlias("codigo") Integer code,
                            @JsonAlias("nome") String name,
                            @JsonAlias("modelos") List<VehicleModels> modelos,
                            List<VehicleYears> years,
                            List<VehicleDetails> vehicleDetails) {
}
