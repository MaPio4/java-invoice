package pl.edu.agh.mwo.invoice.product;

import pl.edu.agh.mwo.invoice.discount.TaxDefinitions;

import java.math.BigDecimal;

public abstract class ProductWithExcise extends Product {
    private BigDecimal excise;

    protected ProductWithExcise(String name, BigDecimal price, BigDecimal tax, BigDecimal excise) {
        super(name, price, tax);
        this.excise = excise;
    }

    protected ProductWithExcise(String name, BigDecimal price, BigDecimal tax) {
        super(name, price, tax);
        this.excise = TaxDefinitions.EXCISE;
    }

    public BigDecimal getExcise() {
        return excise;
    }

    @Override
    public BigDecimal getPriceWithTax() {
        return this.getPrice().multiply(this.getTaxPercent()).add(this.getPrice()).add(this.excise);
    }
}
