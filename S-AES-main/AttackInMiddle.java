import java.util.Scanner;
public class AttackInMiddle {
    public static String[] attack(String PlainText, String CipherText) {

        int Key1 = 0;
        int Key2 = 0;
        String K1 = new String();
        String K2 = new String();
        String[] Mid1 = new String[65536];
        String[] Mid2 = new String[65536];
        String out1 = new String();
        String out2 = new String();
        String out[] = new String[65536];
        int num = 0;

        for (int i = 0; i < 65536; i++) {
            int digits = 16;
            K1 = String.format("%" + digits + "s", Integer.toBinaryString(Key1)).replace(' ', '0');
            Key1++;
            Mid1[i] = Cipher.cipher(PlainText, K1);
        }
        for (int i = 0; i < 65536; i++) {
            int digits = 16;
            K2 = String.format("%" + digits + "s", Integer.toBinaryString(Key2)).replace(' ', '0');
            Key2++;
            Mid2[i] = Decipher.decipher(CipherText, K2);
        }

        for (int i = 0; i < Mid1.length; i++) {
            for (int j = 0; j < Mid2.length; j++) {
                if (Mid1[i].equals(Mid2[j])) {
                    int digits = 16;
                    out1 = String.format("%" + digits + "s", Integer.toBinaryString(i)).replace(' ', '0');
                    out2 = String.format("%" + digits + "s", Integer.toBinaryString(j)).replace(' ', '0');
                    out[num] = out1 + out2;
                    num++;
                }
            }
        }
        return out;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入明文: ");
        String plaintext = scanner.nextLine();
        System.out.print("请输入密文: ");
        String ciphertext = scanner.nextLine();
        long startTime = System.currentTimeMillis();
        String[] keys = AttackInMiddle.attack(plaintext, ciphertext);
        long endTime = System.currentTimeMillis();
        scanner.close();
        long executionTime = endTime - startTime;
        for (String key : keys) {
            if(key != null)
            {
                System.out.println(key);
            }
        }
        System.out.println("破解时长: " + executionTime/1000+ "秒");
    }
}