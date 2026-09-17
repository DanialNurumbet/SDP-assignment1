public class PCconfig {
    private final String processor;
    private final String motherboard;
    private final int ramGb;
    private final int storageGb;

    private final String usageType;
    private final String graphicsCard;
    private final int powerSupplyW;
    private final String coolingType;
    private final String caseModel;
    private final String storageType;
    private final String operatingSystem;
    private final String ramType;
    private final int ramModules;
    private final int storageDevices;
    private final int monitorCount;
    private final double monitorSize;
    private final String monitorResolution;
    private final String keyboard;
    private final String mouse;
    private final boolean wifiEnabled;
    private final boolean bluetoothEnabled;
    private final int ethernetSpeed;
    private final int usbPorts;
    private final int fanCount;
    private final boolean rgbLighting;
    private final String soundCard;
    private final String webcam;
    private final String microphone;
    private final NetworkAdapter networkAdapter;
    private final double budget;

    public static class Builder {
        private final String processor;
        private final String motherboard;
        private final int ramGb;
        private final int storageGb;

        private String usageType = "OFFICE";
        private String graphicsCard = "Integrated Graphics";
        private int powerSupplyW = 500;
        private String coolingType = "Stock";
        private String caseModel = "Standard ATX Case";
        private String storageType = "SSD";
        private String operatingSystem = "Windows 11";
        private String ramType = "DDR4";
        private int ramModules = 2;
        private int storageDevices = 1;
        private int monitorCount = 1;
        private double monitorSize = 24.0;
        private String monitorResolution = "1920x1080";
        private String keyboard = "Standard Keyboard";
        private String mouse = "Standard Mouse";
        private boolean wifiEnabled = false;
        private boolean bluetoothEnabled = false;
        private int ethernetSpeed = 1000;
        private int usbPorts = 6;
        private int fanCount = 2;
        private boolean rgbLighting = false;
        private String soundCard = "Integrated";
        private String webcam = null;
        private String microphone = null;
        private NetworkAdapter networkAdapter = null;
        private double budget = 1000.0;

        public Builder(
                String processor,
                String motherboard,
                int ramGb,
                int storageGb
        ) {
        this.processor = processor;
        this.motherboard = motherboard;
        this.ramGb = ramGb;
        this.storageGb = storageGb;
        }

        public Builder usageType(String usageType) {
            this.usageType = usageType;
            return this;
        }

        public Builder graphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        public Builder powerSupplyW(int powerSupplyW) {
            this.powerSupplyW = powerSupplyW;
            return this;
        }

        public Builder coolingType(String coolingType) {
            this.coolingType = coolingType;
            return this;
        }

        public Builder caseModel(String caseModel) {
            this.caseModel = caseModel;
            return this;
        }

        public Builder storageType(String storageType) {
            this.storageType = storageType;
            return this;
        }

        public Builder operatingSystem(String operatingSystem) {
            this.operatingSystem = operatingSystem;
            return this;
        }

        public Builder ramType(String ramType) {
            this.ramType = ramType;
            return this;
        }

        public Builder ramModules(int ramModules) {
            this.ramModules = ramModules;
            return this;
        }

        public Builder storageDevices(int storageDevices) {
            this.storageDevices = storageDevices;
            return this;
        }

        public Builder monitorCount(int monitorCount) {
            this.monitorCount = monitorCount;
            return this;
        }

        public Builder monitorSize(double monitorSize) {
            this.monitorSize = monitorSize;
            return this;
        }

        public Builder monitorResolution(String monitorResolution) {
            this.monitorResolution = monitorResolution;
            return this;
        }
        public Builder keyboard(String keyboard) {
            this.keyboard = keyboard;
            return this;
        }

        public Builder mouse(String mouse) {
            this.mouse = mouse;
            return this;
        }

        public Builder wifiEnabled(boolean wifiEnabled) {
            this.wifiEnabled = wifiEnabled;
            return this;
        }

        public Builder bluetoothEnabled(boolean bluetoothEnabled) {
            this.bluetoothEnabled = bluetoothEnabled;
            return this;
        }

        public Builder ethernetSpeed(int ethernetSpeed) {
            this.ethernetSpeed = ethernetSpeed;
            return this;
        }

        public Builder usbPorts(int usbPorts) {
            this.usbPorts = usbPorts;
            return this;
        }

        public Builder fanCount(int fanCount) {
            this.fanCount = fanCount;
            return this;
        }

        public Builder rgbLighting(boolean rgbLighting) {
            this.rgbLighting = rgbLighting;
            return this;
        }

        public Builder soundCard(String soundCard) {
            this.soundCard = soundCard;
            return this;
        }

        public Builder webcam(String webcam) {
            this.webcam = webcam;
            return this;
        }

        public Builder microphone(String microphone) {
            this.microphone = microphone;
            return this;
        }

        public Builder networkAdapter(NetworkAdapter networkAdapter) {
            this.networkAdapter = networkAdapter;
            return this;
        }

        public Builder budget(double budget) {
            this.budget = budget;
            return this;
        }
        public PCconfig build(){
            return new PCconfig(this);
        }
    }

    private PCconfig(Builder builder) {
        this.processor = builder.processor;
        this.motherboard = builder.motherboard;
        this.ramGb = builder.ramGb;
        this.storageGb = builder.storageGb;
        this.usageType = builder.usageType;
        this.graphicsCard = builder.graphicsCard;
        this.powerSupplyW = builder.powerSupplyW;
        this.coolingType = builder.coolingType;
        this.caseModel = builder.caseModel;
        this.storageType = builder.storageType;
        this.operatingSystem = builder.operatingSystem;
        this.ramType = builder.ramType;
        this.ramModules = builder.ramModules;
        this.storageDevices = builder.storageDevices;
        this.monitorCount = builder.monitorCount;
        this.monitorSize = builder.monitorSize;
        this.monitorResolution = builder.monitorResolution;
        this.keyboard = builder.keyboard;
        this.mouse = builder.mouse;
        this.wifiEnabled = builder.wifiEnabled;
        this.bluetoothEnabled = builder.bluetoothEnabled;
        this.ethernetSpeed = builder.ethernetSpeed;
        this.usbPorts = builder.usbPorts;
        this.fanCount = builder.fanCount;
        this.rgbLighting = builder.rgbLighting;
        this.soundCard = builder.soundCard;
        this.webcam = builder.webcam;
        this.microphone = builder.microphone;
        this.networkAdapter = builder.networkAdapter;
        this.budget = builder.budget;
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