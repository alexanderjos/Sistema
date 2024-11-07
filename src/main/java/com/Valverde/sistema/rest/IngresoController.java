package com.Valverde.sistema.rest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Valverde.sistema.converter.IngresoConverter;
import com.Valverde.sistema.converter.ProductoConverter;
import com.Valverde.sistema.dto.IngresoDto;
import com.Valverde.sistema.dto.ProductoDto;
import com.Valverde.sistema.entity.Ingreso;
import com.Valverde.sistema.entity.Producto;
import com.Valverde.sistema.service.IngresoService;
import com.Valverde.sistema.service.ProductoService;

import com.Valverde.sistema.util.WrapperResponse;

import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/v1/ingresos")
public class IngresoController {
    @Autowired
    private IngresoService service;

    @Autowired
    private IngresoConverter converter;

    @GetMapping
    public ResponseEntity<List<IngresoDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<IngresoDto> ingreso = converter.fromEntities(service.findAll());
        return new WrapperResponse(true, "success", ingreso).createResponse(HttpStatus.OK);
    }



    
    @PostMapping
    public ResponseEntity<IngresoDto> create (@RequestBody IngresoDto ingreso) {
        Ingreso entity = converter.fromDTO(ingreso);
        IngresoDto dto = converter.fromEntity(service.save(entity));//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngresoDto> update (@PathVariable("id") int id, @RequestBody IngresoDto ingreso) {
        Ingreso entity = converter.fromDTO(ingreso);
        IngresoDto dto = converter.fromEntity(service.save(entity));
//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") short id) {
        service.delete(id);
//        return ResponseEntity.ok(null);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngresoDto> findById (@PathVariable("id") short id) {
        IngresoDto dto = converter.fromEntity(service.findById(id));

//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }
}