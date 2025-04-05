public class Smartfon extends Product {
    private String color;
    private int batteryCapacity;

    public Smartfon(int id, String name, double price, int quantityAvaliable) {
        super(id, name, price, quantityAvaliable);
    }

    public void hardwareConfiguration(String color, int batteryCapacity){
        this.color = color;
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public void printInfo(){
        super.printInfo();
        System.out.println("Color: " + color);
        System.out.println("Battery capacity: " + batteryCapacity);
    }
}
