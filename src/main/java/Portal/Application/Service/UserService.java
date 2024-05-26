package Portal.Application.Service;


import Portal.Application.Entity.UserDetail;
import Portal.Application.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UniqueIdGeneratorService uniqueIdGeneratorService;

    public UserDetail createUser(UserDetail user) {
        String uniqueId = uniqueIdGeneratorService.generateUniqueId(user);
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }
    public UserDetail getUserById(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }
    public UserDetail getUserByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    // Update User
    public UserDetail updateUser(UUID userId, UserDetail userDetails) {
        UserDetail user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            // Update user details with the provided UserDetails object
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());
            user.setEmail(userDetails.getEmail());
            user.setAddress(userDetails.getAddress());
            user.setPhone_Number(userDetails.getPhone_Number());
            user.setAadhaar_Number(userDetails.getAadhaar_Number());
            user.setPan_Card(userDetails.getPan_Card());
            user.setDriving_License(userDetails.getDriving_License());
            user.setVillage_Name(userDetails.getVillage_Name());
            user.setStreet_Name(userDetails.getStreet_Name());
            user.setDistrict(userDetails.getDistrict());
            user.setState(userDetails.getState());
            user.setPincode(userDetails.getPincode());

            // Save the updated user to the database
            return userRepository.save(user);
        }
        return null; // Handle case when user is not found
    }

    // Delete User
    @Transactional
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
    public List<UserDetail> getAllUsers() {
        return userRepository.findAll();
    }

    // Method to authenticate user
    public boolean authenticateUser(String username, String password) {
        UserDetail user = userRepository.findFirstByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
    public boolean isOtpEnabledForUser(String username) {
        UserDetail user = userRepository.findFirstByUsername(username);
        return user != null && user.isOtpEnabled();
    }

    public boolean authenticateUserWithOtp(String username, String otp) {
        UserDetail user = userRepository.findFirstByUsername(username);
        // Assuming the user entity has a field for storing the OTP
        return user != null && user.getOtp() != null && user.getOtp().equals(otp);
    }
}
