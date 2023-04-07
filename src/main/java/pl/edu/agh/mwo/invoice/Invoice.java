package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private int id;
    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    protected Invoice(int id) {
        this.id = id;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        for(Product existingProduct : products.keySet()) {
            if(existingProduct.getName().equals(product.getName())
            && existingProduct.getPrice().equals(product.getPrice())
            && existingProduct.getTaxPercent().equals(product.getTaxPercent())) {
                Integer numberOfExistingProduct = products.get(existingProduct);
                numberOfExistingProduct += quantity;
                products.put(existingProduct, numberOfExistingProduct);
                return;
            }
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getId() {
        return this.id;
    }

    public Map<Product, Integer> getProducts() {
        return this.products;
    }
}
