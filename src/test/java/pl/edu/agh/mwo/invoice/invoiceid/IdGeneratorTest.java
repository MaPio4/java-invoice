package pl.edu.agh.mwo.invoice.invoiceid;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class IdGeneratorTest {
    @Test
    public void testInvoiceFirstId() {
        IdGenerator idGenerator = new IdGenerator();
        int firstId = idGenerator.getNextNumber();
        Assert.assertEquals(1, firstId);
    }

    @Test
    public void testInvoiceTenthId() {
        IdGenerator idGenerator = new IdGenerator();
        for(int i = 0; i < 9; i++) {
            idGenerator.getNextNumber();
        }
        Assert.assertEquals(10, idGenerator.getNextNumber());
    }

    @Test
    public void testInvoiceThousandthId() {
        IdGenerator idGenerator = new IdGenerator();
        for(int i = 0; i < 999; i++) {
            idGenerator.getNextNumber();
        }
        Assert.assertEquals(1000, idGenerator.getNextNumber());
    }

    @Test
    public void testInvoiceNextIdWhenStartIdGiven() {
        IdGenerator idGenerator = null;
        try {
            idGenerator = new IdGenerator(10);
        } catch (WrongInitialNumber e) {
            Assert.fail("WrongInitialNumber exception has been thrown");
        }

        Assert.assertEquals(11, idGenerator.getNextNumber());
    }

    @Test
    public void testInvoiceIdNegativeInitialNumberGiven() {
        IdGenerator idGenerator = null;
        Exception expectedException = null;
        try {
            idGenerator = new IdGenerator(-1);
        } catch (Exception e) {
            expectedException = e;
        }
        Assert.assertNotNull(expectedException);
        Assert.assertEquals(expectedException.getClass(), WrongInitialNumber.class);
    }
}
