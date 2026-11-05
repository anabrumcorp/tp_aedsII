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

    public int getAno() {return ano;}

    public int getMes() {return mes;}

    public int getDia() {return dia;}

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
public String format(){

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

public Veiculo(int id, String marca, String modelo, int ano, String categoria, String[] combustivel, int cilindros, double cilindrada,
String transmissao, String tracao, double consumo_cidade, double consumo_estrada, double co2, boolean turbo, Data data_registro){

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

    public int getId() {return id;}

    public String getMarca() {return marca;}

    public String getModelo() {return modelo;}

    public int getAno() {return ano;}

    public String getCategoria(){return categoria;}

    public String[] getCombustivel(){return combustivel;}

    public int getCilindros(){return cilindros;}

    public double getCilindrada(){return cilindrada;}

    public String getTransmissao(){return transmissao;}
    
    public String getTracao(){return tracao;}

    public double getConsumo_cidade(){return consumo_cidade;}
    
    public double getConsumo_estrada(){return consumo_estrada;}

    public double getCo2(){return co2;}

    public boolean getTurbo(){return turbo;}
    
    public Data getData_registro(){return data_registro;}

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

        // primeira passada: conta quantas linhas de dados existem (sem contar o cabecalho)
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

// ordenacao por heapsort: chave principal co2, desempate por transmissao
class Heapsort {

    // retorna > 0 se a vem depois de b, < 0 se vem antes, 0 se iguais
    private static int comparar(Veiculo a, Veiculo b) {
        if (a.getCo2() > b.getCo2()) return 1;
        if (a.getCo2() < b.getCo2()) return -1;
        return a.getTransmissao().compareTo(b.getTransmissao());
    }

    public static void ordenar(Veiculo[] veiculos) {
        int n = veiculos.length;
        int k=10;

        // 1. constroi o max-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            int pai = i;
            while (true) {
                int maior = pai;
                int esq = 2 * pai + 1;
                int dir = 2 * pai + 2;

                if (esq < n && comparar(veiculos[esq], veiculos[maior]) > 0) maior = esq;
                if (dir < n && comparar(veiculos[dir], veiculos[maior]) > 0) maior = dir;

                if (maior == pai) break;

                Veiculo tmp = veiculos[pai];
                veiculos[pai] = veiculos[maior];
                veiculos[maior] = tmp;

                pai = maior;
            }
        }

        // 2. extrai o maior elemento 
        for (int i = 0; i<k ; i++) {

            int fim = n-1-i;

            if(fim <= 0)break;

            Veiculo tmp = veiculos[0];
            veiculos[0] = veiculos[fim];
            veiculos[fim] = tmp;

            int pai = 0;
            while (true) {
                int maior = pai;
                int esq = 2 * pai + 1;
                int dir = 2 * pai + 2;

                if (esq < fim && comparar(veiculos[esq], veiculos[maior]) > 0) maior = esq;
                if (dir < fim && comparar(veiculos[dir], veiculos[maior]) > 0) maior = dir;

                if (maior == pai) break;

                Veiculo aux = veiculos[pai];
                veiculos[pai] = veiculos[maior];
                veiculos[maior] = aux;

                pai = maior;
            }
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

        Heapsort.ordenar(resultado);

        for (int i = 0; i < resultado.length; i++) {
            System.out.println(resultado[i].format());
        }
    }
}