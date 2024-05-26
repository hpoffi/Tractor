package Portal.Application.Controller;

import Portal.Application.Service.GoogleMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GoogleMapsController {
    @Autowired
    private GoogleMapsService googleMapsService;
    @GetMapping("/coordinates")
    public Map<String, Double> getCoordinates(@RequestParam String address) {
        return googleMapsService.getCoordinates(address);
    }
}
