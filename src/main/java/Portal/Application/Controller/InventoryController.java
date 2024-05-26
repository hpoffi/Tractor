package Portal.Application.Controller;
import Portal.Application.Entity.Inventory;
import Portal.Application.Entity.UserDetail;
import Portal.Application.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:8443")
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllInventories() {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInventoryById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.saveInventory(inventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.ok().build();
    }
//    @GetMapping("/user/{userId}")
//    public List<Inventory> getInventoriesByUser(@PathVariable Long userId) {
//        UserDetail user = new UserDetail();
//        user.setId(userId); // Assume user exists
//        return inventoryService.getInventoriesByUser(user);
//    }
}

