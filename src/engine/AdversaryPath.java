
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class AdversaryPath extends Component {

	private MyGrid grid; 
	private CellA[][] board;
	private boolean needPath = true;
	private int width, height, endX, endY, oldX, oldY;
	private boolean[][] blocked;
	int counter = 0;
	private Stack<int[]> stack = new Stack<int[]>(); 
	private List<CellA> open = new ArrayList<CellA>();
	private List<CellA> closed = new ArrayList<CellA>();
	
	public AdversaryPath(GameObject parent, MyGrid grid, boolean[][] hasBlocked) {
		super(parent);
		this.grid = grid;
		blocked = hasBlocked;
		this.board = new CellA[grid.getHt()][grid.getWd()];
	}
	
	public void graphics() {
		grid.setColor(oldY, oldX, Color.WHITE);
		grid.setColor(parent.posY, parent.posX, Color.gray);
	}
		
	public void moveAdversary() {
		if (stack.isEmpty()) {
			parent.posX = endX;
			parent.posY = endY;
			needPath = true;
			open = new ArrayList<>();
			closed = new ArrayList<>();
		} else {
			int[] tuple = stack.pop();
			oldX = parent.posX;
			oldY = parent.posY;
			parent.posY = tuple[1];
			parent.posX = tuple[0];
		}		
	}
	
	public void logic() {
		if (needPath) {
			counter++;
			if (counter > 1)
				System.out.println();
			needPath = false;
			Random ran = new Random();
			endX = ran.nextInt(79);
			endY = ran.nextInt(39);
			
			//need to make sure we dont go to a path that has an obstruction
			while (blocked[endY][endX]) {
				endX = ran.nextInt(grid.getWd() - 1);
				endY = ran.nextInt(grid.getHt() - 1);
			}
			
			System.out.println("endX is " + endX + " and endy is " + endY);
			aStar(parent.posY, parent.posX, endY, endX);
			getPath(parent.posY, parent.posX, endY, endX);
		} 
		moveAdversary();
	}
	
	public void aStar(int startY, int startX, int endY, int endX) {
		initialize();
		setWeights(endX, endY, startX, startY);
		open.add(board[startY][startX]);
		
		while (true) {
			int lowestIndex = findLowest(open);
			CellA curr = open.get(lowestIndex);
			open.remove(lowestIndex);
			closed.add(curr);
		
			if (curr.getX() == endX && curr.getY() == endY) {
				break;
			}
			
			countNeighbors(curr.getY(), curr.getX());
		}
	}
	
	//goes through and paints the path using the parent of each cell in the path 
	public void getPath(int startY, int startX, int endY, int endX) {
		int posx = endX, 
			posy = endY;
		
		while (!(posx == startX && posy== startY)) {
			int temp = posx;
			posx = board[posy][posx].getParentX();
			posy = board[posy][temp].getParentY();
			int[] tuple = new int[2];
			tuple[0] = posx;
			tuple[1] = posy;
			stack.push(tuple);
		}
	}
	
	//loops through and calculates the distance from the given cell to the end cell
	public void setWeights(int destX, int destY, int sourceX, int sourceY) {
		for (int i = 0; i < grid.getHt(); i++) {
			for (int j = 0; j < grid.getWd(); j++) {
				board[i][j].setDistFromDest(destX, destY);
			}
		}
	} 
	
	//goes through and add the neighbors to the open list
	public void countNeighbors(int i, int j) {
		int originalg = board[i][j].getDistFromSource();
		
		//top three blocks above the point
		for (int x = j - 1; x <= j + 1; x++) {
			if (!outOfBounds(i - 1, x) && !open.contains(board[i - 1][x]) && !closed.contains(board[i - 1][x]) && !blocked[i - 1][x]) {
				open.add(board[i - 1][x]);
				board[i - 1][x].setParent(i, j);
				
				int howMuchToIncr = (x == j) ? 10 : 14;
				board[i - 1][x].setDistFromSource(originalg + howMuchToIncr);
			}
		}
		
		//check the pieces to the left and the right of the point
		if (!outOfBounds(i, j - 1) && !open.contains(board[i][j - 1]) && !closed.contains(board[i][j - 1]) && !blocked[i][j - 1]) {
			open.add(board[i][j - 1]);
			board[i][j - 1].setParent(i, j);
			board[i][j - 1].setDistFromSource(originalg + 10);
		}
		
		if (!outOfBounds(i, j + 1) && !open.contains(board[i][j + 1]) && !closed.contains(board[i][j + 1]) && !blocked[i][j + 1]) {
			open.add(board[i][j + 1]);
			board[i][j + 1].setParent(i, j);
			board[i][j + 1].setDistFromSource(originalg + 10);
		}
		
		//check the bottom three blocks under the point
		for (int x = j - 1; x <= j + 1; x++) {
			if (!outOfBounds(i + 1, x) && !open.contains(board[i + 1][x]) && !closed.contains(board[i + 1][x]) && !blocked[i + 1][x]) {
				open.add(board[i + 1][x]);
				board[i + 1][x].setParent(i, j);
				
				int howMuchToIncr = (x == j) ? 10 : 14;
				board[i + 1][x].setDistFromSource(originalg + howMuchToIncr);
			}
		}
	}
	
	public boolean outOfBounds(int i, int j) {
		return (i < 0 || j < 0 || i >= grid.getHt() || j >= grid.getWd());
	} 
	
	//finds the smallest f value in the open list
	public int findLowest(List<CellA> list) {
		int min = 100000, index = 0;
		
		for(int i = 0; i < list.size(); i++) {
			if (list.get(i).getF() < min) {
				min = list.get(i).getF();
				index = i;
			} else if (list.get(i).getF() == min) {
				if (list.get(i).getDistFromDest() < list.get(index).getDistFromDest())
					index = i;
			}
		}
		
		return index;
	}
	
	public void initialize() {
		for (int i = 0; i < grid.getHt(); i++) {
			for (int j = 0; j < grid.getWd(); j++) {
				board[i][j] = new CellA(j, i);
				board[i][j].setDistFromDest(endX, endY);
			}
		}
	}
}