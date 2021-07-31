package web.model;


public class Car {
    private String model;
    private String make;
    private int produced;

    public Car(String model, String make, int produced) {
        this.model = model;
        this.make = make;
        this.produced = produced;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getProduced() {
        return produced;
    }

    public void setProduced(int produced) {
        this.produced = produced;
    }
}
