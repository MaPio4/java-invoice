package pl.edu.agh.mwo.invoice.printer;

import pl.edu.agh.mwo.invoice.Invoice;
import pl.edu.agh.mwo.invoice.product.Product;

import java.math.BigDecimal;
import java.util.Map;

public class InvoicePrinter {
    private Invoice invoice;

    public InvoicePrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getPrintMessage() {
        String output = "Faktura #" + invoice.getId() + "\n";
        output += this.generateProductsPrint();
        output += "Liczba pozycji: " + invoice.getProducts().size();
        return output;
    }

    private String generateProductsPrint() {
        Map<Product, Integer> products = invoice.getProducts();
        String ouptut = "";

        for (Product product : products.keySet()) {
            ouptut += "-> " + product.getName() + "\t|" + products.get(product) + "\t|"
                    + product.getPriceWithTax().multiply(
                            new BigDecimal(products.get(product))).toString() + "\n";
        }
        return ouptut;
    }
}
