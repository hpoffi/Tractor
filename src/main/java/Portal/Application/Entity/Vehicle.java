package Portal.Application.Entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="vehicle")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleId;
    private String vehicleNumber;
    private String photoUrl;
    private Date availabilityDate;
    private BigDecimal rent;
    private BigDecimal securityDeposit;
    private String insuranceNumber;
    private Date insuranceExpiredDate;
    private Date rcExpiredDate;
    private String engineNumber;
    private String chassisNumber;
    private String pincode;

    public String getVehicleNumber() {
        return vehicleNumber;
    }
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public Date getAvailabilityDate() {
        return availabilityDate;
    }
    public void setAvailabilityDate(Date availabilityDate) {
        this.availabilityDate = availabilityDate;
    }
    public BigDecimal getRent() {
        return rent;
    }
    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }
    public BigDecimal getSecurityDeposit() {
        return securityDeposit;
    }
    public void setSecurityDeposit(BigDecimal securityDeposit) {
        this.securityDeposit = securityDeposit;
    }
    public String getInsuranceNumber() {
        return insuranceNumber;
    }
    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }
    public Date getInsuranceExpiredDate() {
        return insuranceExpiredDate;
    }
    public void setInsuranceExpiredDate(Date insuranceExpiredDate) {
        this.insuranceExpiredDate = insuranceExpiredDate;
    }
    public Date getRcExpiredDate() {
        return rcExpiredDate;
    }
    public void setRcExpiredDate(Date rcExpiredDate) {
        this.rcExpiredDate = rcExpiredDate;
    }
    public String getEngineNumber() {
        return engineNumber;
    }
    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }
    public String getChassisNumber() {
        return chassisNumber;
    }
    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }
    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    @JsonBackReference
    private SellerDetails seller;
    public SellerDetails getSeller() {
        return seller;
    }

    public void setSeller(SellerDetails seller) {
        this.seller = seller;
    }
}