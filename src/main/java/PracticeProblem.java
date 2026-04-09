public class PracticeProblem {

	public static void main(String args[]) {
		String[][] maze = {
      {"", "", "", "", ""},
      {"", "*", "", "", ""},
      {"", "*", "", "*", ""},
      {"S", "*", "", "", "F"},
    };

	System.out.println(searchMazeMoves(maze));

	}

	public static int searchMazeMoves(String[][] arr){
		int row = arr.length-1;
		int col = 0;
		int out = sMMHelper(arr, row, col, 0);

		//since I used Integer.MAX_VALUE instead of -1, we have to rectify it here, also ternary operators are sick as heck 
		return (out == Integer.MAX_VALUE ? -1 : out);
	}

	public static int sMMHelper(String[][] arr, int row, int col, int moves){

		//base case: out of bounds (both ways now because we're omnidirectional) or if arr[row][col] is a wall
		if((col >= arr[0].length || col < 0) || (row < 0 || row >= arr.length) || arr[row][col].equals("*")){
			return Integer.MAX_VALUE;
		}

		//base case: arr[row][col] is at the finish
		if(arr[row][col].equals("F")){
			return moves;
		}
		
		//add wall to avoid moving backwards
		arr[row][col] = "*";

		//recursively call for every direction
		int uMoves = sMMHelper(arr, row - 1, col, moves + 1);
		int dMoves = sMMHelper(arr, row + 1, col, moves + 1);
		int rMoves = sMMHelper(arr, row, col + 1, moves + 1);
		int lMoves = sMMHelper(arr, row, col - 1, moves + 1);

		//remove wall once paths have been found
		arr[row][col] = "";

		//return the smallest of the values (this is why the base case returns Integer.MAX_VALUE, it just makes this easier)
		return Math.min(Math.min(uMoves, dMoves) , Math.min(rMoves, lMoves));
	}

}
