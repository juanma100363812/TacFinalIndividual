package juanma.tac.utils;

import java.util.Arrays;
import java.util.Objects;

/**
 * Usado para generar un arbol de decisiones sobre nodos
 */
public class BooleanoArrayUtil {
    private final int n;
    private boolean[] array;



    private boolean complete = false;

    public BooleanoArrayUtil(int n) {
        this.n = n;
        array = new boolean[n];
        for (int i = 0; i < n; i++) {
            array[i] = false;
        }
    }

    public BooleanoArrayUtil(int n, boolean type) {
        this.n = n;
        array = new boolean[n];
        for (int i = 0; i < n; i++) {
            array[i] = type;
        }
    }

    public boolean[] getArray() {
        return array;
    }

    public int getN() {
        return n;
    }

    public boolean isFinished() {
        boolean ret = true;
        if (!complete) {
            for (boolean p : array) {
                if (!p)
                    return false;
            }
            complete = true;
            return false;
        }
        return ret;
    }

    public BooleanoArrayUtil add() {
        boolean acc = false;
        int pos = n - 1;
        while (pos >= 0 && array[pos]) {
            array[pos] = false;
            pos--;
        }
        if (pos <0)pos = 0;
        array[pos] = true;
        return this;
    }
    public void setArray(boolean[] array) {
        this.array = array;
    }

    public boolean[] cloneArray (){
        boolean[] array_clone = new boolean[n];
        if (n >= 0) System.arraycopy(array, 0, array_clone, 0, n);
        return array_clone;
    }

    public int total(){
        int ret=0;
        for (boolean n: array){
            if(n)ret++;
        }
        return ret;
    }

}
