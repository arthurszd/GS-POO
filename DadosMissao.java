/**
 * Classe DadosMissao — demonstra ENCAPSULAMENTO.
 *
 * Todos os atributos são privados.
 * Dados sensíveis (coordenadas) são protegidos por senha.
 * Getters e setters possuem validação.
 */
public class DadosMissao {

    // ----------------------------------------------------------------
    // Atributos — TODOS PRIVADOS (encapsulamento)
    // ----------------------------------------------------------------

    private String nomeMissao;
    private int    numeroDeTripulantes;
    private double nivelCombustivel;      // porcentagem 0-100
    private String trajetoria;

    // Dados sensíveis — acesso restrito por senha
    private double coordenadaX;
    private double coordenadaY;
    private double coordenadaZ;
    private final String codigoAcesso;    // final: não pode ser alterado após criação

    // Constante: nível mínimo de combustível antes do alerta
    private static final double LIMITE_COMBUSTIVEL = 20.0;

    // ----------------------------------------------------------------
    // Construtor
    // ----------------------------------------------------------------

    public DadosMissao(String nomeMissao, String codigoAcesso) {
        this.nomeMissao          = nomeMissao;
        this.codigoAcesso        = codigoAcesso;
        this.numeroDeTripulantes = 0;
        this.nivelCombustivel    = 100.0;
        this.trajetoria          = "Não definida";
        this.coordenadaX         = 0.0;
        this.coordenadaY         = 0.0;
        this.coordenadaZ         = 0.0;
    }

    // ----------------------------------------------------------------
    // Getters e Setters — com validação
    // ----------------------------------------------------------------

    public String getNomeMissao() {
        return nomeMissao;
    }

    public void setNomeMissao(String nomeMissao) {
        if (nomeMissao != null && !nomeMissao.trim().isEmpty()) {
            this.nomeMissao = nomeMissao;
        } else {
            System.out.println("ERRO: Nome da missão inválido.");
        }
    }

    public int getNumeroDeTripulantes() {
        return numeroDeTripulantes;
    }

    public void setNumeroDeTripulantes(int numero) {
        if (numero >= 0) {
            this.numeroDeTripulantes = numero;
        } else {
            System.out.println("ERRO: Número de tripulantes não pode ser negativo.");
        }
    }

    public double getNivelCombustivel() {
        return nivelCombustivel;
    }

    public void setNivelCombustivel(double nivel) {
        if (nivel >= 0 && nivel <= 100) {
            this.nivelCombustivel = nivel;
            // Alerta automático quando combustível está abaixo de 20%
            if (nivelCombustivel < LIMITE_COMBUSTIVEL) {
                System.out.println("*** ALERTA: Combustível em " + nivelCombustivel
                        + "% — abaixo do limite mínimo de " + LIMITE_COMBUSTIVEL + "%! ***");
            }
        } else {
            System.out.println("ERRO: Nível de combustível deve estar entre 0 e 100.");
        }
    }

    public String getTrajetoria() {
        return trajetoria;
    }

    public void setTrajetoria(String trajetoria) {
        if (trajetoria != null && !trajetoria.trim().isEmpty()) {
            this.trajetoria = trajetoria;
        } else {
            System.out.println("ERRO: Trajetória inválida.");
        }
    }

    // ----------------------------------------------------------------
    // Acesso às coordenadas — protegido por senha
    // ----------------------------------------------------------------

    /**
     * Retorna as coordenadas somente se a senha estiver correta.
     * @param senha código de acesso fornecido pelo usuário
     * @return String com coordenadas, ou mensagem de acesso negado
     */
    public String getCoordenadas(String senha) {
        if (this.codigoAcesso.equals(senha)) {
            return String.format("Coordenadas: X=%.2f | Y=%.2f | Z=%.2f",
                    coordenadaX, coordenadaY, coordenadaZ);
        } else {
            return "ACESSO NEGADO: Código incorreto.";
        }
    }

    /**
     * Define coordenadas somente se a senha estiver correta.
     */
    public void setCoordenadas(double x, double y, double z, String senha) {
        if (this.codigoAcesso.equals(senha)) {
            this.coordenadaX = x;
            this.coordenadaY = y;
            this.coordenadaZ = z;
            System.out.println("Coordenadas atualizadas com sucesso.");
        } else {
            System.out.println("ACESSO NEGADO: Código incorreto.");
        }
    }

    // ----------------------------------------------------------------
    // Exibição geral (sem dados sensíveis)
    // ----------------------------------------------------------------

    public void exibirResumo() {
        System.out.println("==========================================");
        System.out.println("         DADOS DA MISSÃO");
        System.out.println("==========================================");
        System.out.println("Missão      : " + nomeMissao);
        System.out.println("Tripulantes : " + numeroDeTripulantes);
        System.out.printf ("Combustível : %.1f%%%n", nivelCombustivel);
        System.out.println("Trajetória  : " + trajetoria);
        System.out.println("Coordenadas : [PROTEGIDAS — requer código de acesso]");
        System.out.println("==========================================");
    }
}
