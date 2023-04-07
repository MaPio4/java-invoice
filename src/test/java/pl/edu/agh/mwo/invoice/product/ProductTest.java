package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import org.junit.experimental.max.MaxCore;
import pl.edu.agh.mwo.invoice.discount.TaxDefinitions;
import pl.edu.agh.mwo.invoice.product.Product;

public class ProductTest {
    @Test
    public void testProductNameIsCorrect() {
        Product product = new OtherProduct("buty", new BigDecimal("100.0"));
        Assert.assertEquals("buty", product.getName());
    }

    @Test
    public void testProductPriceAndTaxWithDefaultTax() {
        Product product = new OtherProduct("Ogorki", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(TaxDefinitions.VAT_STANDARD, Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testProductPriceAndTaxWithDairyProduct() {
        Product product = new DairyProduct("Szarlotka", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(TaxDefinitions.VAT_FOOD, Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testPriceWithTax() {
        Product product = new DairyProduct("Oscypek", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("108"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }
    @Test
    public void testPriceOfFuelWithTax() {
        LocalDateTime time = LocalDateTime.of(2022, 03, 12, 7, 30);

        FuelCanister product = new FuelCanister("Gasoline", new BigDecimal("100.0"));
        BigDecimal assertValue = new BigDecimal("128.56").setScale(2, RoundingMode.UP);
        BigDecimal valueToAssert = product.getPriceWithTax(time).setScale(2, RoundingMode.UP);
        Assert.assertThat(assertValue, Matchers.comparesEqualTo(valueToAssert));

    }

    @Test
    public void testPriceOfFuelWithTaxOnMotherInLawDay() {
        LocalDateTime time = LocalDateTime.of(2022, 03, 5, 7, 30);

        FuelCanister product = new FuelCanister("Gasoline", new BigDecimal("100.0"));
        BigDecimal assertValue = new BigDecimal("105.56").setScale(2, RoundingMode.UP);
        BigDecimal valueToAssert = product.getPriceWithTax(time).setScale(2, RoundingMode.UP);
        Assert.assertThat(assertValue, Matchers.comparesEqualTo(valueToAssert));
    }

    @Test
    public void testPriceOfWineWithTaxOnMotherInLawDay() {
        LocalDateTime time = LocalDateTime.of(2022, 03, 5, 7, 30);

        FuelCanister product = new FuelCanister("Gasoline", new BigDecimal("100.0"));
        BigDecimal assertValue = new BigDecimal("105.56").setScale(2, RoundingMode.UP);
        BigDecimal valueToAssert = product.getPriceWithTax(time).setScale(2, RoundingMode.UP);
        Assert.assertThat(assertValue, Matchers.comparesEqualTo(valueToAssert));
    }

    @Test
    public void testPriceOfBottleOfWineWithTax() {
        BottleOfWine product = new BottleOfWine("Masseto", new BigDecimal("100.0"));
        BigDecimal assertValue = new BigDecimal("113.56").setScale(2, RoundingMode.UP);
        BigDecimal valueToAssert = product.getPriceWithTax().setScale(2, RoundingMode.UP);
        Assert.assertThat(assertValue, Matchers.comparesEqualTo(valueToAssert));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullName() {
        new OtherProduct(null, new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithEmptyName() {
        new TaxFreeProduct("", new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullPrice() {
        new DairyProduct("Banany", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNegativePrice() {
        new TaxFreeProduct("Mandarynki", new BigDecimal("-1.00"));
    }


}
