package Portal.Application.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="inventory")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;
    private String vehicleNumber;
    private Date deliverystartdate;
    private Date deliveryenddate;
    private Double totalrent;
    private Double paymenttoseller;
    private Date paymentdatetoseller;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserDetail userDetail;
}
