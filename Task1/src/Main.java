public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer(1, "Ryzen", 4500, 2);
        computer.hardwareConfiguration("Ryzen amd 6000", 32);

        Smartfon smartfon = new Smartfon(2, "Xiaomi", 5, 10000);
        smartfon.hardwareConfiguration("Czarny" , 5000);

        Electronicts electronicts = new Electronicts(3, "Steeleseries rival", 300, 2);

        System.out.println("Info o kompuie");
        computer.printInfo();

        System.out.println("Info o smartfonie");
        computer.printInfo();

        System.out.println("Elektronika");
        electronicts.printInfo();

    }
}