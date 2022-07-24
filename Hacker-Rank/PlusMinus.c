#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
    int a[100],n,i;
    float p=0,ne=0,z=0;

    scanf("%d",&n);
    for(i=0;i<n;i++)
        scanf("%d",&a[i]);
    for(i=0;i<n;i++)
    {
        if(a[i]>0)
            p++;
        else if(a[i]<0)
            ne++;
        else    
            z++;        
    }   

    printf("%.6f\n",p/n);
    printf("%.6f\n",ne/n);
    printf("%.6f",z/n);

    return 0;           
}

