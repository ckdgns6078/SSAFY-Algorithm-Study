import java.util.*;
 
class User{
    int idx;            //list의 index
    int num;
    int arrivalTime;    //도착시간
    int deskNum;        //이용한 접수소 num
    int fixNum;         //이용한 정비소 num
 
    User(int idx , int num , int arrivalTime){
        this.idx = idx;
        this.num = num;
        this.arrivalTime = arrivalTime;
        this.deskNum = 0;
        this.fixNum = 0;
 
    }
     
}
 
/**
 * index 설정이 잘못되어서 확인해야됨
 * @author ckdgn
 *
 */
 
public class Solution {
 
    static int n;
    static int m;
    static int k;
    static int a;
    static int b;
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            n = sc.nextInt();   //접수 창구의 수
            m = sc.nextInt();   //정비 창구의 수
            k = sc.nextInt();   //정비소 방문 수
            a = sc.nextInt();   //지갑 두고간 접수 창구
            b = sc.nextInt();   //지갑 두고간 정비 창구
             
            ArrayList<User> userList = new ArrayList();   // 이용자 정보
            ArrayList<int[]> desk = new ArrayList();  // 사용하고 있는 접수 창구
            ArrayList<int[]> fix = new ArrayList();       // 사용하고 있는 정비 창구
            PriorityQueue<User> deskWait = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.num, o2.num)); // 접수 wait
            Queue<Integer> fixWait = new ArrayDeque();        // 정비소 wait
 
            int result =0;
             
            //index info : 걸리는시간 , 증가하는 idx , 사용하고 있는 user index
            for(int i=0; i < n ; i++) {
                int time = sc.nextInt();
                desk.add(new int[] {i+1 ,time , 0 , -1});
            }
            //index info : 소요시간 , 증가하는 idx , 사용하고 있는 user index
            for(int i = 0 ; i < m ; i++) {
                int time = sc.nextInt();
                fix.add(new int[] {i+1 ,time , 0 , -1});
            }
            //장소를 방문한 사람들의 정보 저장    //사용자 값 -1 이 list에 들어있는 값이 된다.
            for(int i = 0 ; i < k ; i++) {
                int arrivalTime = sc.nextInt();
                userList.add(new User(i , i+1 , arrivalTime));
            }       
            int peopleCnt = k;
            int time = 0;
             
            while(peopleCnt>0) { 
                //waiting 으로 사람이동
                for(int i = 0 ; i < userList.size(); i++) {
                    int arriveTime = userList.get(i).arrivalTime;
                    if(arriveTime == time) {
                        deskWait.add(userList.get(i));
                    }
                }
                 
                 
                //접수를 대기하는 인원이 있을 경우 접수소로 이동시킨다.
                if(deskWait.size()>0) {
                    int waitCnt = deskWait.size();
                    for(int i = 0 ; i < desk.size(); i++) {
                        //모두 넣었을 경우 종료
                        if(waitCnt == 0) {
                            break;
                        }
                        //사람이 있을 경우 desk 에 넣기
                        //desk 부터 사람들으 idx로 관리한다.
                        if(desk.get(i)[3]==-1) {
                            User user = deskWait.poll();
                            desk.get(i)[3] = user.idx;
                            waitCnt--;
                        }
                    }
                }
                 
                //사용자들의 소요시간을 증가시킨다.
                //점검이 다음 점검 기다리는 사람으로 이동시킨다.
                for(int i = 0 ; i < desk.size(); i++) {
                     
                    if(desk.get(i)[3]!=-1) {
                        desk.get(i)[2]++;
                    }
                    int endTime = desk.get(i)[1];
                    //정비소 기다리기로 이동시키고 초기회 시켜주기
                    if(endTime == desk.get(i)[2]) {
                        //사용한 정보 저장하기
                        //사용자 정보 : [3]
                        int userIdx = desk.get(i)[3];
                        int deskIdx = desk.get(i)[0];
                        userList.get(userIdx).deskNum = deskIdx;
                        fixWait.add(userIdx);
//                      System.out.println("고치러가는 사용자 정보 : " + userIdx);
                        desk.get(i)[2]=0;
                        desk.get(i)[3]=-1;
                         
                    }
                }
             
                 
                //정비소를 기다리는 사람이 있을 경우 정비소로 이동한다.
                if(fixWait.size()>0) {
                    int waitCnt = fixWait.size();
                    for(int i = 0 ; i < fix.size() ; i++) {
                        if(waitCnt ==0) {
                            break;
                        }
                        if(fix.get(i)[3]==-1) {
                            int idx = fixWait.poll();
//                          System.out.println("들어간 idx : " + idx);
                            fix.get(i)[3] = idx;
                            waitCnt--;
                        }
                         
                    }
                }
                 
                for(int i = 0 ; i < fix.size(); i++) {
                    if(fix.get(i)[3]!=-1) {
                        fix.get(i)[2]++;                        
                    }
                    int endTime = fix.get(i)[1];
                    //정비소 기다리기로 이동시키고 초기회 시켜주기
                    if(endTime == fix.get(i)[2]) {
                        //사용한 정보 저장하기
                        //사용자 정보 : [3]
                        int userIdx = fix.get(i)[3];
                        int fixIdx = fix.get(i)[0];
                        userList.get(userIdx).fixNum = fixIdx;
                        fix.get(i)[2]=0;
                        fix.get(i)[3]=-1;
                        peopleCnt--;
                         
//                      System.out.println("현재시간" +time + "사용자 인덱스 : " + userList.get(userIdx).num);
//                      System.out.println("접수창구 :" + userList.get(userIdx).deskNum + "정비창구 " + userList.get(userIdx).fixNum);
                        if(userList.get(userIdx).deskNum == a && userList.get(userIdx).fixNum ==b) {
//                          System.out.println("사용자 번호 : " + userList.get(userIdx).num);
                            result+= userList.get(userIdx).num;
                        }
                    }
                }
                time++; 
            }
            result = (result==0) ? -1 : result;
            System.out.println("#" + tc +" " + result);
             
        }
 
    }
 
}
