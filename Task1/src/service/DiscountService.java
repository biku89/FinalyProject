package service;

import java.math.BigDecimal;

public class DiscountService {
    /**
     * Klasa reprezentuje metody naliczania promocji. W przyszłści możemy rozszerzać ją o dodatkowe promocje.
     */
    public static final String CODE_PROMO_20 = "PROMO20";

    /**
     * Naliczenie rabatu 10% dla wartości zamówień powyżej 5000 zł
     * @ CODE_PROMO_20 - kod promocyjny
     * @param totalAmount łączna wartość zamówienia przed rabaten
     * @return uwzględnienie rabatu
     */
    public BigDecimal applyDiscountTenPercent(BigDecimal totalAmount) {
        if (totalAmount.compareTo(new BigDecimal("15000")) > 0) {
            System.out.println("Użyto rabatu 10%");
            return totalAmount.multiply(new BigDecimal("0.9"));
        }
        return totalAmount;
    }

    public BigDecimal applyPromoCode(BigDecimal totalAmount, String promoCode) {
        if (CODE_PROMO_20.equalsIgnoreCase(promoCode)) {
            System.out.println("Użyto rabatu 20 % na wszystkie produkty");
            return totalAmount.multiply(new BigDecimal("0.8"));
        } else {
            System.out.println("Nieprawidłowy kod promocyjny");
        }
        return totalAmount;
    }
}
