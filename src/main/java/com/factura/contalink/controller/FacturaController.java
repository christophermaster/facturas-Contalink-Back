package com.factura.contalink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.factura.contalink.Entity.Invoice;
import com.factura.contalink.service.FacturaService;


@RestController
@RequestMapping("/api")
public class FacturaController {
	
    @Autowired
    private FacturaService facturaService;
    
	@GetMapping("/factura")
	public ResponseEntity<String>  getBitacora() {
		return ResponseEntity.status(HttpStatus.OK).body("HOLA");
	}
	

    @GetMapping("/facturas")
    public ResponseEntity<List<Invoice>> obtenerInvoicesPorFechas(
            @RequestParam(required = false) String fechaInicial,
            @RequestParam(required = false) String fechaFinal,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanoPagina) {

        List<Invoice> facturas = facturaService.obtenerInvoicesPorFechas(fechaInicial, fechaFinal);
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }
    
    @GetMapping("/facturas-paginada")
    public ResponseEntity<Page<Invoice>> obtenerInvoicesPorFechasConPaginacion(
            @RequestParam(required = false) String fechaInicial,
            @RequestParam(required = false) String fechaFinal,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanoPagina) {

        Page<Invoice> facturas = facturaService.obtenerInvoicesPorFechasConPaginacion(fechaInicial, fechaFinal,pagina,tamanoPagina);
        return new ResponseEntity<>(facturas, HttpStatus.OK);
    }

}
