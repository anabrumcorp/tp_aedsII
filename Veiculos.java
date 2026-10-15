import java.util.Scanner;
import java.io.File;

//como a data tem os atributos ano, mes e dia definimos uma class
class Data {

    private int ano;
    private int mes;
    private int dia;

    public Data(int ano, int mes, int dia) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;

    }

    public int getAno() {
        return ano;
    }

    public int getMes() {
        return mes;
    }

    public int getDia() {
        return dia;
    }

    // definimos a ordem que a data ficara e o caractere "-" para separar dia, mes e
    // ano
    public static Data parseData(String s) {
        String[] partes = s.split("-");
        int ano = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int dia = Integer.parseInt(partes[2]);

        return new Data(ano, mes, dia);
    }

    // formatacao da data seguindo o padrao
    public String format() {

        return String.format("%02d/%02d/%04d", dia, mes, ano);

    }
}

// definindo da class veiculo
class Veiculo {
    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private String categoria;
    private String[] combustivel;
    private int cilindros;
    private double cilindrada;
    private String transmissao;
    private String tracao;
    private double consumo_cidade;
    private double consumo_estrada;
    private double co2;
    private boolean turbo;
    private Data data_registro;

    public Veiculo(int id, String marca, String modelo, int ano, String categoria, String[] combustivel, int cilindros,
            double cilindrada,
            String transmissao, String tracao, double consumo_cidade, double consumo_estrada, double co2, boolean turbo,
            Data data_registro) {

        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.categoria = categoria;
        this.combustivel = combustivel;
        this.cilindros = cilindros;
        this.cilindrada = cilindrada;
        this.transmissao = transmissao;
        this.tracao = tracao;
        this.consumo_cidade = consumo_cidade;
        this.consumo_estrada = consumo_estrada;
        this.co2 = co2;
        this.turbo = turbo;
        this.data_registro = data_registro;

    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getCategoria() {
        return categoria;
    }

    public String[] getCombustivel() {
        return combustivel;
    }

    public int getCilindros() {
        return cilindros;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public String getTransmissao() {
        return transmissao;
    }

    public String getTracao() {
        return tracao;
    }

    public double getConsumo_cidade() {
        return consumo_cidade;
    }

    public double getConsumo_estrada() {
        return consumo_estrada;
    }

    public double getCo2() {
        return co2;
    }

    public boolean getTurbo() {
        return turbo;
    }

    public Data getData_registro() {
        return data_registro;
    }

    // passa os dados da class para o array, cada um em sua respectiva posicao
    public static Veiculo parseVeiculo(String s) {
        String[] atributos = s.split(",");
        int id = Integer.parseInt(atributos[0]);
        String marca = atributos[1];
        String modelo = atributos[2];
        int ano = Integer.parseInt(atributos[3]);
        String categoria = atributos[4];
        String[] combustivel = atributos[5].split(";");
        int cilindros = Integer.parseInt(atributos[6]);
        double cilindrada = Double.parseDouble(atributos[7]);
        String transmissao = atributos[8];
        String tracao = atributos[9];
        double consumo_cidade = Double.parseDouble(atributos[10]);
        double consumo_estrada = Double.parseDouble(atributos[11]);
        double co2 = Double.parseDouble(atributos[12]);
        boolean turbo = Boolean.parseBoolean(atributos[13]);
        Data data_registro = Data.parseData(atributos[14]);

        return new Veiculo(id, marca, modelo, ano, categoria, combustivel, cilindros, cilindrada, transmissao,
                tracao, consumo_cidade,
                consumo_estrada, co2, turbo, data_registro);
    }

    // formata a string
    public String format() {

        String textoCombustivel = "[";

        // define como os combustiveis serao mostrados e separados por virgula
        for (int i = 0; i < combustivel.length; i++) {

            textoCombustivel += combustivel[i];

            if (i < combustivel.length - 1) {
                textoCombustivel += ",";

            }
        }

        textoCombustivel += "]";

        String consumoC = String.format("%.2f", consumo_cidade);
        String consumoE = String.format("%.2f", consumo_estrada);

        return "[" + id + " ## " + marca + " ## " + modelo + " ## " + ano + " ## " + categoria + " ## "
                + textoCombustivel + " ## " + cilindros + " ## " + cilindrada + " ## " + transmissao + " ## "
                + tracao + " ## " + consumoC + " ## " + consumoE + " ## " + co2 + " ## " + turbo + " ## "
                + data_registro.format() + "]";
    }
}

// ocorre a leitura do arquivo csv
class LeitorCsv {
    public static Veiculo[] ler(String caminhoArquivo) throws Exception {
        File f = new File(caminhoArquivo);

        // primeira passada: conta quantas linhas de dados existem (sem contar o
        // cabecalho)
        Scanner scConta = new Scanner(f);
        scConta.nextLine();
        int total = 0;
        while (scConta.hasNextLine()) {
            scConta.nextLine();
            total++;
        }
        scConta.close();

        Scanner sc = new Scanner(f);
        String cabecalho = sc.nextLine();
        Veiculo[] veiculos = new Veiculo[total];
        int cont = 0;
        while (sc.hasNextLine()) {
            String linha = sc.nextLine();
            veiculos[cont] = Veiculo.parseVeiculo(linha);
            cont++;
        }

        sc.close();
        return veiculos;
    }
}

class MergeSort {

    public static void sort(Veiculo[] veiculos) {
        if (veiculos == null || veiculos.length < 2) {
            return;
        }
        mergeSort(veiculos, 0, veiculos.length - 1);
    }

    public static void mergeSort(Veiculo[] veiculos, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSort(veiculos, esq, meio);
            mergeSort(veiculos, meio + 1, dir);
            intercalar(veiculos, esq, meio, dir);
        }
    }

    public static void intercalar(Veiculo[] veiculos, int esq, int meio, int dir) {
        int nEsq = meio - esq + 1;
        int nDir = dir - meio;

        Veiculo[] vetEsq = new Veiculo[nEsq];
        Veiculo[] vetDir = new Veiculo[nDir];

        for (int i = 0; i < nEsq; i++) {
            vetEsq[i] = veiculos[esq + i];
        }
        for (int j = 0; j < nDir; j++) {
            vetDir[j] = veiculos[meio + 1 + j];
        }

        int iEsq = 0, iDir = 0, i = esq;

        while (iEsq < nEsq && iDir < nDir) {
            Veiculo a = vetEsq[iEsq];
            Veiculo b = vetDir[iDir];

            boolean esqPrimeiro;

            if (a.getConsumo_cidade() != b.getConsumo_cidade()) {
                esqPrimeiro = a.getConsumo_cidade() < b.getConsumo_cidade();
            } else {
                esqPrimeiro = a.getCategoria().compareTo(b.getCategoria()) <= 0;
            }

            if (esqPrimeiro) {
                veiculos[i++] = vetEsq[iEsq++];
            } else {
                veiculos[i++] = vetDir[iDir++];
            }
        }

        while (iEsq < nEsq) {
            veiculos[i++] = vetEsq[iEsq++];
        }
        while (iDir < nDir) {
            veiculos[i++] = vetDir[iDir++];
        }
    }
}

// retorna a classe veiculos totalmente formatada segundo as orientacoes
class Veiculos {

    public static void main(String[] args) throws Exception {
        String caminho = "/tmp/veiculos.csv";
        File f = new File(caminho);

        if (!f.exists()) {
            caminho = "veiculos.csv";
        }

        Veiculo[] veiculos = LeitorCsv.ler(caminho);

        Scanner sc = new Scanner(System.in);
        Veiculo[] encontrados = new Veiculo[veiculos.length];
        int total = 0;

        while (sc.hasNextInt()) {
            int busca = sc.nextInt();
            if (busca == -1) {
                break;
            }

            for (int i = 0; i < veiculos.length; i++) {
                if (veiculos[i].getId() == busca) {
                    encontrados[total] = veiculos[i];
                    total++;
                    break;
                }
            }
        }
        sc.close();

        Veiculo[] resultado = new Veiculo[total];
        for (int i = 0; i < total; i++) {
            resultado[i] = encontrados[i];
        }

        MergeSort.sort(resultado);

        for (int i = 0; i < resultado.length; i++) {
            System.out.println(resultado[i].format());
        }
    }
}