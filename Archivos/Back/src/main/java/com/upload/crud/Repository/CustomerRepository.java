package com.upload.crud.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.upload.crud.Entity.Customer;
@Repository
public interface CustomerRepository extends CrudRepository<Customer,String> {
    
}
