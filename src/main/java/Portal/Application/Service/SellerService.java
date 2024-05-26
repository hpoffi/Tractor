package Portal.Application.Service;

import Portal.Application.Entity.SellerDetails;
import Portal.Application.Repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public List<SellerDetails> getAllSellers(){
        return sellerRepository.findAll();
    }
    public SellerDetails createSeller(SellerDetails user) {
        return sellerRepository.save(user);
    }
    public SellerDetails getUserById(Long userId) {
        return sellerRepository.findById(userId).orElse(null);}

    public SellerDetails updateSeller(Long userId,SellerDetails sellerDetails) {
        SellerDetails user = sellerRepository.findById(userId).orElse(null);
        if (user != null) {
            // Update user details with the provided UserDetails object
            user.setUsername(sellerDetails.getUsername());
            user.setPassword(sellerDetails.getPassword());
            user.setEmail(sellerDetails.getEmail());
            user.setAddress(sellerDetails.getAddress());
            user.setPhone_Number(sellerDetails.getPhone_Number());
            return sellerRepository.save(user);
        }
        return null; // Handle case when user is not found
    }
    public void deleteSeller(Long userId) {
        sellerRepository.deleteById(userId);
    }

    public SellerDetails getUserByEmail(String email) {
        return sellerRepository.findFirstByEmail(email);
    }
}
