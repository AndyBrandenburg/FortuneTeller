import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class FortuneTellerFrame extends JFrame {

    JPanel mainPnl;
    JPanel ctrlPnl;
    JPanel titlePnl;
    JPanel displayPnl;
    JLabel titleLbl;
    ImageIcon fortuneTellerImage;
    JTextArea fortuneArea;
    JButton quitBtn;
    JButton fortuneBtn;
    JScrollPane scroller;
    int curFortuneDex = -1;

    ArrayList<String> fortunes = new ArrayList<>();
    {
        fortunes.add("Never gonna give you up, Never gonna let you down.");
        fortunes.add("I see much happiness in your future.");
        fortunes.add("404 Fortune not found.");
        fortunes.add("The hardest part of ending is starting again.");
        fortunes.add("All your hard work will soon pay off.");
        fortunes.add("Time is like money, it is super valuable and you must cherish it.");
        fortunes.add("A new outlook brightens your image and brings new friends.");
        fortunes.add("I see something pleasant will happen to you in the near future.");
        fortunes.add("Believe in yourself and others will too.");
        fortunes.add("The harder you work, the luckier you get.");
        fortunes.add("Life is like a box of chocolates. You never know what you're gonna get.");
        fortunes.add("You will have good health in your future.");
    }


    public FortuneTellerFrame(){
        setSize(600,600);
        setTitle("Fortune Teller GUI");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension guiSize = toolkit.getScreenSize();

        int width = (int)(guiSize.width * 0.75);
        int height = (int)(guiSize.height * 0.75);

        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);

        createTitlePanel();
        createDisplayPanel();
        createControlPanel();

        setVisible(true);
    }

    public static void main(String[] args)
    {

        SwingUtilities.invokeLater(() -> {
            new FortuneTellerFrame();
        });
    }

    public void createTitlePanel(){

        titlePnl = new JPanel();
        ImageIcon fortuneTellerImage = new ImageIcon("src/Fortune_Teller_Image.jpg");
        Image scaled = fortuneTellerImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        fortuneTellerImage = new ImageIcon(scaled);

        titleLbl = new JLabel(fortuneTellerImage);
        titleLbl.setText("Get your fortune!");
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);
        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setFont(new Font("Serif", Font.BOLD, 40));

        titlePnl.add(titleLbl);
        mainPnl.add(titlePnl, BorderLayout.NORTH);

    }

    public void createDisplayPanel(){

        displayPnl = new JPanel();
        fortuneArea = new JTextArea(10, 45);
        scroller = new JScrollPane(fortuneArea);
        displayPnl.add(scroller);
        mainPnl.add(displayPnl, BorderLayout.CENTER);


        fortuneArea.setFont(new Font("Serif", Font.ITALIC, 25));
    }

    public void createControlPanel(){
        Random rand = new Random();
        ctrlPnl = new JPanel();
        ctrlPnl.setLayout(new GridLayout(1,2));
        fortuneBtn = new JButton("Get a fortune!");
        quitBtn = new JButton("Quit");
        ctrlPnl.add(fortuneBtn);
        ctrlPnl.add(quitBtn);

        mainPnl.add(ctrlPnl, BorderLayout.SOUTH);


        quitBtn.addActionListener((ActionEvent ae) -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?");
            if (response == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });

        fortuneBtn.addActionListener((ActionEvent ae) -> {

            int newDex = curFortuneDex;

            do {
                newDex = rand.nextInt(fortunes.size());
            } while (newDex == curFortuneDex);

            curFortuneDex = newDex;
            fortuneArea.append(fortunes.get(newDex) + "\n");
        });




    }

}
