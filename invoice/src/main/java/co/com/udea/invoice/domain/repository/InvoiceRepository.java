package co.com.udea.invoice.domain.repository;


import co.com.udea.invoice.domain.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceRepository {

    Optional<Invoice> save (Invoice invoicer);
    Optional<Invoice> getInvoice(Long identification);
    Optional<List<Invoice>> getAllInvoicers();
}
