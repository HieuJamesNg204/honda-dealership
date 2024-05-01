public class Honda {
    private int id;
    private String model;
    private String version;
    private long listedPrice;

    public long getListedPrice() {
        return listedPrice;
    }

    public void setListedPrice(long listedPrice) {
        this.listedPrice = listedPrice;
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

    @Override
    public String toString() {
        return id + " - Honda " + model + " " + version + " - " + listedPrice + " dongs";
    }
}