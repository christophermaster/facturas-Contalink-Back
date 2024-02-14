package com.factura.contalink.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.factura.contalink.Entity.Invoice;
import com.factura.contalink.repository.InvoiceRepository;

@Service
public class FacturaService  implements FacturaServiceI{

    @Autowired
    private InvoiceRepository invoiceRepository;
    


    @Override
    public List<Invoice> obtenerInvoicesPorFechas(String fechaInicial, String fechaFinal) {
    	Timestamp fechaInicialTimestamp = fechaInicial != null ? Timestamp.valueOf(fechaInicial) : null;
    	Timestamp fechaFinalTimestamp = fechaFinal != null ? Timestamp.valueOf(fechaFinal) : null;

        if (fechaInicialTimestamp != null && fechaFinalTimestamp != null) {
            // Caso: Ambas fechas proporcionadas
            return invoiceRepository.findByInvoiceDateBetween(fechaInicialTimestamp, fechaFinalTimestamp);
        } else if (fechaInicialTimestamp != null) {
            // Caso: Solo fecha inicial proporcionada
            return invoiceRepository.findByInvoiceDateAfter(fechaInicialTimestamp);
        } else if (fechaFinalTimestamp != null) {
            // Caso: Solo fecha final proporcionada
            return invoiceRepository.findByInvoiceDateBefore(fechaFinalTimestamp);
        } else {
            // Caso: Ninguna fecha proporcionada
            return invoiceRepository.findAll();
        }
    }

    @Override
    public Page<Invoice> obtenerInvoicesPorFechasConPaginacion(String fechaInicial, String fechaFinal, int pagina, int tamanoPagina) {
    	Timestamp fechaInicialTimestamp = fechaInicial != null ? Timestamp.valueOf(fechaInicial) : null;
    	Timestamp fechaFinalTimestamp = fechaFinal != null ? Timestamp.valueOf(fechaFinal) : null;
        if (fechaInicialTimestamp != null && fechaFinalTimestamp != null) {
            // Caso: Ambas fechas proporcionadas con paginación
            return invoiceRepository.findByInvoiceDateBetween(
                    fechaInicialTimestamp, fechaFinalTimestamp, PageRequest.of(pagina, tamanoPagina)
            );
        } else if (fechaInicialTimestamp != null) {
            // Caso: Solo fecha inicial proporcionada con paginación
            return invoiceRepository.findByInvoiceDateAfter(fechaInicialTimestamp, PageRequest.of(pagina, tamanoPagina));
        } else if (fechaFinalTimestamp != null) {
            // Caso: Solo fecha final proporcionada con paginación
            return invoiceRepository.findByInvoiceDateBefore(fechaFinalTimestamp, PageRequest.of(pagina, tamanoPagina));
        } else {
            // Caso: Ninguna fecha proporcionada con paginación
            return invoiceRepository.findAll(PageRequest.of(pagina, tamanoPagina));
        }
    }
}
