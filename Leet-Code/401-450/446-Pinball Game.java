// Problem: Its a kind of pin ball game where you will be given 2D matrix board with obstacles ('\', '/' ) and start and end position, you need to return the end point by travelling ball from start point. When ball hits obstacle it changes it direction perpendicular to the ball current direction towords the face of obstacle.
// Ex: ball going to Left -> Hit the '/' it turns to Down side.
// 				 - - -
// 				|/|/|\|
// 				 - - -
// 				| | |\|X
// 				 - - -
// 				| |\| |O
// 				 - - -

// 				 - - -
// 				|/|\|/|\
// 				 - - -
// 				| | | | 
// 				 - - -
// 				|X|\|/|O
// 				 - - -

// 				 - - -
// 				|/|/| |\
// 				 - - -
// 				|/| |\| 
// 				 - - -
// 				|X|\|/|O
// 				 - - -

// 				 - - -
// 				|/| |\|\|
// 				 - - -
// 				|\| |O|/|
// 				 - - -
// 				|X| |/| |
// 				 - - -

// 				 - - -
// 				|/| | |\|
// 				 - - -
// 				|\| |O|/|
// 				 - - -
// 				|X| |/| |
// 				 - - -
				 - - -

public class PinBallGame {
    public static void main(String[] args) {
        char[][][] board = new char[][][]{{{'/', '/', '\\', ' '}, {' ', ' ', '\\', 'X'}, {' ', '\\', ' ', 'O'}}, {{'/', '\\', '/', '\\'}, {' ', ' ', ' ', ' '}, {'X', '\\', '/', 'O'}}, {{'/', '/', ' ', '\\'}, {'/', ' ', '\\', ' '}, {'X', '\\', '/', 'O'}}};
        Direction[] direction = new Direction[]{Direction.LEFT, Direction.UP, Direction.UP};
        for (int i = 0; i < board.length; i++) {
            int[] end = findEndPoint(board[i], new int[]{2, 3}, direction[i]);
            System.out.println(Arrays.toString(end));
            System.out.println("---------------------------");
        }
    }
    
    enum Direction {
        LEFT(new int[]{0, -1}),
        UP(new int[]{-1, 0}),
        RIGHT(new int[]{0, 1}),
        DOWN(new int[]{1, 0});
        
        private final int[] dir;
        
        Direction(int[] dir) {
            this.dir = dir;
        }
        
        public int[] getDir() {
            return this.dir;
        }
    }
    
    private static int[] findEndPoint(char[][] board, int[] rc, Direction direction) {
        int[] dir = direction.getDir();
        rc[0] = rc[0] + dir[0];
        rc[1] = rc[1] + dir[1];
        System.out.println(Arrays.toString(rc) + " " + direction);
        if (rc[0] < 0 || rc[0] >= board.length || rc[1] < 0 || rc[1] >= board[0].length) {
            return new int[]{-1, -1};
        }
        
        if (board[rc[0]][rc[1]] == 'X') {
            return rc;
        }
        
        if (board[rc[0]][rc[1]] == '\\') {
            if (direction == Direction.LEFT) {
                return findEndPoint(board, rc, Direction.UP);
            } else if (direction == Direction.DOWN) {
                return findEndPoint(board, rc, Direction.RIGHT);
            } else if (direction == Direction.RIGHT) {
                return findEndPoint(board, rc, Direction.DOWN);
            } else if (direction == Direction.UP) {
                return findEndPoint(board, rc, Direction.LEFT);
            }
        } else if (board[rc[0]][rc[1]] == '/') {
            if (direction == Direction.LEFT) {
                return findEndPoint(board, rc, Direction.DOWN);
            } else if (direction == Direction.DOWN) {
                return findEndPoint(board, rc, Direction.LEFT);
            } else if (direction == Direction.RIGHT) {
                return findEndPoint(board, rc, Direction.UP);
            } else if (direction == Direction.UP) {
                return findEndPoint(board, rc, Direction.RIGHT);
            }
        }
        return findEndPoint(board, rc, direction);
    }
}

// Clean code using enums for obstacle and direction
import static java.util.Map.entry; 
import java.util.*;

public class PinBallGame {
    public static void main(String[] args) {
        char[][][] board = new char[][][]{{{'/', '/', '\\', ' '}, {' ', ' ', '\\', 'X'}, {' ', '\\', ' ', 'O'}}, {{'/', '\\', '/', '\\'}, {' ', ' ', ' ', ' '}, {'X', '\\', '/', 'O'}}, {{'/', '/', ' ', '\\'}, {'/', ' ', '\\', ' '}, {'X', '\\', '/', 'O'}}, {{'/', '/', ' ', '\\'}, {' ', ' ', '\\', ' '}, {'X', '\\', '/', 'O'}}};
        Direction[] direction = new Direction[]{Direction.LEFT, Direction.UP, Direction.UP, Direction.UP};
        for (int i = 0; i < board.length; i++) {
            int[] end = findEndPoint(board[i], new int[]{2, 3}, direction[i]);
            System.out.println(Arrays.toString(end));
            System.out.println("---------------------------");
        }
    }
    
    enum Direction {
        LEFT(new int[]{0, -1}),
        UP(new int[]{-1, 0}),
        RIGHT(new int[]{0, 1}),
        DOWN(new int[]{1, 0});
        
        private final int[] dir;
        
        Direction(int[] dir) {
            this.dir = dir;
        }
        
        public int[] getDir() {
            return this.dir;
        }
    }
    
