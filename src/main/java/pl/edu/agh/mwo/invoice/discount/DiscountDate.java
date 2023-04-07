package pl.edu.agh.mwo.invoice.discount;

import java.time.Month;

public class DiscountDate {
    private Month month;
    private int dayOfMonth;

    public DiscountDate(Month month, int dayOfMonth) {
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    public Month getMonth() {
        return month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }
}
