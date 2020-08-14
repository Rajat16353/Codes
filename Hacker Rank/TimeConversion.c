#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

int main()
{
    char str[20],str1[20];
    int i;
    scanf("%s",str);
    i=strlen(str)-2;
    
    if(str[i]=='A' || str[i]=='a')
    {
        if(str[0]=='1' && str[1]=='2')
        {
            strcpy(str1,str);
            str1[i]='\0';
            str1[0]='0';
            str1[1]='0';
        }
        else
        {
            strcpy(str1,str);
            str1[i]='\0';
        }
    }
    else
    {
            if(str[0]=='1' && str[1]=='2')
            {
                strcpy(str1,str);
            str1[i]='\0';
            }
            else
            {
            int a=str[0]-48;
            int b=str[1]-48;
            int c=b+(10*a);
        
            c+=12;    
        
            strcpy(str1,str);
            str1[i]='\0';
            str1[1]=c%10+48;
            str1[0]=c/10+48;
            }
    }    
    //printf("%s",str);    
    printf("%s",str1);
    return 0;
}