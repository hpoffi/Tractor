package Portal.Application.Controller;

import Portal.Application.Entity.SellerDetails;
import Portal.Application.Service.SellerService;
import Portal.Application.dto.OtpRequest;
import Portal.Application.dto.OtpResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8443")
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private OtpController otpController;

    @GetMapping
    public List<SellerDetails> getAllSellers() {
        return sellerService.getAllSellers();
    }

    @PostMapping("/register")
    public ResponseEntity<SellerDetails> createUser(@RequestBody SellerDetails sellerDetails) {
        // Create user
        SellerDetails createdSeller = sellerService.createSeller(sellerDetails);
        //
        // Generate OTP for the newly registered user
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setUsername(createdSeller.getUsername());
        OtpResponseDto otpResponse = otpController.sendOtp(otpRequest);

        // Handle OTP response as needed
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSeller);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");
        String password = requestBody.get("password");

        // Check if username and password are provided
        if (email == null || password == null) {
            return ResponseEntity.badRequest().body("Username and password are required.");
        }

        // Fetch user details from the database
        SellerDetails user = sellerService.getUserByEmail(email);

        // Check if user exists and the provided password matches
        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok().body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        sellerService.deleteSeller(id);
    }
}