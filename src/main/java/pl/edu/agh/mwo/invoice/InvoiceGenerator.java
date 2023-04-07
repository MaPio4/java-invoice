package pl.edu.agh.mwo.invoice;

import pl.edu.agh.mwo.invoice.Invoice;
import pl.edu.agh.mwo.invoice.invoiceid.IdGenerator;
import pl.edu.agh.mwo.invoice.invoiceid.WrongInitialNumber;

public class InvoiceGenerator {

    private IdGenerator idGenerator;

    public InvoiceGenerator() {
        this.idGenerator = new IdGenerator();
    }

    public InvoiceGenerator(int initInvoiceNumber) throws WrongInitialNumber {
        this.idGenerator = new IdGenerator(initInvoiceNumber);
    }

    public Invoice generateNewInvoice() {
        return new Invoice(idGenerator.getNextNumber());
    }


}
