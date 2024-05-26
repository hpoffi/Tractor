package Portal.Application.Service;

import Portal.Application.Entity.Inventory;
import Portal.Application.Entity.UserDetail;
import Portal.Application.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;
    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }
//    public List<Inventory> getInventoriesByUser(UserDetail user) {
//        return inventoryRepository.findByUser(user);
//    }
}
