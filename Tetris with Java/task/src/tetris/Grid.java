package tetris;

public class Grid {
    private final char[][] matrix;
    private boolean isOnTop = false;
    public Grid(int rows, int cols) {
        matrix = new char[rows][cols];
    }

    public void saveMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, matrix[i].length);
        }
        checkTopRow();
    }

    public char[][] getMatrix() {
        char[][] matrixTemp = new char[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, matrixTemp[i], 0, matrix[i].length);
        }
        return matrixTemp;
    }

    public void breakPieces(){
        int counter;
        for(int i = 0; i < matrix.length; i++){
            counter = 0;
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == '0'){
                    counter++;
                }
            }
            if(counter == matrix[i].length){
                dropBlocks(i);
                for(int j = 0; j < matrix[i].length; j++){
                    matrix[0][j] = '-';
                }
            }
        }
    }

    private void dropBlocks(int row){
        for(int i = row;i>0;i--){
            System.arraycopy(matrix[i-1], 0, matrix[i], 0, matrix[i].length);
        }
    }

    private void checkTopRow(){
        for(int i = 0; i < matrix[0].length; i++){
            if (matrix[0][i] == '0') {
                isOnTop = true;
                break;
            }
        }
    }

    public boolean isOnTop() {
        return isOnTop;
    }
}
