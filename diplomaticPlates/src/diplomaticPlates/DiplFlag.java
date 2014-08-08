// Author: Emile Issaelkhoury
// email: emileissak@gmail.com
// Date: 08AUG14

package diplomaticPlates;

import java.awt.*;

import java.io.*;

import java.net.*;

import javax.swing.*;

public class DiplFlag {

	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub

		// BufferedReader reader;

		String name = "";
		boolean noNum = false;
		boolean threeL = false;
		boolean firstL = false;
		boolean lastTwo = false;
		

		while (!noNum || !threeL || !firstL || !lastTwo)
		{
			name = JOptionPane.showInputDialog("Enter the first three letters on the plate: ");
			name = name.toUpperCase();
			noNum = checkVal(name);
			if (!noNum)
			{
				JOptionPane.showMessageDialog(null,"Only three letters. Try again");
				System.out.println("GGOOD");
			}
			else
			{
				threeL = checkLen(name);
				firstL = valCode(name.charAt(0));
				lastTwo = checkCode(name.substring(1, 3));
				//if (!noNum)
				//	JOptionPane.showMessageDialog(null,"Only three letters. Try again");
	
				if (!threeL)
					JOptionPane.showMessageDialog(null,"Only three letters, no numbers. Try again");
	
				else if (!firstL)
					JOptionPane.showMessageDialog(null,"First letter should be D, C, A or S. Try again");
	
				else if (!lastTwo)
					JOptionPane.showMessageDialog(null,"Invalid country code. Try again!");
			}

		}

		// System.out.println("\n" + "You are looking at " +
		// getCode(name.charAt(0)) + " from " + getCountry(name.substring(1,3))
		// + ".");

		//JOptionPane.showMessageDialog(null,"You are looking at " + getCode(name.charAt(0)) + " from "	+ getCountry(name.substring(1, 3)) + ".\n");
		JOptionPane.showMessageDialog(null,"You are looking at " + getCode(name.charAt(0)) + " from "	+ getCountry(name.substring(1, 3)),"Dipomatic Plates",JOptionPane.INFORMATION_MESSAGE, getFlagGUI(getCountry(name.substring(1, 3))));
		
	}
	
	// get the GUI of the flag using a URL
	public static ImageIcon getFlagGUI(String nameFlag) throws IOException
	{
		//Cleaning up the names
		nameFlag = nameFlag.replaceAll(" ","_");
		nameFlag = nameFlag.replaceAll("and","");
		nameFlag = nameFlag.replaceAll("&","");
		nameFlag = nameFlag.replaceAll("the","");
		//nameFlag = nameFlag.toLowerCase();
		
		String theURL = "http://www.sciencekids.co.nz/images/pictures/flags680/"+nameFlag+".jpg";
		URL img = new URL(theURL);
		ImageIcon image = new ImageIcon(img);
		
		//BufferedImage image=ImageIO.read(img);
		//BufferedImage resizedImage=resize(image,100,100);
		/*Image img1 = image.getImage(); 
		BufferedImage bi = new BufferedImage(img1.getWidth(null), img1.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = bi.createGraphics(); 
		g.drawImage(img1, 0, 0, 150, 150, null); 
		//bi.getScaledInstance(150, 150, 0);
		ImageIcon newIcon = new ImageIcon(bi); */
		
		Image img1 = image.getImage();  
		Image newimg = img1.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);  
		ImageIcon newIcon = new ImageIcon(newimg);
		

		return newIcon;
	}


	public static boolean checkVal(String three)

	{
		char[] array = three.toCharArray();
		for (char c : array)
		{
			if (!Character.isLetter(c))
				return false;
		}
		return true;
	}

	public static boolean checkLen(String three)

	{
		boolean checker = true;
		System.out.println(three.length());
		if (three.length() != 3)
		{
			System.out.println("OK");
			checker = false;
		}
		return checker;
	}

	public static boolean valCode(char code)
	{
		// "C" for Foreign Consul; "D" for Diplomat;
		// "S" for Non-Diplomatic Staff; and "A" for a UN employee
		char[] array = { 'C', 'D', 'S', 'A' };
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
		while ((str = in.readLine()) != null)
		{
			if (str != null && !check)
			{
				check = str.substring(0, 2).contains(code) ? true : false;
			}
		}
		in.close();
		return check;
	}

	public static String getCode(char code)
	{
		// "C" for Foreign Consul; "D" for Diplomat;
		// "S" for Non-Diplomatic Staff; and "A" for a UN employee
		char[] array = { 'C', 'D', 'S', 'A' };
		String[] name = { "Foreign Consul", "Diplomat", "Non-Diplomatic Staff",	"UN employee" };
		String str = "";
		for (int c = 0; c < array.length; c++)
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
			if (str != null && !check)
			{
				check = str.substring(0, 2).contains(code) ? true : false;
				stringout = str.substring(5, str.length());
			}
		}
		in.close();
		return stringout;
	}

}