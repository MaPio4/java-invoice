package pl.edu.agh.mwo.invoice.product;

import pl.edu.agh.mwo.invoice.discount.TaxDefinitions;

import java.math.BigDecimal;

public class DairyProduct extends Product {
    public DairyProduct(String name, BigDecimal price) {
        super(name, price, TaxDefinitions.VAT_FOOD);
    }
}
