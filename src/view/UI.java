package view;


import controller.K3;

import javax.swing.*;
import java.awt.*;


public class UI {

    /*********************************************************************
     * k3 is the controller, we use it to access different information.
     *********************************************************************/
    K3 k3;

    /**********************************************
     * screen is the JFrame, the principal window.
     **********************************************/
    private static JFrame screen;
    /*********************************************************************************************
     * cardLayout is the CardLayout use to change between the parameterPanel and the GamePanel.
     ********************************************************************************************/
    CardLayout cardLayout;

    /*************************************************************
     * parameterPanel is the JPanel that contains the parameter.
     *************************************************************/
    JPanel parameterPanel;
    /*********************************************************
     * nameP1TextField is where the player 1 write his name.
     *********************************************************/
    private static JTextField nameP1TextField;
    /*********************************************************
     *nameP2TextField is where the player 2 write his name.
     *********************************************************/
    private static JTextField nameP2TextField;
    /************************************************************************************************
     *typeAiComboBoxP1 is the ComboBox to chose the type of artificial intelligence of the player 1.
     ************************************************************************************************/
    private static JComboBox<String> typeAiComboBoxP1;
    /************************************************************************************************
     *typeAiComboBoxP2 is the ComboBox to chose the type of artificial intelligence of the player 2.
     ************************************************************************************************/
    private static JComboBox<String> typeAiComboBoxP2;
    /***************************************************************************************
     *aiCheckBoxP1 is the CheckBox to chose if the player 1 is an artificial intelligence.
     ***************************************************************************************/
    private static JCheckBox aiCheckBoxP1;
    /***************************************************************************************
     *aiCheckBoxP2 is the CheckBox to chose if the player 2 is an artificial intelligence.
     ***************************************************************************************/
    private static JCheckBox aiCheckBoxP2;
    /*******************************************************
     *saveComboBox is the ComboBox to choose the save game.
     ******************************************************/
    JComboBox<String> saveComboBox;
    /********************************************************************
     *rankingComboBox show the number of victory and lose of all player.
     *******************************************************************/
    JComboBox<String> rankingComboBox;

    /********************************************************************
     * gamePanel is the panel that contains every panel needed to play.
     ********************************************************************/
    JPanel gamePanel;

    /************************************************************************************************************
     * panelP1 is the PlayerPanel that contains the player 1 information, in the left position in the gamePanel.
     ************************************************************************************************************/
    PlayerPanel panelP1;
    /*************************************************************************************************************
     * panelP2 is the PlayerPanel that contains the player 2 information, in the right position in the gamePanel.
     *************************************************************************************************************/
    PlayerPanel panelP2;

    /*****************************************************************************************************
     * centerFrame is the JFrame of that contains centerCardLayout, it is in the center of the gamePanel.
     *****************************************************************************************************/
    JPanel centerFrame;
    /****************************************************************************************
     * centerCardLayout is the CardLayout that contains initPanel, menuPanel and mainPanel.
     ***************************************************************************************/
    CardLayout centerCardLayout;
    /********************************************************************************
     * initPanel is the InitPanel that is used to create the pyramid of the players.
     ********************************************************************************/
    InitPanel initPanel;
    /*****************************************************
     * menuPanel is the JPanel it is the menu in game.
     *****************************************************/
    JPanel menuPanel;
    /*****************************************************************************************
     * mainPanel is the MainPanel that is used to play, display the K3 and the action needed.
     *****************************************************************************************/
    MainPanel mainPanel;

    /**********************************************************************
     * finishButton is the button to stop the construction of the pyramid.
     **********************************************************************/
    JButton finishButton;

    /*********************************************************************************
     * timeP1Label is the label to print the time the player 1 took to do his pyramid.
     *********************************************************************************/
    JLabel timeP1Label;

    /**********************************************************************************
     * timeP1Label is the label to print the time the player 2 took to do his pyramid.
     **********************************************************************************/
    JLabel timeP2Label;

    /**************************************************************************
     * colorblindCheckBox is the check box to check if colorblind mod is active.
     ***************************************************************************/
    JCheckBox colorblindCheckBox;

    /*************************************************************
     * introPanel is the JPanel that contains the intro menu.
     *************************************************************/
    JPanel introPanel;

