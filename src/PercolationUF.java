public class PercolationUF implements IPercolate {
    public boolean[][] myGrid;
    public int myOpenCount;
    public IUnionFind myFinder;
    private final int VTOP;
    private final int VBOTTOM;

    public PercolationUF(IUnionFind finder, int size){
        myGrid = new boolean[size][size];
        myOpenCount = 0;
        VTOP = size * size;
        VBOTTOM = size * size +1;
        myFinder = finder;
        myFinder.initialize(size*size+2);
    }

    public int Index(int row, int col, int size){
        return row*size + col;
    }

    protected Boolean inBounds(int row, int col) {
        if (col < 0 || col >= myGrid[0].length) {
            return false;
        }
        if (row < 0 || row >= myGrid.length) {
            return false;
        }
        return true;
    }

    @Override
    public void open(int row, int col) {
        if(!inBounds(row, col)){
            throw new IndexOutOfBoundsException("not in");
        }
        int index = Index(row, col, myGrid.length);
        if(myGrid[row][col]) return;
        myGrid[row][col] = true;
        myOpenCount++;

        if(inBounds(row, col - 1) && isOpen(row, col -1)) {
            myFinder.union(index, Index(row, col-1, myGrid.length));
        }
        if(inBounds(row, col + 1) && isOpen(row, col +1)) {
            myFinder.union(index, Index(row, col+1, myGrid.length));
        }
        if(inBounds(row-1, col ) && isOpen(row-1, col)) {
            myFinder.union(index, Index(row-1, col, myGrid.length));
        }
        if(inBounds(row+1, col ) && isOpen(row+1, col)) {
            myFinder.union(index, Index(row+1, col, myGrid.length));
        }
        if(row==0) {
            myFinder.union(index, VTOP);
        }
        if(row == myGrid.length -1) {
            myFinder.union(index,VBOTTOM);
        }


    }

    @Override
    public boolean isOpen(int row, int col) {
        if(!inBounds(row, col)) {
            throw new IndexOutOfBoundsException("u not in bounds fool"
            );
        }
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if(!inBounds(row, col)) {
            throw new IndexOutOfBoundsException("u still out of bounds fool");
        }
        return myFinder.connected(Index(row, col, myGrid.length), VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }
}
