import java.util.*;

public class FatorarEsquerda {
    public void executar(Gramatica gramatica) {
        Map<String, List<String>> novasRegras = new HashMap<>();
        boolean fatorado;

        do {
            fatorado = false;
            for (Map.Entry<String, List<String>> entrada : gramatica.regras.entrySet()) {
                String naoTerminal = entrada.getKey();
                novasRegras.putIfAbsent(naoTerminal, new ArrayList<>());
                Map<String, List<String>> prefixosMap = new HashMap<>();

                for (String producao : entrada.getValue()) {
                    String prefixo = producao.substring(0, 1);
                    prefixosMap.putIfAbsent(prefixo, new ArrayList<>());
                    prefixosMap.get(prefixo).add(producao);
                }

                for (Map.Entry<String, List<String>> entradaPrefixo : prefixosMap.entrySet()) {
                    String prefixo = entradaPrefixo.getKey();
                    List<String> producoes = entradaPrefixo.getValue();

                    if (producoes.size() > 1) {
                        fatorado = true;
                        String novoNaoTerminal = naoTerminal + "_";
                        novasRegras.putIfAbsent(novoNaoTerminal, new ArrayList<>());

                        for (String producao : producoes) {
                            if (producao.length() > 1) {
                                novasRegras.get(novoNaoTerminal).add(producao.substring(1));
                            } else {
                                novasRegras.get(novoNaoTerminal).add("");
                            }
                        }
                        novasRegras.get(naoTerminal).add(prefixo + novoNaoTerminal);
                    } else {
                        novasRegras.get(naoTerminal).addAll(producoes);
                    }
                }
            }
            gramatica.regras = novasRegras;
        } while (fatorado);
    }
}
