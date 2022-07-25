package com.vdq.controllers;


import com.vdq.models.Product;
import com.vdq.models.ResponseObject;
import com.vdq.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @GetMapping("")
    public List<Product> getAll()
    {

        List<Product> product = productRepository.findAll();
        return product;
    }
    @GetMapping("{id}")
    // Format Respond Data  = RespondObject(status, message, Object =....)
    ResponseEntity<ResponseObject> findById(@PathVariable Long id)
    {
        Optional<Product> foundProduct = productRepository.findById(id);
        return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Querry Product successfully",foundProduct)
                ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("Failed","cannot find product with id="+id,"")
                );

        // Ternary for if-else
//        if(foundProduct.isPresent()){
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok","Querry Product successfully",foundProduct)
//            );
//        }else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new ResponseObject("Failed","Cannot find product with id ="+id,"")
//            );
//        }
    }


    @PostMapping("/insert")

    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
        List<Product> foundProducts = productRepository.findByProductName(newProduct.getProductName().trim());
        if(foundProducts.size()>0){
            return  ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Failed","Product Name already Taken","")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Insert Successfully",productRepository.save(newProduct))
        );
    }

    // Update

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
        Product updateProduct =  productRepository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setYear(newProduct.getYear());
                    product.setPrice(newProduct.getPrice());
                    product.setUrl(newProduct.getUrl());
                    return productRepository.save(product);
                }).orElseGet(()->{
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Update Product Successfully",updateProduct)
        );

    }
    @DeleteMapping("{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        boolean exitsProduct = productRepository.existsById(id);
        if (exitsProduct){
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Delete product successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Failed","Cannot find product to delete","")
        );
    }





}
