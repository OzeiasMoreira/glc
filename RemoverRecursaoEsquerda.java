import java.util.*;

public class RemoverRecursaoEsquerda {
    public void executar(Gramatica gramatica) {
        List<String> listaNaoTerminais = new ArrayList<>(gramatica.naoTerminais);
        for (int i = 0; i < listaNaoTerminais.size(); i++) {
            String Ai = listaNaoTerminais.get(i);
            for (int j = 0; j < i; j++) {
                String Aj = listaNaoTerminais.get(j);
                List<String> novasProducoes = new ArrayList<>();
                for (String producao : gramatica.regras.get(Ai)) {
                    if (producao.startsWith(Aj)) {
                        String beta = producao.substring(Aj.length());
                        for (String gamma : gramatica.regras.get(Aj)) {
                            novasProducoes.add(gamma + beta);
                        }
                    } else {
                        novasProducoes.add(producao);
                    }
                }
                gramatica.regras.put(Ai, novasProducoes);
            }
            eliminarRecursaoEsquerdaImediata(Ai, gramatica);
        }
    }

    private void eliminarRecursaoEsquerdaImediata(String naoTerminal, Gramatica gramatica) {
        List<String> alfa = new ArrayList<>();
        List<String> beta = new ArrayList<>();
        for (String producao : gramatica.regras.get(naoTerminal)) {
            if (producao.startsWith(naoTerminal)) {
                alfa.add(producao.substring(naoTerminal.length()));
            } else {
                beta.add(producao);
            }
        }

        if (!alfa.isEmpty()) {
            String novoNaoTerminal = naoTerminal + "'";
            gramatica.regras.put(novoNaoTerminal, new ArrayList<>());
            for (String b : beta) {
                gramatica.regras.get(naoTerminal).add(b + novoNaoTerminal);
            }
            for (String a : alfa) {
                gramatica.regras.get(novoNaoTerminal).add(a + novoNaoTerminal);
            }
            gramatica.regras.get(novoNaoTerminal).add(""); // Adicionar produção epsilon
        }
    }
}
