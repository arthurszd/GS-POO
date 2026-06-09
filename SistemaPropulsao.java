/**
 * Classe abstrata SistemaPropulsao.
 * Define atributos e métodos comuns a todos os tipos de propulsão.
 * Subclasses DEVEM implementar o método abstrato acelerar().
 */
public abstract class SistemaPropulsao extends ComponenteEspacial {

    private double potenciaAtual;     // 0 a 100 (%)
    private double empuxoGerado;      // em Newtons
    private boolean motorLigado;

    public SistemaPropulsao(String id, String nome) {
        super(id, nome);
        this.potenciaAtual = 0.0;
        this.empuxoGerado  = 0.0;
        this.motorLigado   = false;
    }

    // ----------------------------------------------------------------
    // Métodos CONCRETOS — comuns a todos os tipos de propulsão
    // ----------------------------------------------------------------

    /** Liga o motor de propulsão */
    public void ligarMotor() {
        this.motorLigado = true;
        ligar(); // chama o método da classe avó ComponenteEspacial
        System.out.println("[" + getNome() + "] Motor de propulsão LIGADO.");
    }

    /** Desliga o motor e zera a potência */
    public void desligarMotor() {
        this.motorLigado = false;
        this.potenciaAtual = 0.0;
        this.empuxoGerado  = 0.0;
        desligar(); // chama o método da classe avó ComponenteEspacial
        System.out.println("[" + getNome() + "] Motor de propulsão DESLIGADO.");
    }

    /**
     * Método ABSTRATO — cada tipo de propulsão acelera de forma diferente.
     * Subclasses DEVEM sobrescrever este método.
     * @param porcentagem potência desejada (0 a 100)
     */
    public abstract void acelerar(int porcentagem);

    /**
     * Calcula e retorna o empuxo gerado com base na potência atual.
     * Cada subclasse pode sobrescrever para ter cálculo próprio.
     */
    public abstract double calcularEmpuxo();

    // ----------------------------------------------------------------
    // Implementação do método abstrato de ComponenteEspacial
    // ----------------------------------------------------------------

    @Override
    public void exibirDetalhes() {
        System.out.println("Motor    : " + (motorLigado ? "LIGADO" : "DESLIGADO"));
        System.out.printf ("Potência : %.1f%%%n", potenciaAtual);
        System.out.printf ("Empuxo   : %.1f N%n", empuxoGerado);
    }

    // ----------------------------------------------------------------
    // Getters e Setters com validação
    // ----------------------------------------------------------------

    public double getPotenciaAtual() { return potenciaAtual; }

    public void setPotenciaAtual(double potencia) {
        if (potencia < 0 || potencia > 100) {
            System.out.println("ERRO: Potência deve estar entre 0 e 100.");
        } else {
            this.potenciaAtual = potencia;
        }
    }

    public double getEmpuxoGerado() { return empuxoGerado; }

    public void setEmpuxoGerado(double empuxo) {
        this.empuxoGerado = empuxo;
    }

    public boolean isMotorLigado() { return motorLigado; }
}
