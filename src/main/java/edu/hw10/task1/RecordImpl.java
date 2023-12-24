package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import java.util.Date;

public record RecordImpl(int amount) {
    public RecordImpl(@Max(number = 4) @Min(number = 1) int amount) {
        this.amount = amount;
    }

    public static RecordImpl create(int amount) {
        return new RecordImpl(amount);
    }

    public static RecordImpl createWithDate(int amount, Date date) {
        return new RecordImpl(amount);
    }

    public static RecordImpl createMinMax(@Max(number = 5) @Min(number = 1) int amount) {
        return new RecordImpl(amount);
    }
}
