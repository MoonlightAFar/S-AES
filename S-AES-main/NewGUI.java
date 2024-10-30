import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewGUI extends JFrame {

    private JTextField encryptText;
    private JTextField decryptText;
    private JTextField encryptKeyText;
    private JTextField decryptKeyText;
    private JButton encryptButton;
    private JButton decryptButton;
    private JTextArea outputArea;
    private JRadioButton singleEncrypt, doubleEncrypt, tripleEncrypt, cbcMode;
    private JRadioButton binaryInput, asciiInput;
    private Box box1, box2, box3, box4, box5, box6, boxV;

    public NewGUI() {
        setTitle("S-AES算法实现");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        boxV = Box.createVerticalBox();
        box1 = Box.createHorizontalBox();
        box2 = Box.createHorizontalBox();
        box3 = Box.createHorizontalBox();
        box4 = Box.createHorizontalBox();
        box5 = Box.createHorizontalBox();
        box6 = Box.createHorizontalBox();

        JLabel title = new JLabel("S-AES算法实现");
        title.setFont(new Font("微软雅黑", Font.BOLD, 45));
        box1.add(title);

        // 加密模式选择
        singleEncrypt = new JRadioButton("单重加密");
        doubleEncrypt = new JRadioButton("双重加密");
        tripleEncrypt = new JRadioButton("三重加密");
        cbcMode = new JRadioButton("CBC模式");
        ButtonGroup encryptGroup = new ButtonGroup();
        encryptGroup.add(singleEncrypt);
        encryptGroup.add(doubleEncrypt);
        encryptGroup.add(tripleEncrypt);
        encryptGroup.add(cbcMode);
        box2.add(new JLabel("加密模式选择："));
        box2.add(singleEncrypt);
        box2.add(doubleEncrypt);
        box2.add(tripleEncrypt);
        box2.add(cbcMode);

        // 输入明文和密钥
        box3.add(new JLabel("加密信息：  "));
        encryptText = new JTextField(20);
        box3.add(encryptText);
        box3.add(Box.createHorizontalStrut(10));
        box3.add(new JLabel("密钥：  "));
        encryptKeyText = new JTextField(20);
        box3.add(encryptKeyText);
        box3.add(Box.createHorizontalStrut(10));
        encryptButton = new JButton("加密");
        encryptButton.setFont(new Font("微软雅黑", Font.BOLD, 12));

        // 加密监听器
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaintext = encryptText.getText();
                String key = encryptKeyText.getText();
                String ciphertext = "";

                // 检查明文长度是否为16的倍数
                if (plaintext.length() % 16 != 0) {
                    JOptionPane.showMessageDialog(NewGUI.this, "明文长度必须是16的倍数！", "输入错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());

                if (binaryInput.isSelected()) {
                    if (singleEncrypt.isSelected()) {
                        ciphertext = Cipher.cipher(plaintext, key);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：二进制加密\n明文：" + plaintext + "\n密钥：" + key + "\n密文：" + ciphertext + "\n\n");
                    } else if (doubleEncrypt.isSelected()) {
                        ciphertext = Cipher.cipher2(plaintext, key);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：二进制加密\n明文：" + plaintext + "\n密钥：" + key + "\n密文：" + ciphertext + "\n\n");
                    } else if (tripleEncrypt.isSelected()) {
                        ciphertext = Cipher.cipher3(plaintext, key);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：二进制加密\n明文：" + plaintext + "\n密钥：" + key + "\n密文：" + ciphertext + "\n\n");
                    } else if (cbcMode.isSelected()) {
                        ciphertext = CBC.CBCcipher(plaintext, key);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：CBC模式加密\n明文：" + plaintext + "\n密钥：" + key + "\n密文：" + ciphertext + "\n\n");
                    }
                } else if (asciiInput.isSelected()) {
                    if (singleEncrypt.isSelected()) {
                        ciphertext = ASCII.asciiEncipher(plaintext, key);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：ASCII码加密\n明文：" + plaintext + "\n密钥：" + key + "\n密文：" + ciphertext + "\n\n");
                    } else if (doubleEncrypt.isSelected()) {
                        ciphertext = ASCII.asciiEncipher2(plaintext, key);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：ASCII码加密\n明文：" + plaintext + "\n密钥：" + key + "\n密文：" + ciphertext + "\n\n");
                    } else if (tripleEncrypt.isSelected()) {
                        ciphertext = ASCII.asciiEncipher3(plaintext, key);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：ASCII码加密\n明文：" + plaintext + "\n密钥：" + key + "\n密文：" + ciphertext + "\n\n");
                    } else if (cbcMode.isSelected()) {
                        ciphertext = CBC.CBCcipher(plaintext, key);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：CBC模式加密\n明文：" + plaintext + "\n密钥：" + key + "\n密文：" + ciphertext + "\n\n");
                    }
                }
            }
        });
        box3.add(encryptButton);

        // 输入密文和解密明文
        box4.add(new JLabel("解密信息：  "));
        decryptText = new JTextField(20);
        box4.add(decryptText);
        box4.add(Box.createHorizontalStrut(10));
        box4.add(new JLabel("密钥：  "));
        decryptKeyText = new JTextField(20);
        box4.add(decryptKeyText);
        box4.add(Box.createHorizontalStrut(10));
        decryptButton = new JButton("解密");
        decryptButton.setFont(new Font("微软雅黑", Font.BOLD, 12));

        // 解密监听器
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ciphertext = decryptText.getText();
                String decryptKey = decryptKeyText.getText();
                String decryptedText = "";

                // 检查密文长度是否为16的倍数
                if (ciphertext.length() % 16 != 0) {
                    JOptionPane.showMessageDialog(NewGUI.this, "密文长度必须是16的倍数！", "输入错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());

                if (binaryInput.isSelected()) {
                    if (singleEncrypt.isSelected()) {
                        decryptedText = Decipher.decipher(ciphertext, decryptKey);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：二进制解密\n密文：" + ciphertext + "\n密钥：" + decryptKey + "\n明文：" + decryptedText + "\n\n");
                    } else if (doubleEncrypt.isSelected()) {
                        decryptedText = Decipher.decipher2(ciphertext, decryptKey);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：二进制解密\n密文：" + ciphertext + "\n密钥：" + decryptKey + "\n明文：" + decryptedText + "\n\n");
                    } else if (tripleEncrypt.isSelected()) {
                        decryptedText = Decipher.decipher3(ciphertext, decryptKey);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：二进制解密\n密文：" + ciphertext + "\n密钥：" + decryptKey + "\n明文：" + decryptedText + "\n\n");
                    } else if (cbcMode.isSelected()) {
                        decryptedText = CBC.CBCdecipher(ciphertext, decryptKey);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：CBC模式解密\n密文：" + ciphertext + "\n密钥：" + decryptKey + "\n明文：" + decryptedText + "\n\n");
                    }
                } else if (asciiInput.isSelected()) {
                    if (singleEncrypt.isSelected()) {
                        decryptedText = ASCII.asciiDecipher(ciphertext, decryptKey);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：ASCII码解密\n密文：" + ciphertext + "\n密钥：" + decryptKey + "\n明文：" + decryptedText + "\n\n");
                    } else if (doubleEncrypt.isSelected()) {
                        decryptedText = ASCII.asciiDecipher2(ciphertext, decryptKey);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：ASCII码解密\n密文：" + ciphertext + "\n密钥：" + decryptKey + "\n明文：" + decryptedText + "\n\n");
                    } else if (tripleEncrypt.isSelected()) {
                        decryptedText = ASCII.asciiDecipher3(ciphertext, decryptKey);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：ASCII码解密\n密文：" + ciphertext + "\n密钥：" + decryptKey + "\n明文：" + decryptedText + "\n\n");
                    } else if (cbcMode.isSelected()) {
                        decryptedText = CBC.CBCdecipher(ciphertext, decryptKey);
                        outputArea.append("时间：" + formatter.format(date) + "\n操作：CBC模式解密\n密文：" + ciphertext + "\n密钥：" + decryptKey + "\n明文：" + decryptedText + "\n\n");
                    }
                }
                decryptText.setText(decryptedText);
            }
        });
        box4.add(decryptButton);

        outputArea = new JTextArea(22, 40);
        outputArea.setEditable(false);
        box6.add(new JScrollPane(outputArea));

        binaryInput = new JRadioButton("二进制");
        asciiInput = new JRadioButton("ASCII");
        ButtonGroup group = new ButtonGroup();
        group.add(binaryInput);
        group.add(asciiInput);
        box5.add(new JLabel("输入信息格式："));
        box5.add(binaryInput);
        box5.add(asciiInput);

        boxV.add(box1);
        boxV.add(Box.createVerticalStrut(20));
        boxV.add(box2);
        boxV.add(box3);
        boxV.add(box4);
        boxV.add(box5);
        boxV.add(Box.createVerticalStrut(20));
        boxV.add(box6);

        add(boxV);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NewGUI();
            }
        });
    }
}