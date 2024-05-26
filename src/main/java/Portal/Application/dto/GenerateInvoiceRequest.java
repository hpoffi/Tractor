package Portal.Application.dto;

import Portal.Application.Entity.Transcation;
import Portal.Application.Entity.UserDetail;

import java.util.List;

public class GenerateInvoiceRequest {
    private List<Transcation> transactions;
    private UserDetail userDetail;

    public List<Transcation> getTransactions() {
        return transactions;
    }
    public UserDetail getUserDetail() {
        return userDetail;
    }
}
