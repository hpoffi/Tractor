package Portal.Application.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8443")
public class MapController {
    @GetMapping("/showMap")
    public String index() {
        return "index";
    }
}
