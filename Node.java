public class Node {
    
    Node[] filhos;
    boolean fimPalavra;
    
    public Node() {
        this.filhos = new Node[26];
        this.fimPalavra = false;
    }
    
    public Node[] getFilhos() {
        return filhos;
    }
    
    public void setFilhos(Node[] filhos) {
        this.filhos = filhos;
    }
    
    public boolean isFimPalavra() {
        return fimPalavra;
    }
    
    public void setFimPalavra(boolean fimPalavra) {
        this.fimPalavra = fimPalavra;
    }
    
    public boolean temFilhos() {
        for (Node filho : filhos) {
            if (filho != null) {
                return true;
            }
        }
        return false;
    }
}
