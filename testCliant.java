// import java.awt.*;
// import java.awt.event.*;
// import java.io.*;
// import java.net.*;

// public class testCliant extends Frame implements ActionListener {

//     TextField address;   // ช่องกรอก IP Server
//     TextField message;   // ช่องกรอกข้อความ
//     Button sendButton;   // ปุ่มส่ง

//     final int PORT = 50101;

//     testCliant() {
//         setTitle("Chat Client");
//         setSize(350, 150);
//         setLayout(new FlowLayout());

//         address     = new TextField("127.0.0.1", 15);
//         message     = new TextField(20);
//         sendButton  = new Button("Send");

//         add(new Label("Address:")); add(address);
//         add(new Label("Message:")); add(message);
//         add(sendButton);

//         sendButton.addActionListener(this);

//         addWindowListener(new WindowAdapter() {
//             public void windowClosing(WindowEvent e) { System.exit(0); }
//         });
//         setVisible(true);
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         try {
//             // 1. สร้าง Socket เชื่อมต่อไปยัง Server
//             Socket socket = new Socket(address.getText(), PORT);

//             // 2. สร้าง OutputStream สำหรับส่งข้อมูล
//             PrintStream dataOut = new PrintStream(socket.getOutputStream());

//             // 3. เตรียมข้อมูล: hostname#ข้อความ
//             InetAddress myAddr = InetAddress.getLocalHost();
//             String msg = myAddr.getHostName() + "#" + message.getText();

//             // 4. ส่งข้อมูลออก
//             dataOut.println(msg);
//             dataOut.close();       // ปิด Stream

//             // 5. เคลียร์ช่อง message
//             message.setText("");

//         } catch (Exception ex) {
//             // กรณีเชื่อมต่อไม่ได้ (Server ไม่ได้เปิด, IP ผิด)
//             System.out.println("เชื่อมต่อไม่ได้: " + ex.getMessage());
//         }
//     }

//     public static void main(String[] args) { new testCliant(); }
// }