    /**************************************************
     * This constructor initialise all the variable.
     * @param k3 is the controller.
     **************************************************/
    public UI(K3 k3)
    {
        this.k3 = k3;
        init();
        initIntroPanel();
        showIntroPanel();
    }

    /***********************************
     * This function initialise screen.
     ***********************************/
    private void init(){
        screen = new JFrame("controller.K3");

        screen.setSize(1280, 720);
        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setResizable(false);


        cardLayout = new CardLayout(2,2);
        screen.setLayout(cardLayout);

        screen.setVisible(true);
    }

    /*************************************************************
     * This function initialise every panel and card layout used.
     ************************************************************/
    public void initPanel()
    {
        initPanel = new InitPanel(k3);
        menuPanel = new JPanel();
        mainPanel = new MainPanel(k3);

        centerFrame = new JPanel();
        centerCardLayout = new CardLayout();

        centerFrame.setLayout(centerCardLayout);

        centerFrame.add(initPanel, "initPanel");
        centerFrame.add(mainPanel, "mainPanel");
        centerFrame.add(menuPanel, "menuPanel");
    }

    /********************************************
     * This function initialise the intro panel.
     *******************************************/
    private void initIntroPanel(){

        introPanel = new JPanel();
        GridLayout grid = new GridLayout(3,3);
        introPanel.setLayout(grid);


        JLabel logo3 = new JLabel(new ImageIcon(UI.class.getResource("background.png")));
        introPanel.add(logo3);
        JButton playButton = new JButton("JOUER");
        playButton.addActionListener(event -> initParameterPanel());
        introPanel.add(playButton);
        JLabel logo4 = new JLabel(new ImageIcon(UI.class.getResource("background.png")));
        introPanel.add(logo4);

        JLabel logo = new JLabel(new ImageIcon(UI.class.getResource("background.png")));
        introPanel.add(logo);
        JButton rulesButton = new JButton("REGLES DU JEU");
        rulesButton.addActionListener(event -> initRulesPanel());
        introPanel.add(rulesButton);
        JLabel logo2 = new JLabel(new ImageIcon(UI.class.getResource("background.png")));
        introPanel.add(logo2);

        JLabel logo5 = new JLabel(new ImageIcon(UI.class.getResource("background.png")));
        introPanel.add(logo5);
        JButton quitButton = new JButton("QUITTER");
        quitButton.addActionListener(event -> closeWindow());
        introPanel.add(quitButton);
        JLabel logo6 = new JLabel(new ImageIcon(UI.class.getResource("background.png")));
        introPanel.add(logo6);

        screen.getContentPane().add(introPanel, "introPanel");
        screen.getContentPane().validate();

    }

    /*******************************************
     * This function initialise the rules panel.
     *******************************************/
    public void initRulesPanel(){

        JPanel rules1Panel = new JPanel();

        JLabel rulesImage = new JLabel(new ImageIcon(UI.class.getResource("rules1.png")));
        rules1Panel.add(rulesImage);

        screen.getContentPane().add(rules1Panel, "rulesPanel");
        screen.getContentPane().validate();
        showRulesPanel();

        JButton nextButton = new JButton("SUIVANT");
        nextButton.addActionListener(event -> nextRulesPanel());
        rules1Panel.add(nextButton);

    }

    /*********************************************************************
     * This function allows the user to change the page in the rule panel.
     *********************************************************************/
    public void nextRulesPanel(){

        JPanel rules2Panel = new JPanel();

        JButton previousButton = new JButton("PRECEDENT");
        previousButton.addActionListener(event -> initRulesPanel());
        rules2Panel.add(previousButton,BorderLayout.EAST);

        JLabel rulesImage = new JLabel(new ImageIcon(UI.class.getResource("rules2.png")));
        rules2Panel.add(rulesImage,BorderLayout.SOUTH);

        screen.getContentPane().add(rules2Panel, "rulesPanel");
        screen.getContentPane().validate();
        showRulesPanel();

        JButton returnButton = new JButton("MENU PRINCIPAL");
        returnButton.addActionListener(event -> showIntroPanel());
        rules2Panel.add(returnButton,BorderLayout.WEST);

    }

    /*******************************************************
     * This function show the introPanel in the screen.
     *******************************************************/
    public void showIntroPanel() { cardLayout.show(screen.getContentPane(), "introPanel"); }

