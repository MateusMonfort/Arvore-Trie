public class ArvoreTrie {
    
    Node raiz;
    
    public ArvoreTrie() {
        this.raiz = new Node();
    }
    
    public void adicionar(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return;
        }
        adicionarRecursivo(raiz, palavra.toLowerCase(), 0);
    }
    
    private void adicionarRecursivo(Node no, String palavra, int indice) {
        if (indice == palavra.length()) {
            no.fimPalavra = true;
            return;
        }
        
        int letraIndice = palavra.charAt(indice) - 'a';
        
        if (no.filhos[letraIndice] == null) {
            no.filhos[letraIndice] = new Node();
        }
        
        adicionarRecursivo(no.filhos[letraIndice], palavra, indice + 1);
    }
    
    public boolean procurar(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return false;
        }
        return procurarRecursivo(raiz, palavra.toLowerCase(), 0);
    }
    
    private boolean procurarRecursivo(Node no, String palavra, int indice) {
        if (no == null) {
            return false;
        }
        
        if (indice == palavra.length()) {
            return no.fimPalavra;
        }
        
        int letraIndice = palavra.charAt(indice) - 'a';
        return procurarRecursivo(no.filhos[letraIndice], palavra, indice + 1);
    }
    
    public void remover(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            System.out.println("Palavra inválida");
            return;
        }
        
        if (procurar(palavra)) {
            removerRecursivo(raiz, palavra.toLowerCase(), 0);
            System.out.println("Palavra '" + palavra + "' removida com sucesso");
        } else {
            System.out.println("Palavra '" + palavra + "' não foi encontrada");
        }
    }
    private boolean removerRecursivo(Node no, String palavra, int indice) {
        if (indice == palavra.length()) {
            if (!no.fimPalavra) {
                return false;
            }
            
            no.fimPalavra = false;
            return !no.temFilhos();
        }
        
        int letraIndice = palavra.charAt(indice) - 'a';
        Node filho = no.filhos[letraIndice];
        
        if (filho == null) {
            return false;
        }
        
        boolean deveRemoverFilho = removerRecursivo(filho, palavra, indice + 1);
        
        if (deveRemoverFilho) {
            no.filhos[letraIndice] = null;
            return !no.fimPalavra && !no.temFilhos();
        }
        return false;
    }
    
    public String[] autocompletar(String prefixo) {
        int total = contarPalavrasComPrefixo(prefixo);
        
        if (total == 0) {
            return new String[0];
        }
        
        String[] resultados = new String[total];
        int[] contador = {0};
        
        if (prefixo == null || prefixo.isEmpty()) {
            coletarPalavras(raiz, "", resultados, contador);
        } else {
            Node noPrefixo = buscarNoPrefixo(raiz, prefixo.toLowerCase(), 0);
            
            if (noPrefixo != null) {
                coletarPalavras(noPrefixo, prefixo.toLowerCase(), resultados, contador);
            }
        }
        
        return resultados;
    }
    
    private Node buscarNoPrefixo(Node no, String prefixo, int indice) {
        if (no == null) {
            return null;
        }
        
        if (indice == prefixo.length()) {
            return no;
        }
        
        int letraIndice = prefixo.charAt(indice) - 'a';
        return buscarNoPrefixo(no.filhos[letraIndice], prefixo, indice + 1);
    }
    
    private void coletarPalavras(Node no, String prefixo, String[] resultados, int[] contador) {
        if (no.fimPalavra) {
            resultados[contador[0]] = prefixo;
            contador[0]++;
        }
        
        for (int i = 0; i < 26; i++) {
            if (no.filhos[i] != null) {
                char proximaLetra = (char) ('a' + i);
                coletarPalavras(no.filhos[i], prefixo + proximaLetra, resultados, contador);
            }
        }
    }
    
    private int contarPalavrasComPrefixo(String prefixo) {
        if (prefixo == null || prefixo.isEmpty()) {
            return contarPalavras();
        }
        
        Node noPrefixo = buscarNoPrefixo(raiz, prefixo.toLowerCase(), 0);
        if (noPrefixo == null) {
            return 0;
        }
        
        return contarPalavrasRecursivo(noPrefixo);
    }
    
    private int contarPalavrasRecursivo(Node no) {
        if (no == null) {
            return 0;
        }
        
        int count = 0;
        if (no.fimPalavra) {
            count = 1;  
        }
        
        for (int i = 0; i < 26; i++) {
            if (no.filhos[i] != null) {
                count += contarPalavrasRecursivo(no.filhos[i]);
            }
        }
        
        return count;
    }
    
    public int contarPalavras() {
        return contarPalavrasRecursivo(raiz);
    }
    
    public void imprimirPalavras() {
        String[] todasPalavras = autocompletar("");
        for (int i = 0; i < todasPalavras.length; i++) {
            System.out.println(todasPalavras[i]);
        }
    }
    
    // Métodos para demonstração em vídeo
    public void demonstrarBusca(String palavra) {
        boolean encontrou = procurar(palavra);
        String status = encontrou ? "ENCONTRADA" : "NÃO ENCONTRADA";
        System.out.println("Buscar '" + palavra.toUpperCase() + "': " + status);
    }
    
    public void demonstrarAutocompletar(String prefixo) {
        String[] sugestoes = autocompletar(prefixo);
        String prefixoDisplay = prefixo.isEmpty() ? "(vazio)" : "'" + prefixo.toUpperCase() + "'";
        System.out.println("Autocompletar " + prefixoDisplay + ": " + java.util.Arrays.toString(sugestoes));
    }
    
    public void imprimirPalavrasTrie() {
        String[] palavras = autocompletar("");
        System.out.print("Palavras na Trie: ");
        if (palavras.length == 0) {
            System.out.println("(nenhuma palavra)");
        } else {
            for (int i = 0; i < palavras.length; i++) {
                System.out.print(palavras[i].toUpperCase());
                if (i < palavras.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
