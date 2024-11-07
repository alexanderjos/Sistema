package com.Valverde.sistema.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Valverde.sistema.converter.RolConverter;
import com.Valverde.sistema.dto.RolDto;
import com.Valverde.sistema.entity.Rol;
import com.Valverde.sistema.service.RolService;
import com.Valverde.sistema.util.WrapperResponse;

import java.util.List;

@RestController
@RequestMapping("/v1/roles")
public class RolController{
    @Autowired
    private RolService service;

    @Autowired
    private RolConverter converter;

    @GetMapping
    public ResponseEntity<List<RolDto>> findAll(
            @RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
            @RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
    ) {
        Pageable page = PageRequest.of(pageNumber, pageSize);
        List<RolDto> rol = converter.fromEntities(service.findAll());
//        return ResponseEntity.ok(rol);
        return new WrapperResponse(true, "success", rol).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RolDto> create (@RequestBody RolDto rol) {
        Rol entity = converter.fromDTO(rol);
        RolDto dto = converter.fromEntity(service.save(entity));
//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDto> update (@PathVariable("id") int id, @RequestBody RolDto rol) {
        Rol entity = converter.fromDTO(rol);
        RolDto dto = converter.fromEntity(service.save(entity));
//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") int id) {
        service.delete(id);
//        return ResponseEntity.ok(null);
        return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDto> findById (@PathVariable("id") int id) {
        RolDto dto = converter.fromEntity(service.findById(id));

//        return ResponseEntity.ok(dto);
        return new WrapperResponse(true, "success", dto).createResponse(HttpStatus.OK);
    }
}
