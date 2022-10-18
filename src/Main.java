import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Main {

    public static int buffer[] = new int[5];
    static Programa programa = new Programa();

    public static void inciarThreads() {
        Produtor produtor1 = new Produtor(1, semaforo, programa);
        Produtor produtor2 = new Produtor(2, semaforo, programa);
        Produtor produtor3 = new Produtor(3, semaforo, programa);

        produtor1.start();
        produtor2.start();
        produtor3.start();
        Consumidor consumidor1 = new Consumidor(1, semaforo, programa);
        Consumidor consumidor2 = new Consumidor(2, semaforo, programa);

        consumidor1.start();
        consumidor2.start();
    }

    static Semaphore semaforo = new Semaphore(5);

    public static void main(String[] args) {
        inciarThreads();
    }
}