/**
 * Created by othscs229 on 5/4/2017.
 */
/**
 * Created by othscs229 on 11/7/2016.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class Frame extends JFrame implements ActionListener
{
Panel p;
    JMenuBar jmenu;
    JMenu mode;
    JMenuItem free,boss;
    public Frame(String title)
    {


        super(title);

        jmenu = new JMenuBar();

        mode = new JMenu("Mode");
        mode.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        jmenu.add(mode);


        free = new JMenuItem("FreePlay",
                KeyEvent.VK_T);
        free.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        free.getAccessibleContext().setAccessibleDescription(
                "Free");
        free.addActionListener(this);
        jmenu.add(free);

        boss = new JMenuItem("Campaign",
                KeyEvent.VK_T);
        boss.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.CTRL_MASK));
        boss.getAccessibleContext().setAccessibleDescription(
                "Boss");
        boss.addActionListener(this);
        jmenu.add(boss);



        setJMenuBar(jmenu);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setResizable(false);

        pack();

     //   p = new Panel();

//        Insets insets = getInsets();

//        int width=p.getWidth()+insets.left+insets.right;
//        int height=p.getHeight()+insets.top+insets.bottom;
//
//        setPreferredSize(new Dimension(width,height));

        setLayout(null);

//       add(p);

        pack();

        setVisible(true);




    }
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == boss )
        {

            Insets insets = getInsets();
            p=new Panel(false);
            int width=p.getWidth()+insets.left+insets.right;
            int height=p.getHeight()+insets.top+insets.bottom;

            setPreferredSize(new Dimension(width,height));

            setLayout(null);

            add(p);


            jmenu.setVisible(false);
            pack();
        }
        else if(e.getSource() == free)
        {
            Insets insets = getInsets();
            p=new Panel(true);
            int width=p.getWidth()+insets.left+insets.right;
            int height=p.getHeight()+insets.top+insets.bottom;

            setPreferredSize(new Dimension(width,height));

            setLayout(null);

            add(p);
            jmenu.setVisible(false);
            pack();
        }
    }
    public Frame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200,200);

    }
    public boolean showOrNah(int score) {
    if (JOptionPane.showConfirmDialog(this, "You got through "+ score+" pipes! Start Over?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
        dispose();
        new Frame("Flappy Bird 2: The UFO");

        return true;
    } else {
        System.exit(0);
    }
    return false;
}
    public boolean showMessage(int score) {
        if (JOptionPane.showConfirmDialog(this, "You won! Start Over?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
            dispose();
            new Frame("Flappy Bird 2: The UFO");

            return true;
        } else {
            System.exit(0);
        }
        return false;
    }
    public boolean showMessage2(int score) {
        if (JOptionPane.showConfirmDialog(this, "The boss destroyed you! Start Over?", "Results", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.YES_OPTION) {
            dispose();
            new Frame("Flappy Bird 2: The UFO");

            return true;
        } else {
            System.exit(0);
        }
        return false;
    }
}