// Author: Emile Issaelkhoury
// email: emileissak@gmail.com

package diplomaticPlates;
import java.io.*;

import javax.swing.*;


public class diplPGUI{

                public static void main(String[] args) throws IOException {

                                // TODO Auto-generated method stub

        //BufferedReader reader;

        String name = "";

        boolean noNum = false;

        boolean threeL = false;

        boolean firstL = false;

        boolean lastTwo = false;

        //reader = new BufferedReader(new InputStreamReader(System.in));

 

        while (!noNum || !threeL || !firstL || !lastTwo)

                {

                //System.out.println("Enter the first three letters on the plate: ");

                //name = reader.readLine();

                name = JOptionPane.showInputDialog("Enter the first three letters on the plate: ");

                name = name.toUpperCase();

                noNum = checkVal(name);

                threeL = checkLen(name);

                firstL = valCode(name.charAt(0));

                lastTwo = checkCode(name.substring(1,3));

                if (!noNum) JOptionPane.showMessageDialog(null,"Only three letters. Try again");

                else if (!threeL) JOptionPane.showMessageDialog(null,"Only three letters, no numbers. Try again");

                else if (!firstL) JOptionPane.showMessageDialog(null,"First letter should be D, C, A or S. Try again");

                else if (!lastTwo) JOptionPane.showMessageDialog(null,"Invalid country code. Try again!");

                }

        //System.out.println("\n" + "You are looking at " + getCode(name.charAt(0)) + " from " + getCountry(name.substring(1,3)) + ".");

        JOptionPane.showMessageDialog(null, "You are looking at " + getCode(name.charAt(0)) + " from " + getCountry(name.substring(1,3)) + ".");

       

                }

                public static boolean checkVal(String three)

                {

                                char[] array = three.toCharArray();

                                for ( char c : array)

                                {

                                                if (!Character.isLetter(c))

                                                                return false;

                                }

                                return true;

                               

                }

                public static boolean checkLen (String three)

                {

                                if ( three.length() != 3)

                                {

                                                return false;

                                }

                                return true;

                }

                public static boolean valCode(char code)

                {

                                // "C" for Foreign Consul; "D" for Diplomat;

                                //"S" for Non-Diplomatic Staff; and "A" for a UN employee

                                char[] array = {'C','D','S','A'};

                                boolean check = false;

                                for (char c : array)

                                {

                                                if (code == c)

                                                                check = true;

                                }

                                return check;

                }

               

                public static boolean checkCode(String code) throws IOException

                {

                                boolean check = false;

                                BufferedReader in = new BufferedReader(new FileReader("plates.txt"));

                                String str = "";
                                // Author: Emile Issaelkhoury - emileissak@gmail.com
                                while ((str = in.readLine()) != null)

                                {

                                       if(str != null && !check)

                                       {

                                         check = str.substring(0,2).contains(code) ? true : false;

                                       }

                                      

                                }

                                in.close();

                                return check;

                }

                public static String getCode(char code)

                {

                                // "C" for Foreign Consul; "D" for Diplomat;

                                //"S" for Non-Diplomatic Staff; and "A" for a UN employee

                                char[] array = {'C','D','S','A'};

                                String[] name = {"Foreign Consul", "Diplomat", "Non-Diplomatic Staff", "UN employee"};

                                String str = "";

                                for (int c=0;c<array.length;c++)

                                {

                                                if (code == array[c])

                                                                str = name[c];

                                }

                                return str;

                }

                public static String getCountry(String code) throws IOException

                {

                                BufferedReader in = new BufferedReader(new FileReader("plates.txt"));

                                String str = "";

                                boolean check = false;

                                String stringout = "";

                                while ((str = in.readLine()) != null)

                                {

                                       if(str != null && !check)

                                       {

                                         check = str.substring(0,2).contains(code) ? true : false;

                                         stringout = str.substring(5, str.length());

                                       }

                                      

                                }

                                in.close();

                                return stringout;

                }

 

}