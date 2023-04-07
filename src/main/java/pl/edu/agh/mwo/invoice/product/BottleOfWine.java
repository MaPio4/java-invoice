package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleOfWine extends Alcohol {
    protected BottleOfWine(String name, BigDecimal price, BigDecimal excise) {
        super(name, price, excise);
    }

    protected BottleOfWine(String name, BigDecimal price) {
        super(name, price);
    }
}
