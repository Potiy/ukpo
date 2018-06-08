package listservice;

public class Data {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String cost;

    public Data(String id, String name, String address, String phone, String cost) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cost = cost;
    }

    public String getId() { return id; }

    public String getName() { return name; }

    public String getAddress() {
        return address;
    }

    public String getPhone() { return phone; }

    public String getCost() {
        return cost;
    }
}
