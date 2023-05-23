package co.com.udea.invoice.web.controller;

import co.com.udea.invoice.domain.model.Invoice;
import co.com.udea.invoice.domain.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(InvoiceController.INVOICE_URL)
public class InvoiceController {

    public static final String INVOICE_URL = "/invoice";
    private final InvoiceService service;

    @Autowired
    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @GetMapping("/{identification}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long identification) {
        return new ResponseEntity<>(service.getinvoice(identification), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoicers() {
        return new ResponseEntity<>(service.getAllInvoicers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Invoice> saveCustomer(@RequestBody Invoice invoice) {
        return new ResponseEntity<>(service.saveInvoice(invoice), HttpStatus.OK);
    }
}
