package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Review {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Min(1)
  @Max(5)
  private int rating;

  @NotBlank(message = "Comment cannot be blank")
  private String comment;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id")
  @JsonIgnore
  private Book book;

  // Default constructor for JPA
  public Review() {
  }

  public Review(int rating, String comment, Book book) {
    this.rating = rating;
    this.comment = comment;
    this.book = book;
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }
}
