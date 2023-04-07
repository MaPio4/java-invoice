package pl.edu.agh.mwo.invoice.printer;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.mwo.invoice.Invoice;
import pl.edu.agh.mwo.invoice.InvoiceGenerator;
import pl.edu.agh.mwo.invoice.product.DairyProduct;
import pl.edu.agh.mwo.invoice.product.OtherProduct;
import pl.edu.agh.mwo.invoice.product.Product;
import pl.edu.agh.mwo.invoice.product.TaxFreeProduct;

import java.math.BigDecimal;

public class InvoicePrinterTest {

    @Test
    public void testInvoicePrintWith3Products() {
        String[] expectedSubstrings = {
                "Faktura #1",
                "-> Wino\t|1\t|12.30",
                "-> Maslanka\t|1\t|108.00",
                "-> Owoce\t|1\t|200",
                "Liczba pozycji: 3"};

        Invoice invoice = new InvoiceGenerator().generateNewInvoice();
        InvoicePrinter invoicePrinter = new InvoicePrinter(invoice);

        invoice.addProduct(new TaxFreeProduct("Owoce", new BigDecimal("200")));
        invoice.addProduct(new DairyProduct("Maslanka", new BigDecimal("100")));
        invoice.addProduct(new OtherProduct("Wino", new BigDecimal("10")));
        Assert.assertThat(new BigDecimal("310"), Matchers.comparesEqualTo(invoice.getNetTotal()));
        String message = invoicePrinter.getPrintMessage();

        for (String substring : expectedSubstrings) {
            if(!message.contains(substring)) {
                Assert.fail("Invoice hasn't got the expected substring: '"+substring + "'\n"+message);
            }
        }
    }

    @Test
    public void testInvoicePrintOfTheSecondInvoiceWith3Products() {
        String expectedOutput = "Faktura #2\n" +
                "-> Wino\t|1\t|12.30\n" +
                "-> Maslanka\t|1\t|108.00\n" +
                "-> Owoce\t|1\t|200\n" +
                "Liczba pozycji: 3";
        InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
        Invoice firstInvoice = invoiceGenerator.generateNewInvoice();
        Invoice invoice = invoiceGenerator.generateNewInvoice();
        InvoicePrinter invoicePrinter = new InvoicePrinter(invoice);

        invoice.addProduct(new TaxFreeProduct("Owoce", new BigDecimal("200")));
        invoice.addProduct(new DairyProduct("Maslanka", new BigDecimal("100")));
        invoice.addProduct(new OtherProduct("Wino", new BigDecimal("10")));
        Assert.assertThat(new BigDecimal("310"), Matchers.comparesEqualTo(invoice.getNetTotal()));
        System.out.println(invoicePrinter.getPrintMessage());

        Assert.assertTrue("Generated invoice print is not as expected.",
                expectedOutput.equals(invoicePrinter.getPrintMessage()));
    }
}
