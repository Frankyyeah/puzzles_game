package ui;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    Random ran = new Random();
    // 2d array
    int[][] data = new int[4][4];

    // coordinate of the empty picture
    int x = 0;
    int y = 0;

    //path of the img
    String[] path = {"PuzzleGame/src/img_ventura/",
            "PuzzleGame/src/img_monterey/",
            "PuzzleGame/src/img_bigsur/"};

    int current_path = ran.nextInt(0, 3);
    
    //win array
    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    //count for steps
    int countStep = 0;

    // Tools Menu
    JMenuItem reStartItem = new JMenuItem("Restart Game");
    JMenuItem reLoginItem = new JMenuItem("Login Again");
    JMenuItem closeItem = new JMenuItem("Exit the Game");

    JMenuItem changePhoto = new JMenuItem("Change Photo");

    JMenuItem Ventura = new JMenuItem("Ventura");
    JMenuItem Monterey = new JMenuItem("Monterey");
    JMenuItem BigSur = new JMenuItem("BigSur");


    // About Menu
    JMenuItem accountItem = new JMenuItem("Contact Us");

    public GameJFrame() {
        //initialization
        initJFrame();

        initJMenuBar();

        initData();

        initImage();

        this.setVisible(true);
    }

    private void initData() {


        // 1d array
        int[] arr = new int[16];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        Random ran = new Random();
        int temp = 0;

        //swap with random address
        for (int i = 0; i < arr.length; i++) {
            int random_location = ran.nextInt(0, arr.length);
            temp = arr[i];
            arr[i] = arr[random_location];
            arr[random_location] = temp;
        }


        int count = 0; // loop of the 1d array

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                data[i][j] = arr[count++];
                if ((data[i][j] == 0)) {  // get initial coordinate of the empty picture
                    x = i;
                    y = j;
                    System.out.println("Blank box in " + x + " and " + y);
                }
            }
        }

    }

    private void initImage() {
        //remove all img
        this.getContentPane().removeAll();

        if (victory()) {
            JLabel win_img = new JLabel(new ImageIcon("/Users/frankchan/Develop/Java/Intro/PuzzleGame/src/img/win.png"));
            win_img.setBounds(0, 0, 320, 320);
            this.getContentPane().add(win_img);
        }

        JLabel step = new JLabel("Step: " + countStep);
        step.setBounds(50, 30, 100, 20);
        this.getContentPane().add(step);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int address = data[i][j]; // random address
                JLabel jLabel = new JLabel(new ImageIcon(path[current_path] + "/0" + address + ".jpg"));
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);
            }
        }



       /* JLabel bg = new JLabel(new ImageIcon("PuzzleGame/src/img/test.png"));
        bg.setBounds(40, 35, 665, 605);
        this.getContentPane().add(bg);*/

        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        // Menu Bar
        JMenuBar jMenuBar = new JMenuBar();
        // tools menu
        JMenu toolsMenu = new JMenu("Tools");

        // change Photo Menu
        JMenu changePhotoMenu = new JMenu("Change Photo");
        changePhotoMenu.add(Ventura);
        changePhotoMenu.add(Monterey);
        changePhotoMenu.add(BigSur);

        toolsMenu.add(changePhoto.add(changePhotoMenu));

        // Other Menu
        toolsMenu.add(reStartItem);
        toolsMenu.add(reLoginItem);
        toolsMenu.add(closeItem);

        // Add all the menu to the JMenuBar
        jMenuBar.add(toolsMenu);

        //about menu
        JMenu aboutMenu = new JMenu("About us");

        aboutMenu.add(accountItem);

        jMenuBar.add(aboutMenu);

        //action listener
        reStartItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);

        accountItem.addActionListener(this);

        Ventura.addActionListener(this);
        Monterey.addActionListener(this);
        BigSur.addActionListener(this);

        // add all items to MenuBar
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603, 680);
        this.setTitle("Puzzle Game v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(this);
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (victory()) {
            //after win , cannot move again
            return;
        }

        int code = e.getKeyCode();
        System.out.println("Current key pressed is " + e.getKeyChar() + " " + code);
        if (code == 83) {
            System.out.println("Pressed 's' ");
            //full image
            this.getContentPane().removeAll();
            JLabel full_img = new JLabel(new ImageIcon(path[current_path] + "/full.jpg"));
            full_img.setBounds(83, 134, 420, 420);
            this.getContentPane().add(full_img);

            //background
            JLabel bg = new JLabel(new ImageIcon("PuzzleGame/src/img/test.png"));
            bg.setBounds(39, 35, 508, 590);
            this.getContentPane().add(bg);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            //after win , cannot move again
            return;
        }

        // left : 37 | up : 38 | right : 39 | down :40
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case 37: {
                System.out.println("Move left");
                if (!(y - 1 < 0)) {
                    data[x][y] = data[x][y - 1];
                    data[x][y - 1] = 0;
                    y--;
                    countStep++;
                    initImage();
                } else {
                    System.out.println("Hit Boundary");
                }
                break;
            }
            case 38: {
                System.out.println("Move up");
                if (!(x - 1 < 0)) {
                    data[x][y] = data[x - 1][y];
                    data[x - 1][y] = 0;
                    x--;
                    countStep++;
                    initImage();
                } else {
                    System.out.println("Hit Boundary");
                }
                break;
            }
            case 39: {
                System.out.println("Move right");
                if (!(y + 1 >= 4)) {
                    data[x][y] = data[x][y + 1];
                    data[x][y + 1] = 0;
                    y++;
                    countStep++;
                    initImage();
                } else {
                    System.out.println("Hit Boundary");
                }
                break;
            }
            case 40: {
                System.out.println("Move down");
                if (!(x + 1 >= 4)) {
                    data[x][y] = data[x + 1][y];
                    data[x + 1][y] = 0;
                    x++;
                    countStep++;
                    initImage();
                } else {
                    System.out.println("Hit Boundary");
                }
                break;
            }
            case 83: { // show full image
                System.out.println("Release button 's' ");
                initImage();
                break;
            }

            case 87: { //win buttom
                data = new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };
                initImage();
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(reStartItem)) {
            System.out.println("Restarting the game");
            initData();
            countStep = 0;
            initImage();
        } else if (obj.equals(reLoginItem)) {
            System.out.println("Re Login");
            this.setVisible(false);
            new LoginJFrame();
        } else if (obj.equals(closeItem)) {
            System.out.println("Terminate the Game");
            System.exit(0);
        } else if (obj.equals(accountItem)) {
            System.out.println("Showing Contact");
            JDialog contact = new JDialog();
            JLabel QRCode = new JLabel(new ImageIcon("PuzzleGame/src/img/QRCode.png"));
            QRCode.setBounds(0, 0, 373, 420);

            contact.add(QRCode);

            contact.setSize(373, 420);
            contact.setAlwaysOnTop(true);
            contact.setLocationRelativeTo(null);
            contact.setModal(true);
            contact.setVisible(true);

        } else if (obj.equals(Ventura)) {
            System.out.println("Change Photo to Ventura");
            current_path = 0;

            countStep = 0;
            initData();
            initImage();

        } else if (obj.equals(Monterey)) {
            System.out.println("Change Photo to Monterey");
            current_path = 1;

            countStep = 0;
            initData();
            initImage();

        } else if (obj.equals(BigSur)) {
            System.out.println("Change Photo to Big Sur");
            current_path = 2;

            countStep = 0;
            initData();
            initImage();
        }
    }
}
