package sf22_2014.android_projekat_sf22_2014.Model;


import java.util.List;

public class Tag {

    private int id;
    private String name;
    private List<Meal> meals;

    public Tag(int id, String name, List<Meal> meals) {
        this.id = id;
        this.name = name;
        this.meals = meals;
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

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
