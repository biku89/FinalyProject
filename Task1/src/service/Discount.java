package service;

import java.math.BigDecimal;

/**
 * Klasa przechowuje informację o promocjach. W przyszłści możemy rozszerzać ją o dodatkowe promocje.
 */


public class Discount {
    /**
     * Naliczenie rabatu 10% dla wartości zamówień powyżej 5000 zł
     * @param totalAmount łączna wartość zamówienia przed rabaten
     * @return uwzględnienie rabatu
     */
    public BigDecimal applyDiscountTenPercent(BigDecimal totalAmount){
        if (totalAmount.compareTo(new BigDecimal("5000")) > 0){
            System.out.println("Użyto rabatu 10%");
            return totalAmount.multiply(new BigDecimal("0.9"));
        }
        return totalAmount;
    }
}