    /*******************************************************
     * This function show the rulesPanel in the screen.
     *******************************************************/
    public void showRulesPanel() { cardLayout.show(screen.getContentPane(), "rulesPanel"); }

    /************************
     * Close the main screen
     ************************/
    public void closeWindow(){
        screen.dispose();
    }


    /*******************************************
     * This function initialise parameterPanel.
     ******************************************/
    private void initParameterPanel(){

        parameterPanel = new JPanel();
        GridLayout grid = new GridLayout(9,2);
        parameterPanel.setLayout(grid);

        JLabel aiLabelP1 = new JLabel("Voulez-vous une IA à la place du joueur 1 ?");
        aiLabelP1.setFont(new Font("Serif", Font.BOLD, 20));
        parameterPanel.add(aiLabelP1);
        aiCheckBoxP1 = new JCheckBox("IA");
        aiCheckBoxP1.addChangeListener(event -> nameP1TextField.setEnabled(!aiCheckBoxP1.isSelected()));
        parameterPanel.add(aiCheckBoxP1);

        JLabel typeIaLabelP1 = new JLabel("Quelle type d'IA voulez-vous pour l'IA du joueur 1 ?");
        typeIaLabelP1.setFont(new Font("Serif", Font.BOLD, 20));
        typeAiComboBoxP1 = new JComboBox<String>();
        typeAiComboBoxP1.addItem("IA Aléatoire");
        //typeAiComboBoxP1.addItem("IA Semi-Aleatoire");
        //typeAiComboBoxP1.addItem("IA Difficile");
        parameterPanel.add(typeIaLabelP1);
        parameterPanel.add(typeAiComboBoxP1);

        JLabel aiLabelP2 = new JLabel("Voulez-vous une IA à la place du joueur 2 ?");
        aiLabelP2.setFont(new Font("Serif", Font.BOLD, 20));
        parameterPanel.add(aiLabelP2);
        aiCheckBoxP2 = new JCheckBox("IA");
        aiCheckBoxP2.addChangeListener(event -> nameP2TextField.setEnabled(!aiCheckBoxP2.isSelected()));
        parameterPanel.add(aiCheckBoxP2);

        JLabel typeIaLabelP2 = new JLabel("Quelle type d'IA voulez-vous pour l'IA du joueur 2 ?");
        typeIaLabelP2.setFont(new Font("Serif", Font.BOLD, 20));
        typeAiComboBoxP2 = new JComboBox<String>();
        typeAiComboBoxP2.addItem("IA Aléatoire");
        //typeAiComboBoxP2.addItem("IA Semi-Aleatoire");
        //typeAiComboBoxP2.addItem("IA Difficile");
        parameterPanel.add(typeIaLabelP2);
        parameterPanel.add(typeAiComboBoxP2);

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

        JLabel okLabel = new JLabel("Commencer la partie :");
        okLabel.setFont(new Font("Serif", Font.BOLD, 20));
        parameterPanel.add(okLabel);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(event -> K3.initGame());
        parameterPanel.add(okButton);

        rankingComboBox = new JComboBox<String>();
        JLabel rankingLabel = new JLabel("Classement :");
        rankingLabel.setFont(new Font("Serif", Font.BOLD, 20));
        parameterPanel.add(rankingLabel);
        parameterPanel.add(rankingComboBox);

        JLabel emptyLabel = new JLabel(" ");
        parameterPanel.add(emptyLabel);
        JButton returnButton = new JButton("MENU PRINCIPAL");
        returnButton.addActionListener(event -> showIntroPanel());
        parameterPanel.add(returnButton);

        screen.getContentPane().add(parameterPanel, "parameterPanel");
        screen.getContentPane().validate();
        showParameterPanel();

    }

