import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        
        ArrayList<Music> result = new ArrayList<>();

        //장르별 음악 count, 핵심: Map.getOrDefault 메소드
        HashMap<String, Integer> countPerGenre = new HashMap<>();
        for(int i=0;i<genres.length;i++) {
            countPerGenre.put(genres[i], countPerGenre.getOrDefault(genres[i], 0) + plays[i]);
        }

        // 1. 속한 노래가 많이 재생된 장르 수록 (내림차순 정렬)
        List<String> sortedGenre = new ArrayList<>(countPerGenre.keySet());
        sortedGenre.sort((o1, o2) -> countPerGenre.get(o2).compareTo(countPerGenre.get(o1)));

        // 2. 장르 내에서 많이 재생된 노래를 먼저 수록합니다.
        for(String genre : sortedGenre) {

            //음악별 재생 정보 저장
            ArrayList<Music> tempList = new ArrayList<>();
            for(int i=0;i<genres.length;i++) {
                if(genre.equals(genres[i])) {
                    tempList.add(new Music(genre, plays[i], i));
                }
            }
            
            //장르의 음악별 재생횟수 역 정렬 + index 정렬
            tempList.sort(Comparator.comparing(Music::getPlayCount).reversed()
                                    .thenComparing(Music::getMusicNum));

            // for(int i=0;i<2;i++) {
            //     result.add(tempList.get(i));
            // }
            result.add(tempList.get(0));
            if(tempList.size() > 1) {
                result.add(tempList.get(1));
            }
        }

        answer = new int[result.size()];

        for(int i=0;i<result.size();i++) {
            answer[i] = result.get(i).getMusicNum();
        }

        return answer;
    }
    
    public class Music {
        String genre;
        int playCount;
        int musicNum;

        public Music(String genre, int playCount, int musicNum) {
            this.genre = genre;
            this.playCount = playCount;
            this.musicNum = musicNum;
        }

        public int getPlayCount() {
            return playCount;
        }

        public int getMusicNum() {
            return musicNum;
        }
    }
}

