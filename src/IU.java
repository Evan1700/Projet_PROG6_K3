import javax.swing.*;
import java.awt.*;

public class IU {
    
    Game game;
    JLabel gameInfoLabel , iaLabel , okLabel ;
    JTextField nameP1TextField , nameP2TextField ;
    JButton menuButton , okButton , newGameButton ;
    JFrame screen;
    JPanel parameterPanel;
    JComboBox<String> typeIaComboBox , saveComboBox , classementComboBox;
    JCheckBox iaCheckBox ;
    CardLayout cardLayout ;

    public IU(Game game)
    {
        this.game = game ;
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
        GridLayout grid = new GridLayout(7,2);
        parameterPanel.setLayout(grid);

        iaLabel = new JLabel("Voulez-vous une IA ?");
        iaLabel.setFont(new Font("Serif", Font.BOLD, 20));
        parameterPanel.add(iaLabel);
        iaCheckBox = new JCheckBox("IA");
        //iaCheckBox.addChangeListener(event -> nomJoueurs2TextField.setEnabled(!iaCheckBox.isSelected()));
        parameterPanel.add(iaCheckBox);

        JLabel typeIaLabel = new JLabel("Quelle type d'IA voulez-vous ?");
        typeIaLabel.setFont(new Font("Serif", Font.BOLD, 20));
        typeIaComboBox = new JComboBox<String>();
        typeIaComboBox.addItem("IA Aléatoire");
        typeIaComboBox.addItem("IA Semi-Aleatoire");
        typeIaComboBox.addItem("IA Difficile");
        parameterPanel.add(typeIaLabel);
        parameterPanel.add(typeIaComboBox);

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
        //okButton.addActionListener(event -> game.initPyramid());
        parameterPanel.add(okButton);

        classementComboBox = new JComboBox<String>();
        JLabel classementLabel = new JLabel("Classement :");
        classementLabel.setFont(new Font("Serif", Font.BOLD, 20));
        parameterPanel.add(classementLabel);
        parameterPanel.add(classementComboBox);

        screen.getContentPane().add(parameterPanel, "parameterDisplay");
        screen.getContentPane().validate();

    }

    public void showParameterDisplay() { cardLayout.show(screen.getContentPane(), "parameterDisplay"); }
}
