package Portal.Application.Service;

import Portal.Application.Entity.SellerDetails;
import Portal.Application.Entity.Vehicle;
import Portal.Application.Pojo.OnboardRequest;
import Portal.Application.Repository.SellerRepository;
import Portal.Application.Repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private SellerRepository sellerRepository;

    public List<Vehicle> getAllUsers(){
        return vehicleRepository.findAll();
    }
//    public Vehicle createUser(OnboardRequest onboardRequest) {
//        Long sellerId = onboardRequest.getSellerId();
//        SellerDetails seller = sellerRepository.findById(sellerId)
//                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + sellerId));
//
//        onboardRequest.getVehicleNumber();
//        return vehicleRepository.save(onboardRequest);
//    }
    public Vehicle getUserById(Long userId) {
        return vehicleRepository.findById(userId).orElse(null);}
    public Vehicle updateUser(Long userId, Vehicle vehicle) {
        Vehicle user = vehicleRepository.findById(userId).orElse(null);
        if (user != null) {
            // Update user details with the provided UserDetails object
            user.setVehicleNumber(vehicle.getVehicleNumber());
            user.setPhotoUrl(vehicle.getPhotoUrl());
            user.setAvailabilityDate(vehicle.getAvailabilityDate());
            user.setRent(vehicle.getRent());
            user.setSecurityDeposit(vehicle.getSecurityDeposit());
            user.setInsuranceNumber(vehicle.getInsuranceNumber());
            user.setInsuranceExpiredDate(vehicle.getInsuranceExpiredDate());
            user.setRcExpiredDate(vehicle.getRcExpiredDate());
            user.setChassisNumber(vehicle.getChassisNumber());
            user.setEngineNumber(vehicle.getEngineNumber());
//            user.setLatitude(vehicle.getLatitude());
//            user.setLongitude(vehicle.getLongitude());
            user.setPincode(vehicle.getPincode());

            Long sellerId = vehicle.getSeller().getSellerId();
            SellerDetails seller = sellerRepository.findById(sellerId)
                    .orElseThrow(() -> new RuntimeException("Seller not found with id: " + sellerId));

            vehicle.setSeller(seller);
            return vehicleRepository.save(user);
        }
        return null; // Handle case when user is not found
    }
    public void deleteUser(Long userId) {
        vehicleRepository.deleteById(userId);
    }
}