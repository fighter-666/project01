package com.example.myapplication.data;

public class ComplexItemEntity {

    private String title;
    private String secondTitle;
    private String thirdTitle;
    private String fourthTitle;



    public ComplexItemEntity(String title, String secondTitle, String thirdTitle, String fourthTitle) {
        this.title = title;
        this.secondTitle = secondTitle;
        this.thirdTitle = thirdTitle;
        this.fourthTitle = fourthTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecondTitle() {
        return secondTitle;
    }

    public void setSecondTitle(String secondTitle) {
        this.secondTitle = secondTitle;
    }

    public String getThirdTitle() {
        return thirdTitle;
    }

    public void setThirdTitle(String thirdTitle) {
        this.thirdTitle = thirdTitle;
    }

    public String getFourthTitle() {
        return fourthTitle;
    }

    public void setFourthTitle(String fourthTitle) {
        this.fourthTitle = fourthTitle;
    }

    @Override
    public String toString() {
        return "{" +
                "title='" + title + '\'' +
                ", secondTitle='" + secondTitle + '\'' +
                ", thirdTitle='" + thirdTitle + '\'' +
                ", fourthTitle='" + fourthTitle + '\'' +
                '}';
    }
}