    /**************************************
     * This function initialise gamePanel.
     *************************************/
    public void initGamePanel()
    {

        gamePanel = new JPanel();
        panelP1 = new PlayerPanel(k3,0);
        panelP1.setBackground(Color.GRAY);
        panelP2 = new PlayerPanel(k3, 1);
        panelP2.setBackground(Color.GRAY);

        panelP1.setPreferredSize(new Dimension(426, 720));
        panelP2.setPreferredSize(new Dimension(426, 720));

        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(panelP1, BorderLayout.LINE_START);
        gamePanel.add(centerFrame);
        gamePanel.add(panelP2, BorderLayout.LINE_END);

        initMenuPanel();
        JButton menuButton = new JButton("MENU");
        menuButton.addActionListener(event -> showMenuPanel());
        mainPanel.add(menuButton, BorderLayout.NORTH);
        initPanel.add(menuButton, BorderLayout.NORTH);

        timeP1Label = new JLabel("Temps : 0.00s");
        panelP1.add(timeP1Label, BorderLayout.WEST);

        timeP2Label = new JLabel("Temps : 0.00s");
        panelP2.add(timeP2Label, BorderLayout.WEST);

        finishButton = new JButton("FINISH");
        finishButton.addActionListener(event -> k3.endTime());
        panelP1.add(finishButton, BorderLayout.EAST);

        screen.getContentPane().add(gamePanel, "gamePanel");
        screen.getContentPane().validate();
        
    }

    /********************************************************************
     * This function change the finish button between the 2 player panel.
     ********************************************************************/
    public void changeFinishButton()
    {
        panelP1.remove(finishButton);
        panelP2.add(finishButton);
    }

    /******************************************************************
     * This function remove the finish button from the player 2 panel.
     *****************************************************************/
    public void removeFinishButon() { panelP2.remove(finishButton); }

    /*****************************************
     * This function initialise the menuPanel.
     *****************************************/
    public void initMenuPanel()
    {

        GridLayout menuGrid = new GridLayout(13,2);
        menuPanel.setLayout(menuGrid);
        menuGrid.setVgap(15);

        JLabel colorblindLabel = new JLabel("Mode Daltonien");
        colorblindLabel.setFont(new Font("Serif", Font.BOLD, 20));
        menuPanel.add(colorblindLabel);
        colorblindCheckBox = new JCheckBox();
        menuPanel.add(colorblindCheckBox);

        JLabel addModifLabel = new JLabel("Appliquer les modifications");
        addModifLabel.setFont(new Font("Serif", Font.BOLD, 20));
        menuPanel.add(addModifLabel);
        JButton addModifButton = new JButton("OK");
        addModifButton.addActionListener(event -> saveModif());
        menuPanel.add(addModifButton);

        JButton restartButton = new JButton("Recommencer une partie");
        restartButton.addActionListener(event -> restartGame());
        menuPanel.add(restartButton);
        
    }

    /***********************************************************************
     * This function show a pop-up when one of the namePXTextField is empty.
     ***********************************************************************/
    public static void errorPlayerName(){ JOptionPane.showMessageDialog(screen, "Empty player name"); }

    /***********************************************************************
     * This function show a pop-up when the game is done.
     * @param winner is the name of the winner.
     ***********************************************************************/
    public void endGamePopUp(String winner) {
        JOptionPane.showConfirmDialog(screen,winner +" is the winner of the game","Fin du jeu",JOptionPane.DEFAULT_OPTION);}

    public int isLeft() { return JOptionPane.showConfirmDialog(screen,"Voulez-vous placez votre pion a gauche du k3 ?","Rajout du K3",JOptionPane.YES_NO_OPTION); }

    /*******************************************************
     * This function show the parameterPanel in the screen.
     *******************************************************/
    public void showParameterPanel() { cardLayout.show(screen.getContentPane(), "parameterPanel"); }

    /*******************************************************
     * This function show the menuPanel in the centerFrame.
     *******************************************************/
    public void showMenuPanel(){ centerCardLayout.show(centerFrame, "menuPanel"); }

    /*******************************************************
     * This function show the initPanel in the centerFrame.
     *******************************************************/
    public void showInitPanel() { centerCardLayout.show(centerFrame, "initPanel"); }

    /*******************************************************
     * This function show the mainPanel in the centerFrame.
     *******************************************************/
    public void showMainPanel() { centerCardLayout.show(centerFrame, "mainPanel"); }

    /*******************************************************
     * This function show the parameterPanel in the screen.
     *******************************************************/
    public void showGamePanel() { cardLayout.show(screen.getContentPane(), "gamePanel"); }

    /*************************************************************
     * This function update al the panel from the user interface.
     *************************************************************/
    public void updateIU()
    {
        initPanel.updateUI();
        panelP1.updateUI();
        panelP2.updateUI();
        gamePanel.updateUI();
        mainPanel.updateUI();
    }

