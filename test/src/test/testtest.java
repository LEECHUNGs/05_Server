package test;

public class testtest {
	public static void main(String[] args) {
		String[] id_list = {"muzi", "frodo", "apeach", "neo"};
		String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
		int k = 2;
		
		StringBuffer[] sb = new StringBuffer[id_list.length];
        for(int i = 0; i<sb.length; i++)
        	sb[i] = new StringBuffer();
        
        
        int[] c = new int[id_list.length];
        int space = 0;
        int a = 0;
        int b = 0;
        for(int i = 0; i<report.length; i++) {
            space = report[i].indexOf(" ");
            for(int j = 0; j<id_list.length; j++) { // 신고자 신고받은자 탐색
                if(id_list[j].equals(report[i].substring(0, space))) // 신고자
                    a = j;
                if(id_list[j].equals(report[i].substring(space+1))) // 피신고자
                    b = j;
            }
            
            if(sb[a].indexOf(b+"") != -1)  // 중복 신고
                continue;
            
            sb[a].append(b);
            sb[a].append(" ");
            
            c[b]++; // 신고 스택
        }
        
        int[] answer = new int[id_list.length];
        for(int i = 0; i<id_list.length; i++) {
            if(c[i] >= k) {
                for(int j = 0; j<sb.length; j++) {
                    if(sb[j].indexOf(i+"") > 0)
                        answer[j]++;
                }
            }
        }
        
        
        for(int i : answer)
        	System.out.print(answer + " ");
		
	}
}
