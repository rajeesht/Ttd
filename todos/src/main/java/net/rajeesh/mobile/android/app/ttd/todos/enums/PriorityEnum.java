package net.rajeesh.mobile.android.app.ttd.todos.enums;

/**
 * Created by rtripathi on 11/17/15.
 */
public enum PriorityEnum {
    HIGH(1), NORMAL(0), LOW(-1);
    private int priority;

    PriorityEnum(int priority) {
        this.priority = priority;
    }

    public int getPriorityValue() {
        return this.priority;
    }
}
