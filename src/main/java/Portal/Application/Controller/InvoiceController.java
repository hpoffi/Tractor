package Portal.Application.Controller;

import Portal.Application.Entity.Invoice;
import Portal.Application.Service.InvoiceService;
import Portal.Application.dto.GenerateInvoiceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@CrossOrigin(origins = "http://localhost:8443")
@RequestMapping("/invoices")
@RestController
public class InvoiceController {
    private final static Logger logger = LoggerFactory.getLogger(InvoiceController.class);
    @Autowired
    private InvoiceService invoiceService;
    @PostMapping("/generate")
    public ResponseEntity<?> generateInvoice(@RequestBody GenerateInvoiceRequest request) {
        try {
            Invoice invoice = invoiceService.generateInvoice(request.getTransactions(), request.getUserDetail());
            return ResponseEntity.ok(invoice);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to generate the invoice. Please try again later.");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable("id") Long id) {
        try {
            // Call the service method to retrieve the invoice by ID
            Optional<Invoice> invoice = invoiceService.getInvoiceById(id);
            if (invoice != null) {
                return ResponseEntity.ok(invoice);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Return an error response in case of failure
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to retrieve the invoice. Please try again later.");
        }
    }
}

