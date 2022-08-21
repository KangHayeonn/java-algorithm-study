import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    class MusicInfo{
        int count=0;
        ArrayList<Musics> musics = new ArrayList<>();
    }

    static class Musics{
        int idx, plays;

        public Musics(int idx, int plays) {
            this.idx = idx;
            this.plays = plays;
        }
    }


    public int[] solution(String[] genres, int[] plays) {

        int[] answer = {};
        HashMap<String,MusicInfo> hashMap = new HashMap<>();

        for(int i=0;i<genres.length; i++){
            MusicInfo now;

            if(!hashMap.containsKey(genres[i])){
                hashMap.put(genres[i], new MusicInfo());
            }

            now = hashMap.get(genres[i]);

            now.count += plays[i];
            now.musics.add(new Musics(i,plays[i]));
        }

        String[] keySet = new String[hashMap.keySet().size()];
        int idx =0;

        for (String s : hashMap.keySet()) {
            keySet[idx++] = s;
        }

        Arrays.sort(keySet, (a,b)->{
            return hashMap.get(b).count  -  hashMap.get(a).count; 
        });
        
        answer = new int[keySet.length*2];
        idx = 0;

        for (String s : keySet) {
           MusicInfo now = hashMap.get(s);

           Collections.sort(now.musics, (a, b)->{
               if(a.plays==b.plays){
                   return a.idx - b.idx;
               }
               return b.plays - a.plays;
           });

           answer[idx++] = now.musics.get(0).idx;
           if(now.musics.size()>1)
            answer[idx++] = now.musics.get(1).idx;
        }
        int[] newAnswer = new int[idx];
        
        for(int i=0;i<idx;i++){
            newAnswer[i] = answer[i];
        }

        return newAnswer;
    }
}
