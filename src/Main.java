public class Main {
    public static void main(String[] args) {
        NetworkAdapter networkAdapter = new NetworkAdapter("Intel", "Wi-Fi 6E", 2500);

        PCconfig gamingPC = new PCconfig(
                "AMD Ryzen 7 7800X3D",
                "ASUS TUF Gaming B650-Plus",
                32,
                1000,
                "GAMING",
                "NVIDIA RTX 4070 SUPER",
                750,
                "Air",
                "NZXT H5 Flow",
                "NVMe",
                "Windows 11",
                "DDR5",
                2,
                1,
                2,
                27.0,
                "2560x1440",
                "Logitech G Pro Keyboard",
                "Logitech G Pro X Superlight",
                true,
                true,
                2500,
                8,
                4,
                true,
                "Integrated",
                "Logitech C920",
                "HyperX QuadCast",
                networkAdapter,
                1800.0
        );

        System.out.println(gamingPC);
    }
}
