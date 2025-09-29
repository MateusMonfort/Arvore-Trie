public class DemoVideo {
    public static void main(String[] args) { 
        ArvoreTrie trie = new ArvoreTrie();
        
        // PARTE 1: INSERÇÃO
        System.out.println("\nPARTE 1: INSERÇÃO");
        System.out.println("-".repeat(30));
        
        System.out.println("Inserindo palavras: CAT, CAR, CARD, DOG");
        
        trie.adicionar("cat");
        System.out.println("Inserido: CAT");
        
        trie.adicionar("car");  
        System.out.println("Inserido: CAR (compartilha prefixo 'CA' com CAT)");
        
        trie.adicionar("card");
        System.out.println("Inserido: CARD (compartilha prefixo 'CAR' com CAR)");
        
        trie.adicionar("dog");
        System.out.println("Inserido: DOG (nova ramificação)");
        
        System.out.println("\nTotal de palavras: " + trie.contarPalavras());
        trie.imprimirPalavrasTrie();
        
        // PARTE 2: BUSCA
        System.out.println("\nPARTE 2: BUSCA");
        System.out.println("-".repeat(30));
        
        System.out.println("Testando palavras que EXISTEM:");
        trie.demonstrarBusca("car");
        trie.demonstrarBusca("cat");
        trie.demonstrarBusca("card");
        trie.demonstrarBusca("dog");
        
        System.out.println("\nTestando palavras que NÃO EXISTEM:");
        trie.demonstrarBusca("ca");      // apenas prefixo
        trie.demonstrarBusca("cars");    // letra extra
        trie.demonstrarBusca("hello");   // palavra inexistente

        // PARTE 3: AUTOCOMPLETAR
        System.out.println("\nPARTE 3: AUTOCOMPLETAR");
        System.out.println("-".repeat(30));
        
        trie.demonstrarAutocompletar("ca");
        trie.demonstrarAutocompletar("d");
        trie.demonstrarAutocompletar("car");
        
        // PARTE 4: REMOÇÃO
        System.out.println("\nPARTE 4: REMOÇÃO");
        System.out.println("-".repeat(30));
        
        System.out.println("Estado antes da remoção:");
        trie.demonstrarBusca("car");
        trie.demonstrarBusca("card");
        
        System.out.println("\nRemovendo palavra 'CAR'...");
        trie.remover("car");
        System.out.println("CAR removido");
        
        System.out.println("\nEstado após a remoção:");
        trie.demonstrarBusca("car");
        trie.demonstrarBusca("card");
        
        System.out.println("\nAutocompletar 'ca' após remoção:");
        trie.demonstrarAutocompletar("ca");

        System.out.println("\nTotal de palavras: " + trie.contarPalavras());
        trie.imprimirPalavrasTrie();

    }
}
