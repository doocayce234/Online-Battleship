public class addShip {
	//COL and ROW enters the ring!!!
	public static int col;
	public static int row;
	//Okay, the name is self-explanatory!
	public static String[][] addShip(String[][] board, String cords, int length, boolean direction) {
		System.out.println(cords);
		col = Integer.parseInt(cords.substring(1)) - 1;
		row = cords.charAt(0) - 'A';
		System.out.println("col: " + col + " row: " + row);
		for(int i = 0; i < length; i = i + 1) {
			if(direction) {
				if(length == 5) {
					board[row][col + i] = "C";
				}
				else if(length == 4) {
					board[row][col + i] = "B";
				}
				else if(length == 3) {
					board[row][col + i] = "D";
				}
			}
			else if(!direction) {
				if(length == 5) {
					board[row + i][col] = "C";
				}
				else if(length == 4) {
					board[row + i][col] = "B";
				}
				else if(length == 3) {
					board[row + i][col] = "D";
				}
			}
		}
		return board;
	}
}
