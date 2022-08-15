import java.util.*;
import java.util.regex.Pattern;


class Solution {
   public static String[] solution(String[] files) {

        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String head1="", head2="", numStr1="", numStr2="", temp;
                int num1, num2, i=0, j=0;

                o1 =  o1.toUpperCase();
                o2 =  o2.toUpperCase();

                for (i=0;i<o1.length();i++){
                    temp = o1.substring(0,i+1);
                    if(!Pattern.matches("^[a-zA-Z.\\-\\s]*$", temp)) break;
                    head1 = temp;
                }
                
                for(j=i;j<o1.length();j++){
                    temp = o1.substring(i,j+1);
                    if(!Pattern.matches("^[0-9]*$", temp)) break;
                    numStr1=temp;
                }

                num1 =  Integer.parseInt(numStr1);


                for (i=0;i<o2.length();i++){
                    temp = o2.substring(0,i+1);
                    if(!Pattern.matches("^[a-zA-Z.\\-\\s]*$", temp)) break;
                    head2 = temp;
                }

              
                for(j=i;j<o2.length();j++){
                    temp = o2.substring(i,j+1);
                    if(!Pattern.matches("^[0-9]*$", temp)) break;
                    numStr2=temp;
                }

                num2 =  Integer.parseInt(numStr2);

                if(head1.compareTo(head2)==0) return num1 -num2  ;
                return head1.compareTo(head2);
            }
        });
        return files;
    }
}
