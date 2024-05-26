package Portal.Application.Service;
// GoogleMapsService.java

import Portal.Application.dto.GoogleMapsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleMapsService {

    private final RestTemplate restTemplate;

    public GoogleMapsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Double> getCoordinates(String address) {
        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=AIzaSyCF6QpDTURnRPxfRFbsTAytym0dn6MRL6M";
        GoogleMapsResponse response = restTemplate.getForObject(apiUrl, GoogleMapsResponse.class);
        if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
            GoogleMapsResponse.Result result = response.getResults().get(0);
            double latitude = result.getGeometry().getLocation().getLat();
            double longitude = result.getGeometry().getLocation().getLng();
            Map<String, Double> coordinates = new HashMap<>();
            coordinates.put("latitude", latitude);
            coordinates.put("longitude", longitude);
            return coordinates;
        } else {
            return null;
        }
    }
}


