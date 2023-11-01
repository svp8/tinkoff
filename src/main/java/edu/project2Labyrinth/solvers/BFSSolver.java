package edu.project2Labyrinth.solvers;

import edu.project2Labyrinth.Cell;
import edu.project2Labyrinth.Coordinate;
import edu.project2Labyrinth.Labyrinth;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static edu.project2Labyrinth.Labyrinth.getValidNeighbour;

public class BFSSolver  implements Solver{
    ArrayList<Coordinate> result = new ArrayList<>();
    Deque<Coordinate> queue=new ArrayDeque<>();
    @Override
    public List<Coordinate> solve(Labyrinth labyrinth, Coordinate coordinate1, Coordinate coordinate2) {
        List<Cell> cells = labyrinth.getCells();
        boolean[][] visited = new boolean[labyrinth.getHeight()][labyrinth.getWidth()];
        result.add(coordinate1);
        queue.offer(coordinate1);
        Map<Coordinate,Coordinate> relations=new HashMap<>();
        relations.put(coordinate1,null);
        while(!queue.isEmpty()){
            Coordinate current = queue.poll();
            visited[current.y()][current.x()]=true;
            if(current.equals(coordinate2)){
                break;
            }
            List<Cell> neighbours = getValidNeighbour(new Cell(current.y(), current.x()), cells);
            for(Cell neighbour:neighbours){
                Coordinate coordinate=neighbour.getCoordinate();
                if(!visited[coordinate.y()][coordinate.x()]){
                    relations.put(neighbour.getCoordinate(),current);
                    queue.offer(neighbour.getCoordinate());
                }
            }
        }
        List<Coordinate> path=new ArrayList<>();
        Coordinate current = coordinate2;
        path.add(current);
        current=relations.get(current);
        while(current!=null){
            path.addFirst(current);
            current=relations.get(current);
        }
        return path;
    }
    record BFSCoordinate(Coordinate coordinate,Coordinate parent){}
    void bfs(Coordinate current, boolean[][] visited, List<Coordinate> path, Coordinate end, List<Cell> cells) {
        path.add(current);
        if (current.equals(end)) {
            result = new ArrayList<>(path);
            return;
        }
        visited[current.y()][current.x()] = true;
        List<Cell> neighbours = getValidNeighbour(new Cell(current.y(), current.x()), cells);
        for (Cell neighbour : neighbours) {
            Coordinate neighbourCoordinate = neighbour.getCoordinate();
            if (!visited[neighbourCoordinate.y()][neighbourCoordinate.x()]) {
                bfs(neighbourCoordinate, visited, path, end, cells);
                path.remove(path.size() - 1);
            }
        }
    }
}
