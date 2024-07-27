import java.util.*;

public class ConverterParaFormaBinaria {
    public void executar(Gramatica gramatica) {
        Map<String, List<String>> novasRegras = new HashMap<>();
        int indiceNovaVar = 1;

        for (Map.Entry<String, List<String>> entrada : gramatica.regras.entrySet()) {
            String naoTerminal = entrada.getKey();
            novasRegras.putIfAbsent(naoTerminal, new ArrayList<>());

            for (String producao : entrada.getValue()) {
                if (producao.length() > 2) {
                    String varAnterior = naoTerminal;
                    for (int i = 0; i < producao.length() - 2; i++) {
                        String novaVar = "X" + indiceNovaVar++;
                        novasRegras.putIfAbsent(varAnterior, new ArrayList<>());
                        novasRegras.get(varAnterior).add(producao.substring(i, i + 1) + novaVar);
                        varAnterior = novaVar;
                    }
                    novasRegras.putIfAbsent(varAnterior, new ArrayList<>());
                    novasRegras.get(varAnterior).add(producao.substring(producao.length() - 2));
                } else {
                    novasRegras.get(naoTerminal).add(producao);
                }
            }
        }
        gramatica.regras = novasRegras;
    }
}
