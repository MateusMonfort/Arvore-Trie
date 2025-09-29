import java.util.Scanner;

public class Main {
    private static ArvoreTrie trie = new ArvoreTrie();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== ÁRVORE TRIE ===");
        System.out.println("Escolha o modo de operação:");
        System.out.println("1 - Modo Automático (testes predefinidos)");
        System.out.println("2 - Modo Manual (menu interativo)");
        System.out.print("Opção: ");
        
        int opcao = scanner.nextInt();
        scanner.nextLine();
        
        switch (opcao) {
            case 1:
                modoAutomatico();
                break;
            case 2:
                modoManual();
                break;
            default:
                System.out.println("Opção inválida! Executando modo automático...");
                modoAutomatico();
        }
        
        scanner.close();
    }
    
    private static void modoAutomatico() {
        System.out.println("\n=== MODO AUTOMÁTICO ===");
        
        System.out.println("\n=== Teste de Inserção ===");
        System.out.println("Adicionando palavras: cat, car, card, care, careful, cats, dog, dogs");
        trie.adicionar("cat");
        trie.adicionar("car");
        trie.adicionar("card");
        trie.adicionar("care");
        trie.adicionar("careful");
        trie.adicionar("cats");
        trie.adicionar("dog");
        trie.adicionar("dogs");
        
        System.out.println("Total de palavras inseridas: " + trie.contarPalavras());
        
        System.out.println("\n=== Teste de Busca ===");
        trie.demonstrarBusca("car");
        trie.demonstrarBusca("care");
        trie.demonstrarBusca("ca");
        trie.demonstrarBusca("borg");
        
        System.out.println("\n=== Teste de Autocompletar ===");
        trie.demonstrarAutocompletar("car");
        trie.demonstrarAutocompletar("ca");
        trie.demonstrarAutocompletar("do");
        
        System.out.println("\n=== Teste de Remoção ===");
        System.out.println("Removendo 'car'...");
        trie.remover("car");
        System.out.println("Removendo 'hello' (palavra inexistente)...");
        trie.remover("hello");
        
        System.out.println("\nApós remoções:");
        trie.demonstrarBusca("car");
        trie.demonstrarBusca("card");
        trie.demonstrarAutocompletar("car");
        
        System.out.println("\n=== Todas as Palavras Restantes ===");
        trie.imprimirPalavras();
        
        System.out.println("\nTotal final de palavras: " + trie.contarPalavras());
        
    }
    
    private static void modoManual() {
        System.out.println("\n=== MODO MANUAL ===");
        int opcao;
        
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    inserirPalavra();
                    break;
                case 2:
                    removerPalavra();
                    break;
                case 3:
                    buscarPalavra();
                    break;
                case 4:
                    autocompletar();
                    break;
                case 5:
                    System.out.println("\n=== Todas as Palavras ===");
                    trie.imprimirPalavras();
                    break;
                case 6:
                    mostrarInformacoes();
                    break;
                case 7:
                    testeRapido();
                    break;
                case 8:
                    limparTrie();
                    break;
                case 9:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            
            if (opcao != 9) {
                System.out.println("\nPressione Enter para continuar...");
                scanner.nextLine();
            }
            
        } while (opcao != 9);
    }
    
    private static void exibirMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1 - Inserir palavra");
        System.out.println("2 - Remover palavra");
        System.out.println("3 - Buscar palavra");
        System.out.println("4 - Autocompletar");
        System.out.println("5 - Exibir todas as palavras");
        System.out.println("6 - Informações da Trie");
        System.out.println("7 - Teste rápido (inserir várias palavras)");
        System.out.println("8 - Limpar Trie");
        System.out.println("9 - Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void inserirPalavra() {
        System.out.print("Digite a palavra a ser inserida: ");
        String palavra = scanner.nextLine().trim();
        
        if (palavra.isEmpty()) {
            System.out.println("Palavra não pode ser vazia!");
            return;
        }
        
        if (!palavra.matches("[a-zA-Z]+")) {
            System.out.println("Palavra deve conter apenas letras!");
            return;
        }
        
        trie.adicionar(palavra);
        System.out.println("Palavra '" + palavra + "' inserida com sucesso!");
    }
    
    private static void removerPalavra() {
        System.out.print("Digite a palavra a ser removida: ");
        String palavra = scanner.nextLine().trim();
        
        if (palavra.isEmpty()) {
            System.out.println("Palavra não pode ser vazia!");
            return;
        }
        
        trie.remover(palavra);
    }
    
    private static void buscarPalavra() {
        System.out.print("Digite a palavra a ser buscada: ");
        String palavra = scanner.nextLine().trim();
        
        if (palavra.isEmpty()) {
            System.out.println("Palavra não pode ser vazia!");
            return;
        }
        
        trie.demonstrarBusca(palavra);
    }
    
    private static void autocompletar() {
        System.out.print("Digite o prefixo para autocompletar: ");
        String prefixo = scanner.nextLine().trim();
        
        String[] sugestoes = trie.autocompletar(prefixo);
        
        if (sugestoes.length == 0) {
            System.out.println("Nenhuma sugestão encontrada para '" + prefixo + "'");
        } else {
            System.out.println("Sugestões para '" + prefixo + "':");
            for (int i = 0; i < sugestoes.length; i++) {
                System.out.println("  " + (i + 1) + ". " + sugestoes[i]);
            }
            System.out.println("Total: " + sugestoes.length + " sugestões");
        }
    }
    
    private static void mostrarInformacoes() {
        System.out.println("\n=== INFORMAÇÕES DA TRIE ===");
        int totalPalavras = trie.contarPalavras();
        
        if (totalPalavras == 0) {
            System.out.println("Trie vazia: SIM");
        } else {
            System.out.println("Trie vazia: NÃO");
            System.out.println("Número total de palavras: " + totalPalavras);
            
            System.out.println("\nTodas as palavras armazenadas:");
            trie.imprimirPalavras();
        }
        System.out.println("===============================");
    }
    
    private static void testeRapido() {
        System.out.println("\n=== TESTE RÁPIDO ===");
        System.out.println("Inserindo conjunto de palavras de teste...");
        
        String[] palavrasTeste = {
            "java", "javascript", "python", "programming", "program",
            "computer", "code", "coding", "algorithm", "data",
            "structure", "tree", "trie", "search", "insert"
        };
        
        for (String palavra : palavrasTeste) {
            trie.adicionar(palavra);
            System.out.println("+ " + palavra);
        }
        
        System.out.println("\nTotal de " + palavrasTeste.length + " palavras inseridas!");
        System.out.println("Total atual na Trie: " + trie.contarPalavras() + " palavras");
        
        System.out.println("\nTeste de autocompletar:");
        trie.demonstrarAutocompletar("prog");
        trie.demonstrarAutocompletar("java");
        trie.demonstrarAutocompletar("cod");
    }
    
    private static void limparTrie() {
        System.out.print("Tem certeza que deseja limpar toda a Trie? (s/N): ");
        String confirmacao = scanner.nextLine().trim().toLowerCase();
        
        if (confirmacao.equals("s") || confirmacao.equals("sim")) {
            trie = new ArvoreTrie();
            System.out.println("Trie limpa com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }
    
}
