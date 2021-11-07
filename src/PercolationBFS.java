import java.util.*;

public class PercolationBFS extends PercolationDFSFast {
    public PercolationBFS(int n){
        super(n);
    }

    @Override
    protected void dfs(int row, int col){
        if(!inBounds(row,col)){
            return;
        }
        myGrid[row][col] = FULL;
        Queue<Integer> qp = new LinkedList<>();
        qp.add((myGrid.length * row) + col);
        while(qp.size()!=0) {
            int a = qp.remove();
            int c = a % myGrid.length;
            int r = a / myGrid.length;

            if (inBounds(r, c + 1) && isOpen(r, c + 1) && !isFull(r, c + 1)) {
                myGrid[r][c + 1] = FULL;
                qp.add(r * myGrid.length + c + 1);
            }
            if (inBounds(r, c - 1) && isOpen(r, c - 1) && !isFull(r, c - 1)) {
                myGrid[r][c - 1] = FULL;
                qp.add(r * myGrid.length + c - 1);
            }
            if (inBounds(r+1, c) && isOpen(r+1, c) && !isFull(r+1, c)) {
                myGrid[r + 1][c] = FULL;
                qp.add((r + 1) * myGrid.length + c);
            }
            if (inBounds(r-1, c) && isOpen(r-1, c) && !isFull(r-1, c)) {
                myGrid[r - 1][c] = FULL;
                qp.add((r -1) * myGrid.length + c);
            }
        }

    }
}
