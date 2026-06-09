import java.util.Random;

/**
 * Sensor de Radiação.
 * Herda de ComponenteEspacial E implementa a interface Sensor.
 * Terceiro tipo de sensor exigido pelo trabalho.
 */
public class SensorRadiacao extends ComponenteEspacial implements Sensor {

    private double valorAtual; // em mSv/h (millisievert por hora)
    private double limiteMaximo;
    private Random random;

    // Limites de radiação (mSv/h) — referência: normas de proteção radiológica
    private static final double LIMITE_ATENCAO = 0.5;
    private static final double LIMITE_ALERTA  = 1.0;
    private static final double LIMITE_CRITICO = 2.0;

    public SensorRadiacao(String id) {
        super(id, "Sensor de Radiação");
        this.limiteMaximo = LIMITE_ATENCAO;
        this.random = new Random();
        this.valorAtual = 0.1;
    }

    // ----------------------------------------------------------------
    // Implementação do contrato Sensor
    // ----------------------------------------------------------------

    @Override
    public double lerValor() {
        // Simula leitura entre 0.05 e 2.5 mSv/h
        valorAtual = 0.05 + (random.nextDouble() * 2.45);
        return valorAtual;
    }

    @Override
    public boolean verificarFuncionamento() {
        return isLigado();
    }

    @Override
    public String retornarTipo() {
        return "Radiação";
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
        System.out.printf("Leitura  : %.3f mSv/h%n", valorAtual);
        System.out.println("Limite   : " + limiteMaximo + " mSv/h");
    }

    public double getValorAtual() { return valorAtual; }
}
