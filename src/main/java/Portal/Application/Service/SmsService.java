package Portal.Application.Service;

import Portal.Application.Config.TwilioConfig;
import Portal.Application.dto.OtpRequest;
import Portal.Application.dto.OtpResponseDto;
import Portal.Application.dto.OtpStatus;
import Portal.Application.dto.OtpValidationRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

@Service
@Slf4j
public class SmsService {
    private final static Logger logger = LoggerFactory.getLogger(SmsService.class);
    @Autowired
    private TwilioConfig twilioConfig;

    private Map<String, String> otpMap = new HashMap<>();

    public OtpResponseDto sendSMS(OtpRequest otpRequest) {
        try {
            if (otpRequest != null && otpRequest.getPhoneNumber() != null) {
                PhoneNumber to = new PhoneNumber(otpRequest.getPhoneNumber());
                PhoneNumber from = new PhoneNumber(twilioConfig.getPhoneNumber());
                String otp = generateOTP();
                String otpMessage = "Dear Customer, " + otp + " is your one-time password (OTP) for phone verification to login at application. Thank you.";
                Message message = Message.creator(to, from, otpMessage).create();
                otpMap.put(otpRequest.getUsername(), otp);
                return new OtpResponseDto(OtpStatus.DELIVERED, otpMessage);
            } else {
                // Handle null values gracefully
                return new OtpResponseDto(OtpStatus.FAILED, "Phone number is null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new OtpResponseDto(OtpStatus.FAILED, e.getMessage());
        }
    }

    public String validateOtp(OtpValidationRequest otpValidationRequest) {
        String otp = otpMap.get(otpValidationRequest.getUsername());
        if (otp != null && otp.equals(otpValidationRequest.getOtpNumber())) {
            otpMap.remove(otpValidationRequest.getUsername());
            return "OTP is valid!";
        } else {
            return "OTP is invalid!";
        }
    }

    private String generateOTP() {
        return new DecimalFormat("000000").format(new Random().nextInt(999999));
    }
}
