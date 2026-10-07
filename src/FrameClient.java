package src;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
public class FrameClient extends JFrame implements MouseMotionListener,MouseListener,ActionListener{
    private int PosMoveX;
    private int PosMoveY;
    private int PosChlickX;
    private int PosChlickY;
    private int ServerposX;
    private int ServerposY;
 
    
    FrameClient(){
        setTitle("hunter Brid");
        setSize(1280,720);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        addMouseListener(this);
        addMouseMotionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawObject Dw=new DrawObject(this);
        Dw.repaint();
        add(Dw);
        setVisible(true);
    }
        @Override
    public void actionPerformed(ActionEvent e) {
         
             
    }
        @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseMoved(MouseEvent e) {

        
    }
    @Override
    public void mouseClicked(MouseEvent e) {   
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    void SendToServer(){
       try {

        } catch (Exception ex) {
            System.out.println("เชื่อมต่อไม่ได้: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new FrameClient();
    }

}