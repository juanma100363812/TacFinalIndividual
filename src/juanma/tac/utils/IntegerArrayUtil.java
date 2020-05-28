package juanma.tac.utils;

/**
 * Usado para generar un arbol de decisiones no boleanas,
 */
public class IntegerArrayUtil {
    private final int n;
    private final int base;
    private final int[] array;
    private boolean complete = false;

    public IntegerArrayUtil(int n,int base) {
        this.n = n;
        this.base = base;
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = 0;
        }
    }

    public int[] getArray() {
        return array;
    }

    public int getN() {
        return n;
    }

    public int getBase() {
        return base-1;
    }

    public boolean isFinished() {
        boolean ret = true;
        if (!complete) {
            for (Integer p : array) {
                if (p != this.base-1)
                    return false;
            }
            complete = true;
            return false;
        }
        return ret;
    }

    public IntegerArrayUtil add() {
        int pos = n - 1;
        while (pos >= 0 && array[pos]==this.base-1) {
            array[pos]=0;
            pos--;
        }
        if (pos <0)pos = 0;
        array[pos]++;
        if (array[pos]>=this.base)array[pos]=this.base-1;
        return this;
    }
}
