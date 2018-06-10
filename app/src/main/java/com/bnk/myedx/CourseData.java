package com.bnk.myedx;

public class CourseData {

    private String name,image,description,about;
    String company;

    public CourseData() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseData(String name, String image, String description, String about, String company) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.about = about;
        this.company = company;
    }

    public String getImage() {
        return image;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
