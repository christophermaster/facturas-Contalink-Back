package com.factura.contalink.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.factura.contalink.Entity.Invoice;

public interface FacturaServiceI {
	List<Invoice> obtenerInvoicesPorFechas(String fechaInicial, String fechaFinal);
    Page<Invoice> obtenerInvoicesPorFechasConPaginacion(String fechaInicial, String fechaFinal, int pagina, int tamanoPagina);
}
