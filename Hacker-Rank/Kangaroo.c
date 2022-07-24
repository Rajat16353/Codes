#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

int main()
{
    int x1,x2,v1,v2;
    scanf("%d %d %d %d",&x1,&v1,&x2,&v2);
    if(v1>v2)
    {
        while(x1<x2)
        {
            x1+=v1;
            x2+=v2;
            if(x1==x2)
            {
                printf("YES");
                break;
            }
            
        }
    }
    else
    {
        while(x1>x2)
        {
            x1+=v1;
            x2+=v2;
            if(x1==x2)
            {
                printf("YES");
                break;
            }
            
        }
    }
    if(x1!=x2)
        printf("NO");    
    return 0;
}