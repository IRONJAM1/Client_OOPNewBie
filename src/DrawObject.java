package src;

import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.util.Timer;

public class DrawObject extends JPanel {
    private String status = "MAIN";
    private FrameClient frame;
    private BufferedReader dataIn;
    private Socket socket;
    private PrintStream dataOut;
    private String IpServer;
    private JTextArea Detail;
    private JButton Play;
    private String Nameroom;
    private BufferedReader in;
    final int PORT = 8080;

    String playerName;
    Boolean isCreator = false;
    Image bg = Toolkit.getDefaultToolkit().createImage("Strorage\\bg.jpg");
    ImageIcon Pl = new ImageIcon("Strorage\\btnPlay.png");
    // ImageIcon Pl = new ImageIcon("Strorage\\btnPlay.png");

    DrawObject(FrameClient frame) {
        this.frame = frame;
        this.setLayout(new GridBagLayout());
        Detail = new JTextArea(10, 20);
        Detail.setVisible(false);
        add(Detail);
        Play = new JButton(Pl);
        Play.setContentAreaFilled(false);
        Play.setBorderPainted(false);
        Play.addActionListener(e -> {
            IpServer = JOptionPane.showInputDialog("IPSERVER");
            try {
                socket = new Socket(IpServer, PORT);
                dataOut = new PrintStream(socket.getOutputStream());
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                startTask();
                String[] options = { "Create", "Join" };
                String msg = "";
                int result = JOptionPane.showOptionDialog(
                        Play, // parent
                        "เลือกโหมดการเล่น", // ข้อความ
                        "Multiplayer", // ชื่อหน้าต่าง
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
                if (result == 0) {
                    msg = "CREATEROOM:" + JOptionPane.showInputDialog("Rommname");
                    playerName = JOptionPane.showInputDialog("player name");
                    isCreator = true;
                } else if (result == 1) {
                    msg = "JOIN:" + JOptionPane.showInputDialog("Rommname");
                    playerName = JOptionPane.showInputDialog("player name");
                    isCreator = false;
                }
                msg = msg + ":" + playerName;
                dataOut.println(msg);
                status = "LOBBY";
                showlobby();
                System.out.println("access");
            } catch (Exception er) {
                // TODO: handle exception
            }
        });
        this.add(Play);

    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        if (status.equals("MAIN")) {
            g.drawImage(bg, 0, 0, this);
        } else if (status.equals("LOBBY")) {
            showlobby();
        }
        
    }

    void showlobby() {
        Play.setVisible(false);
        Detail.setVisible(true);
        String text = isCreator ? "Start" : "Ready";
        JButton readyButton = new JButton(text);
        readyButton.addActionListener(e -> {
            if (isCreator) {
                dataOut.println("START:");
            } else {
                dataOut.println("READY:");
            }
        });
    }

    void startTask() {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                try {
                    String message;

                    while ((message = in.readLine()) != null) {

                        System.out.println("SERVER: " + message);

                        String[] part = message.split(":");
                        String command = part[0];

                        if (command.equals("ROOM_UPDATE")) {

                            Detail.setText("");

                            for (int i = 1; i < part.length; i++) {
                                Detail.append(part[i] + "\n");
                            }

                        } else if (command.equals("IS")) {

                        } else {

                            Detail.append(command + "\n");
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0);
    }

}
