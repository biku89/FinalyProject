package service;

import model.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odpowiedzialna za przetwarzanie zamówień, generowanie faktur oraz ich zarządzaniem.
 */

public class OrderProcessor {
    private final List<Order> orders = new ArrayList<>();

    /**
     * Tworzy nowe zamówienie na podsrawie zawartości koszyka. Rabat jest stosowany jeśli są spełnione warunki
     *
     * @param clientName - imię i nazwisko klienta
     * @param cart       - koszyk klienta
     * @return - zwraca utworzone zamówienie
     */
    public Order createOrder(String clientName, Cart cart) {
        Order newOrder = new Order(clientName, cart.getClientCart());

        Discount discount = new Discount();
        BigDecimal discounted = discount.applyDiscountTenPercent(newOrder.getPriceSummary());
        newOrder.setPriceAfterDiscount(discounted);

        orders.add(newOrder);
        System.out.println("Zamówienie zostało złożone.");
        return newOrder;
    }

    /**
     * Przetwarzanie zamówień w osobnych wątkach
     * Każde zamówienie czeka aż poprzednie skończy się przetwarzać
     *
     * @param order - zamówienie które będzie przetwarzane
     */
    public void processOrder(Order order) {
        OrderThread orderThread = new OrderThread(order);
        Thread thread = new Thread(orderThread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println("Błąd przy przetwarzaniu zamówienia");
        }
    }

    /**
     * Generuje fakturę dla danego zamówienia i wyświetla jej szczegóły.
     *
     * @param order zamówienie na podstawie którego jest generowana faktura.
     */
    public void generateInvoice(Order order) {
        System.out.println("Faktura : ");
        order.printOrderSummary();
    }

    public void showAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("Brak złożonych zamówień");
        } else {
            System.out.println("Złożone zamówienia: ");
            orders.forEach(Order::printOrderSummary);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }
}
