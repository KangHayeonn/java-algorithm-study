class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer=1;
        a += a%2;
        b += b%2;
        while(a!=b){
            a/=2;
            b/=2;
            a += a%2;
            b += b%2;
            answer++;
        }return answer;
    }
    
}
