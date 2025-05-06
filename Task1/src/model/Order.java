package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.TimeZone;
import java.time.format.DateTimeFormatter;

/**
 * Reprezentacja zamówień klienta która zwiera listę produktów, datę ich złożenia oraz sumę zamówienia.
 */
public class Order {
    private String clientName;
    private List<CartItem> orderItems;
    private BigDecimal priceSummary;
    private ZonedDateTime orderDate;
    private BigDecimal priceAfterDiscount;

    public Order(String clientName, List<CartItem> orderItems) {
        this.clientName = clientName;
        this.orderItems = orderItems;
        this.priceSummary = sumOrder();
        this.orderDate = ZonedDateTime.now(TimeZone.getDefault().toZoneId());
    }

    /**
     * Oblicza sumę zamówienia bez rabatu
     *
     * @return całkowita wartość zamówienia
     */
    private BigDecimal sumOrder() {
        return orderItems.stream()
                .map(CartItem::getTotalPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Wyświetla podsumomowanie zakupów dla pojedynczego klienta z uwzględnieniem rabatów
     */
    public void printOrderSummary() {
        System.out.println("Zamówienie dla: " + clientName);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm z");
        System.out.println("Data: " + orderDate.format(formatter));
        System.out.println("Produkty: ");

        for (CartItem item : orderItems) {
            System.out.println(" - " + item.getProduct().getName()
                    + ", Konfiguracja: " + item.getConfiguration()
                    + ", Ilość : " + item.getQuantity()
                    + ", cena: " + item.getTotalPrice() + " zł ");

        }
        System.out.println("Suma zamówienia przed rabatem: " + priceSummary);
        System.out.println("Suma zamówienia po rabacie " + getFinalPrice() + " zł");
    }


    public BigDecimal getPriceSummary() {
        return priceSummary;
    }

    public String getClientName() {
        return clientName;
    }

    public void setPriceAfterDiscount(BigDecimal priceAfterDiscount) {
        this.priceAfterDiscount = priceAfterDiscount;
    }

    public BigDecimal getFinalPrice() {
        return priceAfterDiscount != null ? priceAfterDiscount : priceSummary;
    }
}
