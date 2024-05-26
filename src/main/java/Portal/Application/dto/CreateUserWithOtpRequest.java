package Portal.Application.dto;

import Portal.Application.Entity.UserDetail;
import jdk.dynalink.NamedOperation;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserWithOtpRequest {
    private UserDetail userDetails;
    private String otp;

    public UserDetail getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetail userDetails) {
        this.userDetails = userDetails;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
