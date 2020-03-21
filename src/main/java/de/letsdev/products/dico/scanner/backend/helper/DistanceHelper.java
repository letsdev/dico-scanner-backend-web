package de.letsdev.products.dico.scanner.backend.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class DistanceHelper {

    @Autowired
    private Environment environment;

    public static void findLocations(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = 0.0;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        //TODO check if distance < 500 (config value)
        String maxDistanceInMeters = environment.getProperty("coscan.search.area.alert.max.distance.meters");

        System.out.println("distance: " + distance);
    }
}
