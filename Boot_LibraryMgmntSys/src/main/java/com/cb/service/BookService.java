package com.cb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cb.BootLibraryMgmntSysApplication;
import com.cb.attributes.Book;
import com.cb.attributes.User;
import com.cb.exceptions.BookNotFoundException;
import com.cb.exceptions.IllegalIdException;
import com.cb.exceptions.IllegalQuantityException;
import com.cb.repo.LibRepo;
import com.cb.repo.UserRepo;

@Service
public class BookService {

    private final BootLibraryMgmntSysApplication bootLibraryMgmntSysApplication;

    @Autowired
    private LibRepo librepos;

    @Autowired
    private UserRepo userRepo;

    BookService(BootLibraryMgmntSysApplication bootLibraryMgmntSysApplication) {
        this.bootLibraryMgmntSysApplication = bootLibraryMgmntSysApplication;
    }

    public List<Book> displayBooks() {
        return librepos.findAll();
    }

    public Optional<Book> findBookById(Long id) throws IllegalIdException {
        Optional<Book> list = librepos.findById(id);
        if (list.isEmpty()) throw new IllegalIdException("Book does not exist with that id");
        return list;
    }

    public void addBook(Book b) throws IllegalIdException {
        if (librepos.existsById(b.getId())) throw new IllegalIdException("Book with this id already exists");
        b.setIsAvailable(b.getQuantity() > 0);
        librepos.save(b);
    }

    public void updateBookCost(Long id, Double cost) throws BookNotFoundException {
        Optional<Book> getBook = librepos.findById(id);
        if (getBook.isEmpty()) throw new BookNotFoundException("Book not found for ID: " + id);

        Book book = getBook.get();
        if (cost > 0) {
            book.setCost(cost);
            librepos.save(book);
        }
    }

    public String deleteBookById(Long id) throws BookNotFoundException {
        Optional<Book> getBook = librepos.findById(id);
        if (getBook.isEmpty()) throw new BookNotFoundException("Book not found for ID: " + id);
        librepos.deleteById(id);
        return "Book with ID " + id + " deleted";
    }

    public List<User> bookOwnedBy(Long id) {
        Optional<Book> b = librepos.findById(id);
        return b.map(Book::getOwnedBy).orElse(List.of());
    }

   
    public String buyBook(Long userId, Long bookId) {
        Optional<User> userOpt = userRepo.findById(userId);
        Optional<Book> bookOpt = librepos.findById(bookId);

        if (userOpt.isEmpty()) return "User not found";
        if (bookOpt.isEmpty()) return "Book not found";

        Book book = bookOpt.get();
        User user = userOpt.get();

     
        if (book.getQuantity() <= 0) {
            book.setIsAvailable(false);
            librepos.save(book);
            return " Sorry, '" + book.getName() + "' is out of stock.";
        }

      
        book.setQuantity(book.getQuantity() - 1);

      
        if (book.getQuantity() == 0) {
            book.setIsAvailable(false);
        }

       
        book.getOwnedBy().add(user);
        user.getBorrowedBooks().add(book.getName());

       
        librepos.save(book);
        userRepo.save(user);

        return user.getName() + "successfully bought '" + book.getName() + "'. Remaining stock: " + book.getQuantity();
    }
    
    public String restockBook(Long id, int addedQty) throws BookNotFoundException, IllegalQuantityException {
    	if(addedQty<1)throw new IllegalQuantityException("The quantity is not entered correctly");
        Optional<Book> bookOpt = librepos.findById(id);
        if (bookOpt.isEmpty()) throw new BookNotFoundException("Book not found");
        Book book = bookOpt.get();

        book.setQuantity(book.getQuantity() + addedQty);
        book.setIsAvailable(true);
        librepos.save(book);
        return "Book " + book.getName() + " restocked to " + book.getQuantity();
    }
    
    
    public String addUser(User u) throws IllegalIdException {
    	  Optional<User> user = userRepo.findById(u.getId());
          if (!user.isEmpty()) throw new IllegalIdException("User already exists with that id");
          userRepo.save(u);
          return "USER ADDED";
    	
    }
    
    
    public List<User> displayUsers() {
        return userRepo.findAll();
    }

}
