import java.util.*;

public class Gramatica {
    Map<String, List<String>> regras;
    Set<String> naoTerminais;
    Set<String> terminais;
    String simboloInicial;

    public Gramatica() {
        this.regras = new HashMap<>();
        this.naoTerminais = new HashSet<>();
        this.terminais = new HashSet<>();
    }

    public void adicionarRegra(String naoTerminal, String producao) {
        this.regras.putIfAbsent(naoTerminal, new ArrayList<>());
        this.regras.get(naoTerminal).add(producao);
        this.naoTerminais.add(naoTerminal);
        for (char c : producao.toCharArray()) {
            if (Character.isLowerCase(c)) {
                this.terminais.add(String.valueOf(c));
            } else {
                this.naoTerminais.add(String.valueOf(c));
            }
        }
    }

    public void definirSimboloInicial(String simboloInicial) {
        this.simboloInicial = simboloInicial;
    }

    public void simplificar() {
        new RemoverSimbolosInuteis().executar(this);
        new RemoverProducoesVazias().executar(this);
        new RemoverProducoesUnitarias().executar(this);
        new SubstituirProducoes().executar(this);
    }

    public void converterParaFormaNormalChomsky() {
        new RemoverProducoesVazias().executar(this);
        new RemoverProducoesUnitarias().executar(this);
        new RemoverSimbolosInuteis().executar(this);
        new ConverterParaFormaBinaria().executar(this);
    }

    public void converterParaFormaNormalGreibach() {
        new RemoverSimbolosInuteis().executar(this);
        new ConverterParaFormaGreibach().executar(this);
    }

    public void fatorarEsquerda() {
        new FatorarEsquerda().executar(this);
    }

    public void removerRecursaoEsquerda() {
        new RemoverRecursaoEsquerda().executar(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entrada : regras.entrySet()) {
            sb.append(entrada.getKey()).append(" -> ");
            sb.append(String.join(" | ", entrada.getValue()));
            sb.append("\n");
        }
        return sb.toString();
    }
}
