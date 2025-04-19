public class OrderThread implements Runnable {
    private Order order;

    public OrderThread(Order order) {
        this.order = order;
    }

    public void run(){
        System.out.println("Przetwarzanie zamówienia dla: " + order.getClientName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e){
            System.out.println("Przetwarzania zostało przerwane " + e.getMessage());
            return;
        }
        System.out.println("Zamówienie zakończono dla: " + order.getClientName());
        order.printOrderSummary();
    }
}
