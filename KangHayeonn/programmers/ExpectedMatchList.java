class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;
		
		while(true) {
			if(a < b) {
				if((a % 2 != 0) && (b == a + 1)) { // a가 홀수고 b와 하나 차이 날 때 종료됨
					break;
				}
			}
			if (b < a) {
				if((b % 2 != 0) && (a == b + 1)) { // b가 홀수고 a와 하나 차이 날 때 종료됨
					break;
				}
			}
			a = (a + 1) / 2;
			b = (b + 1) / 2;
			answer += 1;
		}
		
		return answer;
    }
}
