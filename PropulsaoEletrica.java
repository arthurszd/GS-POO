/**
 * Propulsão Elétrica (motor iônico).
 * Herda de SistemaPropulsao com comportamento de aceleração totalmente diferente
 * da Propulsão Química — demonstra polimorfismo via herança.
 *
 * Motores iônicos produzem empuxo baixo, mas extremamente eficiente a longo prazo.
 */
public class PropulsaoEletrica extends SistemaPropulsao {

    // Atributos ESPECÍFICOS desta subclasse
    private double cargaBateria;        // 0 a 100 (%)
    private double eficiencia;          // fator de eficiência do motor iônico
    private double consumoEnergetico;   // % de bateria por % de potência

    // Empuxo máximo de motor iônico é muito menor que o químico
    private static final double EMPUXO_MAXIMO = 5_000.0;

    public PropulsaoEletrica(String id) {
        super(id, "Propulsão Elétrica"); // super() chamando construtor da mãe
        this.cargaBateria      = 100.0;
        this.eficiencia        = 0.92; // 92% de eficiência elétrica
        this.consumoEnergetico = 0.1;  // 0.1% de bateria por % de potência
    }

    // ----------------------------------------------------------------
    // Implementação do método abstrato acelerar()
    // (comportamento DIFERENTE da Propulsão Química — polimorfismo)
    // ----------------------------------------------------------------

    @Override
    public void acelerar(int porcentagem) {
        if (!isMotorLigado()) {
            System.out.println("[Propulsão Elétrica] ERRO: Ligue o motor antes de acelerar!");
            return;
        }
        if (porcentagem < 0 || porcentagem > 100) {
            System.out.println("ERRO: Potência deve ser entre 0 e 100%.");
            return;
        }
        if (cargaBateria <= 5.0) {
            System.out.println("[Propulsão Elétrica] ERRO: Bateria insuficiente (< 5%)!");
            return;
        }

        setPotenciaAtual(porcentagem);
        double empuxo = calcularEmpuxo();
        setEmpuxoGerado(empuxo);

        // Consome bateria proporcionalmente à potência
        double consumo = consumoEnergetico * porcentagem;
        cargaBateria = Math.max(0, cargaBateria - consumo);

        System.out.println("[Propulsão Elétrica] Acelerando a " + porcentagem + "% de potência.");
        System.out.printf ("[Propulsão Elétrica] Empuxo gerado: %.1f N (motor iônico)%n", empuxo);
        System.out.printf ("[Propulsão Elétrica] Bateria restante: %.1f%%%n", cargaBateria);
    }

    // ----------------------------------------------------------------
    // Cálculo de empuxo com fórmula diferente — eficiência elétrica
    // ----------------------------------------------------------------

    @Override
    public double calcularEmpuxo() {
        return EMPUXO_MAXIMO * (getPotenciaAtual() / 100.0) * eficiencia;
    }

    // ----------------------------------------------------------------
    // Sobrescreve exibirDetalhes() adicionando info de bateria
    // ----------------------------------------------------------------

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes(); // chama exibirDetalhes() da classe mãe com super()
        System.out.printf ("Bateria    : %.1f%%%n", cargaBateria);
        System.out.printf ("Eficiência : %.0f%%%n", eficiencia * 100);
    }

    // Getters e setters específicos
    public double getCargaBateria() { return cargaBateria; }

    public void recarregarBateria(double porcentagem) {
        if (porcentagem > 0) {
            cargaBateria = Math.min(100.0, cargaBateria + porcentagem);
            System.out.printf("[Propulsão Elétrica] Bateria recarregada para %.1f%%%n", cargaBateria);
        }
    }
}
