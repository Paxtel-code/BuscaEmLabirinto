import java.util.Arrays;

class Node{
    int[] posicao;
    Node pai;
    double g,h,f;

    public Node(int[] posicao, Node pai, double g, double h, double f) {
        this.posicao = posicao;
        this.pai = pai;
        this.g = g;
        this.h = h;
        this.f = f;
    }

    @Override
    public String toString() {
        return STR."Nodo{posicao=\{Arrays.toString(posicao)}, pai=\{pai}, g=\{g}, j=\{h}, f=\{f}\{'}'}";
    }
}
