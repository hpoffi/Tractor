package Portal.Application.dto;

import lombok.Data;

@Data
public class OtpResponseDto {
    public OtpResponseDto(OtpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
    private OtpStatus status;
    private String message;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }
}
