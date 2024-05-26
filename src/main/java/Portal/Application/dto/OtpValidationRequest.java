package Portal.Application.dto;

public class OtpValidationRequest {
    private String username;
    private String otpNumber;
    public String getOtpNumber() {
        return otpNumber;
    }
    public void setOtpNumber(String otpNumber) {
        this.otpNumber = otpNumber;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
