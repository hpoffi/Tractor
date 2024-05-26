package Portal.Application.Controller;

import Portal.Application.Entity.UserDetail;
import Portal.Application.Service.UserService;
import Portal.Application.dto.OtpRequest;
import Portal.Application.dto.OtpResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:8443")
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private OtpController otpController;
    @PostMapping("/register")
    public ResponseEntity<UserDetail> createUser(@RequestBody UserDetail userDetails) {
        // Create user
        UserDetail createdUser = userService.createUser(userDetails);
        //
        // Generate OTP for the newly registered user
        OtpRequest otpRequest = new OtpRequest();
        otpRequest.setUsername(createdUser.getUsername());
        OtpResponseDto otpResponse = otpController.sendOtp(otpRequest);

        // Handle OTP response as needed
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
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
        UserDetail user = userService.getUserByEmail(email);

        // Check if user exists and the provided password matches
        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok().body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    // Get User by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserDetail> getUserById(@PathVariable(value = "id") UUID userId) {
        UserDetail user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDetail> updateUser(@PathVariable(value = "id") UUID userId, @RequestBody UserDetail userDetails) {
        UserDetail updatedUser = userService.updateUser(userId, userDetails);
        if (updatedUser != null) {
            return ResponseEntity.ok().body(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    // Get All Users
    @GetMapping
    public ResponseEntity<List<UserDetail>> getAllUsers() {
        List<UserDetail> users = userService.getAllUsers();
        return ResponseEntity.ok().body(users);
    }
//    @PostMapping("/logout")
//    public ResponseEntity<String> logout() {
//        // Invalidate the session
//        SecurityContextHolder.clearContext();
//
//        // Return a response indicating successful logout
//        return ResponseEntity.ok().body("Logged out successfully");
//    }
}
