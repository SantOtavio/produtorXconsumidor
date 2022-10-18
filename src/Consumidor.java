import java.util.concurrent.Semaphore;

public class Consumidor extends Thread {
    private int idThread;
    private Semaphore semaforo;
    public String status;
    static Programa programa;
    static boolean flag = true;

    public Consumidor(int id, Semaphore semaphore, Programa programa) {
        this.idThread = id;
        this.semaforo = semaphore;
        this.programa = programa;
    }

    private void processar() {
        try {
            // System.out.println("Thread #" + idThread + " processando");
            Thread.sleep((long) (Math.random() * 1000));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void entrarRegiaoNaoCritica() {
        //System.out.println("Thread #" + idThread + " em região não crítica");
        processar();
    }

    private void entrarRegiaoCritica() {
        //System.out.println("Thread #" + idThread
        //      + " entrando em região crítica " + (int) (Math.random() * 100));
        processar();
        if (Main.buffer[1] != 0) {
            status = "Thread #" + idThread + " consumiu valor " + Main.buffer[1];
            programa.setInfosSistemaConsumidor(status);
            Main.buffer[1] = 0;
        } else if (Main.buffer[2] != 0) {
            status = "Thread #" + idThread + " consumiu valor " + Main.buffer[2];
            programa.setInfosSistemaConsumidor(status);
            Main.buffer[2] = 0;
        } else if (Main.buffer[3] != 0) {
            status = "Thread #" + idThread + " consumiu valor " + Main.buffer[3];
            programa.setInfosSistemaConsumidor(status);
            Main.buffer[3] = 0;
        } else if (Main.buffer[4] != 0) {
            status = "Thread #" + idThread + " consumiu valor " + Main.buffer[4];
            programa.setInfosSistemaConsumidor(status);
            Main.buffer[4] = 0;
        }
        // System.out.println("Thread #" + idThread + " saindo da região crítica");
    }

    public void run() {
        while (true){
            if (flag) {
                entrarRegiaoNaoCritica();
                try {
                    semaforo.acquire();
                    entrarRegiaoCritica();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaforo.release();
                }
            } else {
                System.out.println("Consumidor em espera");
            }
        }
    }

}