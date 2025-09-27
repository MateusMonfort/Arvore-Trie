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
        System.out.println("Procurar 'car': " + trie.procurar("car"));
        System.out.println("Procurar 'care': " + trie.procurar("care"));
        System.out.println("Procurar 'ca': " + trie.procurar("ca"));
        System.out.println("Procurar 'borg': " + trie.procurar("borg"));
        
        System.out.println("\n=== Teste de Autocompletar ===");
        String[] sugestoesCar = trie.autocompletar("car");
        System.out.println("Autocompletar 'car': " + java.util.Arrays.toString(sugestoesCar));
        
        String[] sugestoesCa = trie.autocompletar("ca");
        System.out.println("Autocompletar 'ca': " + java.util.Arrays.toString(sugestoesCa));
        
        String[] sugestoesDo = trie.autocompletar("do");
        System.out.println("Autocompletar 'do': " + java.util.Arrays.toString(sugestoesDo));
        
        System.out.println("\n=== Teste de Remoção ===");
        System.out.println("Removendo 'car'...");
        trie.remover("car");
        System.out.println("Removendo 'hello' (palavra inexistente)...");
        trie.remover("hello");
        
        System.out.println("\nApós remoções:");
        System.out.println("Procurar 'car': " + trie.procurar("car"));
        System.out.println("Procurar 'card': " + trie.procurar("card"));
        System.out.println("Autocompletar 'car': " + java.util.Arrays.toString(trie.autocompletar("car")));
        
        System.out.println("\n=== Todas as Palavras Restantes ===");
        trie.imprimirPalavras();
        
        System.out.println("\nTotal final de palavras: " + trie.contarPalavras());
        
        System.out.println("\n=== Demonstração Adicional ===");
        demonstracaoVideo();
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
        
        boolean encontrada = trie.procurar(palavra);
        if (encontrada) {
            System.out.println("Palavra '" + palavra + "' encontrada na Trie!");
        } else {
            System.out.println("Palavra '" + palavra + "' NÃO encontrada na Trie!");
        }
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
        System.out.println("'prog' -> " + java.util.Arrays.toString(trie.autocompletar("prog")));
        System.out.println("'java' -> " + java.util.Arrays.toString(trie.autocompletar("java")));
        System.out.println("'cod' -> " + java.util.Arrays.toString(trie.autocompletar("cod")));
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
    
    public static void demonstracaoVideo() {
        ArvoreTrie demo = new ArvoreTrie();
        
        System.out.println("\nDemonstração Passo-a-Passo:");
        
        System.out.println("\n1. Trie vazia:");
        System.out.println("   Total de palavras: " + demo.contarPalavras());
        
        System.out.println("\n2. Inserindo 'CAT':");
        demo.adicionar("cat");
        System.out.println("   Palavras: " + java.util.Arrays.toString(demo.autocompletar("")));
        System.out.println("   Procurar 'cat': " + demo.procurar("cat"));
        
        System.out.println("\n3. Inserindo 'CAR':");
        demo.adicionar("car");
        System.out.println("   Palavras: " + java.util.Arrays.toString(demo.autocompletar("")));
        System.out.println("   Autocompletar 'ca': " + java.util.Arrays.toString(demo.autocompletar("ca")));
        
        System.out.println("\n4. Inserindo 'CARD':");
        demo.adicionar("card");
        System.out.println("   Autocompletar 'car': " + java.util.Arrays.toString(demo.autocompletar("car")));
        
        System.out.println("\n5. Aplicação prática - Sistema de busca:");
        demo.adicionar("java");
        demo.adicionar("javascript");
        demo.adicionar("python");
        demo.adicionar("programming");
        
        System.out.println("   Buscar 'java': " + java.util.Arrays.toString(demo.autocompletar("java")));
        System.out.println("   Buscar 'prog': " + java.util.Arrays.toString(demo.autocompletar("prog")));
        
        System.out.println("\n6. Total final de palavras: " + demo.contarPalavras());
    }
}
