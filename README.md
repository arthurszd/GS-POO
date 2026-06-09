# 🚀 Plataforma de Monitoramento Espacial

**Global Solution 2026 — Programação Orientada a Objetos**  
**FIAP — Graduação**

---

## 📋 Sobre o Projeto

Sistema de monitoramento de uma estação espacial desenvolvido em Java, **Classes Abstratas**, **Interfaces**, **Encapsulamento** e **Herança**.

O sistema simula o monitoramento em tempo real de sensores ambientais, sistemas de propulsão e dados de missão, emitindo alertas automáticos quando os parâmetros saem dos limites seguros.

---

## 🗂️ Estrutura do Projeto

```
projeto-espacial/
├── ComponenteEspacial.java   → Classe abstrata base
├── Sensor.java               → Interface de contratos dos sensores
├── DadosMissao.java          → Encapsulamento de dados sensíveis
├── SistemaPropulsao.java     → Classe abstrata de propulsão
├── PropulsaoQuimica.java     → Herda de SistemaPropulsao
├── PropulsaoEletrica.java    → Herda de SistemaPropulsao
├── SensorTemperatura.java    → Implementa Sensor
├── SensorPressao.java        → Implementa Sensor
├── SensorRadiacao.java       → Implementa Sensor
└── SistemaMonitoramento.java → Classe principal com menu
```

---

## 🧠 Conceitos de POO Aplicados

### 1. Classe Abstrata

`ComponenteEspacial` é a classe abstrata base de todo o sistema. Não pode ser instanciada diretamente — serve como molde para todos os componentes da estação.

```java
public abstract class ComponenteEspacial {
    private String id;
    private String nome;
    private boolean status;
    private double temperatura;

    // Métodos concretos — compartilhados por todas as filhas
    public void ligar() { ... }
    public void desligar() { ... }

    // Método abstrato — OBRIGA todas as filhas a implementar
    public abstract void exibirDetalhes();
}
```

`SistemaPropulsao` também é abstrata e herda de `ComponenteEspacial`, adicionando seu próprio método abstrato:

```java
public abstract class SistemaPropulsao extends ComponenteEspacial {
    public abstract void acelerar(int porcentagem);
    public abstract double calcularEmpuxo();
}
```

---

### 2. Interface

`Sensor` define o **contrato de comportamento** que todos os sensores devem cumprir, independentemente de hierarquia.

```java
public interface Sensor {
    int NIVEL_ATENCAO = 1;   // public static final implícito
    int NIVEL_ALERTA  = 2;
    int NIVEL_CRITICO = 3;

    double lerValor();
    boolean verificarFuncionamento();
    String retornarTipo();
    int verificarAlerta();
    void setLimiteMaximo(double limite);
    double getLimiteMaximo();
}
```

Os três sensores **herdam de `ComponenteEspacial` E implementam `Sensor`** — demonstrando múltiplos tipos numa mesma classe:

```java
public class SensorTemperatura extends ComponenteEspacial implements Sensor { ... }
public class SensorPressao     extends ComponenteEspacial implements Sensor { ... }
public class SensorRadiacao    extends ComponenteEspacial implements Sensor { ... }
```

---

### 3. Encapsulamento

`DadosMissao` protege todos os seus dados com atributos `private`, validação nos setters e acesso restrito às coordenadas por senha.

```java
public class DadosMissao {
    private String nomeMissao;
    private int    numeroDeTripulantes;
    private double nivelCombustivel;     // validado: 0 a 100
    private double coordenadaX;          // acesso somente com senha
    private final String codigoAcesso;   // imutável após criação

    // Setter com validação — não aceita valores negativos
    public void setNivelCombustivel(double nivel) {
        if (nivel >= 0 && nivel <= 100) {
            this.nivelCombustivel = nivel;
            if (nivelCombustivel < 20.0) {
                System.out.println("*** ALERTA: Combustível crítico! ***");
            }
        }
    }

    // Coordenadas protegidas por senha
    public String getCoordenadas(String senha) {
        if (this.codigoAcesso.equals(senha)) {
            return "Coordenadas: X=" + coordenadaX + "...";
        }
        return "ACESSO NEGADO: Código incorreto.";
    }
}
```

