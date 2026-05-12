import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FrameConta extends FrameBase implements ActionListener {
    ButtonBase depositar;
    ButtonBase sacar;
    ButtonBase voltar;
    JLabel saldo;
    BufferedReader readerSaldo;
    static FileWriter saldoWriter;

    FrameConta(){
        this.setSize(600,560);

        voltar = new ButtonBase("Voltar");
        voltar.setLocation(160,350);
        voltar.setFont(fonte);
        voltar.addActionListener(this);

        saldo = new JLabel("Saldo da conta "+Main.conta+" e de "+Main.saldo);
        saldo.setBounds(160,50,300,50);
        saldo.setFont(fonte);
        saldo.setForeground(Color.decode("#61A966"));
        saldo.setFocusable(false);

        depositar = new ButtonBase("Depositar");
        depositar.setLocation(160,110);
        depositar.setFont(fonte);
        depositar.addActionListener(this);

        sacar = new ButtonBase("Sacar");
        sacar.setLocation(160,230);
        sacar.setFont(fonte);
        sacar.addActionListener(this);


        this.add(saldo);
        this.add(depositar);
        this.add(sacar);
        this.add(voltar);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==depositar){
            Main.saldo +=
                    Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade que deseja depositar"));
            JOptionPane.showMessageDialog(null,"Deposito realizado com sucesso");
            saldo.setText("Saldo da conta "+Main.conta+" e de "+Main.saldo);

            String text = "";

            try {
                readerSaldo =  new BufferedReader(new FileReader("saldo.txt"));

                try {
                    String rewriteSaldo = readerSaldo.readLine();
                    int x = 1;
                    System.out.println(rewriteSaldo);

                    while (rewriteSaldo != null){
                        if (x == Main.pos){
                            text = text + Main.saldo+"\n";
                            System.out.println(rewriteSaldo);
                            rewriteSaldo = readerSaldo.readLine();
                        }else{
                            text = text + rewriteSaldo + "\n";
                            rewriteSaldo = readerSaldo.readLine();
                            System.out.println(rewriteSaldo);
                        }
                        x++;
                    }

                    readerSaldo.close();

                } catch (IOException ex) {
                    System.out.println("something went wrong");
                    throw new RuntimeException(ex);
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            try {
                saldoWriter = new FileWriter("saldo.txt");

                System.out.println("escrevendo");
                saldoWriter.write(text);
                saldoWriter.close();
            }
            catch (IOException ex){
                System.out.println("something went wrong");
                throw new RuntimeException(ex);
            }

        }
        if (e.getSource()==sacar){
            int saque = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade que deseja sacar"));
            if ((Main.saldo-saque) >= 0) {
                Main.saldo -=saque;
                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso");
                saldo.setText("Saldo da conta " + Main.conta + " e de " + Main.saldo);

                String text = "";

                try {
                    readerSaldo =  new BufferedReader(new FileReader("saldo.txt"));

                    try {
                        String rewriteSaldo = readerSaldo.readLine();
                        int x = 1;
                        System.out.println(rewriteSaldo);

                        while (rewriteSaldo != null){
                            if (x == Main.pos){
                                text = text + Main.saldo+"\n";
                                System.out.println(rewriteSaldo);
                                rewriteSaldo = readerSaldo.readLine();
                            }else{
                                text = text + rewriteSaldo + "\n";
                                rewriteSaldo = readerSaldo.readLine();
                                System.out.println(rewriteSaldo);
                            }
                            x++;
                        }

                        readerSaldo.close();

                    } catch (IOException ex) {
                        System.out.println("something went wrong");
                        throw new RuntimeException(ex);
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    saldoWriter = new FileWriter("saldo.txt");

                    System.out.println("escrevendo");
                    saldoWriter.write(text);
                    saldoWriter.close();
                }
                catch (IOException ex){
                    System.out.println("something went wrong");
                    throw new RuntimeException(ex);
                }

            }else {
                JOptionPane.showMessageDialog(null,"Saldo insuficiente");
            }
        }
        if (e.getSource() == voltar){
            this.dispose();
        }

    }
}
