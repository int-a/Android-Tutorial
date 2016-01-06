package netgenes.switchingscreens;

import java.io.Serializable;

/**
 * Created by Anthony on 12/1/2015.
 */
public class Human implements Serializable{

    private double height, weight;
    private String name = "";

    public Human(double height, String name, double weight) {
        this.height = height;
        this.name = name;
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }
}
