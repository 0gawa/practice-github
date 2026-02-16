package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "本のタイトルを入力してください")
  private String title;

  @NotBlank(message = "著者の名前を入力してください")
  private String authorName; // feature branchでは 'author' でした

  @Column(name = "book_summary", length = 2000)
  private String summary; // feature branchでは 'description' でした

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
  private List<Review> reviews = new ArrayList<>();

  public Book() {
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthorName() {
    return authorName;
  }

  public void setAuthorName(String authorName) {
    this.authorName = authorName;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public void addReview(Review review) {
    reviews.add(review);
    review.setBook(this);
  }
}
