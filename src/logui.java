import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class logui{
    public static void main(String[] args) {
        final JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        // frame 3

        JFrame frame3 = new JFrame();
        JPanel panel3 = new JPanel();
        frame3.setSize(300,200);
        frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame3.add(panel3);
        panel3.setLayout(null);

        JLabel success1 = new JLabel("Your order name");
        success1.setBounds(80,30,300,25);
        panel3.add(success1);
        JTextField successtext =new JTextField();
        successtext.setBounds(70,60,100,25);
        panel3.add(successtext);

        JButton okayButton = new JButton("Okay");
        okayButton.setBounds(80,100,100,25);
        okayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buy = successtext.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/users","root","abiabi123");
                    Statement st=con.createStatement();
                    ResultSet rs;
                    if(con!=null){
                        System.out.println("Connected to buy");
                    }
                    else {
                        System.out.println("not");
                    }
                    String p ="Delete from items where item_name=?;";
                    PreparedStatement l=con.prepareStatement(p);
                    l.setString(1,buy);
                    l.executeUpdate();
                    frame3.dispose();

                }

                catch (Exception z){
                    System.out.println(z);
                }

            }
        });
        panel3.add(okayButton);




        JFrame frame1 = new JFrame();
        JPanel panel1 = new JPanel();
        frame1.setSize(500,400);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(panel1);
        panel1.setLayout(null);
        JButton buyButton = new JButton("Buy");
        buyButton.setBounds(80,300,100,25);
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.setVisible(true);
            }
        });
        panel1.add(buyButton);

        JTable table;
        table = new JTable();
        table.setBounds(70,20,330,200);
        table.setBorder(new EmptyBorder(5,5,5,5));
        panel1.add(table);


        JButton displayItems = new JButton("Display Items");
        displayItems.setBounds(175,250,130,25);
        displayItems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/users","root","abiabi123");
                    Statement st=con.createStatement();
                    ResultSet rs;
                    if(con!=null){
                        System.out.println("Connected to display table");
                    }
                    else {
                        System.out.println("not");
                    }
                    String query ="Select * from items;";
                    rs = st.executeQuery(query);
                    ResultSetMetaData rsmd=rs.getMetaData();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int col = rsmd.getColumnCount();
                    String[] colname=new String[col];
                    for(int i=0;i<col;i++){
                        colname[i]=rsmd.getColumnName(i+1);
//                        System.out.println(colname[i]);
                    }
                    model.setColumnIdentifiers(colname);
                    String things,text,cost,area;
                    while(rs.next()){
                        things=rs.getString(1);
                        text=rs.getString(2);
                        cost=rs.getString(3);
                        area=rs.getString(4);
                        String[] row ={things,text,cost,area};
                        model.addRow(row);

                    }
                    st.close();

                }
                catch (Exception r){
                    System.out.println(r);
                }
            }});
        panel1.add(displayItems);










        // sell form

        JFrame frame2 = new JFrame();
        JPanel panel2 = new JPanel();
        frame2.setSize(400,300);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.add(panel2);
        panel2.setLayout(null);

        JLabel itemName = new JLabel("Item Name");
        itemName.setBounds(60,50,80,25);
        panel2.add(itemName);

        JTextField itemNameText = new JTextField(20);
        itemNameText.setBounds(150,50,165,25);
        panel2.add(itemNameText);

        JLabel description = new JLabel("Description");
        description.setBounds(60,90,80,25);
        panel2.add(description);

        JTextField descriptionText = new JTextField(20);
        descriptionText.setBounds(150,90,165,25);
        panel2.add(descriptionText);

        JLabel price = new JLabel("Price");
        price.setBounds(60,130,80,25);
        panel2.add(price);

        JTextField priceText = new JTextField(20);
        priceText.setBounds(150,130,165,25);
        panel2.add(priceText);

        JLabel location = new JLabel("Location");
        location.setBounds(60,170,80,25);
        panel2.add(location);

        JTextField locationText = new JTextField(20);
        locationText.setBounds(150,170,165,25);
        panel2.add(locationText);

        JButton sellNow = new JButton("Sell Now");
        sellNow.setBounds(120,210,100,25);
        sellNow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String location =locationText.getText();
                String price =priceText.getText();
                String content =descriptionText.getText();
                String name=itemNameText.getText();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/users","root","abiabi123");
                    Statement st=con.createStatement();
                    ResultSet rs;
                    if(con!=null){
                        System.out.println("Connected to items");
                    }
                    else {
                        System.out.println("not");
                    }

                    String insert ="insert into items values (?,?,?,?);";
                    PreparedStatement k =con.prepareStatement(insert);
                    k.setString(1,name);
                    k.setString(2,content);
                    k.setString(3,price);
                    k.setString(4,location);
                    k.executeUpdate();
                    frame2.dispose();

                }
                catch (Exception a){
                    System.out.println(a);
                }
            }
        });
        panel2.add(sellNow);


        // sell button
        JButton sellButton = new JButton("Sell");
        sellButton.setBounds(300,300,100,25);
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame2.setVisible(true);
            }
        });
        panel1.add(sellButton);




        // Login form

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JLabel success = new JLabel(" ");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        JButton button = new JButton("Login");
        button.setBounds(10, 90, 80, 25);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = passwordText.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/users", "root", "abiabi123");
                    Statement st = con.createStatement();
                    ResultSet rs;
                    if (con != null) {
                        System.out.println("Connected");
                    } else {
                        System.out.println("not");
                    }
                    String check = "Select * from auth where name=? and password=?;";
                    PreparedStatement ps = con.prepareStatement(check);
                    ps.setString(1, username);
                    ps.setString(2, password);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        String checkname = rs.getString("name");
                        String checkpass = rs.getString("password");
                        if (username.equals(checkname) && password.equals(checkpass)) {
                            success.setText("Login Sucessful");
                            frame1.setVisible(true);
                        } else {
                            success.setText("Login unsucessful");

                        }

                    }

                } catch (Exception s) {
                    System.out.println(s);
                }
            }
        });
        panel.add(button);
        frame.setVisible(true);








    }

}