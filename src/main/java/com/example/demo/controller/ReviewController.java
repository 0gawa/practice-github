package com.example.demo.controller;

import com.example.demo.model.Review;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ReviewRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/{bookId}/reviews")
public class ReviewController {

  private final ReviewRepository reviewRepository;
  private final BookRepository bookRepository;

  public ReviewController(ReviewRepository reviewRepository, BookRepository bookRepository) {
    this.reviewRepository = reviewRepository;
    this.bookRepository = bookRepository;
  }

  @GetMapping
  public List<Review> getReviewsByBookId(@PathVariable Long bookId) {
    return reviewRepository.findByBookId(bookId);
  }

  @PostMapping
  public ResponseEntity<Review> addReview(@PathVariable Long bookId, @Valid @RequestBody Review review) {
    return bookRepository.findById(bookId)
        .map(book -> {
          review.setBook(book);
          Review savedReview = reviewRepository.save(review);
          return ResponseEntity.ok(savedReview);
        })
        .orElse(ResponseEntity.notFound().build());
  }
}
