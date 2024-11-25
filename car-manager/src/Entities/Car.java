package Entities;

public class Car {
    private int id;
    private String model;
    private int modelYear;
    private int distance;
    private Make make;
    private int price;

    public Car() {

    }

    public Car(int id, String model, int modelYear, int distance, int price, Make make) {
        this.id         = id;
        this.model      = model;
        this.modelYear  = modelYear;
        this.distance   = distance;
        this.make       = make;
        this.price      = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getModelYear() {
        return modelYear;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", modelYear=" + modelYear +
                ", distance=" + distance +
                ", make=" + make +
                ", price=" + price +
                '}';
    }
}
