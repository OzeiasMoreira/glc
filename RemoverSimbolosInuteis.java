import java.util.*;

public class RemoverSimbolosInuteis {
    public void executar(Gramatica gramatica) {
        Set<String> geradores = new HashSet<>();
        boolean adicionado;

        do {
            adicionado = false;
            for (Map.Entry<String, List<String>> entrada : gramatica.regras.entrySet()) {
                String naoTerminal = entrada.getKey();
                for (String producao : entrada.getValue()) {
                    boolean todosGeradores = true;
                    for (char simbolo : producao.toCharArray()) {
                        String simboloStr = String.valueOf(simbolo);
                        if (gramatica.naoTerminais.contains(simboloStr) && !geradores.contains(simboloStr)) {
                            todosGeradores = false;
                            break;
                        }
                    }
                    if (todosGeradores && geradores.add(naoTerminal)) {
                        adicionado = true;
                    }
                }
            }
        } while (adicionado);

        gramatica.naoTerminais.retainAll(geradores);
        gramatica.regras.keySet().retainAll(geradores);
    }
}
