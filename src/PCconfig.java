public class PCconfig {
    private String processor;
    private String motherboard;
    private int ramGb;
    private int storageGb;

    private String usageType;
    private String graphicsCard;
    private int powerSupplyW;
    private String coolingType;
    private String caseModel;
    private String storageType;
    private String operatingSystem;
    private String ramType;
    private int ramModules;
    private int storageDevices;
    private int monitorCount;
    private double monitorSize;
    private String monitorResolution;
    private String keyboard;
    private String mouse;
    private boolean wifiEnabled;
    private boolean bluetoothEnabled;
    private int ethernetSpeed;
    private int usbPorts;
    private int fanCount;
    private boolean rgbLighting;
    private String soundCard;
    private String webcam;
    private String microphone;
    private NetworkAdapter networkAdapter;
    private double budget;

    public PCconfig(
            String processor,
            String motherboard,
            int ramGb,
            int storageGb,
            String usageType,
            String graphicsCard,
            int powerSupplyW,
            String coolingType,
            String caseModel,
            String storageType,
            String operatingSystem,
            String ramType,
            int ramModules,
            int storageDevices,
            int monitorCount,
            double monitorSize,
            String monitorResolution,
            String keyboard,
            String mouse,
            boolean wifiEnabled,
            boolean bluetoothEnabled,
            int ethernetSpeed,
            int usbPorts,
            int fanCount,
            boolean rgbLighting,
            String soundCard,
            String webcam,
            String microphone,
            NetworkAdapter networkAdapter,
            double budget
    ) {
        this.processor = processor;
        this.motherboard = motherboard;
        this.ramGb = ramGb;
        this.storageGb = storageGb;
        this.usageType = usageType;
        this.graphicsCard = graphicsCard;
        this.powerSupplyW = powerSupplyW;
        this.coolingType = coolingType;
        this.caseModel = caseModel;
        this.storageType = storageType;
        this.operatingSystem = operatingSystem;
        this.ramType = ramType;
        this.ramModules = ramModules;
        this.storageDevices = storageDevices;
        this.monitorCount = monitorCount;
        this.monitorSize = monitorSize;
        this.monitorResolution = monitorResolution;
        this.keyboard = keyboard;
        this.mouse = mouse;
        this.wifiEnabled = wifiEnabled;
        this.bluetoothEnabled = bluetoothEnabled;
        this.ethernetSpeed = ethernetSpeed;
        this.usbPorts = usbPorts;
        this.fanCount = fanCount;
        this.rgbLighting = rgbLighting;
        this.soundCard = soundCard;
        this.webcam = webcam;
        this.microphone = microphone;
        this.networkAdapter = networkAdapter;
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "PC Configuration:\n" +
                "Processor: " + processor + "\n" +
                "Motherboard: " + motherboard + "\n" +
                "RAM: " + ramGb + " GB\n" +
                "Storage: " + storageGb + " GB\n" +
                "Usage type: " + usageType + "\n" +
                "Graphics Card: " + graphicsCard + "\n" +
                "Power Supply: " + powerSupplyW + " W\n" +
                "Cooling: " + coolingType + "\n" +
                "Case: " + caseModel + "\n" +
                "Storage Type: " + storageType + "\n" +
                "Operating System: " + operatingSystem + "\n" +
                "RAM Type: " + ramType + "\n" +
                "RAM Modules: " + ramModules + "\n" +
                "Storage Devices: " + storageDevices + "\n" +
                "Monitor Count: " + monitorCount + "\n" +
                "Monitor Size: " + monitorSize + "\"\n" +
                "Monitor Resolution: " + monitorResolution + "\n" +
                "Keyboard: " + keyboard + "\n" +
                "Mouse: " + mouse + "\n" +
                "Wi-Fi: " + wifiEnabled + "\n" +
                "Bluetooth: " + bluetoothEnabled + "\n" +
                "Ethernet Speed: " + ethernetSpeed + " Mbps\n" +
                "USB Ports: " + usbPorts + "\n" +
                "Fan Count: " + fanCount + "\n" +
                "RGB Lighting: " + rgbLighting + "\n" +
                "Sound Card: " + soundCard + "\n" +
                "Webcam: " + webcam + "\n" +
                "Microphone: " + microphone + "\n" +
                "Network Adapter: " + networkAdapter + "\n" +
                "Budget: $" + budget;
    }
}