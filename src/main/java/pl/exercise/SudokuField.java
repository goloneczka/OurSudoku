package pl.exercise;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuField {

    private int x = 0;
    private int y = 0;
    private int value = 0;
    private boolean constPoint = false;

    SudokuField(int x, int y, int value, boolean constPoint) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.constPoint = constPoint;
    }

    SudokuField(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.constPoint = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int value) {
        this.value = value;
    }

    public boolean isConstPoint() {
        return constPoint;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
                append("x", x).
                append("y", y).
                append("value", value).toString();

    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        SudokuField rhs = (SudokuField) obj;
        return new EqualsBuilder().
                append(x, rhs.getX()).
                append(y, rhs.getY()).
                append(value, rhs.getFieldValue()).
                isEquals();

    }

    @Override

    public int hashCode() {
        return new HashCodeBuilder(17, 31).
                append(x).append(y).append(value).append(constPoint).
                toHashCode();
    }
}
