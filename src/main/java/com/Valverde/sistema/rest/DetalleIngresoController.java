package com.Valverde.sistema.rest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Valverde.sistema.converter.DetalleIngresoConverter;
import com.Valverde.sistema.dto.DetalleIngresoDto;
import com.Valverde.sistema.entity.DetalleIngreso;
import com.Valverde.sistema.service.DetalleIngresoService;

import com.Valverde.sistema.util.WrapperResponse;

import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/v1/detalleIngreso")
public class DetalleIngresoController {
    @Autowired
    private DetalleIngresoService service;

    @Autowired
    private DetalleIngresoConverter converter;
    @GetMapping
    public ResponseEntity<List<DetalleIngresoDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<DetalleIngresoDto> detalleIngreso = converter.fromEntities(service.findAll());
        return new WrapperResponse(true, "success", detalleIngreso).createResponse(HttpStatus.OK);
    }



    
    @PostMapping
    public ResponseEntity<DetalleIngresoDto> create (@RequestBody DetalleIngresoDto detalleIngreso) {
        DetalleIngreso entity = converter.fromDTO(detalleIngreso);
        DetalleIngresoDto dto = converter.fromEntity(service.save(entity));//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleIngresoDto> update (@PathVariable("id") int id, @RequestBody DetalleIngresoDto detalleIngreso) {
        DetalleIngreso entity = converter.fromDTO(detalleIngreso);
        DetalleIngresoDto dto = converter.fromEntity(service.save(entity));
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
    public ResponseEntity<DetalleIngresoDto> findById (@PathVariable("id") short id) {
        DetalleIngresoDto dto = converter.fromEntity(service.findById(id));

//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }
}