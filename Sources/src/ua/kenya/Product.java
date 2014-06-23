package ua.kenya;

/**
 * Created by zidd on 3/23/14.
 */
public class Product {
    private int id;
    private int callories;
    private String name;
    private String discription;

    public int getCallories() {
        return callories;
    }

    public int getId() {
        return id;

    }

    public String getName() {
        return name;
    }

    public String getDiscription() {
        return discription;
    }

    public Product(int id, int callories, String name, String discription) {
        this.id = id;
        this.callories = callories;
        this.name = name;
        this.discription = discription;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id + ". " + name + "\nCallories: " + callories + "\nDiscription: " + discription);
        return sb.toString();
    }
}
