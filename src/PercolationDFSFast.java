import java.util.*;

public class PercolationDFSFast extends PercolationDFS {

    public PercolationDFSFast(int n){
        super(n);
    }


    @Override
    public void updateOnOpen(int row, int col){
        int n = 0;
        if(row == 0){
            n = 1;
        }
        if(row!= myGrid[row].length-1 && myGrid[row+1][col] == FULL) {
            n = 1;
        }
        if(row != 0 && myGrid[row -1][col] == FULL){
            n = 1;
        }
        if(col!= 0 && myGrid[row][col-1] == FULL) {
            n = 1;
        }
        if(col!= myGrid[col].length -1 && myGrid[row][col+1] == FULL) {
            n = 1;
        }
        if (n == 1){
            dfs(row, col);
        }

    }
}


