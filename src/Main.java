import java.util.*;

class Node {
    int[] posicao;
    Node pai;
    double g, h, f;
    //int custo;

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

public class Main {

    final String RESET = "\u001B[0m", RED = "\u001B[41m", GREEN = "\u001B[42m", YELLOW = "\u001B[43m", BLUE = "\u001B[44m", MAGENTA = "\u001B[45m", CYAN = "\u001B[46m", GREY = "\u001B[47m", BLACK = "\u001B[40m", WHITE = "\u001B[7m";
    public static Random random = new Random();

    public static Scanner LER = new Scanner(System.in);

    List<Node> explorados = new ArrayList<>();

    public void main(String[] args) {
        //▓ - Parede
        //0 - Solido e Plano
        //1 - Rochoso
        //2 - Arenoso
        //3 - Pântano
        //@ - Agente
        //$ - Moedas

        char mapa[][] = {
                {'▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '@', '0', '0', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '▓', '0', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '▓', '0', '▓', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '▓', '0', '▓', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '▓', '3', '▓', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '▓', '3', '▓', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '▓', '3', '▓', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '▓', '3', '▓', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '▓', '0', '▓', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '▓', '▓', '0', '▓', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '$', '0', '0', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓',}};

        int[] inicio = {2, 9};
        int treasures = 1;
        int t = treasures;
        //int[][] treasureLoc = new int[t][2];
        int[][] treasureLoc = {{14, 9}};
        mapa[14][9] = '$';

/*        do {
            int row = random.nextInt(20);
            int col = random.nextInt(20);
            if (mapa[col][row] != '▓') {
                mapa[col][row] = '$';
                treasureLoc[treasures - t][0] = col;
                treasureLoc[treasures - t][1] = row;
                t--;
            }
        } while (t > 0);*/
        System.out.println();

        impMapa(mapa, null, null);

        System.out.println();
        System.out.println("Press enter to continue...");
        LER.nextLine();

        List<Node> caminho = encontrarCaminho(mapa, inicio, treasureLoc);
        explorados.removeAll(caminho);
        impMapa(mapa, caminho, explorados);
        if (caminho != null) {
            System.out.println("Caminho encontrado:");
            for (int i = 1; i < caminho.size(); i++) {
                Node node = caminho.get(i - 1);
                System.out.print(Arrays.toString(node.posicao) + "->");
                if (i % 3 == 0) System.out.println();
            }
        }
        System.out.println();

        impMapa(mapa, caminho, null);

    }

    public boolean isTreasure(int[][] treasureLoc, Node nodeAtual) {
        for (int i = 0; i < treasureLoc.length; i++) {
            if (nodeAtual.posicao[0] == treasureLoc[i][0] && nodeAtual.posicao[1] == treasureLoc[i][1])
                return true;
        }
        return false;
    }

    public boolean verificarCaminhoPrincipal(List<Node> caminho, int i, int j) {
        if (caminho != null)
            for (int k = 0; k < caminho.size(); k++) {
                Node node = caminho.get(k);
                if (node.posicao[0] == i && node.posicao[1] == j) return true;
            }
        return false;
    }

    public void impMapa(char Mapa[][], List<Node> caminho, List<Node> explorados) {
        //Imprime os números de coordenada de coluna bunitin
        System.out.print("   ");
        for (int i = 0; i < Mapa.length; i++) {
            if (i > 9) System.out.print(" " + i + "");
            else System.out.print(" " + i + " ");
        }

        System.out.println();
        for (int i = 0; i < Mapa.length; i++) {
            if (i > 9) System.out.print(i + " ");
            else System.out.print(" " + i + " ");

            for (int j = 0; j < Mapa[i].length; j++) {
                if (Mapa[i][j] == '▓')
                    System.out.print(BLACK + "   " + RESET); //se for parede
                else if (Mapa[i][j] == '|')
                    System.out.print(GREEN + " " + Mapa[i][j] + " EXIT" + RESET); //se por saída
                else if (Mapa[i][j] == '@')
                    System.out.print(RED + ">:)" + RESET); //se for o bonequin
                else if (Mapa[i][j] == '$')
                    System.out.print(YELLOW + "($)" + RESET); //se for a grana
                else if (verificarCaminhoPrincipal(caminho, i, j) && caminho != null)
                    System.out.print(BLUE + "   " + RESET); //se foi o caminho do boneco
                else if (verificarCaminhoPrincipal(explorados, i, j) && explorados != null)
                    System.out.print(MAGENTA + "   " + RESET);
                else if (Mapa[i][j] == '1')
                    System.out.print(GREY + "   " + RESET); //se for pantano
                else if (Mapa[i][j] == '2')
                    System.out.print(YELLOW + "   " + RESET); //se for pantano
                else if (Mapa[i][j] == '3')
                    System.out.print(GREEN + "   " + RESET); //se for pantano
                else
                    System.out.print(WHITE + "   " + RESET); //se for chão de pedra
            }
            System.out.println(); // Imprime uma nova linha após cada linha da matriz
        }
    }

