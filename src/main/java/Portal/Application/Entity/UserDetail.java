package Portal.Application.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name="username",nullable = false, unique = true)
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="email",nullable = false, unique = true)
    private String email;
    @Column(name="Address")
    private String Address;
    @Column(name="Phone_Number")
    private String Phone_Number;
    @Column(name="Aadhaar_Number")
    private String Aadhaar_Number;
    @Column(name="Pan_Card")
    private String Pan_Card;
    @Column(name="Driving_license")
    private String Driving_License;
    @Column(name="Village_Name")
    private String Village_Name;
    @Column(name="Street_Name")
    private String Street_Name;
    @Column(name="District")
    private String District;
    @Column(name="State")
    private String State;
    @Column(name="Pincode")
    private String Pincode;

    public void setOtpEnabled(boolean otpEnabled) {
        this.otpEnabled = otpEnabled;
    }

    private boolean otpEnabled;
    private String otp;

    public void setId(UUID id) {this.id = id;}
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress(){return Address;}
    public void setAddress(String address){this.Address=address;}
    public String getPhone_Number(){return Phone_Number;}
    public void setPhone_Number(String phone_Number){this.Phone_Number=phone_Number;}
    public String getAadhaar_Number(){return Aadhaar_Number;}
    public void setAadhaar_Number(String aadhaar_Number){this.Aadhaar_Number=aadhaar_Number;}
    public String getPan_Card(){return Pan_Card;}
    public void setPan_Card(String pan_Card){this.Pan_Card=pan_Card;}
    public String getDriving_License() {return Driving_License;}
    public void setDriving_License(String driving_License) {this.Driving_License = driving_License;}
    public String getVillage_Name() {
        return Village_Name;
    }
    public void setVillage_Name(String village_Name) {
        Village_Name = village_Name;
    }
    public String getStreet_Name() {
        return Street_Name;
    }
    public void setStreet_Name(String street_Name) {
        Street_Name = street_Name;
    }
    public String getDistrict() {
        return District;
    }
    public void setDistrict(String district) {
        District = district;
    }
    public String getState() {
        return State;
    }
    public void setState(String state) {
        State = state;
    }
    public String getPincode() {
        return Pincode;
    }
    public void setPincode(String pincode) {
        Pincode = pincode;
    }
    public boolean isOtpEnabled() {
        return otpEnabled;
    }
    public String getOtp() {return otp;}

    @OneToMany(mappedBy = "userDetail")
    private List<Inventory> inventoryList=new ArrayList<>();

}
