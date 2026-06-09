import java.util.Scanner;

/**
 * SistemaMonitoramento — classe principal da Plataforma de Monitoramento Espacial.
 *
 * Contém o menu interativo com todas as funcionalidades do sistema:
 *  1. Verificar sensores
 *  2. Controlar propulsão
 *  3. Gerenciar dados da missão
 *  4. Simular alertas
 *  5. Exibir status completo
 *
 * Demonstra polimorfismo: sensores e propulsores são tratados pelo tipo da interface/classe abstrata.
 */
public class SistemaMonitoramento {

    // Arrays usando os tipos abstratos/interface — polimorfismo
    private static Sensor[]           sensores    = new Sensor[3];
    private static SistemaPropulsao[] propulsores = new SistemaPropulsao[2];
    private static DadosMissao        missao;
    private static Scanner            scanner     = new Scanner(System.in);

    // ----------------------------------------------------------------
    // Método main — ponto de entrada
    // ----------------------------------------------------------------

    public static void main(String[] args) {

        inicializarSistema();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> menuSensores();
                case 2 -> menuPropulsao();
                case 3 -> menuMissao();
                case 4 -> simularAlertas();
                case 5 -> exibirStatusCompleto();
                case 0 -> System.out.println("\nEncerrando sistema... Boa viagem espacial!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    // ----------------------------------------------------------------
    // Inicialização — cria todos os objetos do sistema
    // ----------------------------------------------------------------

    private static void inicializarSistema() {
        System.out.println("============================================");
        System.out.println("  PLATAFORMA DE MONITORAMENTO ESPACIAL");
        System.out.println("     FIAP — Global Solution 2026 — POO");
        System.out.println("============================================");
        System.out.println("Inicializando sistemas...\n");

        // Cria os 3 sensores (polimorfismo via interface Sensor)
        sensores[0] = new SensorTemperatura("SENS-TEMP-01");
        sensores[1] = new SensorPressao("SENS-PRES-01");
        sensores[2] = new SensorRadiacao("SENS-RAD-01");

        // Liga todos os sensores
        for (Sensor s : sensores) {
            ((ComponenteEspacial) s).ligar();
        }

        // Cria os 2 sistemas de propulsão (polimorfismo via classe abstrata)
        propulsores[0] = new PropulsaoQuimica("PROP-QUIM-01", "Hidrogênio Líquido", 5000.0);
        propulsores[1] = new PropulsaoEletrica("PROP-ELET-01");

        // Cria os dados da missão com código de acesso secreto
        missao = new DadosMissao("Missão Orion-7", "FIAP2026");
        missao.setNumeroDeTripulantes(6);
        missao.setTrajetoria("Terra → Orbita LEO → Lua");
        missao.setCoordenadas(1200.5, -340.8, 890.2, "FIAP2026");

        System.out.println("\nSistema inicializado com sucesso!\n");
    }

    // ----------------------------------------------------------------
    // Menu Principal
    // ----------------------------------------------------------------

    private static void exibirMenuPrincipal() {
        System.out.println("\n============================================");
        System.out.println("           MENU PRINCIPAL");
        System.out.println("============================================");
        System.out.println("  1. Verificar sensores");
        System.out.println("  2. Controlar propulsão");
        System.out.println("  3. Gerenciar dados da missão");
        System.out.println("  4. Simular alertas");
        System.out.println("  5. Exibir status completo");
        System.out.println("  0. Sair");
        System.out.println("============================================");
    }

    // ----------------------------------------------------------------
    // Menu 1 — Sensores
    // ----------------------------------------------------------------

    private static void menuSensores() {
        System.out.println("\n--- SISTEMA DE SENSORES ---");
        System.out.println("1. Ler todos os sensores");
        System.out.println("2. Verificar funcionamento");
        System.out.println("3. Voltar");

        int op = lerInteiro("Opção: ");
        switch (op) {
            case 1 -> lerTodosSensores();
            case 2 -> verificarFuncionamentoSensores();
            case 3 -> {} // volta ao menu principal
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void lerTodosSensores() {
        System.out.println("\n--- LEITURA DOS SENSORES ---");
        for (Sensor s : sensores) {
            double valor = s.lerValor();
            System.out.printf("[%s] Leitura: %.3f | Alerta: %s%n",
                    s.retornarTipo(), valor, nivelParaTexto(s.verificarAlerta()));
        }
    }

    private static void verificarFuncionamentoSensores() {
        System.out.println("\n--- FUNCIONAMENTO DOS SENSORES ---");
        for (Sensor s : sensores) {
            System.out.println("[" + s.retornarTipo() + "] " +
                    (s.verificarFuncionamento() ? "OPERACIONAL" : "FALHA DETECTADA"));
        }
    }

    // ----------------------------------------------------------------
    // Menu 2 — Propulsão
    // ----------------------------------------------------------------

    private static void menuPropulsao() {
        System.out.println("\n--- SISTEMA DE PROPULSÃO ---");
        System.out.println("1. Ligar motores");
        System.out.println("2. Desligar motores");
        System.out.println("3. Acelerar");
        System.out.println("4. Ver status da propulsão");
        System.out.println("5. Voltar");

        int op = lerInteiro("Opção: ");
        switch (op) {
            case 1 -> {
                for (SistemaPropulsao p : propulsores) p.ligarMotor();
            }
            case 2 -> {
                for (SistemaPropulsao p : propulsores) p.desligarMotor();
            }
            case 3 -> {
                int potencia = lerInteiro("Potência desejada (0-100%): ");
                for (SistemaPropulsao p : propulsores) {
                    p.acelerar(potencia); // polimorfismo: cada propulsor acelera diferente
                }
            }
            case 4 -> {
                for (SistemaPropulsao p : propulsores) p.exibirStatus();
            }
            case 5 -> {}
            default -> System.out.println("Opção inválida.");
        }
    }

    // ----------------------------------------------------------------
    // Menu 3 — Dados da Missão
    // ----------------------------------------------------------------

    private static void menuMissao() {
        System.out.println("\n--- DADOS DA MISSÃO ---");
        System.out.println("1. Exibir resumo da missão");
        System.out.println("2. Atualizar combustível");
        System.out.println("3. Atualizar tripulantes");
        System.out.println("4. Ver coordenadas (requer senha)");
        System.out.println("5. Voltar");

        int op = lerInteiro("Opção: ");
        switch (op) {
            case 1 -> missao.exibirResumo();
            case 2 -> {
                System.out.print("Novo nível de combustível (0-100%): ");
                double nivel = scanner.nextDouble();
                missao.setNivelCombustivel(nivel);
            }
            case 3 -> {
                int num = lerInteiro("Número de tripulantes: ");
                missao.setNumeroDeTripulantes(num);
            }
            case 4 -> {
                System.out.print("Digite o código de acesso: ");
                String senha = scanner.next();
                System.out.println(missao.getCoordenadas(senha));
            }
            case 5 -> {}
            default -> System.out.println("Opção inválida.");
        }
    }

    // ----------------------------------------------------------------
    // Menu 4 — Simular Alertas
    // ----------------------------------------------------------------

    private static void simularAlertas() {
        System.out.println("\n======================================");
        System.out.println("      VERIFICAÇÃO DE ALERTAS");
        System.out.println("======================================");

        boolean algumAlerta = false;

        for (Sensor s : sensores) {
            double valor = s.lerValor();
            int nivel   = s.verificarAlerta();

            if (nivel > 0) {
                algumAlerta = true;
                System.out.println(">>> " + nivelParaTexto(nivel) + " <<<");
                System.out.printf ("    Sensor   : %s%n", s.retornarTipo());
                System.out.printf ("    Valor    : %.3f%n", valor);
                System.out.printf ("    Limite   : %.3f%n", s.getLimiteMaximo());
                System.out.println("--------------------------------------");
            }
        }

        // Alerta de combustível
        if (missao.getNivelCombustivel() < 20.0) {
            algumAlerta = true;
            System.out.println(">>> ALERTA: Nível de combustível crítico! <<<");
        }

        if (!algumAlerta) {
            System.out.println("Todos os sistemas operam dentro dos parâmetros normais.");
        }

        System.out.println("======================================");
    }

    // ----------------------------------------------------------------
    // Menu 5 — Status Completo
    // ----------------------------------------------------------------

    private static void exibirStatusCompleto() {
        System.out.println("\n==========================================");
        System.out.println("         STATUS COMPLETO DO SISTEMA");
        System.out.println("==========================================");

        System.out.println("\n[SENSORES]");
        for (Sensor s : sensores) {
            ((ComponenteEspacial) s).exibirStatus();
        }

        System.out.println("\n[PROPULSÃO]");
        for (SistemaPropulsao p : propulsores) {
            p.exibirStatus();
        }

        System.out.println("\n[MISSÃO]");
        missao.exibirResumo();
    }

    // ----------------------------------------------------------------
    // Utilitários
    // ----------------------------------------------------------------

    private static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Valor inválido. " + mensagem);
        }
        return scanner.nextInt();
    }

    private static String nivelParaTexto(int nivel) {
        return switch (nivel) {
            case Sensor.NIVEL_ATENCAO -> "ATENÇÃO";
            case Sensor.NIVEL_ALERTA  -> "ALERTA";
            case Sensor.NIVEL_CRITICO -> "CRÍTICO";
            default -> "NORMAL";
        };
    }
}