    public int verificiarBioma(Node noAtual, char[][] mapa) {
        return ((int) mapa[noAtual.posicao[0]][noAtual.posicao[1]]);

    }

    public List<Node> encontrarCaminho(char[][] mapa, int[] inicio, int[][] treasureLoc) {
        //aberta: Esta lista contém os nós descobertos, mas ainda não foram explorados completamente. Isso significa que esses nós foram considerados como possíveis candidatos para fazer parte do caminho ótimo
        List<Node> aberto = new ArrayList<>();
        //fechada: nós que já foram explorados completamente. Quando um nó é removido da lista aberta para ser explorado, ele é movido para a lista fechada.
        List<Node> fechado = new ArrayList<>();

        // Cria o nó ond o boneco está e adiciona no começo da lista
        Node noInicial = new Node(inicio, null, 0, heurEuclidean(inicio, treasureLoc), 0);
        aberto.add(noInicial);

        // Enquanto houver nós na lista aberta
        while (!aberto.isEmpty()) {
            // Encontra o nó com menor valor de f na lista aberta
            Node noAtual = aberto.get(0);
            for (int i = 1; i < aberto.size(); i++) {
                if (aberto.get(i).f < noAtual.f) {
                    noAtual = aberto.get(i);
                }
            }
            // Remove o nó atual da lista aberta e adiciona à lista fechada
            aberto.remove(noAtual);
            fechado.add(noAtual);

            // Se o nó atual é o treasureLoc, reconstrói o caminho e retorna
            if (isTreasure(treasureLoc, noAtual)) {
                return reconstruirCaminho(noAtual);
            }

            // Gera os sucessores do nó atual
            List<Node> sucessores = gerarSucessores(noAtual, mapa);

            for (Node sucessor : sucessores) {
                // Se o sucessor está na lista fechada (ja foi visitado anteriormente)
                if (contemNode(fechado, sucessor)) {
                    continue;
                }

                int bioma = verificiarBioma(noAtual, mapa);
                int custo = 0;

                // Calcula o custo do caminho até o sucessor com base no bioma atual
                if (bioma == 0) custo = 1;
                else if (bioma == 1) custo = 10;
                else if (bioma == 2) custo = 4;
                else if (bioma == 3) custo = 20;
                double novoG = noAtual.g + custo; // Supõe que o custo de movimento entre células adjacentes é 1

                // Se o sucessor não está na lista aberta ou o novo custo é menor que o custo anterior
                if (!contemNode(aberto, sucessor) || novoG < sucessor.g) {
                    sucessor.g = novoG;
                    sucessor.h = heurEuclidean(sucessor.posicao, treasureLoc);
                    sucessor.f = sucessor.g + sucessor.h;
                    sucessor.pai = noAtual;

                    // Adiciona o sucessor à lista aberta se não estiver presente
                    if (!contemNode(aberto, sucessor)) {
                        aberto.add(sucessor);
                    }
                }
            }
        }

        // Se nenhum caminho foi encontrado, retorna null
        return null;
    }

    public double heurEuclidean(int[] inicio, int[][] treasureLoc) {
        double h;
        double lower = 999999;
        for (int i = 0; i < treasureLoc.length; i++) {
            h = Math.sqrt(Math.pow(inicio[0] - treasureLoc[i][0], 2) + Math.pow(inicio[1] - treasureLoc[i][1], 2));
            if (h < lower) lower = h;
        }
        return lower;
    }

    public List<Node> reconstruirCaminho(Node no) {
        List<Node> caminho = new ArrayList<>();
        while (no != null) {
            caminho.add(0, no);
            no = no.pai;
        }
        return caminho;
    }

    public boolean contemNode(List<Node> lista, Node node) {
        for (Node n : lista) {
            if (Arrays.equals(n.posicao, node.posicao)) {
                return true;
            }
        }
        return false;
    }

    public List<Node> gerarSucessores(Node no, char[][] mapa) {
        List<Node> sucessores = new ArrayList<>();

        //CIMA (i-1, j)
        //BAIXO (i+1, j)
        //DIREITA (i, j+1)
        //ESQUERDA (i, j-1)

        int[][] direcoes = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] direcao : direcoes) {
            int x = no.posicao[0] + direcao[0];
            int y = no.posicao[1] + direcao[1];

            // Verifica se o sucessor não é uma parede
            if (mapa[x][y] != '▓') {
                Node sucessor = new Node(new int[]{x, y}, null, 0, 0, 0);
                sucessores.add(sucessor);
                explorados.add(sucessor);
            }
        }

        return sucessores;
    }
}
