import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FrameInicial extends FrameBase implements ActionListener {
    static ButtonBase entrar;
    static JTextField entrarconta;
    static ButtonBase criar;
    static BufferedReader reader;
    static BufferedReader readerSaldo;

    FrameInicial() {
        entrarconta = new JTextField();
        entrarconta.setFont(fonte);
        entrarconta.setBounds(160,60,260,50);
        entrarconta.setToolTipText("numero da conta");

        entrar = new ButtonBase("entrar em uma conta");
        entrar.setFont(fonte);
        entrar.addActionListener(this);
        entrar.setLocation(160,120);

        criar = new ButtonBase("criar uma conta");
        criar.setFont(fonte);
        criar.addActionListener(this);
        criar.setLocation(160,250);

        this.setSize(600,560);

        this.add(criar);
        this.add(entrar);
        this.add(entrarconta);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==entrar){
            String numero = entrarconta.getText();

            try {
                reader =  new BufferedReader(new FileReader("data.txt"));
                readerSaldo =  new BufferedReader(new FileReader("saldo.txt"));

                String line;
                String lineSaldo;
                Main.pos = 1;
                while ((line = reader.readLine()) != null){
                    lineSaldo = readerSaldo.readLine();
                    if (line.equals(numero)){
                        Main.conta = Integer.parseInt(line);
                        Main.saldo = Integer.parseInt(lineSaldo);
                        FrameConta conta = new FrameConta();
                        break;
                    }
                    Main.pos++;
                }
                if (Main.conta == 0){
                    JOptionPane.showMessageDialog(null,"Erro:Conta nao encontrada");
                    Main.pos = 1;
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }

        if (e.getSource()==criar){
            try {
                FrameCriar frameCriar = new FrameCriar();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
