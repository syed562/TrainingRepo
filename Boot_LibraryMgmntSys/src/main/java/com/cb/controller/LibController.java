package com.cb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cb.attributes.Book;
import com.cb.attributes.User;
import com.cb.exceptions.BookNotFoundException;
import com.cb.exceptions.IllegalIdException;
import com.cb.exceptions.IllegalQuantityException;
import com.cb.service.BookService;

@RestController
@RequestMapping("/api/books")
public class LibController {

    @Autowired
    private BookService service;

   
    @GetMapping
    public ResponseEntity<List<Book>> displayAllBooks() {

    return ResponseEntity.ok(service.displayBooks());
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<?> findBookById(@PathVariable Long id) {
        try {
            Optional<Book> book = service.findBookById(id);
            
            return book.map(ResponseEntity::ok)
                       
            		.orElseGet(() -> ResponseEntity.notFound().build());
            
        
        }catch (IllegalIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book b) {
        try {
            service.addBook(b);
            
            return ResponseEntity.ok("BOOK IS ADDED SUCCESSFULLY");
        } catch (IllegalIdException e) {
        	
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @PutMapping("/{id}/cost")
    public ResponseEntity<?> updateBookDetails(@PathVariable Long id, @RequestParam Double cost) {
        try {
            service.updateBookCost(id, cost);
           
            return ResponseEntity.ok("DETAILS ARE UPDATED");
        } catch (BookNotFoundException e) {
            
       return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
        	
            String res = service.deleteBookById(id);
            
           return ResponseEntity.ok(res);
        } catch (BookNotFoundException e) {
        	
          return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @PostMapping("/{bookId}/buy")
    public ResponseEntity<?> buyBook(@RequestParam Long userId, @PathVariable Long bookId) {
        try {
            String res = service.buyBook(userId, bookId);
           
            if (res.toLowerCase().contains("successfully") || res.toLowerCase().contains("remaining stock"))
              
              return ResponseEntity.ok(res);
            else
            	
           return ResponseEntity.badRequest().body(res);
        } catch (Exception e) {
            
            return ResponseEntity.internalServerError().body("Internal error: " + e.getMessage());
        }
    }

    
    @PostMapping("/{id}/restock")
    public ResponseEntity<?> restockBook(@PathVariable Long id, @RequestParam int qty) {
        try {
            String res = service.restockBook(id, qty);
            return ResponseEntity.ok(res);
        }
        catch (BookNotFoundException | IllegalQuantityException e) {
        	
          return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e) {
        	
            return ResponseEntity.internalServerError().body("Internal error: " + e.getMessage());
        }
    }

    
    @GetMapping("/{id}/owners")
    public ResponseEntity<?> bookOwners(@PathVariable Long id) {
        try {
            List<User> owners = service.bookOwnedBy(id);
          
          return ResponseEntity.ok(owners);
        }
        catch (Exception e) {
        	
        return ResponseEntity.internalServerError().body("Internal error: " + e.getMessage());
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> displayUsers() {

    return ResponseEntity.ok(service.displayUsers());
    }
    
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody User b) {
        try {
            service.addUser(b);
            
            return ResponseEntity.ok("USER IS  SUCCESSFULLY ADDED");
        } catch (IllegalIdException e) {
        	
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
