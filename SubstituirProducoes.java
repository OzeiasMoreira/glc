import java.util.*;

public class SubstituirProducoes {
    public void executar(Gramatica gramatica) {
        boolean alterado;
        do {
            alterado = false;
            Map<String, List<String>> novasRegras = new HashMap<>();
            for (Map.Entry<String, List<String>> entrada : gramatica.regras.entrySet()) {
                String naoTerminal = entrada.getKey();
                novasRegras.putIfAbsent(naoTerminal, new ArrayList<>());

                for (String producao : entrada.getValue()) {
                    if (producao.length() == 1 && gramatica.naoTerminais.contains(producao)) {
                        novasRegras.get(naoTerminal).addAll(gramatica.regras.get(producao));
                        alterado = true;
                    } else {
                        novasRegras.get(naoTerminal).add(producao);
                    }
                }
            }
            gramatica.regras = novasRegras;
        } while (alterado);
    }
}
