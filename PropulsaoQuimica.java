/**
 * Propulsão Química.
 * Herda de SistemaPropulsao e implementa o comportamento específico
 * de motores de combustão química (como foguetes convencionais).
 *
 * Usa super() para chamar construtores e métodos da classe mãe.
 */
public class PropulsaoQuimica extends SistemaPropulsao {

    // Atributos ESPECÍFICOS desta subclasse
    private String tipoCombustivel;
    private double nivelCombustivel; // litros restantes
    private double consumoPorSegundo; // litros/s por % de potência

    // Constante de empuxo máximo para propulsão química (Newtons)
    private static final double EMPUXO_MAXIMO = 500_000.0;

    public PropulsaoQuimica(String id, String tipoCombustivel, double nivelCombustivel) {
        super(id, "Propulsão Química"); // chama o construtor da classe mãe com super()
        this.tipoCombustivel   = tipoCombustivel;
        this.nivelCombustivel  = nivelCombustivel;
        this.consumoPorSegundo = 2.5; // litros por segundo por 1% de potência
    }

    // ----------------------------------------------------------------
    // Implementação do método abstrato acelerar()
    // ----------------------------------------------------------------

    @Override
    public void acelerar(int porcentagem) {
        if (!isMotorLigado()) {
            System.out.println("[Propulsão Química] ERRO: Ligue o motor antes de acelerar!");
            return;
        }
        if (porcentagem < 0 || porcentagem > 100) {
            System.out.println("ERRO: Potência deve ser entre 0 e 100%.");
            return;
        }
        if (nivelCombustivel <= 0) {
            System.out.println("[Propulsão Química] ERRO: Sem combustível!");
            return;
        }

        setPotenciaAtual(porcentagem);
        double empuxo = calcularEmpuxo();
        setEmpuxoGerado(empuxo);

        // Consome combustível proporcional à potência
        double consumo = consumoPorSegundo * porcentagem;
        nivelCombustivel = Math.max(0, nivelCombustivel - consumo);

        System.out.println("[Propulsão Química] Acelerando a " + porcentagem + "% de potência.");
        System.out.printf ("[Propulsão Química] Empuxo gerado: %.1f N%n", empuxo);
        System.out.printf ("[Propulsão Química] Combustível restante: %.1f L%n", nivelCombustivel);
    }

    // ----------------------------------------------------------------
    // Implementação do cálculo de empuxo específico para química
    // ----------------------------------------------------------------

    @Override
    public double calcularEmpuxo() {
        // Empuxo químico cresce de forma não-linear (curva de potência)
        return EMPUXO_MAXIMO * Math.pow(getPotenciaAtual() / 100.0, 1.2);
    }

    // ----------------------------------------------------------------
    // Sobrescreve exibirDetalhes() para adicionar info de combustível
    // ----------------------------------------------------------------

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes(); // chama o método da classe mãe com super()
        System.out.println("Combustível : " + tipoCombustivel);
        System.out.printf ("Nível       : %.1f L%n", nivelCombustivel);
    }

    // Getters e setters específicos
    public String getTipoCombustivel() { return tipoCombustivel; }
    public double getNivelCombustivel() { return nivelCombustivel; }

    public void reabastece(double litros) {
        if (litros > 0) {
            nivelCombustivel += litros;
            System.out.printf("[Propulsão Química] Reabastecido: +%.1f L (total: %.1f L)%n",
                    litros, nivelCombustivel);
        }
    }
}
