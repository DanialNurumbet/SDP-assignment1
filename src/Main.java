public class Main {
    public static void main(String[] args) {
        NetworkAdapter networkAdapter = new NetworkAdapter("Intel", "Wi-Fi 6E", 2500);

        PCpresetDirector director = new PCpresetDirector();

        PCconfig basic = director.PCBasic();
        PCconfig gaming =  director.PCGaming();
        PCconfig workstation = director.PCWorkstation();

        System.out.println("-----BASIC-----");
        System.out.println(basic);
        System.out.println();
        System.out.println("-----GAMING-----");
        System.out.println(gaming);
        System.out.println();
        System.out.println("-----WORKSTATION-----");
        System.out.println(workstation);
    }
}
