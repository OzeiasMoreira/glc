public class Principal {
    public static void main(String[] args) {
        Gramatica gramatica = new Gramatica();
        gramatica.definirSimboloInicial("S");
        gramatica.adicionarRegra("S", "aAa");
        gramatica.adicionarRegra("S", "bBv");
        gramatica.adicionarRegra("A", "a");
        gramatica.adicionarRegra("A", "aA");

        System.out.println("Gramática Original:");
        System.out.println(gramatica);

        gramatica.simplificar();
        System.out.println("Gramática Simplificada:");
        System.out.println(gramatica);

        gramatica.converterParaFormaNormalChomsky();
        System.out.println("Forma Normal de Chomsky:");
        System.out.println(gramatica);

        gramatica.converterParaFormaNormalGreibach();
        System.out.println("Forma Normal de Greibach:");
        System.out.println(gramatica);

        gramatica.fatorarEsquerda();
        System.out.println("Gramática Fatorada à Esquerda:");
        System.out.println(gramatica);

        gramatica.removerRecursaoEsquerda();
        System.out.println("Recursão à Esquerda Removida:");
        System.out.println(gramatica);
    }
}

