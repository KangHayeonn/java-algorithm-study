class Solution {
    public int solution(int n, int a, int b) {
        int answer = 1;

        while(!((a%2==0 && b==a-1) || (b%2==0 && a==b-1))) {
            if(a%2==0) {
                a /= 2;
            } else {
                a /= 2;
                a++;
            }

            if(b%2==0) {
                b /= 2;
            } else {
                b /= 2;
                b++;
            }
            answer++;
        }
        
        return answer;
    }
}
