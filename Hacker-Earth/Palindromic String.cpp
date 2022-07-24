#include <iostream>
#include <string.h>
 
using namespace std;
 
int main() 
{
	char s[100];
	cin>>s;
	int i,j,flag=1;
	j=strlen(s)-1;
	for(i=0;i<j;i++,j--)
	{
	    if(s[i]!=s[j])
	   { 
	       flag=0;
	       break;
	   }
	}
	if(flag==1)
	{
	    cout<<"YES";
	}
	else
	{
	    cout<<"NO";
	}
	return 0;
}