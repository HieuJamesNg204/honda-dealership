public class Honda {
    private int id;
    private String model;
    private String version;
    private double listedPrice;

    public double getListedPrice() {
        return listedPrice;
    }

    public void setListedPrice(double listedPrice) {
        this.listedPrice = listedPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}