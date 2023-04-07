package pl.edu.agh.mwo.invoice.invoiceid;

public class IdGenerator {
    private int numberOfGeneratedInvoice;

    public IdGenerator() {
        this.numberOfGeneratedInvoice = 0;
    }

    public IdGenerator(int initialNumber) throws WrongInitialNumber {
        if (initialNumber < 0) {
            throw new WrongInitialNumber();
        }
        this.numberOfGeneratedInvoice = initialNumber;
    }

    public int getNextNumber() {
        this.numberOfGeneratedInvoice++;
        return this.numberOfGeneratedInvoice;
    }


}
