import java.util.ArrayList;
import java.util.Scanner;
 
public class Solution {
    static class Atom implements Comparable<Atom>{
        double x;
        double y;
        int dir;
        int energy;
         
        public Atom(double x, double y, int dir, int energy) {
            super();
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.energy = energy;
        }
 
        @Override
        public int compareTo(Atom o) {
            return (this.x == o.x) ? Double.compare(this.y, o.y) : Double.compare(this.x, o.x);
        }
    }
     
    static double[] dx = {0.0, 0.0, -0.5, 0.5};
    static double[] dy = {0.5, -0.5, 0.0, 0.0};
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        int T = sc.nextInt();
         
        for(int t = 1; t <= T; t++) {
            int N = sc.nextInt();
            int ans = 0;
            ArrayList<Atom> atoms = new ArrayList();
             
            for(int i = 0; i < N; i++) {
                atoms.add(new Atom(sc.nextDouble(), sc.nextDouble(), sc.nextInt(), sc.nextInt()));
            }
 
            while(atoms.size()>1) {
                for(int i = 0; i < atoms.size(); i++) {
                    Atom a = atoms.get(i);
                    a.x += dx[a.dir];
                    a.y += dy[a.dir];
                }
                 
                atoms.sort(null);
                 
                // 충돌
                int i = 0;
                for(; i < atoms.size();) {
                    Atom a1 = atoms.get(i);
                    boolean flag = false;
                    int j = i+1;
                    for(; j < atoms.size();) {
                        Atom a2 = atoms.get(j);
                        if(a1.x == a2.x && a1.y == a2.y) {
                            atoms.remove(a2);
                            ans += a2.energy;
                            flag= true;
                             
                        }else {
                            break;
                        }
                    }
                    if(flag) {
                        atoms.remove(a1);
                        ans += a1.energy;
                    }else
                        i++;
                }
 
                // 벗어남
                 
                for(int k = atoms.size()-1; k >= 0 ; k--) {
                    Atom a = atoms.get(k);
                    if(a.x < -1000 || a.x > 1000 || a.y < -1000 || a.y > 1000) {
                        atoms.remove(a);
                    }
                }
            }
            System.out.printf("#%d %d\n", t, ans);
        }
    }
}
