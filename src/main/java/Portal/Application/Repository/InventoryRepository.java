package Portal.Application.Repository;

import Portal.Application.Entity.Inventory;
import Portal.Application.Entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository  extends JpaRepository<Inventory, Long> {
//    List<Inventory> findByUser(UserDetail user);
}
