public class PCpresetDirector {

    public PCconfig buildBasicPC(){
        return new PCconfig.Builder(
                "Intel Core i3-12100",
                "MSI H610M",
                8,
                256
        )
                .usageType("OFFICE")
                .graphicsCard("Integrated Graphics")
                .powerSupplyW(550)
                .budget(450)
                .build();
    }

    public PCconfig buildGamingPC(){
        return new PCconfig.Builder(
                "AMD Ryzen 7 7800X3D",
                "ASUS ROG STRIX B650",
                32,
                2000
        )
                .usageType("GAMING")
                .graphicsCard("NVIDIA RTX 4080 Super")
                .powerSupplyW(850)
                .coolingType("Liquid 360mm")
                .caseModel("NZXT H7 Flow")
                .storageType("NVMe SSD")
                .ramType("DDR5")
                .ramModules(2)
                .monitorCount(2)
                .monitorSize(27.0)
                .monitorResolution("2560x1440")
                .wifiEnabled(true)
                .bluetoothEnabled(true)
                .fanCount(6)
                .rgbLighting(true)
                .budget(2500.0)
                .build();
    }

    public PCconfig buildWorkstationPC(){
        return new PCconfig.Builder(
                "Intel Core i9-14900K",
                "ASUS ProArt Z790",
                64,
                4000
        )
                .usageType("WORKSTATION")
                .graphicsCard("NVIDIA RTX 4090")
                .powerSupplyW(1000)
                .coolingType("Custom Liquid")
                .caseModel("Fractal Design Meshify 2 XL")
                .storageType("NVMe SSD")
                .ramType("DDR5")
                .ramModules(4)
                .storageDevices(3)
                .monitorCount(3)
                .monitorSize(32.0)
                .monitorResolution("3840x2160")
                .wifiEnabled(true)
                .bluetoothEnabled(true)
                .ethernetSpeed(10000)
                .usbPorts(10)
                .fanCount(7)
                .rgbLighting(false)
                .soundCard("External Audio Interface")
                .webcam("4K Pro Webcam")
                .microphone("Studio XLR Microphone")
                .budget(5000.0)
                .build();
    }

}