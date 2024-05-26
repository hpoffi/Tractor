package Portal.Application.Controller;
import Portal.Application.Entity.Vehicle;
import Portal.Application.Pojo.OnboardRequest;
import Portal.Application.Service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8443")
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable Long id) {
        return vehicleService.getUserById(id);
    }

//    @PostMapping("/onboard")
//    public Vehicle createVehicle(@RequestBody OnboardRequest onboardRequest) {
//        return vehicleService.createUser(onboardRequest);
//    }

    @PutMapping("/{id}")
    public Vehicle updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        return vehicleService.updateUser(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteUser(id);
    }
}

