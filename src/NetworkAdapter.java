public class NetworkAdapter {
    private String manufacturer;
    private String standart;
    private int ethernetSpeed;

    public NetworkAdapter(String manufacturer, String standart, int ethernetSpeed) {
        this.manufacturer = manufacturer;
        this.standart = standart;
        this.ethernetSpeed = ethernetSpeed;
    }

    public String getManufacturer() {
        return manufacturer;
    }
    public String getStandart() {
        return standart;
    }
    public int getEthernetSpeed() {
        return ethernetSpeed;
    }
    @Override
    public String toString() {
        return manufacturer + " | " + standart + " | Speed: " + ethernetSpeed + "Mbps";
    }
}
