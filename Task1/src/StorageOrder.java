import java.util.ArrayList;
import java.util.List;

public class StorageOrder {
    List<Order> ordersHistory = new ArrayList<>();

    public void saveOrder(Order order){
        ordersHistory.add(order);
        System.err.println("Zapisano zamówienie");
    }

    public void showHistoryOrder(){
        if (ordersHistory.isEmpty()){
            System.out.println("Historia zamówień jest pusta");
            return;
        }
        ordersHistory.stream().forEach(Order::printOrderSummary);
    }

    public List<Order> getOrdersHistory() {
        return ordersHistory;
    }

    public void setOrdersHistory(List<Order> ordersHistory) {
        this.ordersHistory = ordersHistory;
    }
}
