import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Solution {
       private static final Pattern GET_NUMBER = Pattern.compile("[0-9]+");

    public static int[] solution(String[] info, String[] query) {

        HashMap<String, Integer> infoHashMap = new HashMap<>();
        int answer[] = new int[query.length];
        int[][][][][] arr = new int[3][2][2][2][100002];
        int[][][][][] arrSum = new int[3][2][2][2][100002];

        int language, languageEnd, group, groupEnd, jos, josEnd, food, foodEnd;

        infoHashMap.put("cpp",0);
        infoHashMap.put("java",1);
        infoHashMap.put("python",2);
        infoHashMap.put("backend",0);
        infoHashMap.put("frontend",1);
        infoHashMap.put("junior",0);
        infoHashMap.put("senior",1);
        infoHashMap.put("chicken",0);
        infoHashMap.put("pizza",1);

        for(String i : info){
            String temp[] = i.split("[0-9]");

            temp = temp[0].split(" ");
            String b = getOnlyNumber(i);
            int score = Integer.parseInt(b);

            language = infoHashMap.get(temp[0]);
            group = infoHashMap.get(temp[1]);
            jos = infoHashMap.get(temp[2]);
            food = infoHashMap.get(temp[3]);

            arr[language][group][jos][food][score]++;
        }


        for (int i = 0; i < 3; i++) {       //누적합으로 변경.
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        for (int m = 100000; m >=0; m--) {
                            arrSum[i][j][k][l][m] = arrSum[i][j][k][l][m+1] +arr[i][j][k][l][m];
                        }
                    }
                }
            }
        }


        int idx=0;

        for(String qr : query){
            String sp[] = qr.split(" ");

            int sum = 0, getSum = Integer.parseInt(sp[7]);

            if(sp[0].equals("-")){
                language = 0;
                languageEnd = 3;
            }else{
                language = infoHashMap.get(sp[0]);
                languageEnd = language +1;
            }

            if(sp[2].equals("-")){
                group = 0;
                groupEnd = 2;
            }else{
                group = infoHashMap.get(sp[2]);
                groupEnd = group +1;
            }

            if(sp[4].equals("-")){
                jos = 0;
                josEnd = 2;
            }else{
                jos = infoHashMap.get(sp[4]);
                josEnd = jos +1;
            }

            if(sp[6].equals("-")){
                food = 0;
                foodEnd = 2;
            }else{
                food = infoHashMap.get(sp[6]);
                foodEnd = food +1;
            }

            for ( int i=language; i < languageEnd; i++) {       //누적합으로 변경.
                for (int j = group; j < groupEnd; j++) {
                    for (int k =jos; k < josEnd; k++) {
                        for ( int m=food; m < foodEnd; m++) {
                            sum += arrSum[i][j][k][m][getSum];
                        }
                    }
                }
            }
            answer[idx++] = sum;

        }

        return answer;

    }

    public static String getOnlyNumber(final String str) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = GET_NUMBER.matcher(str);

        while (matcher.find()) {
            sb.append(matcher.group());
        }
        return sb.toString();
    }
}
