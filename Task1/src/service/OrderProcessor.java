package service;

import model.Cart;
import model.Order;
import model.OrderStorage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Klasa odpowiedzialna za przetwarzanie zamówień, generowanie faktur oraz ich zarządzaniem.
 */

public class OrderProcessor {
    private final List<Order> orders = new ArrayList<>();
    private final OrderStorage storageOrder = new OrderStorage();

    /**
     * Tworzy nowe zamówienie na podsrawie zawartości koszyka. Rabat jest stosowany jeśli są spełnione warunki
     *
     * @param clientName - imię i nazwisko klienta
     * @param cart       - koszyk klienta
     * @return - zwraca utworzone zamówienie
     */
    public Order createOrder(String clientName, Cart cart, String promoCode) {
        Order newOrder = new Order(clientName, cart.getCartItems());

        BigDecimal discounted = applyDiscounts(newOrder, promoCode);
        newOrder.setPriceAfterDiscount(discounted);

        orders.add(newOrder);
        storageOrder.saveOrder(newOrder);

        System.out.println("Zamówienie zostało złożone.");
        return newOrder;
    }

    private BigDecimal applyDiscounts(Order order, String promoCode) {
        DiscountService discountService = new DiscountService();
        if (promoCode != null && promoCode.equalsIgnoreCase(DiscountService.CODE_PROMO_20)){
            return  discountService.applyPromoCode(order.getPriceSummary(), promoCode);
        } else {
            return discountService.applyDiscountTenPercent(order.getPriceSummary());
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

    public CompletableFuture<String> generateInvoiceAsync(Order order) {
        return CompletableFuture.supplyAsync(() -> {
            generateInvoice(order);
            return "Faktura została wygenerowana";
        });
    }

    public void showAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("Brak złożonych zamówień");
        } else {
            System.out.println("Złożone zamówienia: ");
            orders.forEach(Order::printOrderSummary);
        }
    }
    public void printOrderHistory(){
        storageOrder.showHistoryOrder();
    }

    public List<Order> getOrders() {
        return orders;
    }
}
