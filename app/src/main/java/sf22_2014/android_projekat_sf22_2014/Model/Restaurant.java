package sf22_2014.android_projekat_sf22_2014.Model;


import android.graphics.Bitmap;
import android.location.Address;

import java.util.List;


public class Restaurant {

    private int id;
    private String name;
    private String description;
    private Bitmap smallPhoto;
    private Bitmap largePhoto;
    private Address address;
    private int startHour;
    private int endHour;
    private int endMinute;
    private int startMinute;
    private String phone;
    private String email;
    private String url;
    private List<Meal> meals;


    public Restaurant(String name, String description, Bitmap smallPhoto, int startHour, String url) {
        this.name = name;
        this.description = description;
        this.smallPhoto = smallPhoto;
        this.startHour = startHour;
        this.url = url;
    }

    public Restaurant(int id, String name, String description, Bitmap smallPhoto) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.smallPhoto = smallPhoto;
    }

    public Restaurant(int id, String name, String description, Bitmap smallPhoto, int startHour, int endHouse, String url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.smallPhoto = smallPhoto;
        this.startHour = startHour;
        this.endHour = endHouse;
        this.url = url;

    }


    public Restaurant(int id, String name, String description, Bitmap smallPhoto,  String url, int startHour, int endHour,
                      String phone, String email) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.smallPhoto = smallPhoto;
        this.url = url;
        this.startHour = startHour;
        this.endHour = endHour;
        this.phone = phone;
        this.email = email;
    }

    public Restaurant(int id, List<Meal> meals, String url, String email, String phone, int startMinute,
                      int endMinute, int endHour, int startHour, Address address, Bitmap largePhoto,
                      Bitmap smallPhoto, String description, String name) {
        this.id = id;
        this.meals = meals;
        this.url = url;
        this.email = email;
        this.phone = phone;
        this.startMinute = startMinute;
        this.endMinute = endMinute;
        this.endHour = endHour;
        this.startHour = startHour;
        this.address = address;
        this.largePhoto = largePhoto;
        this.smallPhoto = smallPhoto;
        this.description = description;
        this.name = name;
    }

    public Restaurant(int id, String name, String description, int startHour, int endHour,
                      int endMinute, int startMinute, String phone, String email, String url) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startHour = startHour;
        this.endHour = endHour;
        this.endMinute = endMinute;
        this.startMinute = startMinute;
        this.phone = phone;
        this.email = email;
        this.url = url;
}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getSmallPhoto() {
        return smallPhoto;
    }

    public void setSmallPhoto(Bitmap smallPhoto) {
        this.smallPhoto = smallPhoto;
    }

    public Bitmap getLargePhoto() {
        return largePhoto;
    }

    public void setLargePhoto(Bitmap largePhoto) {
        this.largePhoto = largePhoto;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
