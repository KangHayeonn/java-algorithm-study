class Solution {
    static int[][] distance;
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        distance = new int[N+1][N+1];

        //거리를 최대 거리로 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                distance[i][j] = 500001;
            }
        }

        //info = [출발 노드, 도착 노드, 거리]
        for(int[] info : road) {
            if(distance[info[0]][info[1]] < info[2]) continue;

            //양방향 그래프 갱신
            distance[info[0]][info[1]] = info[2];
            distance[info[1]][info[0]] = info[2];
        }

        //한 정점에서 다른 정점까지의 거리 업데이트
        //모든 정점 검사
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=1;j++) {
                //J -> K distance보다 J -> I -> K 를 거쳐가는게 짧으면 업데이트
                for (int k = 1; k <= N; k++) {
                    if(distance[j][k] > distance[j][i] + distance[i][k]) {
                        distance[j][k] = distance[j][i] + distance[i][k];
                    }
                }
                //본인 노드 -> 본인 노드의 경우에는 0으로 설정
                if(i==j) {
                    distance[i][j] = 0;
                }
            }
        }
        
        //1번 마을로 부터 K 시간 내에 배달 가능한것만 출력
        for(int i=1;i<=N;i++) {
            if(distance[1][i] <= K) answer++;
        }

        return answer;
    }
}
