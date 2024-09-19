package java_algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 차량정비소 {
    static int N, M, K, A, B, result;
    static int[] NArr;
    static int[] MArr;
    static int[] KArr;
    static List<ArrayList<Integer>> customerList;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            A = Integer.parseInt(st.nextToken())-1; // 0부터 시작하기 위해 -1
            B = Integer.parseInt(st.nextToken())-1; // 0부터 시작하기 위해 -1
            NArr = new int[N];
            MArr = new int[M];
            KArr = new int[K];
            
            // N 배열 채워넣기 - 접수창구 시간
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                NArr[i] = Integer.parseInt(st.nextToken());
            }
            // M 배열 채워넣기 - 정비창구 시간
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                MArr[i] = Integer.parseInt(st.nextToken());
            }
            // K 배열 채워넣기, 고객의 도착 시간이 가장 큰 값 저장
            int maxTime = 0;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) {
                KArr[i] = Integer.parseInt(st.nextToken());
                maxTime = Math.max(maxTime, KArr[i]);
            }
//            System.out.println(Arrays.toString(NArr));
//            System.out.println(Arrays.toString(MArr));
//            System.out.println(Arrays.toString(KArr));
            
            result = 0;
            
            // 고객의 도착시간 기준으로 리스트에 넣는다. 
            customerList = new ArrayList<>();
            // 고객 리스트를 초기화
            for (int i = 0; i < maxTime+1; i++) {
                customerList.add(new ArrayList<Integer>());
            }
