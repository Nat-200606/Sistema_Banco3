import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Objects;

public class FrameCriar extends FrameBase implements ActionListener {
    static JTextField criarconta;
    static ButtonBase criar;
    static FileWriter data;
    static FileWriter saldo;
    static BufferedReader reader;
    static BufferedReader readerSaldo;

    FrameCriar() throws IOException {
        this.setSize(480,380);

        criarconta = new JTextField();
        criarconta.setFont(fonte);
        criarconta.setBounds(120,90,260,50);
        criarconta.setToolTipText("Numero da conta a ser criada");

        criar = new ButtonBase("Criar conta");
        criar.setFont(fonte);
        criar.addActionListener(this);
        criar.setLocation(120,150);

        this.add(criar);
        this.add(criarconta);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==criar && !Objects.equals(criarconta.getText(), "")){

            String text = "";
            String textSaldo = "";

            try {
                reader =  new BufferedReader(new FileReader("C:\\Users\\natal\\Downloads\\Projetos\\Java\\Sistema_Banco3\\data.txt"));
                readerSaldo =  new BufferedReader(new FileReader("C:\\Users\\natal\\Downloads\\Projetos\\Java\\Sistema_Banco3\\saldo.txt"));

                try {
                    String rewrite  = reader.readLine();
                    String rewriteSaldo = readerSaldo.readLine();
                    System.out.println(rewrite);

                    while (rewrite != null){
                        text = text + rewrite + "\n";
                        textSaldo = textSaldo + rewriteSaldo + "\n";
                        System.out.println(text);
                        rewrite  = reader.readLine();
                        rewriteSaldo = readerSaldo.readLine();
                    }
                    text = text + criarconta.getText();
                    textSaldo = textSaldo + "0";

                    reader.close();
                    readerSaldo.close();

                } catch (IOException ex) {
                    System.out.println("something went wrong");
                    throw new RuntimeException(ex);
                }

                try {
                    data = new FileWriter("data.txt");
                    saldo = new FileWriter("saldo.txt");

                    saldo.write(textSaldo);
                    saldo.close();
                    data.write(text);
                    data.close();
                } catch (IOException ex) {
                    System.out.println("something went wrong");
                    throw new RuntimeException(ex);
                }

            } catch (FileNotFoundException ex) {
                System.out.println("file not found");
                throw new RuntimeException(ex);
            }


            /*for (int x =0; x < Main.pos; x++) {
                text = "\n" + text;
            }*/


            JOptionPane.showMessageDialog(null
                    ,"Conta de numero "+criarconta.getText()+" criada com sucesso");

            this.dispose();
        }
    }
}
