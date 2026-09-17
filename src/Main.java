public class Main {
    public static void main(String[] args) {
        PCpresetDirector director = new PCpresetDirector();

        PCconfig basic = director.buildBasicPC();
        PCconfig gaming =  director.buildGamingPC();
        PCconfig workstation = director.buildWorkstationPC();

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
