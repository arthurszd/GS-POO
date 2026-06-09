/**
 * Classe abstrata que representa um componente genérico de uma estação espacial.
 * Não pode ser instanciada diretamente — serve como molde para subclasses concretas.
 */
public abstract class ComponenteEspacial {

    // Atributos comuns a todos os componentes
    private String id;
    private String nome;
    private boolean status;       // true = ligado, false = desligado
    private double temperatura;   // em graus Celsius

    // Construtor
    public ComponenteEspacial(String id, String nome) {
        this.id = id;
        this.nome = nome;
        this.status = false;
        this.temperatura = 20.0;
    }

    // ----------------------------------------------------------------
    // Métodos CONCRETOS — implementados aqui, herdados pelas filhas
    // ----------------------------------------------------------------

    /** Liga o componente */
    public void ligar() {
        this.status = true;
        System.out.println("[" + nome + "] Componente LIGADO.");
    }

    /** Desliga o componente */
    public void desligar() {
        this.status = false;
        System.out.println("[" + nome + "] Componente DESLIGADO.");
    }

    /** Exibe informações básicas do componente */
    public void exibirStatus() {
        System.out.println("------------------------------------------");
        System.out.println("ID       : " + id);
        System.out.println("Nome     : " + nome);
        System.out.println("Status   : " + (status ? "LIGADO" : "DESLIGADO"));
        System.out.printf ("Temp.    : %.1f°C%n", temperatura);
        // Chama o método abstrato para exibir detalhes específicos da subclasse
        exibirDetalhes();
        System.out.println("------------------------------------------");
    }

    // ----------------------------------------------------------------
    // Método ABSTRATO — obriga todas as subclasses a implementar
    // ----------------------------------------------------------------

    /**
     * Exibe detalhes específicos do tipo de componente.
     * Cada subclasse concreta DEVE implementar este método.
     */
    public abstract void exibirDetalhes();

    // ----------------------------------------------------------------
    // Getters e Setters
    // ----------------------------------------------------------------

    public String getId() { return id; }
    public String getNome() { return nome; }
    public boolean isLigado() { return status; }

    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double temperatura) {
        if (temperatura >= -273.15) {  // zero absoluto como limite inferior
            this.temperatura = temperatura;
        } else {
            System.out.println("ERRO: Temperatura abaixo do zero absoluto.");
        }
    }
}
