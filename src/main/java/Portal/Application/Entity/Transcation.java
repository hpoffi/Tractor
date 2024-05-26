package Portal.Application.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="transaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transcation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private BigDecimal transcationAmount;
    private Date date;
    private String paymentStatus;

    public BigDecimal getTransactionAmount() {
        return transcationAmount;
    }

    public void setTranscationAmount(BigDecimal transcationAmount) {
        this.transcationAmount = transcationAmount;
    }

    @ManyToOne
    @JoinColumn(name = "user_detail_id")
    private UserDetail userDetail;
    @ManyToOne
    @JoinColumn(name="user_id")
    private SellerDetails sellerDetails;

    @ManyToOne
    @JoinColumn(name="invoice_id")
    private Invoice invoice;


}