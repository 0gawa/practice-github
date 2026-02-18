package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookViewController {

  private final BookRepository bookRepository;

  public BookViewController(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @GetMapping
  public String listBooks(Model model) {
    model.addAttribute("books", bookRepository.findAll());
    return "books";
  }

  @GetMapping("/new")
  public String showRegistrationForm(Model model) {
    model.addAttribute("book", new Book());
    return "book_form";
  }

  @PostMapping("/new")
  public String registerBook(@Valid Book book, BindingResult result) {
    if (result.hasErrors()) {
      return "book_form";
    }
    bookRepository.save(book);
    return "redirect:/books";
  }

  @PostMapping("/delete/{id}")
  public String deleteBook(@PathVariable Long id) {
    System.out.println("Deleting book with ID: " + id);
    bookRepository.deleteById(id);
    return "redirect:/books";
  }
}
