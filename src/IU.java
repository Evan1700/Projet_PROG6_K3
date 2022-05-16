import javax.swing.*;
import java.awt.*;

public class IU {
    
    Game game;
    Controller controller;
    JLabel gameInfoLabel , iaLabelP1, iaLabelP2 , okLabel ;
    JTextField nameP1TextField , nameP2TextField ;
    JButton menuButton , okButton , newGameButton ;
    JFrame screen;
    JPanel parameterPanel;
    JComboBox<String> typeIaComboBoxP1,typeIaComboBoxP2 , saveComboBox , classementComboBox;
    JCheckBox iaCheckBoxP1, iaCheckBoxP2 ;
    CardLayout cardLayout ;

    public IU(Game game, Controller controller)
    {
        this.game = game ;
        this.controller = controller;
        init();
        initParameterPanel();
        showParameterDisplay();
    }

    private void init(){
        screen = new JFrame("K3");

        screen.setSize(1280, 720);
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setResizable(false);


        cardLayout = new CardLayout(2,2);
        screen.setLayout(cardLayout);

        screen.setVisible(true);
    }

    private void initParameterPanel(){

        parameterPanel = new JPanel();
        GridLayout grid = new GridLayout(9,2);
        parameterPanel.setLayout(grid);

        iaLabelP1 = new JLabel("Voulez-vous une IA à la place du joueur 1 ?");
        iaLabelP1.setFont(new Font("Serif", Font.BOLD, 20));
        parameterPanel.add(iaLabelP1);
        iaCheckBoxP1 = new JCheckBox("IA");
        iaCheckBoxP1.addChangeListener(event -> nameP1TextField.setEnabled(!iaCheckBoxP1.isSelected()));
        parameterPanel.add(iaCheckBoxP1);

        JLabel typeIaLabelP1 = new JLabel("Quelle type d'IA voulez-vous pour l'IA du joueur 1 ?");
        typeIaLabelP1.setFont(new Font("Serif", Font.BOLD, 20));
        typeIaComboBoxP1 = new JComboBox<String>();
        typeIaComboBoxP1.addItem("IA Aléatoire");
        typeIaComboBoxP1.addItem("IA Semi-Aleatoire");
        typeIaComboBoxP1.addItem("IA Difficile");
        parameterPanel.add(typeIaLabelP1);
        parameterPanel.add(typeIaComboBoxP1);

        iaLabelP2 = new JLabel("Voulez-vous une IA à la place du joueur 2 ?");
        iaLabelP2.setFont(new Font("Serif", Font.BOLD, 20));
        parameterPanel.add(iaLabelP2);
        iaCheckBoxP2 = new JCheckBox("IA");
        iaCheckBoxP2.addChangeListener(event -> nameP2TextField.setEnabled(!iaCheckBoxP2.isSelected()));
        parameterPanel.add(iaCheckBoxP2);

        JLabel typeIaLabelP2 = new JLabel("Quelle type d'IA voulez-vous pour l'IA du joueur 2 ?");
        typeIaLabelP2.setFont(new Font("Serif", Font.BOLD, 20));
        typeIaComboBoxP2 = new JComboBox<String>();
        typeIaComboBoxP2.addItem("IA Aléatoire");
        typeIaComboBoxP2.addItem("IA Semi-Aleatoire");
        typeIaComboBoxP2.addItem("IA Difficile");
        parameterPanel.add(typeIaLabelP2);
        parameterPanel.add(typeIaComboBoxP2);

        nameP1TextField = new JTextField("Joueur 1");
        JLabel nameP1Label = new JLabel("Nom du joueur 1 :");
        nameP1Label.setFont(new Font("Serif", Font.BOLD, 20));
        parameterPanel.add(nameP1Label);
        parameterPanel.add(nameP1TextField);

        nameP2TextField = new JTextField("Joueur 2");
        JLabel nameP2Label = new JLabel("Nom du joueur 2 :");
        nameP2Label.setFont(new Font("Serif", Font.BOLD, 20));
        parameterPanel.add(nameP2Label);
        parameterPanel.add(nameP2TextField);

        okLabel = new JLabel("Commencer la partie :");
        okLabel.setFont(new Font("Serif", Font.BOLD, 20));
        parameterPanel.add(okLabel);
        okButton = new JButton("OK");
        okButton.addActionListener(event -> controller.initGame());
        parameterPanel.add(okButton);

        classementComboBox = new JComboBox<String>();
        JLabel classementLabel = new JLabel("Classement :");
        classementLabel.setFont(new Font("Serif", Font.BOLD, 20));
        parameterPanel.add(classementLabel);
        parameterPanel.add(classementComboBox);

        screen.getContentPane().add(parameterPanel, "parameterDisplay");
        screen.getContentPane().validate();

    }

    public void errorPlayerName(){ JOptionPane.showMessageDialog(screen, "Empty player name"); }

    public void showParameterDisplay() { cardLayout.show(screen.getContentPane(), "parameterDisplay"); }
}
