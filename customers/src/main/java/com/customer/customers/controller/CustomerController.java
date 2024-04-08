package com.customer.customers.controller;

import com.customer.customers.model.dto.CustomerDto;
import com.customer.customers.model.dto.ResponseDto;
import com.customer.customers.model.request.DepositRequest;
import com.customer.customers.model.request.RegisterRequest;
import com.customer.customers.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService service;

    @PostMapping
    public ResponseEntity<CustomerDto> save(@Valid @RequestBody RegisterRequest request){
        var dto = service.save(request);
        var location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}").build(dto.getId());
        return ResponseEntity.created(location).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@Valid @RequestBody RegisterRequest request, @PathVariable Long id){
        var dto = service.update(request,id);
        var location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{id}").build(dto.getId());
        return ResponseEntity.created(location).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> get(@Valid @PathVariable Long id){
        var dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/{username}")
    public ResponseEntity<CustomerDto> getByUsername(@Valid @PathVariable String username){
        var dto = service.findByUsername(username);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@Valid @PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deposit")
    public ResponseEntity<ResponseDto> deposit(@Valid @RequestBody DepositRequest request){
        var dto = service.depositMoney(request);
        return ResponseEntity.ok().body(dto);
    }



}
