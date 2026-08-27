#include <stdio.h>
#include <stdlib.h>

int MaiorSub (char* string){

int tam=0;
int maior = 0;


while(string[tam] != '\0'){
    tam ++;
}
if(tam==0){return 0;}

for(int i=0; i<tam; i++){
    int count = 0;
    int jaLida[256] = {0};

        for (int j = i; j < tam; j++) {
            int caractere = string[j];

            if(jaLida[caractere] == 1){
                break;
            }
    
            jaLida[caractere] = 1;
            count++;
}
            if(count>maior){

                maior = count;
            }
}
 
return maior;

}

int main(){

    char string[100];
    int resultado = 0;

scanf ("%s", string);

resultado = MaiorSub(string);

printf("%d\n", resultado);

return 0;
}