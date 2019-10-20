import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;

import jfiglet.FigletFont;

/**
 * Java CLI-MenuBuilder A Java Class that automatically generates a Command Line
 * Menu using Java Figlet(jfiglet) by lalyos https://github.com/lalyos/jfiglet
 * 
 * @author Tobias Schneider (https://github.com/iMilchshake)
 * @version 1.1
 */
public class MenuBuilder {

	public static void main(String[] args) throws Exception {

		// Example on how to use this class
		String[] subMenuEntries = { "[1] Start", "[2] Options", "[3] Exit" };
		System.out.print(BuildMenu("Menu Builder", subMenuEntries, "fonts/standard.flf", 2, 1, 1, 0, 0));
	}

	/**
	 * @param Text             The Bannertext
	 * @param menuEntries      Array with the submenu entries (leave this empty if
	 *                         you dont want to display a submenu)
	 * @param font             direction of the font (*.flf)
	 * @param borderGapBannerX Gap between Border and Banner (X)
	 * @param borderGapBannerY Gap between Border and Banner (Y)
	 * @param borderGapMenuX   Gap between Border and Menu (X)
	 * @param borderGapMenuY   Gap between Border and Menu (Y)
	 * @param MenuTextGap      Gap between Menu entries
	 * @return String with the whole Menu
	 */
	public static String BuildMenu(String Text, String[] menuEntries, String font, int borderGapBannerX,
			int borderGapBannerY, int borderGapMenuX, int borderGapMenuY, int MenuTextGap) throws Exception {

		// SETTINGS:
		char[] border_symbs = { 'X', 'X', 'X', '└', 'X', '│', '┌', '├', 'X', '┘', '─', 'X', '┐', 'X', '┬', 'X' }; // X =
		// No
		// valid
		// character
		// known
		
		int border = 1; // bordersize, not implemented. DO NOT CHANGE

		// Create Figlet-Text (Banner)
		String figletString = FigletFont.convertOneLine(font, Text); // Create Figlet Text
		figletString = figletString.replaceAll("[\n\r]+", "\n"); // Get Rid of Empty Lines
		figletString = figletString.replaceAll("\n[ \t]*\n", "\n"); // Get Rid of Empty Lines

		// Get X and Y Size of Banner (rows & maxCollumns)
		int rows = 0, maxCollumns = 0, currentLength = 0;
		for (int i = 0; i < figletString.length(); i++) {
			currentLength++;
			if (figletString.charAt(i) == '\n') // Found Linebreak
			{
				rows++;
				if (currentLength - 1 > maxCollumns)
					maxCollumns = currentLength - 1;
				currentLength = 0;
			}
		}

		// Get Rid of Linebreaks
		figletString = figletString.replaceAll("\n", "");

		// Get Longest SubMenu Entry
		int subMenuTextWidth = 0;
		for (String s : menuEntries)
			if (s.length() > subMenuTextWidth)
				subMenuTextWidth = s.length();

		// Build SubMenuString
		String subMenuString = "";
		for (int i = 0; i < menuEntries.length; i++) {
			for (int c = 0; c <= subMenuTextWidth; c++) {
				if (c < menuEntries[i].length())
					subMenuString += menuEntries[i].charAt(c);
				else
					subMenuString += ' ';
			}

			for (int a = 0; a < (subMenuTextWidth + 1) * MenuTextGap; a++) // add x empty rows after each entry
				subMenuString += ' ';
		}

		// Calculate overall Size of Grid
		int bannerHeight = rows + border * 2 + borderGapBannerY * 2;
		int gridWidth = maxCollumns + border * 2 + borderGapBannerX * 2;
		int gridHeight = bannerHeight;

		if (menuEntries.length > 0) // if there are menuEntries add the area to the grid
			gridHeight += Math.max(
					menuEntries.length + ((menuEntries.length - 1) * MenuTextGap) + borderGapMenuY * 2 + border, 0);

		// create Grid
		int[][] grid = new int[gridWidth][gridHeight]; // 0 empty, 1 border, 2 banner-text, 3 submenu-text [X,Y]

		// Fill Grid with placeholder-numbers (0=Empty, 1=Border, 2=Banner-Area,
		// 3=SubMenu-Area)
		for (int y = 0; y < gridHeight; y++) {
			for (int x = 0; x < gridWidth; x++) {
				if (y < bannerHeight) { // BANNER
					if (x < border || y < border || y >= bannerHeight - border || x >= gridWidth - border)
						grid[x][y] = 1;
					else if (x < border + borderGapBannerX || y < border + borderGapBannerY
							|| y >= bannerHeight - border - borderGapBannerY
							|| x >= gridWidth - border - borderGapBannerX)
						grid[x][y] = 0;
					else
						grid[x][y] = 2;
				} else if (menuEntries.length > 0) { // SUB MENU AREA
					if (x < border
							|| (y >= gridHeight - border && x < subMenuTextWidth + border * 2 + borderGapMenuX * 2)
							|| x == subMenuTextWidth + border * 2 + borderGapMenuX * 2)
						grid[x][y] = 1;
					else if (x < border + borderGapMenuX || (y < bannerHeight + borderGapMenuY)
							|| x >= subMenuTextWidth + border * 2 + borderGapMenuX
							|| (y >= gridHeight - borderGapMenuY - border))
						grid[x][y] = 0;
					else if (x < subMenuTextWidth + border * 2 + borderGapMenuX * 2)
						grid[x][y] = 3;
					else
						grid[x][y] = 0;
				} else { // Would be Sub Menu Area but there are no entries
					grid[x][y] = 0;
				}
			}
		}

		// Replace Placeholder-Numbers with real characters
		int bannerindex = 0;
		int submenuindex = 0;
		String output = "";
		for (int y = 0; y < gridHeight; y++) {
			for (int x = 0; x < gridWidth; x++) {
				if (grid[x][y] == 0) // Empty
					output += ' ';
				else if (grid[x][y] == 1) { // Border
					boolean left = false, right = false, up = false, down = false;

					if (x > 0)
						left = (grid[x - 1][y] == 1);
					if (y > 0)
						up = (grid[x][y - 1] == 1);
					if (x < gridWidth - 1)
						right = (grid[x + 1][y] == 1);
					if (y < gridHeight - 1)
						down = (grid[x][y + 1] == 1);

					output += border_symbs[getSymbNumber(up, right, down, left)];
				} else if (grid[x][y] == 2) { // Banner
					output += figletString.charAt(bannerindex++);
				} else if (grid[x][y] == 3) // SubMenu
					output += subMenuString.charAt(submenuindex++);
			}
			output += "\n";
		}
		return output;
	}

	public static String RequestSecret(String request_text, Console console) {
		return new String(console.readPassword(request_text));
	}
	
	public static String RequestPublic(String request_text, Console console) {
		 return console.readLine(request_text);
	}
	
	private static int getSymbNumber(boolean up, boolean right, boolean down, boolean left) {
		// convert booleans to numbers
		int u = up ? 1 : 0;
		int r = right ? 1 : 0;
		int d = down ? 1 : 0;
		int l = left ? 1 : 0;

		return u * 1 + r * 2 + d * 4 + l * 8; // every possible combination represents another number
	}

	public static String ConsoleInput(String requestText) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print(requestText);
		String input = null;
		try {
			input = reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return input;
	}

}
