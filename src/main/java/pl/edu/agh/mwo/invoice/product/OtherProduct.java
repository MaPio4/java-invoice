package pl.edu.agh.mwo.invoice.product;

import pl.edu.agh.mwo.invoice.discount.TaxDefinitions;

import java.math.BigDecimal;

public class OtherProduct extends Product {
    public OtherProduct(String name, BigDecimal price) {
        super(name, price, TaxDefinitions.VAT_STANDARD);
    }
}
