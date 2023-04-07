package pl.edu.agh.mwo.invoice.product;

import pl.edu.agh.mwo.invoice.discount.TaxDefinitions;

import java.math.BigDecimal;

public abstract class Alcohol extends ProductWithExcise {
    protected Alcohol(String name, BigDecimal price, BigDecimal excise) {
        super(name, price, TaxDefinitions.VAT_FOOD, excise);
    }

    protected Alcohol(String name, BigDecimal price) {
        super(name, price, TaxDefinitions.VAT_FOOD);
    }
}
