package co.com.udea.invoice.persistence.crud;

import co.com.udea.invoice.persistence.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceCrud extends JpaRepository<InvoiceEntity, Long> {
}
