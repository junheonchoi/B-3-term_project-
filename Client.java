import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Client {

    BufferedReader in;
    PrintWriter out;

    JFrame frame = new JFrame("이상형 만나기 프로젝트 (2%)");
    JTextField textField = new JTextField(25);
    JTextArea messageArea = new JTextArea(25,25);
    ImageIcon ic1 = new ImageIcon("C://back.PNG");
    JLabel im1 = new JLabel(ic1); 
    public Client() {

        // Layout GUI
        textField.setEditable(false);
        messageArea.setEditable(false);
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "East");
        frame.add(im1);
        frame.pack();

        // Add Listeners
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
    }

    private String getServerAddress() {
        return JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to 2%",
            JOptionPane.QUESTION_MESSAGE);
    }
    
    
    private String getMEMBERSHIP_NAME() {
        return JOptionPane.showInputDialog(
            frame,
            "[MEMBERSHIP-0]-please type your NAME:",
            "[MEMBERSHIP-0]",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private String getMEMBERSHIP_PICTURE() {
        return JOptionPane.showInputDialog(
            frame,
            "[MEMBERSHIP-1]-please type path of your picture:",
            "[MEMBERSHIP-1]",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private String getMEMBERSHIP_AGE() {
        return JOptionPane.showInputDialog(
            frame,
            "[MEMBERSHIP-2]-please type your AGE:",
            "[MEMBERSHIP-2]",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private String getMEMBERSHIP_HEIGHT() {
        return JOptionPane.showInputDialog(
            frame,
            "[MEMBERSHIP-3]-please type your HEIGHT(cm):",
            "[MEMBERSHIP-3]",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private String getMEMBERSHIP_WEIGHT() {
        return JOptionPane.showInputDialog(
            frame,
            "[MEMBERSHIP-4]-please type your WEIGHT(kg):",
            "[MEMBERSHIP-4]",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private String getMEMBERSHIP_JOB() {
        return JOptionPane.showInputDialog(
            frame,
            "[MEMBERSHIP-5]-please type your JOB:",
            "[MEMBERSHIP-5]",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private String getMEMBERSHIP_HOBBY() {
        return JOptionPane.showInputDialog(
            frame,
            "[MEMBERSHIP-6]-please type your HOBBY:",
            "[MEMBERSHIP-6]",
            JOptionPane.PLAIN_MESSAGE);
    }
    
    private String getMEMBERSHIP_SII() {
        return JOptionPane.showInputDialog(
            frame,
            "[MEMBERSHIP-7]-please type stuffs you are interested in:",
            "[MEMBERSHIP-7]",
            JOptionPane.PLAIN_MESSAGE);
    }
 
    private void run() throws IOException {

        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            String line = in.readLine();
            if (line.startsWith("MEMBERSHIP")) {
            	out.println(getMEMBERSHIP_NAME());
            	out.println(getMEMBERSHIP_PICTURE());
                out.println(getMEMBERSHIP_AGE());
                out.println(getMEMBERSHIP_HEIGHT());
                out.println(getMEMBERSHIP_WEIGHT());
                out.println(getMEMBERSHIP_JOB());
                out.println(getMEMBERSHIP_HOBBY());
                out.println(getMEMBERSHIP_SII());
        }
       }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}