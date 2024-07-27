import java.util.*;

public class RemoverSimbolosInalcancaveis {
    public void executar(Gramatica gramatica) {
        Set<String> alcançaveis = new HashSet<>();
        Queue<String> fila = new LinkedList<>();
        fila.add(gramatica.simboloInicial);
        alcançaveis.add(gramatica.simboloInicial);

        while (!fila.isEmpty()) {
            String atual = fila.poll();
            if (gramatica.regras.containsKey(atual)) {
                for (String producao : gramatica.regras.get(atual)) {
                    for (char simbolo : producao.toCharArray()) {
                        String simboloStr = String.valueOf(simbolo);
                        if (gramatica.naoTerminais.contains(simboloStr) && !alcançaveis.contains(simboloStr)) {
                            alcançaveis.add(simboloStr);
                            fila.add(simboloStr);
                        }
                    }
                }
            }
        }

        gramatica.naoTerminais.retainAll(alcançaveis);
        gramatica.regras.keySet().retainAll(alcançaveis);
    }
}
