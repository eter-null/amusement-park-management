package VisitorsAndOperationsManagerpkg;

import java.io.Serializable;
import java.time.LocalDate;

public class Review implements Serializable {
    private String reviewerID;
    private String itemToReview;
    private int reviewStars;
    private LocalDate reviewDate;
    private String comment;

    public Review(String reviewerID, String itemToReview, int reviewStars, LocalDate reviewDate, String comment) {
        this.reviewerID = reviewerID;
        this.itemToReview = itemToReview;
        this.reviewStars = reviewStars;
        this.reviewDate = reviewDate;
        this.comment = comment;
    }

    public String getReviewerID() {
        return reviewerID;
    }

    public void setReviewerID(String reviewerID) {
        this.reviewerID = reviewerID;
    }

    public String getItemToReview() {
        return itemToReview;
    }

    public void setItemToReview(String itemToReview) {
        this.itemToReview = itemToReview;
    }

    public int getReviewStars() {
        return reviewStars;
    }

    public void setReviewStars(int reviewStars) {
        this.reviewStars = reviewStars;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

//    @Override
//    public String toString(){
//
//    }
}
