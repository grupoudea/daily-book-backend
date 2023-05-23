package co.com.udea.invoice.persistence.mapper;

import co.com.udea.invoice.domain.model.Invoice;
import co.com.udea.invoice.persistence.entity.InvoiceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    Invoice toInvoice(InvoiceEntity entity);


    InvoiceEntity toInvoiceEntity(Invoice invoice);

    List<Invoice> toInvoicers(List<InvoiceEntity> list);
}
