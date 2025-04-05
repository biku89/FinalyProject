public class Computer extends Product {
    private String procesorType;
    private int ram;

    public Computer(int id, String name, double price, int quantityAvaliable) {
        super(id, name, price, quantityAvaliable);

    }

    public void hardwareConfiguration(String procesorType, int ram){
        this.procesorType = procesorType;
        this.ram = ram;
    }
    @Override
    public void printInfo(){
        super.printInfo();
        System.out.println("Typ procesora " + procesorType);
        System.out.println("Ram " + ram);
    }
}
