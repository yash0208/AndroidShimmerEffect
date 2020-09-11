package com.rajaryan.androidshimmereffect;

public class WorkshopClass {
    String Name,Image,Teacher;

    public WorkshopClass() {
    }

    public WorkshopClass(String name, String image, String teacher) {
        Name = name;
        Image = image;
        Teacher = teacher;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTeacher() {
        return Teacher;
    }

    public void setTeacher(String teacher) {
        Teacher = teacher;
    }
}
