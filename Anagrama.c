#include <stdio.h>
#include <stdlib.h>

 int Anagrama (char *palavra1, char *palavra2){

    int tamanho1 = 0;
    int tamanho2 = 0;
    int count = 0;

    while(palavra1[tamanho1] != '\0'){

        tamanho1 ++;
    }

    while(palavra2[tamanho2] != '\0'){

        tamanho2++;
    }


    if(tamanho1 == tamanho2){

        for(int i=0; i<tamanho1; i++){
            for(int j=0; j<tamanho1; j++){

                if(palavra1[i] == palavra2[j] || palavra1[i] == palavra2[j] + 32 || palavra1[i] == palavra2[j] - 32){
					palavra2[j]=' ';
					count++;
					break;
                }
            }
        }
    }

                if(count == tamanho1){
                    return 1;

                }else{
                    return 0;
 
                }
 }

    int main(){

    char string1[100];
    char string2[100];

    while(scanf ("%s %s", string1, string2) != EOF){

        if(string1[0] == 'F' && string1[1] == 'I' && string1[2] == 'M' ||
            string2[0] == 'F' && string2[1] == 'I' && string2[2] == 'M'){

                break;
            }

    if (Anagrama(string1, string2)== 1){
    printf("SIM\n");
    }else {
    printf("NAO\n");
    }
    }

return 0;
}

