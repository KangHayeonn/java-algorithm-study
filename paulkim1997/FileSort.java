import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        List<FileInfo> fileList = new ArrayList<>();

        for(int i=0;i<files.length;i++) {
            String currentFile = files[i];
            String head ="";
            String number = "";
            String tail = "";
            int index = 0;
            int startIndex = 0;
            //head
            while(!isNumeric(currentFile.charAt(index))) {
                index++;
            }
            head = currentFile.substring(startIndex, index);
            startIndex = index;

            //number
            while(isNumeric(currentFile.charAt(index))) {
                index++;
                if(index == currentFile.length()) break;
            }
            number = currentFile.substring(startIndex, index);
            startIndex = index;

            //tail
            tail = currentFile.substring(startIndex);
            
            FileInfo fileInfo = new FileInfo(head, number, tail);
            fileList.add(fileInfo);


            System.out.println();

        }
        fileList.sort(Comparator.comparing(FileInfo::getHeadLower)
                      .thenComparing(FileInfo::getNumberValue));
      
        String[] answer = new String[fileList.size()];
        for(int i=0;i<fileList.size();i++) {
            FileInfo info = fileList.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(info.getHead());
            sb.append(info.getNumber());
            sb.append(info.getTail());
            answer[i] = sb.toString();
        }
        return answer;
    }
    
     public class FileInfo {
        private String head;
        private String number;
        private String tail;

        public FileInfo(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        public String getHeadLower() {
            return this.head.toLowerCase();
        }

        public int getNumberValue() {
            return Integer.parseInt(this.number);
        }

        public String getNumber() {
            return this.number;
        }

        public String getHead() {
            return head;
        }

        public String getTail() {
            return this.tail;
        }
    }

    public boolean isNumeric(char c) {
        return c - '0' < 10 && c - '0' >= 0;
    }
}
