import java.util.concurrent.Semaphore;

public class Produtor extends Thread {
    private int idThread;
    private Semaphore semaforo;
    public String status;
    static Programa programa;
    static boolean flag = true;


    public Produtor(int id, Semaphore semaphore, Programa programa) {
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
        if (idThread <= 3) {
            if (Main.buffer[1] == 0) {
                Main.buffer[1] = (int) (Math.random() * 100);
                status = "Thread #" + idThread + " produziu valor " + Main.buffer[1];
                programa.setInfosSistemaProdutor(status);
            } else if (Main.buffer[2] == 0) {
                Main.buffer[2] = (int) (Math.random() * 100);
                status = "Thread #" + idThread + " produziu valor " + Main.buffer[2];
                programa.setInfosSistemaProdutor(status);
            } else if (Main.buffer[3] == 0) {
                Main.buffer[3] = (int) (Math.random() * 100);
                status = "Thread #" + idThread + " produziu valor " + Main.buffer[3];
                programa.setInfosSistemaProdutor(status);
            } else if (Main.buffer[4] == 0) {
                Main.buffer[4] = (int) (Math.random() * 100);
                status = "Thread #" + idThread + " produziu valor " + Main.buffer[4];
                programa.setInfosSistemaProdutor(status);
            }
        }
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
                System.out.println("Produtor em Espera");
            }
        }
    }

}