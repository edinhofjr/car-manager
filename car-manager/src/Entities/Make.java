package Entities;

public class Make {
    private int id;
    private String name;
    private Country country;

    public Make() {}

    public Make(int id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Make(String name, Country country) {
        this.name = name;
        this.country = country;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
