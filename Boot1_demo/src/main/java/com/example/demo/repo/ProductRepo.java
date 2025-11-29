package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Product;

public interface ProductRepo  extends CrudRepository<Product, Integer>{

}
