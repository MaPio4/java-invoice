package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import pl.edu.agh.mwo.invoice.Invoice;
import pl.edu.agh.mwo.invoice.invoiceid.WrongInitialNumber;
import pl.edu.agh.mwo.invoice.product.DairyProduct;
import pl.edu.agh.mwo.invoice.product.OtherProduct;
import pl.edu.agh.mwo.invoice.product.Product;
import pl.edu.agh.mwo.invoice.product.TaxFreeProduct;

public class InvoiceGeneratorTest {


    @Test
    public void generateFirstInvoiceAndCheckItsId() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Invoice invoice = invoiceGenerator.generateNewInvoice();
        Assert.assertEquals(1, invoice.getId());
    }

    @Test
    public void generateTenInvoicesAndCheckItsId() {
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        for(int i = 0; i < 9; i++) {
            invoiceGenerator.generateNewInvoice();
        }
        Assert.assertEquals(10, invoiceGenerator.generateNewInvoice().getId());
    }

    @Test
    public void generateTenInvoicesAndCheckItsIdWhenInitNumberIsGiven() {
        InvoiceGenerator invoiceGenerator;
        try {
            invoiceGenerator = new InvoiceGenerator(10);
        } catch (WrongInitialNumber e) {
            Assert.fail("'WrongInitialNumber' exception was thrown");
            return;
        }

        for(int i = 0; i < 9; i++) {
            invoiceGenerator.generateNewInvoice();
        }
        Assert.assertEquals(20, invoiceGenerator.generateNewInvoice().getId());
    }
}
