package Portal.Application.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import Portal.Application.Entity.UserDetail;
import org.springframework.stereotype.Service;

@Service
public class UniqueIdGeneratorService {

    public String generateUniqueId(UserDetail userDetail) {
        try {
            UUID uuid = UUID.randomUUID();
            String combinedString = uuid.toString() + userDetail.getUsername();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(combinedString.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating unique ID", e);
        }
    }
}