    enum Obstacle {
        FORWARD(Map.ofEntries(entry(Direction.LEFT, Direction.DOWN),
                             entry(Direction.DOWN, Direction.LEFT),
                             entry(Direction.UP, Direction.RIGHT),
                             entry(Direction.RIGHT, Direction.UP))),
        BACKWARD(Map.ofEntries(entry(Direction.LEFT, Direction.UP),
                             entry(Direction.UP, Direction.LEFT),
                             entry(Direction.DOWN, Direction.RIGHT),
                             entry(Direction.RIGHT, Direction.DOWN)));
        
        private final Map<Direction, Direction> diversion;
        Obstacle(Map<Direction, Direction> diversion) {
            this.diversion = diversion;
        }
        
        public Map<Direction, Direction> getDiversion() {
            return this.diversion;
        }
    }
    
    private static int[] findEndPoint(char[][] board, int[] rc, Direction direction) {
        int[] dir = direction.getDir();
        rc[0] = rc[0] + dir[0];
        rc[1] = rc[1] + dir[1];
        System.out.println(Arrays.toString(rc) + " " + direction);
        if (rc[0] < 0 || rc[0] >= board.length || rc[1] < 0 || rc[1] >= board[0].length) {
            return new int[]{-1, -1};
        }
        
        if (board[rc[0]][rc[1]] == 'X') {
            return rc;
        }
        
        if (board[rc[0]][rc[1]] == '\\') {
            return findEndPoint(board, rc, Obstacle.BACKWARD.getDiversion().get(direction));
        } else if (board[rc[0]][rc[1]] == '/') {
            return findEndPoint(board, rc, Obstacle.FORWARD.getDiversion().get(direction));
        }
        return findEndPoint(board, rc, direction);
    }
}


// Solution considering the possibility of a cycle
import static java.util.Map.entry; 
import java.util.*;

public class PinBallGame {
    public static void main(String[] args) {
        char[][][] board = new char[][][]{{{'/', '/', '\\', ' '}, {' ', ' ', '\\', 'X'}, {' ', '\\', ' ', 'O'}}, {{'/', '\\', '/', '\\'}, {' ', ' ', ' ', ' '}, {'X', '\\', '/', 'O'}}, {{'/', '/', ' ', '\\'}, {'/', ' ', '\\', ' '}, {'X', '\\', '/', 'O'}}, {{'/', ' ', '\\', '\\'}, {'\\', ' ', 'O', '/'}, {'X', ' ', '/', ' '}}, {{'/', ' ', ' ', '\\'}, {'\\', ' ', 'O', '/'}, {'X', ' ', '/', ' '}}};
        int[][] start = new int[][]{{2, 3}, {2, 3}, {2, 3}, {1, 2}, {1, 2}};
        Direction[] direction = new Direction[]{Direction.LEFT, Direction.UP, Direction.UP, Direction.LEFT, Direction.LEFT};
        for (int i = 0; i < board.length; i++) {
            int[] end = findEndPoint(board[i], start[i].clone(), start[i], direction[i], direction[i]);
            System.out.println(Arrays.toString(end));
            System.out.println("---------------------------");
        }
    }
    
    enum Direction {
        LEFT(new int[]{0, -1}),
        UP(new int[]{-1, 0}),
        RIGHT(new int[]{0, 1}),
        DOWN(new int[]{1, 0});
        
        private final int[] dir;
        
        Direction(int[] dir) {
            this.dir = dir;
        }
        
        public int[] getDir() {
            return this.dir;
        }
    }
    
    enum Obstacle {
        FORWARD(Map.ofEntries(entry(Direction.LEFT, Direction.DOWN),
                             entry(Direction.DOWN, Direction.LEFT),
                             entry(Direction.UP, Direction.RIGHT),
                             entry(Direction.RIGHT, Direction.UP))),
        BACKWARD(Map.ofEntries(entry(Direction.LEFT, Direction.UP),
                             entry(Direction.UP, Direction.LEFT),
                             entry(Direction.DOWN, Direction.RIGHT),
                             entry(Direction.RIGHT, Direction.DOWN)));
        
        private final Map<Direction, Direction> diversion;
        Obstacle(Map<Direction, Direction> diversion) {
            this.diversion = diversion;
        }
        
        public Map<Direction, Direction> getDiversion() {
            return this.diversion;
        }
    }
    
    private static int[] findEndPoint(char[][] board, int[] rc, int[] start, Direction direction, Direction initialDirection) {
        int[] dir = direction.getDir();
        rc[0] = rc[0] + dir[0];
        rc[1] = rc[1] + dir[1];
        System.out.println(Arrays.toString(rc) + " " + direction);
        if (rc[0] < 0 || rc[0] >= board.length || rc[1] < 0 || rc[1] >= board[0].length) {
            return new int[]{-1, -1};
        }
        if (rc[0] == start[0] && rc[1] == start[1] && direction == initialDirection) return new int[]{-1, -1};
        
        if (board[rc[0]][rc[1]] == 'X') {
            return rc;
        }
        
        if (board[rc[0]][rc[1]] == '\\') {
            return findEndPoint(board, rc, start, Obstacle.BACKWARD.getDiversion().get(direction), initialDirection);
        } else if (board[rc[0]][rc[1]] == '/') {
            return findEndPoint(board, rc, start, Obstacle.FORWARD.getDiversion().get(direction), initialDirection);
        }
        return findEndPoint(board, rc, start, direction, initialDirection);
    }
}