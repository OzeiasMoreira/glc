import java.util.*;

public class RemoverProducoesVazias {
    public void executar(Gramatica gramatica) {
        Set<String> nulos = new HashSet<>();

        // Encontrar não-terminais nulos
        boolean adicionado;
        do {
            adicionado = false;
            for (Map.Entry<String, List<String>> entrada : gramatica.regras.entrySet()) {
                String naoTerminal = entrada.getKey();
                for (String producao : entrada.getValue()) {
                    if (producao.isEmpty() || producao.chars().allMatch(c -> nulos.contains(String.valueOf((char) c)))) {
                        if (nulos.add(naoTerminal)) {
                            adicionado = true;
                        }
                    }
                }
            }
        } while (adicionado);

        // Remover produções vazias e adicionar novas produções
        Map<String, List<String>> novasRegras = new HashMap<>();
        for (Map.Entry<String, List<String>> entrada : gramatica.regras.entrySet()) {
            String naoTerminal = entrada.getKey();
            novasRegras.putIfAbsent(naoTerminal, new ArrayList<>());

            for (String producao : entrada.getValue()) {
                Set<String> novasProducoes = new HashSet<>();
                novasProducoes.add(producao);

                for (int i = 0; i < producao.length(); i++) {
                    if (nulos.contains(String.valueOf(producao.charAt(i)))) {
                        Set<String> paraAdicionar = new HashSet<>();
                        for (String prod : novasProducoes) {
                            paraAdicionar.add(prod.substring(0, i) + prod.substring(i + 1));
                        }
                        novasProducoes.addAll(paraAdicionar);
                    }
                }
                novasRegras.get(naoTerminal).addAll(novasProducoes);
            }

            novasRegras.get(naoTerminal).removeIf(String::isEmpty);
        }

        gramatica.regras = novasRegras;
    }
}