---

### 4. Herança

`PropulsaoQuimica` e `PropulsaoEletrica` herdam de `SistemaPropulsao`, cada uma com atributos e comportamentos próprios. O método `acelerar()` é sobrescrito de forma diferente em cada uma.

```java
// Propulsão Química — empuxo não-linear, consome combustível líquido
public class PropulsaoQuimica extends SistemaPropulsao {
    private String tipoCombustivel;
    private double nivelCombustivel;

    @Override
    public void acelerar(int porcentagem) {
        // comportamento específico da propulsão química
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes(); // chama o método da classe mãe
        System.out.println("Combustível: " + tipoCombustivel);
    }
}

// Propulsão Elétrica — motor iônico, alta eficiência, consome bateria
public class PropulsaoEletrica extends SistemaPropulsao {
    private double cargaBateria;
    private double eficiencia;

    @Override
    public void acelerar(int porcentagem) {
        // comportamento completamente diferente da química
    }
}
```

---

## ⚙️ Como Compilar e Executar

### Pré-requisito
Java 11 ou superior instalado.

### Compilar
```bash
javac *.java
```

### Executar
```bash
java SistemaMonitoramento
```

---

## 🖥️ Menu do Sistema

```
============================================
           MENU PRINCIPAL
============================================
  1. Verificar sensores
  2. Controlar propulsão
  3. Gerenciar dados da missão
  4. Simular alertas
  5. Exibir status completo
  0. Sair
============================================
```

### Exemplo de saída — leitura de sensores

```
--- LEITURA DOS SENSORES ---
[Temperatura] Leitura: 69.832 | Alerta: ATENÇÃO
[Pressão]     Leitura: 133.60 | Alerta: ALERTA
[Radiação]    Leitura: 2.139  | Alerta: CRÍTICO
```

### Exemplo de saída — sistema de alertas

```
======================================
      VERIFICAÇÃO DE ALERTAS
======================================
>>> CRÍTICO <<<
    Sensor   : Radiação
    Valor    : 2.139
    Limite   : 0.500
--------------------------------------
>>> ALERTA <<<
    Sensor   : Pressão
    Valor    : 133.6
    Limite   : 110.0
======================================
```

### Exemplo de saída — aceleração polimórfica

```
[Propulsão Química]  Acelerando a 75% de potência.
[Propulsão Química]  Empuxo gerado: 421340.8 N
[Propulsão Química]  Combustível restante: 4812.5 L

[Propulsão Elétrica] Acelerando a 75% de potência.
[Propulsão Elétrica] Empuxo gerado: 3450.0 N (motor iônico)
[Propulsão Elétrica] Bateria restante: 92.5%
```

---

## 📊 Limites dos Sensores

| Sensor       | Unidade | Atenção | Alerta | Crítico |
|-------------|---------|---------|--------|---------|
| Temperatura  | °C      | ≥ 60    | ≥ 80   | ≥ 100   |
| Pressão      | kPa     | ≥ 110   | ≥ 120  | ≥ 135   |
| Radiação     | mSv/h   | ≥ 0.5   | ≥ 1.0  | ≥ 2.0   |

---

## ✅ Critérios de Avaliação Atendidos

| Critério | Pontos | Status |
|---|---|---|
| Classe Abstrata (`ComponenteEspacial`, `SistemaPropulsao`, método abstrato, `extends`) | 2.0 | ✅ |
| Interface (`Sensor`, métodos definidos, implementada em 3 classes, `implements`) | 2.0 | ✅ |
| Encapsulamento (`DadosMissao`, atributos privados, getters/setters com validação, senha) | 2.0 | ✅ |
| Herança (`SistemaPropulsao`, 2 tipos de propulsão, `super()`, atributos específicos) | 2.0 | ✅ |
| Sistema de Alertas (verificação automática, 3 níveis, menu interativo) | 2.0 | ✅ |
| **Total** | **10.0** | ✅ |

---

## 👨‍💻 Tecnologias

- Java 21
- POO — Classes Abstratas, Interfaces, Encapsulamento, Herança, Polimorfismo
