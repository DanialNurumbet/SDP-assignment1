public class Main {
    public static void main(String[] args) {
        NetworkAdapter networkAdapter = new NetworkAdapter("Intel", "Wi-Fi 6E", 2500);

        PCconfig gamingPC = new PCconfig.Builder(
                "AMD Ryzen 7 7800X3D",
                "ASUS TUF Gaming B650-Plus",
                32,
                1000
                )
                .usageType("GAMING")
                .graphicsCard("RTX 4070 SUPER")
                .powerSupplyW(750)
                .coolingType("Air")
                .caseModel("NZXT H5 Flow")
                .storageType("NVMe")
                .operatingSystem("Windows 11")
                .ramType("DDR5")
                .ramModules(2)
                .storageDevices(1)
                .monitorCount(2)
                .monitorSize(27.0)
                .monitorResolution("2560x1440")
                .keyboard("Logitech G Pro Keyboard")
                .mouse("Logitech G Pro X Superlight")
                .wifiEnabled(true)
                .bluetoothEnabled(true)
                .ethernetSpeed(2500)
                .usbPorts(8)
                .fanCount(4)
                .rgbLighting(true)
                .soundCard("Integrated")
                .webcam("Logitech C920")
                .microphone("HyperX QuadCast")
                .networkAdapter(
                        new NetworkAdapter("Intel", "Wi-Fi 6E", 2500)
                )
                .budget(1800.0)
                .build();

        System.out.println(gamingPC);
    }
}
