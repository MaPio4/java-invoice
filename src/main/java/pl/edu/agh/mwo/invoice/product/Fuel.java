package pl.edu.agh.mwo.invoice.product;

import pl.edu.agh.mwo.invoice.discount.DiscountCalendar;
import pl.edu.agh.mwo.invoice.discount.DiscountDate;
import pl.edu.agh.mwo.invoice.discount.TaxDefinitions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Fuel extends ProductWithExcise {

    private final DiscountDate dayOfExciseDiscount = DiscountCalendar.MOTHER_IN_LAW_DAY;

    protected Fuel(String name, BigDecimal price, BigDecimal excise) {
        super(name, price, TaxDefinitions.VAT_STANDARD, excise);
    }

    protected Fuel(String name, BigDecimal price) {
        super(name, price, TaxDefinitions.VAT_STANDARD);
    }

    @Override
    public BigDecimal getPriceWithTax() {
        LocalDateTime now = LocalDateTime.now();
        return this.getPriceWithTaxOnDay(now);
    }

    public BigDecimal getPriceWithTax(LocalDateTime onTime) {
        return this.getPriceWithTaxOnDay(onTime);
    }

    private BigDecimal getPriceWithTaxOnDay(LocalDateTime onTime) {

        if (onTime.getMonth() == dayOfExciseDiscount.getMonth() && onTime.getDayOfMonth()
                == dayOfExciseDiscount.getDayOfMonth()) {
            return this.getPrice().add(this.getExcise());
        }

        return this.getPrice().multiply(
                this.getTaxPercent()).add(this.getPrice()).add(this.getExcise());
    }
}
