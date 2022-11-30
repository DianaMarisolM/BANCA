package com.upload.crud.Controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upload.crud.Entity.Customer;
import com.upload.crud.Service.CustomerService;
import com.upload.crud.Validation.Exception.InvalidDataException;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Object> save(@Valid @RequestBody Customer customer, BindingResult result) {

        if (result.hasErrors()) {
            throw new InvalidDataException("Error de datos",result);
        }
        return new ResponseEntity<>(customerService.save(customer), null, HttpStatus.OK);
    }
}
