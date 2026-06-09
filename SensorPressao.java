import java.util.Random;

/**
 * Sensor de Pressão.
 * Herda de ComponenteEspacial E implementa a interface Sensor.
 */
public class SensorPressao extends ComponenteEspacial implements Sensor {

    private double valorAtual; // em kPa (kilopascal)
    private double limiteMaximo;
    private Random random;

    // Limites de pressão interna da estação (kPa)
    private static final double LIMITE_ATENCAO = 110.0;
    private static final double LIMITE_ALERTA  = 120.0;
    private static final double LIMITE_CRITICO = 135.0;

    public SensorPressao(String id) {
        super(id, "Sensor de Pressão");
        this.limiteMaximo = LIMITE_ATENCAO;
        this.random = new Random();
        this.valorAtual = 101.3; // pressão atmosférica padrão
    }

    // ----------------------------------------------------------------
    // Implementação do contrato Sensor
    // ----------------------------------------------------------------

    @Override
    public double lerValor() {
        // Simula pressão entre 95 e 140 kPa
        valorAtual = 95.0 + (random.nextDouble() * 45.0);
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return isLigado();
    }

    @Override
    public String retornarTipo() {
        return "Pressão";
    }

    @Override
    public int verificarAlerta() {
        if (valorAtual >= LIMITE_CRITICO) return Sensor.NIVEL_CRITICO;
        if (valorAtual >= LIMITE_ALERTA)  return Sensor.NIVEL_ALERTA;
        if (valorAtual >= LIMITE_ATENCAO) return Sensor.NIVEL_ATENCAO;
        return 0;
    }

    @Override
    public void setLimiteMaximo(double limite) {
        if (limite > 0) this.limiteMaximo = limite;
    }

    @Override
    public double getLimiteMaximo() {
        return limiteMaximo;
    }

    // ----------------------------------------------------------------
    // Implementação do método abstrato de ComponenteEspacial
    // ----------------------------------------------------------------

    @Override
    public void exibirDetalhes() {
        System.out.printf("Leitura  : %.1f kPa%n", valorAtual);
        System.out.println("Limite   : " + limiteMaximo + " kPa");
    }

    public double getValorAtual() { return valorAtual; }
}
