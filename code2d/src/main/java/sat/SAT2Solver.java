package sat;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import sat.formula.Clause;
import sat.formula.Formula;
import sat.formula.Literal;
import sat.formula.PosLiteral;

public class SAT2Solver {
    public static Ggraph fileReading(String fileName) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            int varNum = 0,calNum = 0;
            Ggraph g = new Ggraph();
            while (sc.hasNextLine()) {  //按行读取字符串
                String lineprime = sc.nextLine();
                //System.out.println(lineprime);
                if(lineprime.length()<=0){continue;}
                String[] line = lineprime.split(" ");
                if(line[0].indexOf("c")!=-1){continue;}
                else if(line[0].indexOf("p")!=-1) {
                    if (line[1].indexOf("cnf") != -1) {
                        calNum = Integer.parseInt(line[2]);
                        varNum = Integer.parseInt(line[3]);
                        g = new Ggraph(calNum*2,varNum*2);
                        continue;
                    }
                }
                else{
                    int a = Integer.parseInt(line[0]);
                    int b = Integer.parseInt(line[1]);
                    if(a == b){continue;}
                    if(a>0&&b>0){g.addSide(a+varNum,b);g.addSide(b+varNum,a);}
                    if(a<0&&b>0){g.addSide(-a,b);g.addSide(b+varNum,-a+varNum);}
                    if(a>0&&b<0){g.addSide(a+varNum,-b+varNum);g.addSide(-b,a);}
                    if(a<0&&b<0){g.addSide(-a,-b+varNum);g.addSide(-b,-a+varNum);}
                }
            }
            return g;
        }
    }
    public static String solve(Ggraph g){
        g.solveSCC();
        String result = new String();
        int[] color = g.getColor();
        for (int i = 1; i <= (g.getNodeN()/2); ++i)
            if (color[i] == color[i + g.getNodeN()/2]) {
                return "NO SOLUTION";
            } else if(color[i] < color[i + g.getNodeN()/2]){
                result+=("x"+i+" = 1,");
            }else {
                result+=("x"+i+" = 0,");
            }
        return result;
    }
}
