import java.util.*;

public class RemoverProducoesUnitarias {
    public void executar(Gramatica gramatica) {
        boolean mudou;
        do {
            mudou = false;
            Map<String, List<String>> novasRegras = new HashMap<>();
            for (Map.Entry<String, List<String>> entrada : gramatica.regras.entrySet()) {
                String naoTerminal = entrada.getKey();
                novasRegras.putIfAbsent(naoTerminal, new ArrayList<>());

                for (String producao : entrada.getValue()) {
                    if (producao.length() == 1 && gramatica.naoTerminais.contains(producao)) {
                        String naoTerminalDestino = producao;
                        if (gramatica.regras.containsKey(naoTerminalDestino)) {
                            novasRegras.get(naoTerminal).addAll(gramatica.regras.get(naoTerminalDestino));
                            mudou = true;
                        } else {
                            novasRegras.get(naoTerminal).add(producao);
                        }
                    } else {
                        novasRegras.get(naoTerminal).add(producao);
                    }
                }
            }
            gramatica.regras = novasRegras;
        } while (mudou);
    }
}
