package br.com.fipetable.main;

import br.com.fipetable.models.VehicleBrands;
import br.com.fipetable.models.VehicleDetails;
import br.com.fipetable.service.VehicleCalls;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final VehicleCalls vehicleCalls = new VehicleCalls();
    private final String BASE_URL = "https://parallelum.com.br/fipe/api/v1/";

    public void showMenu() {
        String searchTypeVehicle;
        int searchBrandVehicle;
        String searchBrandsForTypeVehicle;
        String searchBrands;

        while (true) {
            System.out.print("\nEnter the type of vehicle you want to search (Carros, Motos, Caminhões, 0 to exit): ");
            searchTypeVehicle = scanner.nextLine();

            if (searchTypeVehicle.equalsIgnoreCase("0")) {
                break;
            } else if (searchTypeVehicle.toLowerCase().trim().contains("carr")) {
                searchTypeVehicle = "carros";
            } else if (searchTypeVehicle.toLowerCase().trim().contains("mot")) {
                searchTypeVehicle = "motos";
            } else {
                searchTypeVehicle = "caminhoes";
            }

            searchBrandsForTypeVehicle = BASE_URL + searchTypeVehicle.toLowerCase().replace("õ", "o").trim() + "/marcas/";

            vehicleCalls.getAllBrands(searchBrandsForTypeVehicle);

            System.out.print("\nEnter the code of brand you want to search: ");
            searchBrandVehicle = scanner.nextInt();
            scanner.nextLine();

            searchBrands = searchBrandsForTypeVehicle + searchBrandVehicle + "/modelos/";

            VehicleBrands vehicleBrands = vehicleCalls.getAllVehicleBrands(searchBrands);

            System.out.print("\nEnter the name of car model you want to search: ");
            String searchModelVehicle = scanner.nextLine();

            vehicleCalls.getFilteredVehicleModels(vehicleBrands, searchModelVehicle);

            System.out.print("\nEnter the code of car model you want to search: ");
            int codeModel = scanner.nextInt();
            scanner.nextLine();

            List<VehicleDetails> allVehicleDetails = vehicleCalls.getVehicleDetails(codeModel, searchBrands);

            System.out.println("\nAll available vehicles filtered by year:");
            allVehicleDetails.stream()
                    .sorted(Comparator.comparing(VehicleDetails::yearModel))
                    .forEach(c -> System.out.println(
                            "Model: " + c.model() +
                                    ", Year: " + c.yearModel() +
                                    ", Value: " + c.value() +
                                    ", Fuel: " + c.fuel()));

            System.out.println("\nVehicle model with less value:");
            allVehicleDetails.stream()
                    .min(Comparator.comparing(VehicleDetails::value))
                    .ifPresent(c -> System.out.println(
                            "Model: " + c.model() +
                                    ", Year: " + c.yearModel() +
                                    ", Value: " + c.value() +
                                    ", Fuel: " + c.fuel()));

            System.out.println("\nVehicle model with highest value:");
            allVehicleDetails.stream()
                    .max(Comparator.comparing(VehicleDetails::value))
                    .ifPresent(c -> System.out.println(
                            "Model: " + c.model() +
                                    ", Year: " + c.yearModel() +
                                    ", Value: " + c.value() +
                                    ", Fuel: " + c.fuel()));
        }
    }
}
