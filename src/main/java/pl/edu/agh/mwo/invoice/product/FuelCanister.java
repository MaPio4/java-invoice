package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanister extends Fuel {
    protected FuelCanister(String name, BigDecimal price, BigDecimal excise) {
        super(name, price, excise);
    }

    protected FuelCanister(String name, BigDecimal price) {
        super(name, price);
    }

}
