import java.math.BigDecimal;

public class Discount {
    public BigDecimal applyDiscountTenPercent(BigDecimal totalAmount){
        if (totalAmount.compareTo(new BigDecimal("5000")) > 0){
            System.out.println("Użyto rabatu 10%");
            return totalAmount.multiply(new BigDecimal("0.9"));
        }
        return totalAmount;
    }
}
