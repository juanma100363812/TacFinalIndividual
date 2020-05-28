package juanma.tac.utils;

public class TimeUtils {

    public static void timeElapsed(int n,MedidaCallback callback){
        long timeInit = System.nanoTime();
        callback.execute();
        long elapsed = System.nanoTime()-timeInit;
        System.out.println(n+";"+elapsed);
    }


    public interface MedidaCallback{
        void execute();
    }
}
