import java.util.*;

class Node {
    int[] posicao;
    Node pai;
    double heuristica, heurCusto;
    int custo;

    public Node(int[] posicao, Node pai, double heuristica, double heurCusto, int custo) {
        this.posicao = posicao;
        this.pai = pai;
        this.heuristica = heuristica;
        this.heurCusto = heurCusto;
        this.custo = custo;
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
                {'▓', '0', '0', '0', '0', '0', '▓', '0', '0', '0', '0', '0', '▓', '0', '0', '▓', '0', '0', '0', '▓',},
                {'▓', '▓', '▓', '▓', '0', '0', '▓', '0', '0', '@', '0', '0', '▓', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '▓', '0', '0', '0', '0', '0', '0', '0', '0', '▓', '0', '0', '▓', '0', '▓', '▓', '▓',},
                {'▓', '0', '0', '▓', '3', '▓', '▓', '3', '▓', '▓', '▓', '▓', '▓', '0', '0', '▓', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '▓', '3', '▓', '0', '0', '0', '0', '0', '0', '▓', '0', '▓', '0', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '3', '▓', '▓', '▓', '0', '▓', '▓', '▓', '▓', '0', '▓', '0', '▓',},
                {'▓', '▓', '▓', '▓', '▓', '▓', '▓', '0', '0', '0', '0', '0', '▓', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '0', '▓', '0', '0', '0', '0', '▓', '0', '0', '▓', '0', '▓', '▓', '▓', '0', '▓', '▓',},
                {'▓', '0', '0', '0', '▓', '0', '0', '▓', '0', '▓', '0', '0', '▓', '0', '0', '0', '▓', '0', '▓', '▓',},
                {'▓', '0', '0', '▓', '▓', '0', '0', '▓', '0', '▓', '0', '0', '▓', '▓', '▓', '▓', '▓', '0', '▓', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '▓', '0', '▓', '0', '0', '0', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '▓', '▓', '▓', '▓', '▓', '▓', '0', '▓', '0', '0', '0', '0', '0', '▓', '▓', '▓', '0', '▓',},
                {'▓', '0', '0', '0', '0', '▓', '0', '0', '0', '▓', '▓', '▓', '▓', '▓', '0', '▓', '0', '▓', '0', '▓',},
                {'▓', '0', '0', '0', '0', '▓', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '▓', '0', '▓',},
                {'▓', '▓', '0', '▓', '▓', '▓', '▓', '0', '▓', '▓', '0', '0', '▓', '▓', '▓', '▓', '▓', '▓', '0', '▓',},
                {'▓', '▓', '0', '0', '0', '0', '0', '0', '0', '▓', '0', '0', '▓', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '0', '0', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '0', '0', '0', '0', '0', '▓', '▓', '▓', '▓', '▓',},
                {'▓', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '▓', '0', '0', '0', '0', '0', '0', '▓',},
                {'▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓', '▓',}};
        //12 8
        int[] inicio = {2, 9};
        int treasures = 1;
        int t = treasures;
        //int[][] treasureLoc = new int[t][2];
        int[][] treasureLoc = {{14, 9}};
        mapa[treasureLoc[0][0]][treasureLoc[0][1]] = '$';

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
    public boolean contemNode(List<Node> lista, Node node) {
        for (Node n : lista) {
            if (Arrays.equals(n.posicao, node.posicao)) {
                return true;
            }
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

    public int getCusto(int i, int j, char[][] mapa) {
        int bioma = Character.getNumericValue(mapa[i][j]);
        if (bioma == 0) return 1;
        else if (bioma == 1) return 10;
        else if (bioma == 2) return 4;
        else if (bioma == 3) return 20;
        return 1;
    }

    public List<Node> encontrarCaminho(char[][] mapa, int[] inicio, int[][] treasureLoc) {
        //aberta: Esta lista contém os nós descobertos, mas ainda não foram explorados completamente. Isso significa que esses nós foram considerados como possíveis candidatos para fazer parte do caminho ótimo
        List<Node> aberto = new ArrayList<>();
        //fechada: nós que já foram explorados completamente. Quando um nó é removido da lista aberta para ser explorado, ele é movido para a lista fechada.
        List<Node> fechado = new ArrayList<>();

        // Cria o nó ond o boneco está e adiciona no começo da lista
        int custo = getCusto(inicio[0], inicio[1], mapa);

        Node noInicial = new Node(inicio, null, 0, heurEuclidean(inicio, treasureLoc), custo);
        aberto.add(noInicial);

        // Enquanto houver nós na lista aberta
        while (!aberto.isEmpty()) {

            // Encontra o nó com menor valor de f na lista aberta
            Node noAtual = aberto.get(0);
            for (int i = 1; i < aberto.size(); i++) {
                if (aberto.get(i).heurCusto < noAtual.heurCusto) {
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
            System.out.println("{" + noAtual.posicao[0] + "," +noAtual.posicao[1] + "}" );

            // Gera os sucessores do nó atual
            List<Node> sucessores = gerarSucessores(noAtual, mapa);

            for (Node sucessor : sucessores) {
                // Se o sucessor está na lista fechada (ja foi visitado anteriormente)
                if (contemNode(fechado, sucessor)) {
                    continue;
                }

                custo = getCusto(noAtual.posicao[0],noAtual.posicao[1], mapa);

                int novoG = noAtual.custo + custo;

                // Se o sucessor não está na lista aberta ou o novo custo é menor que o custo anterior
                if (!contemNode(aberto, sucessor) || novoG < sucessor.custo) {
                    sucessor.custo = novoG;
                    sucessor.heuristica = heurEuclidean(sucessor.posicao, treasureLoc);
                    sucessor.heurCusto = sucessor.custo + sucessor.heuristica;
                    sucessor.pai = noAtual;

                    // Adiciona o sucessor à lista aberta se não estiver presente
                    if (!contemNode(aberto, sucessor)) {
                        aberto.add(sucessor);
                    }
                }
            }
        }
        System.out.println("nenhum caminho encontrado");
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

    public List<Node> gerarSucessores(Node nodeAtual, char[][] mapa) {
        List<Node> sucessores = new ArrayList<>();

        int[][] direcoes = {
                {1, 0}, //BAIXO (i+1, j)
                {-1, 0},  //CIMA (i-1, j)
                {0, 1}, //DIREITA (i, j+1)
                {0, -1}}; //ESQUERDA (i, j-1)

        for (int[] direcao : direcoes) {
            int i = nodeAtual.posicao[0] + direcao[0];
            int j = nodeAtual.posicao[1] + direcao[1];

            // Verifica se o sucessor não é uma parede
            if (mapa[i][j] != '▓') {
                int custo = getCusto(i,j,mapa);
                Node sucessor = new Node(new int[]{i, j}, nodeAtual, 0, 0, custo);
                sucessores.add(sucessor);
                explorados.add(sucessor);
            }
        }

        return sucessores;
    }
}
