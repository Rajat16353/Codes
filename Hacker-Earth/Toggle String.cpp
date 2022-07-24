#include <iostream>
#include<string.h>
 
using namespace std;
 
int main() 
{
	char s[100];
	cin>>s;
	for(int i=0;i<strlen(s);i++)
	{
	    if(s[i]<97)
	    {
	        s[i]+=32;
	    }
	    else
	    {
	        s[i]-=32;
	    }
	}
	cout<<s;
}