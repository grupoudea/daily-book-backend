package co.com.udea.invoice.persistence.repositoryimpl;

import co.com.udea.invoice.domain.model.Invoice;
import co.com.udea.invoice.domain.repository.InvoiceRepository;
import co.com.udea.invoice.persistence.crud.InvoiceCrud;
import co.com.udea.invoice.persistence.entity.InvoiceEntity;
import co.com.udea.invoice.persistence.mapper.InvoiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final InvoiceCrud persistence;
    private final InvoiceMapper mapper;

    @Autowired
    public InvoiceRepositoryImpl(InvoiceCrud persistence, InvoiceMapper mapper) {
        this.persistence = persistence;
        this.mapper = mapper;
    }

    @Override
    public Optional<Invoice> save(Invoice invoice) {
        Optional<InvoiceEntity> entity = persistence.findById(invoice.getId());

        return entity.isPresent()?
                Optional.empty():
                Optional.of(mapper.toInvoice(persistence.save(mapper.toInvoiceEntity(invoice))));
    }

    @Override
    public Optional<Invoice> getInvoice(Long identification) {
        return Optional.of(mapper.toInvoice(persistence.findById(identification)
                .orElse(null)));
    }

    @Override
    public Optional<List<Invoice>> getAllInvoicers() {
        return Optional.of(mapper.toInvoicers(persistence.findAll()));
    }
}
