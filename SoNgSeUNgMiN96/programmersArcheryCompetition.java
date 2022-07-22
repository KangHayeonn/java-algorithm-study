import java.util.*;
class Solution {
    static ArrayList<int[]> answerList;
    static int[] answer = new int[11];
    static int max=0, subMax;

    static public int[] solution(int n, int[] info) {
        int[] pick = new int[11];
        int subScore=0;
        for(int i=0;i<10;i++) {
            if(info[i]!=0) subScore += 10-i;
        }

        dfs(info,pick, n, 0, 0, subScore );

        if(answerList!=null)
            Collections.sort(answerList,(a,b)->{
                int aIdx = 10, bIdx = 10;
                while (aIdx>0&&a[aIdx]==b[bIdx]){
                    aIdx = --bIdx;
                }
                return b[bIdx] - a[aIdx];
            });
        if(max==0) return new int[] {-1};
        return answerList.get(0);
    }
    static void dfs(int[] info,int[] pick, int n, int depth, int score, int subscore){
        if(depth==10){
            pick[10] = n;
            if(max<score - subscore){
                max = score - subscore;
                answerList = new ArrayList<>();
                int[] newList = Arrays.copyOf(pick,11);
                answerList.add(newList);
            }else if(score - subscore>0 && max == score - subscore){        //max가 0으로 초기화시켜둬서,
                int[] newList = Arrays.copyOf(pick,11);
                answerList.add(newList);
            }
            pick[10] = 0;
            return;
        }
        int arrows = info[depth]+1, sub = 10-depth;
        if(arrows<=n){   //여길 쏴서 뻇을 수 있는지?
            pick[depth] = arrows;    //여길 쏴본다.
            if(info[depth]!=0) dfs(info,pick,n-arrows, depth +1, score + sub, subscore- sub);
            else dfs(info,pick,n-arrows, depth +1, score + sub, subscore);
            pick[depth] = 0;    //여길 안쏴본다.
        }

        dfs(info,pick,n, depth +1, score, subscore);
    }
}
