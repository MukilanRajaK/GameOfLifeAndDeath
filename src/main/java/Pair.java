import java.util.Objects;

public class Pair {
    public final int row;
    public final int column;

    public Pair(int value1, int value2)
    {
        row=value1;
        column=value2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return row == pair.row && column == pair.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
