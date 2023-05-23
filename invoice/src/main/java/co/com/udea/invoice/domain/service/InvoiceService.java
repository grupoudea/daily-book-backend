package co.com.udea.invoice.domain.service;

import co.com.udea.customer.domain.errorhandler.BadResponseHandler;
import co.com.udea.customer.domain.model.enums.Responses;
import co.com.udea.invoice.domain.model.Invoice;
import co.com.udea.invoice.domain.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository repository;

    @Autowired
    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Invoice getinvoice(Long identification) {
        return repository.getInvoice(identification)
                .orElseThrow( () -> {
                    throw new BadResponseHandler(Responses.NOT_FOUND_ENTITY.getMensaje(),
                            Responses.NOT_FOUND_ENTITY.getCodigo(),
                            Responses.NOT_FOUND_ENTITY.getHttpStatus());
                });
    }

    @Transactional(readOnly = true)
    public List<Invoice> getAllInvoicers() {
        return repository.getAllInvoicers()
                .orElseThrow( () -> {
                    throw new BadResponseHandler(Responses.NOT_FOUND_ENTITIES.getMensaje(),
                            Responses.NOT_FOUND_ENTITIES.getCodigo(),
                            Responses.NOT_FOUND_ENTITIES.getHttpStatus());
                });
    }

    @Transactional
    public Invoice saveInvoice(Invoice invoice) {
        return repository.save(invoice)
                .orElseThrow( () -> {
                    throw new BadResponseHandler(Responses.NOT_SAVE_ENTITY.getMensaje(),
                            Responses.NOT_SAVE_ENTITY.getCodigo(),
                            Responses.NOT_SAVE_ENTITY.getHttpStatus());
                });
    }
}