    /*************************************************************
     * This function is used when the players quit the menuPanel.
     ************************************************************/
    public void saveModif(){
        
        if((k3.getPlayersPyramid()[0].getBaseTile().isEmpty()) && (k3.getPlayersPyramid()[1].getBaseTile().isEmpty())){
            showMainPanel();
            if(colorblindCheckBox.isSelected()) k3.setColorblind();
            else k3.disableColorblind();
            updateIU();
            return;
        }
        showInitPanel();
        if(colorblindCheckBox.isSelected()) k3.setColorblind();
        else k3.disableColorblind();
        updateIU();
        return;

    }

    /*********************************
     * This function restart the game.
     *********************************/
    public void restartGame(){
        closeWindow();
        new K3();
    }

    //------------------------------------GETTER------------------------------------------------

    /**************************************************
     * This function is a getter to nameP1TextField.
     * @return nameP1TextField
     **************************************************/
    public static JTextField getNameP1TextField() { return nameP1TextField; }

    /************************************************
     * This function is a getter to nameP2TextField.
     * @return nameP2TextField
     ************************************************/
    public static JTextField getNameP2TextField() { return nameP2TextField; }

    /****************************************
     * This function is a getter to screen.
     * @return screen
     ****************************************/
    public JFrame getScreen() { return screen; }

    /***********************************
     * This function is a getter to parameterPanel.
     * @return parameterPanel
     ***********************************/
    public JPanel getParameterPanel() { return parameterPanel; }

    /***********************************
     * This function is a getter to typeAiComboBoxP1.
     * @return typeAiComboBoxP1
     ***********************************/
    public static JComboBox<String> getTypeAiComboBoxP1() { return typeAiComboBoxP1; }

    /***********************************
     * This function is a getter to typeAiComboBoxP2.
     * @return typeAiComboBoxP2
     ***********************************/
    public static JComboBox<String> getTypeAiComboBoxP2() { return typeAiComboBoxP2; }

    /***********************************
     * This function is a getter to saveComboBox.
     * @return saveComboBox
     ***********************************/
    public JComboBox<String> getSaveComboBox() { return saveComboBox; }

    /***********************************
     * This function is a getter to rankingComboBox.
     * @return rankingComboBox
     ***********************************/
    public JComboBox<String> getRankingComboBox() { return rankingComboBox; }

    /***********************************
     * This function is a getter to aiCheckBoxP1.
     * @return aiCheckBoxP1
     ***********************************/
    public static JCheckBox getAiCheckBoxP1() { return aiCheckBoxP1; }

    /***********************************
     * This function is a getter to aiCheckBoxP2.
     * @return aiCheckBoxP2
     ***********************************/
    public static JCheckBox getAiCheckBoxP2() { return aiCheckBoxP2; }

    /***********************************
     * This function is a getter to cardLayout.
     * @return cardLayout
     ***********************************/
    public CardLayout getCardLayout() { return cardLayout; }

    /***********************************
     * This function is a getter to panelP1.
     * @return panelP1
     ***********************************/
    public PlayerPanel getPanelP1() { return panelP1; }

    /***********************************
     * This function is a getter to panelP2.
     * @return panelP2
     ***********************************/
    public PlayerPanel getPanelP2() { return panelP2; }

    /***********************************
     * This function is a getter to centerFrame.
     * @return centerFrame
     ***********************************/
    public JPanel getCenterFrame() { return centerFrame; }

    /***********************************
     * This function is a getter to centerCardLayout.
     * @return centerCardLayout
     ***********************************/
    public CardLayout getCenterCardLayout() { return centerCardLayout; }

    /***********************************
     * This function is a getter to initPanel.
     * @return initPanel
     ***********************************/
    public InitPanel getInitPanel() { return initPanel; }

    /***********************************
     * This function is a getter to menuPanel.
     * @return menuPanel
     ***********************************/
    public JPanel getMenuPanel() { return menuPanel; }

    /***********************************
     * This function is a getter to mainPanel.
     * @return mainPanel
     ***********************************/
    public MainPanel getMainPanel() { return mainPanel; }

    /********************************************************
     * This function is a getter for the time of the player.
     * @param activePlayer the player that we need the time.
     * @return timeP1Label or timeP2Label.
     ********************************************************/
    public JLabel getTimeLabel(int activePlayer){
        if (activePlayer == 0) return timeP1Label;
        return timeP2Label;
    }

}
