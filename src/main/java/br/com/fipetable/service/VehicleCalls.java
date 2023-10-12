package br.com.fipetable.service;

import br.com.fipetable.models.VehicleBrands;
import br.com.fipetable.models.VehicleDetails;
import br.com.fipetable.models.VehicleModels;
import br.com.fipetable.models.VehicleYears;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class VehicleCalls {
    private final ApiConsumer consumer = new ApiConsumer();
    private final ConvertData converter = new ConvertData();

    public List<VehicleDetails> getVehicleDetails(int getCodeModels, String searchBrands) {
        String json;
        String searchDetailsVehicle;
        String searchYearVehicle;

        searchYearVehicle = searchBrands + getCodeModels + "/anos/";
        json = consumer.getData(searchYearVehicle);
        List<VehicleYears> vehicleYearsList = converter.getList(json, VehicleYears.class);

        List<VehicleDetails> allVehicleDetails = new ArrayList<>();

        for (var allVehicleYear : vehicleYearsList) {
            searchDetailsVehicle = searchYearVehicle + allVehicleYear.code();

            json = consumer.getData(searchDetailsVehicle);

            VehicleDetails vehicleDetails = converter.getData(json, VehicleDetails.class);
            allVehicleDetails.add(vehicleDetails);
        }

        return allVehicleDetails;
    }

    public void getFilteredVehicleModels(VehicleBrands vehicleBrands, String searchModelVehicle) {
        vehicleBrands.modelos().stream()
                .filter(c -> c.name().toLowerCase().contains(searchModelVehicle.toLowerCase()))
                .sorted(Comparator.comparing(VehicleModels::code))
                .toList()
                .forEach(c -> System.out.println(c.code() + " - " + c.name()));
    }

    public VehicleBrands getAllVehicleBrands(String searchBrands) {
        String json;
        json = consumer.getData(searchBrands);

        VehicleBrands vehicleBrands = converter.getData(json, VehicleBrands.class);

        vehicleBrands.modelos().stream()
                .sorted(Comparator.comparing(VehicleModels::code))
                .forEach(m -> System.out.println(m.code() + " - " + m.name().toUpperCase()));
        return vehicleBrands;
    }

    public void getAllBrands(String searchBrands) {
        String json;
        json = consumer.getData(searchBrands);
        List<VehicleBrands> vehicleBrandsList = converter.getList(json, VehicleBrands.class);

        vehicleBrandsList.stream()
                .sorted(Comparator.comparing(VehicleBrands::name))
                .forEach(c -> System.out.println(c.code() + " - " + c.name().toUpperCase()));
    }
}
