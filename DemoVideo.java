public class DemoVideo {
    public static void main(String[] args) {
        System.out.println("DEMONSTRAÇÃO ÁRVORE TRIE - VÍDEO TDE");
        System.out.println("=" .repeat(50));
        
        ArvoreTrie trie = new ArvoreTrie();
        
        // PARTE 1: INSERÇÃO
        System.out.println("\nPARTE 1: INSERÇÃO");
        System.out.println("-".repeat(30));
        
        System.out.println("Inserindo palavras: CAT, CAR, CARD, DOG");
        trie.adicionar("cat");
        System.out.println("Inserido: CAT");
        
        trie.adicionar("car");  
        System.out.println("Inserido: CAR");
        
        trie.adicionar("card");
        System.out.println("Inserido: CARD");
        
        trie.adicionar("dog");
        System.out.println("Inserido: DOG");
        
        System.out.println("\nTotal de palavras: " + trie.contarPalavras());
        
        // PARTE 2: BUSCA
        System.out.println("\nPARTE 2: BUSCA");
        System.out.println("-".repeat(30));
        
        demonstrarBusca(trie, "car");
        demonstrarBusca(trie, "ca");
        demonstrarBusca(trie, "card");
        demonstrarBusca(trie, "dog");
        demonstrarBusca(trie, "cat");
        demonstrarBusca(trie, "hello");
        
        // PARTE 3: AUTOCOMPLETAR
        System.out.println("\nPARTE 3: AUTOCOMPLETAR");
        System.out.println("-".repeat(30));
        
        demonstrarAutocompletar(trie, "ca");
        demonstrarAutocompletar(trie, "car");
        demonstrarAutocompletar(trie, "d");
        
        // PARTE 4: REMOÇÃO
        System.out.println("\nPARTE 4: REMOÇÃO");
        System.out.println("-".repeat(30));
        
        System.out.println("Removendo palavra 'CAR':");
        trie.remover("car");
        
        System.out.println("Verificando após remoção:");
        demonstrarBusca(trie, "car");
        demonstrarBusca(trie, "card");
        
        System.out.println("Autocompletar 'ca' após remoção:");
        demonstrarAutocompletar(trie, "ca");
        
        // PARTE 5: APLICAÇÃO PRÁTICA
        System.out.println("\nPARTE 5: APLICAÇÃO PRÁTICA - DICIONÁRIO");
        System.out.println("-".repeat(30));
        
        ArvoreTrie dicionario = new ArvoreTrie();
        System.out.println("Criando dicionário de programação:");
        
        String[] palavras = {"java", "javascript", "python", "programming", 
                           "program", "computer", "code", "coding"};
        
        for (int i = 0; i < palavras.length; i++) {
            String palavra = palavras[i];
            dicionario.adicionar(palavra);
            System.out.println("+ " + palavra);
        }
        
        System.out.println("\nSistema de busca:");
        System.out.println("Buscar 'java': " + dicionario.procurar("java"));
        System.out.println("Buscar 'code': " + dicionario.procurar("code"));
        
        System.out.println("\nSugestões automáticas:");
        System.out.print("Digite 'prog' -> ");
        System.out.println(java.util.Arrays.toString(dicionario.autocompletar("prog")));
        
        System.out.print("Digite 'java' -> ");
        System.out.println(java.util.Arrays.toString(dicionario.autocompletar("java")));
        
        System.out.print("Digite 'cod' -> ");
        System.out.println(java.util.Arrays.toString(dicionario.autocompletar("cod")));
        
        // ESTATÍSTICAS FINAIS
        System.out.println("\nESTATÍSTICAS FINAIS");
        System.out.println("-".repeat(30));
        System.out.println("Total de palavras no dicionário: " + dicionario.contarPalavras());
        System.out.println("Todas as palavras:");
        dicionario.imprimirPalavras();
        
        System.out.println("\nDEMONSTRAÇÃO CONCLUÍDA");
        System.out.println("Árvore Trie implementada com sucesso!");
    }
    
    private static void demonstrarBusca(ArvoreTrie trie, String palavra) {
        boolean encontrou = trie.procurar(palavra);
        String status = encontrou ? "ENCONTRADA" : "NÃO ENCONTRADA";
        System.out.println("Buscar '" + palavra + "': " + status);
    }
    
    private static void demonstrarAutocompletar(ArvoreTrie trie, String prefixo) {
        String[] sugestoes = trie.autocompletar(prefixo);
        System.out.println("Autocompletar '" + prefixo + "': " + java.util.Arrays.toString(sugestoes));
    }
}
