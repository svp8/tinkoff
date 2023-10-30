package edu.project2Labyrinth.generators;

import edu.project2Labyrinth.Cell;
import edu.project2Labyrinth.Coordinate;
import edu.project2Labyrinth.Labyrinth;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class RandomizedDFSGenerator implements Generator {
    private final Deque<Cell> stack = new ArrayDeque<>();
    private Labyrinth labyrinth;
    private List<Cell> grid;
    private Cell current;

    public void init(int height,int width){
        grid=new ArrayList<>();
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                grid.add(new Cell(i,j));
            }
        }
    }
    public Cell randomUnvisitedNeighbour(Cell cell){
        Coordinate coordinate=cell.getCoordinate();
        int x=coordinate.x();
        int y=coordinate.y();
        List<Cell> neighbours=new ArrayList<>();
        Cell top = checkNeighbourInGridBounds(grid, new Cell(x, y - 1));
        Cell right = checkNeighbourInGridBounds(grid, new Cell(x + 1, y));
        Cell bottom = checkNeighbourInGridBounds(grid, new Cell(x, y + 1));
        Cell left = checkNeighbourInGridBounds(grid, new Cell(x - 1, y));
        if (top != null) if(!top.isVisited()) neighbours.add(top);
        if (right != null) if(!right.isVisited()) neighbours.add(right);
        if (bottom != null)if(!bottom.isVisited()) neighbours.add(bottom);
        if (left != null) if(!left.isVisited())neighbours.add(left);
        if (neighbours.size() ==  1) {
            return neighbours.get(0);
        }
        if (neighbours.size() > 0) {
            return neighbours.get(new Random().nextInt(neighbours.size()));
        } else {
            return null;
        }
    }

    private Cell checkNeighbourInGridBounds(List<Cell> grid, Cell neighbour) {
        if (grid.contains(neighbour)) {
            return grid.get(grid.indexOf(neighbour));
        } else {
            return null;
        }
    }

    private void carve() {
        while (!stack.isEmpty()){
            current=stack.peekFirst();
            current.setVisited(true);
            Cell next = randomUnvisitedNeighbour(current);
                if (next != null) {
                    stack.addFirst(next);
                    removeWalls(current,next);
//                    current = next;
                } else if (!stack.isEmpty()) {
                    stack.pop();
                }
        }
    }
    public void removeWalls(Cell current,Cell next) {
        Coordinate coordinateCurrent=current.getCoordinate();
        Coordinate coordinateNext=next.getCoordinate();
        boolean[] wallsCurrent=current.getWalls();
        boolean[] wallsNext=next.getWalls();
        int x = coordinateCurrent.x() - coordinateNext.x();
        // top 0, right 1, bottom 2, left 3

        if(x == 1) {
            wallsCurrent[3] = false;
            wallsNext[1] = false;
        } else if (x == -1) {
            wallsCurrent[1] = false;
            wallsNext[3] = false;
        }

        int y = coordinateCurrent.y() - coordinateNext.y();

        if(y == 1) {
            wallsCurrent[0] = false;
            wallsNext[2] = false;
        } else if (y == -1) {
            wallsCurrent[2] = false;
            wallsNext[0] = false;
        }
    }

    @Override
    public Labyrinth generate(int height, int width) {
        init(height,width);
        current=grid.get(0);
        stack.push(current);
        carve();
        return new Labyrinth(height,width,grid);
    }
}
