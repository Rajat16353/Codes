#include <iostream>
 
using namespace std;
 
int main() {
	int t,n,costg,costp;
	cin>>t;
	for(int k=0;k<t;k++)
	{
    	cin>>costg>>costp;
    	cin>>n;
    	int s[n][2],tcost=0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<2;j++)
            {
                cin>>s[i][j];
            }
        }
        if(k%2==0)
        {
            for(int i=0;i<n;i++)
            {
                if(s[i][0]==1)
                    tcost+=costg;
                if(s[i][1]==1)
                    tcost+=costp;
            }
        }
        else
        {
            for(int i=0;i<n;i++)
            {
                if(s[i][0]==1)
                    tcost+=costp;
                if(s[i][1]==1)
                    tcost+=costg;
            }
        }
        cout<<tcost<<"\n";
	}
}
 