package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa przechowująca historię złożonych zamówień.
 */
public class StorageOrder {
    private List<Order> ordersHistory = new ArrayList<>();

    /**
     * Dodaje zamówwienie do listy historii.
     *
     * @param order - zamówienie do zapisania.
     */
    public synchronized void saveOrder(Order order) {
        ordersHistory.add(order);
        System.out.println("Zapisano zamówienie");
    }

    /**
     * Wyświetla historię wszystkich zamówień
     */
    public void showHistoryOrder() {
        if (ordersHistory.isEmpty()) {
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
