/**
 * Interface Sensor — contrato que TODOS os sensores devem cumprir.
 *
 * Define O QUE cada sensor deve fazer, mas não COMO fazer.
 * Qualquer classe que implemente Sensor DEVE ter estes métodos.
 */
public interface Sensor {

    // Constantes compartilhadas por todos os sensores (public static final implícito)
    int NIVEL_ATENCAO  = 1;
    int NIVEL_ALERTA   = 2;
    int NIVEL_CRITICO  = 3;

    /**
     * Realiza a leitura do sensor e retorna o valor atual.
     * @return valor lido (simulado com Random)
     */
    double lerValor();

    /**
     * Verifica se o sensor está funcionando corretamente.
     * @return true se operacional, false caso contrário
     */
    boolean verificarFuncionamento();

    /**
     * Retorna o tipo do sensor como texto (ex: "Temperatura", "Pressão").
     * @return String com o tipo do sensor
     */
    String retornarTipo();

    /**
     * Verifica se o valor atual ultrapassa algum limite de alerta.
     * @return nível de alerta (0 = normal, 1 = atenção, 2 = alerta, 3 = crítico)
     */
    int verificarAlerta();

    /**
     * Define o limite máximo aceitável para leituras normais.
     * @param limite valor máximo normal
     */
    void setLimiteMaximo(double limite);

    /**
     * Retorna o limite máximo configurado.
     */
    double getLimiteMaximo();
}