//            System.out.println(customerList);
            // 고객 리스트에 도착 시간 기준으로 고객 번호를 넣는다.
            for (int i = 0; i < KArr.length; i++) {
                int customerIdx = KArr[i];
                customerList.get(customerIdx).add(i);
            }
            System.out.println(customerList);
            
            // 접수창구 우선순위
            // 1. 여러 고객이 기다리고 있는 경우 고객번호가 낮은 순서대로 우선접수한다.
            // 2. 빈 창구가 여러개인경우 접수창구 번호가 작은곳부터 간다.
            // 우선순위 큐를 사용한다.
            
            // 정비창구 우선순위
            // 1. 먼저 기다리는 고객 우선
            // 2. 두명 이상의 고객들이 접수창구에서 동시에 접수를 완료하고, 정비창구로 이동한 경우, 이용했던 접수창구 번호가 작은 고객 우선
            // 3. 빈 창구가 여러개인경우 정비창구 번호가 작은곳부터 간다.
            // 동시에 접수한 고객들을 리스트 안에 넣고 접수창구번호 기준 오름차순 정렬을 해서 큐에 넣는다. 
            
            PriorityQueue<Integer> 접수큐 = new PriorityQueue<Integer>(); // (고객번호)
            Queue<int[]> 정비큐 = new ArrayDeque<int[]>(); // (고객번호, 접수번호);
            
            int[][] 접수배열 = new int[N][2]; // [접수번호][대기시간, 고객번호]
            for (int i = 0; i < 접수배열.length; i++) {
                Arrays.fill(접수배열[i], -1);
            }
            int[][] 정비배열 = new int[M][3]; // [정비번호][대기시간, 고객번호, 접수번호]
            for (int i = 0; i < 정비배열.length; i++) {
                Arrays.fill(정비배열[i], -1);
            }
            int count = 0;
            // 1. customerList 크기 만큼 반복한다. 
            for (int n = 0; n < customerList.size(); n++) {
                List<Integer> customerNumberList = customerList.get(n);
                // 고객번호 리스트가 비어있지 않으면
                if(!customerNumberList.isEmpty()) {
                    for (int i = 0; i < customerNumberList.size(); i++) {
                        int customerNumber = customerNumberList.get(i);
                        접수큐.offer(customerNumber);
                        customerNumber = 접수큐.poll();
                        // 접수배열이 비어있는지 확인
                        boolean isempty = false;
                        for (int j = 0; j < 접수배열.length; j++) {
                            if(접수배열[j][0]==-1) {
                                isempty = true;
                                접수배열[j][0] = NArr[j];
                                접수배열[j][1] = customerNumber;
                                break;
                            }
                        }
                        if(!isempty) {
                            접수큐.offer(customerNumber);
                        }
                    }
                }
                
//                System.out.println("count: " + count);
//                System.out.println("접수배열");
//                for (int i = 0; i < 접수배열.length; i++) {
//                    System.out.println(Arrays.toString(접수배열[i]));
//                }
//                System.out.println("정비배열");
//                for (int i = 0; i < 정비배열.length; i++) {
//                    System.out.println(Arrays.toString(정비배열[i]));
//                }
//                System.out.println("접수큐: " + 접수큐);
//                for (int i = 0; i < 정비큐.size(); i++) {
//                    System.out.println("정비큐: " + Arrays.toString(정비큐.peek()));
//                }
//                count++;
                
                List<int[]> sortList = new ArrayList<int[]>();
                // 접수배열 대기시간 -1
                for (int i = 0; i < 접수배열.length; i++) {
                    if(접수배열[i][0]!=-1) {
                        접수배열[i][0]--;    
                    }
                    
                    // 정비창구로 이동하기 위해 sortList에 [고객번호, 접수번호] 넣기
                    if(접수배열[i][0]==0) {
                        int[] temp = new int[2];
                        temp[0] = 접수배열[i][1];
                        temp[1] = i;
                        sortList.add(temp);
                        Arrays.fill(접수배열[i], -1);
                    }
                }
                // sortList를 접수번호 기준으로 오름차순 정렬
                if(!sortList.isEmpty()) {
                    Collections.sort(sortList, (o1, o2) -> Integer.compare(o1[1], o2[1]));
                    for (int i = 0; i < sortList.size(); i++) {
                        int[] temp = new int[2];
                        for (int j = 0; j < sortList.get(i).length; j++) {
                            temp[j] = sortList.get(i)[j];
                        }
                        정비큐.offer(temp);
                    }
                }
                sortList.clear();
                
                // 정비배열 대기시간 -1
                for (int i = 0; i < 정비배열.length; i++) {
                    if(정비배열[i][0]!=-1) {
                        정비배열[i][0]--;    
                    }
                    
                    // 정비배열 대기시간이 0이 되면, A와 B를 비교해서 같으면 result에 추가
                    if(정비배열[i][0]==0) {
                        if(정비배열[i][2]==A && i==B) {
                            result+=정비배열[i][1]+1;
                        }
                        Arrays.fill(정비배열[i], -1);
                    }
                    
                }
                
                // 정비큐가 비어있지 않으면 정비배열에 넣기
                
                if(!정비큐.isEmpty()) {
                    L:while(true) {
                        for (int i = 0; i < 정비배열.length; i++) {
                            if(정비배열[i][0]==-1) {
                                int[] curArr = 정비큐.poll();
                                정비배열[i][0] = MArr[i]; // 대기시간
                                정비배열[i][1] = curArr[0]; // 고객번호
                                정비배열[i][2] = curArr[1]; // 접수번호
                                break;
                            }
                            if(i==정비배열.length-1 || 정비큐.isEmpty()) break L;
                        }
                    }
                }
                
            }
            
            // 접수배열, 정비배열이 모두 -1이고, 접수큐와 정비큐 모두 empty면 break;
            boolean isExit1 = true;
            for (int i = 0; i < 접수배열.length; i++) {
                if(접수배열[i][0]!=-1) {
                    isExit1 = false;
                    break;
                }
            }
            for (int i = 0; i < 정비배열.length; i++) {
                if(정비배열[i][0]!=-1) {
                    isExit1 = false;
                    break;
                }
            }
            if(isExit1 && 접수큐.isEmpty() && 정비큐.isEmpty()) {
                if(result==0) result=-1;
                sb.append("#").append(tc).append(" ").append(result).append('\n');
                System.out.println("ddddasf");
                continue;
            }
            
            
            // 2. 다 이용할 때까지 반복한다.
            
            while(true) {
                
                
//                System.out.println("count: " + count);
//                System.out.println("접수배열");
//                for (int i = 0; i < 접수배열.length; i++) {
//                    System.out.println(Arrays.toString(접수배열[i]));
//                }
//                System.out.println("정비배열");
//                for (int i = 0; i < 정비배열.length; i++) {
//                    System.out.println(Arrays.toString(정비배열[i]));
//                }
//                System.out.println("접수큐: " + 접수큐);
//                for (int i = 0; i < 정비큐.size(); i++) {
//                    System.out.println("정비큐: " + Arrays.toString(정비큐.peek()));
//                }
//                System.out.println();
//                count++;
                
                
                
                if(!접수큐.isEmpty()) {
                    L:while(true) {
                        for (int i = 0; i < 접수배열.length; i++) {
                            if(접수배열[i][0]==-1) {
                                int curIdx = 접수큐.poll();
                                접수배열[i][0] = NArr[i]; // 대기시간
                                접수배열[i][1] = curIdx; // 고객번호
                                break;
                            }
                            if(i==접수배열.length-1 || 접수큐.isEmpty()) {
                                break L;
                            }
                        }
                    }
                }
                
                
                
                List<int[]> sortList = new ArrayList<int[]>();
                // 접수배열 대기시간 -1
                for (int i = 0; i < 접수배열.length; i++) {
                    if(접수배열[i][0]!=-1) {
                        접수배열[i][0]--;    
                    }
                    
                    // 정비창구로 이동하기 위해 sortList에 [고객번호, 접수번호] 넣기
                    if(접수배열[i][0]==0) {
                        int[] temp = new int[2];
                        temp[0] = 접수배열[i][1];
                        temp[1] = i;
                        sortList.add(temp);
                        Arrays.fill(접수배열[i], -1);
                    }
                }
                // sortList를 접수번호 기준으로 오름차순 정렬
                if(!sortList.isEmpty()) {
                    Collections.sort(sortList, (o1, o2) -> Integer.compare(o1[1], o2[1]));
                    for (int i = 0; i < sortList.size(); i++) {
                        int[] temp = new int[2];
                        for (int j = 0; j < sortList.get(i).length; j++) {
                            temp[j] = sortList.get(i)[j];
                        }
                        정비큐.offer(temp);
                    }
                }
                sortList.clear();
                
                // 정비배열 대기시간 -1
                for (int i = 0; i < 정비배열.length; i++) {
                    if(정비배열[i][0]!=-1) {
                        정비배열[i][0]--;    
                    }
                    
                    // 정비배열 대기시간이 0이 되면, A와 B를 비교해서 같으면 result에 추가
                    if(정비배열[i][0]==0) {
                        if(정비배열[i][2]==A && i==B) {
                            result+=정비배열[i][1]+1;
                        }
                        Arrays.fill(정비배열[i], -1);
                    }
                    
                }
                
                // 정비큐가 비어있지 않으면 정비배열에 넣기
                
                if(!정비큐.isEmpty()) {
                    L:while(true) {
                        for (int i = 0; i < 정비배열.length; i++) {
                            if(정비배열[i][0]==-1) {
                                int[] curArr = 정비큐.poll();
                                정비배열[i][0] = MArr[i]; // 대기시간
                                정비배열[i][1] = curArr[0]; // 고객번호
                                정비배열[i][2] = curArr[1]; // 접수번호
                                break;
                            }
                            if(i==정비배열.length-1 || 정비큐.isEmpty()) break L;
                        }
                    }
                }
                
                // 접수배열, 정비배열이 모두 -1이고, 접수큐와 정비큐 모두 empty면 break;
                boolean isExit = true;
                for (int i = 0; i < 접수배열.length; i++) {
                    if(접수배열[i][0]!=-1) {
                        isExit = false;
                        break;
                    }
                }
                for (int i = 0; i < 정비배열.length; i++) {
                    if(정비배열[i][0]!=-1) {
                        isExit = false;
                        break;
                    }
                }
                if(isExit && 접수큐.isEmpty() && 정비큐.isEmpty()) {
                    break;
                }
            }
            
            if(result==0) result=-1;
            sb.append("#").append(tc).append(" ").append(result).append('\n');
        }
        System.out.println(sb);
    }
}


//1
//2 1 4 2 1
//2 5
//1
//0 1 3 10


//1
//2 2 6 1 2
//3 2
//4 2
//0 0 1 2 3 4


//1
//2 2 8 2 1
//10 3
//2 9
//0 2 3 3 4 6 6 7
