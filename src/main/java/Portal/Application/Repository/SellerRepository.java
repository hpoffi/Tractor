package Portal.Application.Repository;

import Portal.Application.Entity.SellerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<SellerDetails, Long> {
    SellerDetails findFirstByEmail(String email);
}
