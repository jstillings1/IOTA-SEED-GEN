
/**
 *
 * @author jstil
 */
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;

public class SeedGen {
    static final String TRYTE_ALPHABET = "9ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final int SEED_LEN = 81;

    public static void main(String[] args) {
        // our secure randomness source
        SecureRandom sr;
        try {
            sr = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            // this should not happen!
            e.printStackTrace();
            return;
        }

        // the resulting seed
        StringBuilder sb = new StringBuilder(SEED_LEN);

        for(int i = 0; i < SEED_LEN; i++) {
            int n = sr.nextInt(27);
            char c = TRYTE_ALPHABET.charAt(n);

            sb.append(c);
        }


        // create a jframe
        JFrame frame = new JFrame("IOTA SEED CREATER");
        JLabel seedlabel = new JLabel("Your Secure Seed is:",JLabel.LEFT);
        JButton buttonCopy = new JButton("Copy");
        JTextField seed = new JTextField(sb.toString(),70);
        seed.setFont(new Font("Serif", Font.BOLD, 12));
        seedlabel.setFont(new Font("Serif", Font.BOLD, 24));

        frame.setSize(1024, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        //Set up Gui
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        layout.putConstraint(SpringLayout.WEST, seedlabel,
                5,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, seedlabel,
                50,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, seed,
                5,
                SpringLayout.EAST, seedlabel);
        layout.putConstraint(SpringLayout.NORTH, seed,
                50,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, buttonCopy,
                800,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, buttonCopy,
                100,
                SpringLayout.NORTH, panel);
        panel.add(seedlabel);
        panel.add(seed);
        panel.add(buttonCopy);
        frame.setContentPane(panel);
        frame.setVisible(true);
        buttonCopy.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent le) {
                StringSelection stringSelection = new StringSelection (seed.getText());
                Clipboard clpbrd = Toolkit.getDefaultToolkit ().getSystemClipboard ();
                clpbrd.setContents (stringSelection, null);
            }
        });






        // clear StringBuilder just in case.
        for(int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, (char) 0);
        }

    }

}
