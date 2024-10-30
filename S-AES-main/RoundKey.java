public class RoundKey {
    public static String Half_Byte_Replace(String a) {
        String a1 = a.substring(0, 8);
        String a2 = a.substring(8, 16);
        int a11 = Integer.parseInt(a1, 2);
        int a22 = Integer.parseInt(a2, 2);
        int s1 = SBox.s_box(a11);
        int s2 = SBox.s_box(a22);
        String s11 = Integer.toBinaryString(s1);
        String s22 = Integer.toBinaryString(s2);
        while (s11.length() < 8) {
            s11 = "0" + s11;
        }
        while (s22.length() < 8) {
            s22 = "0" + s22;
        }
        String out = s11 + s22;
        return out;
    }
    public static String Half_Byte_Replace_1(String a){
        String a1 = a.substring(0, 8);
        String a2 = a.substring(8, 16);
        int a11 = Integer.parseInt(a1, 2);
        int a22 = Integer.parseInt(a2, 2);
        int s1= SBox.s_box_1(a11);
        int s2 = SBox.s_box_1(a22);
        String s11 = Integer.toBinaryString(s1);
        String s22 = Integer.toBinaryString(s2);

        while (s11.length() < 8) {
            s11 = "0" + s11;
        }
        while (s22.length() < 8) {
            s22 = "0" + s22;
        }
        String out = s11 + s22;
        return out;
    }
    public static String Line_Shift(String a) {
        String[] nibble = new String[4];
        for (int i = 0; i < 4; i++) {
            nibble[i] = a.substring(i * 4, (i + 1) * 4);
        }
        String out = nibble[0] + nibble[3] + nibble[2] + nibble[1];
        return out;
    }
    public static String Column_Confusion(String a) { 
        int[] state = new int[4];
        for (int i = 0; i < 4; i++) {
            String nibble = a.substring(i * 4, (i + 1) * 4);
            state[i] = Integer.parseInt(nibble, 2);
        }
        int[] newState = new int[4];
        newState[0] = state[0] ^ multiplyGF2_4(0x04, state[1]);
        newState[1] = state[1] ^ multiplyGF2_4(0x04, state[0]);
        newState[2] = state[2] ^ multiplyGF2_4(0x04, state[3]);
        newState[3] = state[3] ^ multiplyGF2_4(0x04, state[2]);
        String result = "";
        for (int i = 0; i < 4; i++) {
            result += String.format("%4s", Integer.toBinaryString(newState[i])).replace(' ', '0');
        }
        return result;
    }
    public static String Column_Confusion_1(String a) { 
        int[] state = new int[4];
        for (int i = 0; i < 4; i++) {
            String nibble = a.substring(i * 4, (i + 1) * 4);
            state[i] = Integer.parseInt(nibble, 2);
        }
        int[] newState = new int[4];
        newState[0] = multiplyGF2_4(0x09, state[0]) ^ multiplyGF2_4(0x02, state[1]);
        newState[1] = multiplyGF2_4(0x09, state[1]) ^ multiplyGF2_4(0x02, state[0]);
        newState[2] = multiplyGF2_4(0x09, state[2]) ^ multiplyGF2_4(0x02, state[3]);
        newState[3] = multiplyGF2_4(0x09, state[3]) ^ multiplyGF2_4(0x02, state[2]);
        String result = "";
        for (int i = 0; i < 4; i++) 
        {
            result += String.format("%4s", Integer.toBinaryString(newState[i])).replace(' ', '0');
        }
        return result;
    }
    public static int multiplyGF2_4(int a, int b) {
        int result = 0;
        int bitMask = 1;
        for (int i = 0; i < 4; i++) 
        {
            if ((b & bitMask) != 0) 
            {
                result ^= a;
            }
            boolean carry = (a & 0x08) != 0;
            a <<= 1;
            if (carry) {a ^= 0x03;
            }
            bitMask <<= 1;
        }
        return result & 0x0F;
    }
    public static String key_addition (String In, String Kn) 
    {
        StringBuffer Xor = new StringBuffer();
        int k = In.length();
        String[] In1 = In.split("");
        String[] Kn1 = Kn.split("");
        for (int i = 0; i < k; i++) 
        {
            if (In1[i].equals(Kn1[i])) 
            {
                Xor.append("0");
            } 
            else {
                Xor.append("1");
            }
        }
        return Xor.toString();
    }
}
