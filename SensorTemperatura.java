import java.util.Random;

/**
 * Sensor de Temperatura.
 * Herda de ComponenteEspacial E implementa a interface Sensor.
 * Demonstra que uma classe pode ter UMA mãe e MÚLTIPLOS contratos.
 */
public class SensorTemperatura extends ComponenteEspacial implements Sensor {

    private double valorAtual;
    private double limiteMaximo;
    private Random random;

    // Limites típicos para temperatura em uma estação espacial (°C)
    private static final double LIMITE_ATENCAO = 60.0;
    private static final double LIMITE_ALERTA  = 80.0;
    private static final double LIMITE_CRITICO = 100.0;

    public SensorTemperatura(String id) {
        super(id, "Sensor de Temperatura");
        this.limiteMaximo = LIMITE_ATENCAO;
        this.random = new Random();
        this.valorAtual = 20.0;
    }

    // ----------------------------------------------------------------
    // Implementação do contrato Sensor
    // ----------------------------------------------------------------

    @Override
    public double lerValor() {
        // Simula leitura com variação aleatória de ±15°C ao redor de 50°C
        valorAtual = 30.0 + (random.nextDouble() * 90.0);
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return isLigado();
    }

    @Override
    public String retornarTipo() {
        return "Temperatura";
    }

    @Override
    public int verificarAlerta() {
        if (valorAtual >= LIMITE_CRITICO) return Sensor.NIVEL_CRITICO;
        if (valorAtual >= LIMITE_ALERTA)  return Sensor.NIVEL_ALERTA;
        if (valorAtual >= LIMITE_ATENCAO) return Sensor.NIVEL_ATENCAO;
        return 0; // normal
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
        System.out.printf("Leitura  : %.1f°C%n", valorAtual);
        System.out.println("Limite   : " + limiteMaximo + "°C");
    }

    public double getValorAtual() { return valorAtual; }
}
