#include <stdio.h>
#include <stdlib.h>

char* InverteString (char *string){

    int tam = 0;

    while(string[tam] != '\0'){

        tam ++;
    }

    char *saida = (char*) malloc((tam + 1) * sizeof(char));


    for(int i=0; i<tam; i++){

            saida[i] = string[tam - 1 - i];

    }

    saida[tam] = '\0';

    return saida;

}

int main() {
    char palavra[100];

    while (scanf("%s", palavra) == 1) {
        char *invertida = InverteString(palavra);

        printf("%s\n", invertida);

        free(invertida);
    }

    return 0;
}
