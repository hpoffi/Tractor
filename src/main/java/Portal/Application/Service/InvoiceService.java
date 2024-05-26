package Portal.Application.Service;

import Portal.Application.Entity.Invoice;
import Portal.Application.Entity.Transcation;
import Portal.Application.Entity.UserDetail;
import Portal.Application.Repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceService {
    @Autowired
    public InvoiceRepository invoiceRepository;
    public Invoice generateInvoice(List<Transcation> transcations, UserDetail userDetail) throws Exception{
        Invoice invoice = new Invoice();
        // Generate a unique invoice number with error handling
        String invoiceNumber = generateUniqueInvoiceNumber();
        if(invoiceNumber==null){
            throw new Exception("failed to generate unique invoice number.");
        }
        invoice.setInvoiceNumber(invoiceNumber);
        invoice.setDate(LocalDateTime.now());

        BigDecimal subtotal = calculateSubTotal(transcations);
        invoice.setSubtotal(subtotal);

        BigDecimal totalAmount = calculateTotalAmount(transcations);
        invoice.setTotalAmount(totalAmount);

        invoice.setUserDetail(userDetail);

        // Save the generated invoice in the database
        invoiceRepository.save(invoice);
        return invoice;
    }
    public BigDecimal calculateSubTotal(List<Transcation> transactions) {
        BigDecimal subTotal = BigDecimal.ZERO;
        for (Transcation transaction : transactions) {
            subTotal = subTotal.add(transaction.getTransactionAmount());
        }
        return subTotal;
    }

    public BigDecimal calculateTotalAmount(List<Transcation> transactions) {
        // For demonstration purposes, let's assume a 10% tax rate and no other charges
        BigDecimal taxRate = BigDecimal.valueOf(0.10);
        BigDecimal subTotal = calculateSubTotal(transactions);
        BigDecimal taxAmount = subTotal.multiply(taxRate);
        return subTotal.add(taxAmount);
    }

    private String generateUniqueInvoiceNumber() {
        // Generate a unique identifier
        String uniqueId = UUID.randomUUID().toString().replace("-", "");
        // Generate invoice number by combining with current timestamp
        return "INV-" + LocalDateTime.now().toString().replace(":", "") + "-" + uniqueId;
    }
    public void downloadInvoice(Long invoiceId, OutputStream outputStream) throws IOException {
        // Assuming invoice files are stored in a directory and named after their IDs
        File invoiceFile = new File("path/to/invoice/files/" + invoiceId + ".pdf");

        try (FileInputStream fis = new FileInputStream(invoiceFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    }
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }
}
