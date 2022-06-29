class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, 0, target);
        return answer;
    }
    public void dfs(int[] numbers, int idx, int sum, int target) {
        if(idx == numbers.length) {
            if(sum == target) answer += 1;
            return;
        }
        dfs(numbers, idx+1, sum+numbers[idx], target);
        dfs(numbers, idx+1, sum-numbers[idx], target);
    }
}
