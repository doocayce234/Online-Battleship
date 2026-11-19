public class hitShip {
	//Checks if the cords given has hit the other players ship or not with the given turn, 1 == p1 and 2 == p2. Pretty obvious!
	//the cords given to this method, its max is J9, its least is A0
	public static boolean hitShip(String cords, int turn, boolean comOrNot, boolean changeValues) {
		if(turn == 1 && comOrNot == true) {
			if(play.p2OriginalBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equals("C") || play.p2OriginalBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equals("B") || play.p2OriginalBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equals("D")) {
				if(changeValues == false){
				    return true;
				}
				play.p1DummyBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "X";
				play.p2board[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "X";
				return true;
			}
			else{
				if(changeValues == false){
				    return false;
				}
				play.p1DummyBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "*";
				play.p2board[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "*";
			}
		}
		else if(turn == 1 && comOrNot == false) {
			if(play.comOriginalBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equals("C") || play.comOriginalBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equals("B") || play.comOriginalBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equals("D")) {
                if(changeValues == false){
				    return true;
				}				
				play.p1DummyBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "X";
				play.comBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "X";
				return true;
			}
			else{
				if(changeValues == false){
				    return false;
				}
				play.p1DummyBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "*";
				play.comBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "*";
			}
		}
		else if(turn == 2 && comOrNot == true) {
			if(play.p1OriginalBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equals("C") || play.p1OriginalBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equals("B") || play.p1OriginalBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equals("D")) {
				if(changeValues == false){
				    return true;
				}
				play.p2DummyBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "X";
				play.p1board[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "X";
				return true;
			}
			else{
				if(changeValues == false){
				    return false;
				}
				play.p2DummyBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "*";
				play.p1board[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "*";
			}
		}
		else if(turn == 2 && comOrNot == false) {
		    // this one is when its the computer calling this method!
			if(play.p1OriginalBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equals("C") || play.p1OriginalBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equals("B") || play.p1OriginalBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equals("D")) {
				if(changeValues == false) {
				    return true;
				}
				if(play.comDummyBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1].equalsIgnoreCase("X")){
				    return false;
				}
				play.comDummyBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "X";
				play.p1board[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "X";
				return true;
			}
			else{
				if(changeValues == false){
				    return false;
				}
				play.comDummyBoard[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "*";
				play.p1board[cords.charAt(0) - 'A'][Integer.parseInt(cords.substring(1)) - 1] = "*";
			}
		}
		return false;
	}
	//This one was a pain in my a--. You understand!
	public static int sunkShip(String[][] board, int turn, String cords, boolean comOrNot) {
		//returns 5 for sunk cruiser
		//returns 41 for sunk battleship #1
		//returns 42 for sunk battleship #2
		//returns 31 for sunk destroyer #1
		//returns 32 for sunk destroyer #2
		//returns 0 for not sunk
		String[][] copy = play.createCopy(board);
		int col = Integer.parseInt(cords.substring(1)) - 1;
		int row = cords.charAt(0) - 'A';
		int length = 0;
		boolean direction = false;
		String typeOfShip = "";
		int sunk = 0;
		if((turn == 1 && comOrNot == true) && play.p2OriginalBoard[row][col].equalsIgnoreCase("C")) {
			length = 5;
			col = play.p2cruiserCords[0] / 10;
			row = play.p2cruiserCords[0] % 10;
			direction = play.p2cruiserDirection;
			typeOfShip = "C";
		}
		else if((turn == 1 && comOrNot == false) && play.comOriginalBoard[row][col].equalsIgnoreCase("C")) {
			length = 5;
			col = play.comCruiserCords[0] / 10;
			row = play.comCruiserCords[0] % 10;
			direction = play.comCruiserDirection;
			typeOfShip = "C";
		}
		else if((turn == 2 && (comOrNot == true || comOrNot == false))  && play.p1OriginalBoard[row][col].equalsIgnoreCase("C")) {
			length = 5;
			col = play.p1cruiserCords[0] / 10;
			row = play.p1cruiserCords[0] % 10;
			direction = play.p1cruiserDirection;
			typeOfShip = "C";
		}
		else if((turn == 1 && comOrNot == true) && play.p2OriginalBoard[row][col].equalsIgnoreCase("B")) {
			length = 4;
			if((col >= play.p2battleshipCords[0] / 10 && col <= play.p2battleshipCords[1] / 10) && (row >= play.p2battleshipCords[0] % 10 && row <= play.p2battleshipCords[1] % 10)) {
				col = play.p2battleshipCords[0] / 10;
				row = play.p2battleshipCords[0] % 10;
				direction = play.p2battleshipDirection;
				typeOfShip = "B1";
			}
			else if((col >= play.p2battleship2Cords[0] / 10 && col <= play.p2battleship2Cords[1] / 10) && (row >= play.p2battleship2Cords[0] % 10 && row <= play.p2battleship2Cords[1] % 10)) {
				col = play.p2battleship2Cords[0] / 10;
				row = play.p2battleship2Cords[0] % 10;
				direction = play.p2battleship2Direction;
				typeOfShip = "B2";
			}
		}
		else if((turn == 1 && comOrNot == false) && play.comOriginalBoard[row][col].equalsIgnoreCase("B")) {
			length = 4;
			if(col >= play.comBattleshipCords[0] / 10 && col <= play.comBattleshipCords[1] / 10 && row >= play.comBattleshipCords[0] % 10 && row <= play.comBattleshipCords[1] % 10) {
				col = play.comBattleshipCords[0] / 10;
				row = play.comBattleshipCords[0] % 10;
				direction = play.comBattleshipDirection;
				typeOfShip = "B1";
			}
			else if(col >= play.comBattleship2Cords[0] / 10 && col <= play.comBattleship2Cords[1] / 10 && row >= play.comBattleship2Cords[0] % 10 && row <= play.comBattleship2Cords[1] % 10) {
				length = 4;
				col = play.comBattleship2Cords[0] / 10;
				row = play.comBattleship2Cords[0] % 10;
				direction = play.comBattleship2Direction;
				typeOfShip = "B2";
			}
		}
		else if((turn == 2 && (comOrNot == true || comOrNot == false))  && play.p1OriginalBoard[row][col].equalsIgnoreCase("B")) {
			length = 4;
			if(col >= play.p1battleshipCords[0] / 10 && col <= play.p1battleshipCords[1] / 10 && row >= play.p1battleshipCords[0] % 10 && row <= play.p1battleshipCords[1] % 10) {
				col = play.p1battleshipCords[0] / 10;
				row = play.p1battleshipCords[0] % 10;
				direction = play.p1battleshipDirection;
				typeOfShip = "B1";
			}
			else if(col >= play.p1battleship2Cords[0] / 10 && col <= play.p1battleship2Cords[1] / 10 && row >= play.p1battleship2Cords[0] % 10 && row <= play.p1battleship2Cords[1] % 10) {
				col = play.p1battleship2Cords[0] / 10;
				row = play.p1battleship2Cords[0] % 10;
				direction = play.p1battleship2Direction;
				typeOfShip = "B2";
			}
		}
		else if((turn == 1 && comOrNot == true) && play.p2OriginalBoard[row][col].equalsIgnoreCase("D")) {
			length = 3;
			if(col >= play.p2destroyerCords[0] / 10 && col <= play.p2destroyerCords[1] / 10 && row >= play.p2destroyerCords[0] % 10 && row <= play.p2destroyerCords[1] % 10) {
				col = play.p2destroyerCords[0] / 10;
				row = play.p2destroyerCords[0] % 10;
				direction = play.p2destroyerDirection;
				typeOfShip = "D1";
			}
			else if(col >= play.p2destroyer2Cords[0] / 10 && col <= play.p2destroyer2Cords[1] / 10 && row >= play.p2destroyer2Cords[0] % 10 && row <= play.p2destroyer2Cords[1] % 10) {
				col = play.p2destroyer2Cords[0] / 10;
				row = play.p2destroyer2Cords[0] % 10;
				direction = play.p2destroyer2Direction;
				typeOfShip = "D2";
			}
		}
		else if((turn == 1 && comOrNot == false) && play.comOriginalBoard[row][col].equalsIgnoreCase("D")) {
			length = 3;
			if(col >= play.comDestroyerCords[0] / 10 && col <= play.comDestroyerCords[1] / 10 && row >= play.comDestroyerCords[0] % 10 && row <= play.comDestroyerCords[1] % 10) {
				col = play.comDestroyerCords[0] / 10;
				row = play.comDestroyerCords[0] % 10;
				direction = play.comDestroyerDirection;
				typeOfShip = "D1";
			}
			else if(col >= play.comDestroyer2Cords[0] / 10 && col <= play.comDestroyer2Cords[1] / 10 && row >= play.comDestroyer2Cords[0] % 10 && row <= play.comDestroyer2Cords[1] % 10) {
				col = play.comDestroyer2Cords[0] / 10;
				row = play.comDestroyer2Cords[0] % 10;
				direction = play.comDestroyer2Direction;
				typeOfShip = "D2";
			}
		}
		else if(turn == 2 && (comOrNot == true || comOrNot == false)  && play.p1OriginalBoard[row][col].equalsIgnoreCase("D")) {
			length = 3;
			if(col >= play.p1destroyerCords[0] / 10 && col <= play.p1destroyerCords[1] / 10 && row >= play.p1destroyerCords[0] % 10 && row <= play.p1destroyerCords[1] % 10) {
				col = play.p1destroyerCords[0] / 10;
				row = play.p1destroyerCords[0] % 10;
				direction = play.p1destroyerDirection;
				typeOfShip = "D1";
			}
			else if(col >= play.p1destroyer2Cords[0] / 10 && col <= play.p1destroyer2Cords[1] / 10 && row >= play.p1destroyer2Cords[0] % 10 && row <= play.p1destroyer2Cords[1] % 10) {
				col = play.p1destroyer2Cords[0] / 10;
				row = play.p1destroyer2Cords[0] % 10;
				direction = play.p1destroyer2Direction;
				typeOfShip = "D2";
			}
		}
		//fasel is vertical(row)
		//true is horizontal(col)
		for(int i = 0; i < length; i = i + 1) {
			if(direction) {
				if(copy[row][col + i].equalsIgnoreCase("X")) {
					sunk = sunk + 1;
				}
			}
			else if(!direction) {
				if(copy[row + i][col].equalsIgnoreCase("X")) {
					sunk = sunk + 1;
				}
			}
		}
		if(sunk == length && typeOfShip.equalsIgnoreCase("B1")) {
			System.out.println(41);
			return 41;
		}
		else if(sunk == length && typeOfShip.equalsIgnoreCase("B2")) {
			System.out.println(42);
			return 42;
		}
		else if(sunk == length && typeOfShip.equalsIgnoreCase("D1")) {
			System.out.println(31);
			return 31;
		}
		else if(sunk == length && typeOfShip.equalsIgnoreCase("D2")) {
			System.out.println(32);
			return 32;
		}
		else if(sunk == length) {
			return length;
		}
		return 0;
	}
}
