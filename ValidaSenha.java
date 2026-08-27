import java.util.Scanner;

class ValidaSenha{
    public static boolean Senha(String s){

        if (s == null || s.length() < 8) {
            return false;
        }

        boolean maiuscula = false;
        boolean minuscula = false;
        boolean especial = false;
        boolean numero = false;


        for(int i=0; i<s.length(); i++){

            char c = s.charAt(i);

            if(c >= 'A' && c <= 'Z'){
                maiuscula = true; 

            }else if(c >= 'a' && c <= 'z'){
                minuscula = true;

            }else if(c >= '0' && c <= '9'){
                numero = true;

            }else{
                especial = true; 
            }
        }

        boolean resultado = maiuscula && minuscula && numero && especial;
         
        return resultado;

    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String senha = sc.nextLine();

            if (Senha(senha)) {
                System.out.println("SIM");
            } else {
                System.out.println("NÃO");
            }
        }

        sc.close();
    }
}
