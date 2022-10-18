import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Programa extends JFrame implements Runnable {
    private JPanel panel1;
    private JButton pararProdutoresButton;
    private JButton pararConsumidoresButton;
    private JTextField infosSistema;
    private JButton iniciarProdutoresButton;
    private JButton iniciarConsumidoresButton;
    private JLabel infoProdutor;
    private JLabel infoConsumidor;

    public Programa() {
        criarComponentes();
        pararProdutoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produtor.flag = false;
            }
        });
        iniciarProdutoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Produtor.flag = true;
            }
        });
        pararConsumidoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Consumidor.flag = false;
            }
        });
        iniciarConsumidoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Consumidor.flag = true;
            }
        });
    }

    private void criarComponentes() {
        setContentPane(panel1);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        run();
        pack();
    }

    public void setInfosSistemaProdutor(String infos) {
        infoProdutor.setText(infos);
    }

    public void setInfosSistemaConsumidor(String infos) {
        infoConsumidor.setText(infos);
    }

    @Override
    public void run() {
        if (!isVisible()) {
            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "A janela ja está aberta!");
        }
    }
}
