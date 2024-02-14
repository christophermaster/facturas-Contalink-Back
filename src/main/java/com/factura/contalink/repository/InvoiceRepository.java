package com.factura.contalink.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.factura.contalink.Entity.Invoice;

import java.sql.Timestamp;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByInvoiceDateBetween(Timestamp fechaInicial, Timestamp fechaFinal);

    List<Invoice> findByInvoiceDateAfter(Timestamp fechaInicial);

    List<Invoice> findByInvoiceDateBefore(Timestamp fechaFinal);

    Page<Invoice> findByInvoiceDateBetween(Timestamp fechaInicial, Timestamp fechaFinal, Pageable pageable);

    Page<Invoice> findByInvoiceDateAfter(Timestamp fechaInicial, Pageable pageable);

    Page<Invoice> findByInvoiceDateBefore(Timestamp fechaFinal, Pageable pageable);
}

