#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
    int a[100][100],n,i,j,ds1=0,ds2=0,ad;

    scanf("%d",&n);
    for(i=0;i<n;i++)
        for(j=0;j<n;j++)
            scanf("%d",&a[i][j]);
    for(i=0,j=0;i<n;i++,j++)
        ds1=ds1+a[i][j];
    for(i=n-1,j=0;i>-1;i--,j++)
        ds2=ds2+a[i][j];

    ad=abs(ds1-ds2);

    printf("%d",ad);

    return 0;           
}

