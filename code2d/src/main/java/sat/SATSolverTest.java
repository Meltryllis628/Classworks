package sat;

/*
import static org.junit.Assert.*;

import org.junit.Test;
*/

import static sat.SATSolver.solve;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.Normalizer;
import java.util.Scanner;

import sat.env.*;
import sat.formula.*;


public class SATSolverTest {
    Literal a = PosLiteral.make("a");
    Literal b = PosLiteral.make("b");
    Literal c = PosLiteral.make("c");
    Literal na = a.getNegation();
    Literal nb = b.getNegation();
    Literal nc = c.getNegation();


    public static Formula fileReading(String fileName) throws FileNotFoundException {
        try (Scanner sc = new Scanner(new FileReader(fileName))) {
            int varNum = 0,calNum = 0;
            Formula f = new Formula();
            while (sc.hasNextLine()) {  //按行读取字符串
                int add = 1;
                String lineprime = sc.nextLine();
                //System.out.println(lineprime);
                if(lineprime.length()<=0){continue;}
                String[] line = lineprime.split(" ");
                Clause c = new Clause();
                for(int i = 0;i<line.length;i++){
                    if(line[i].indexOf("c")!=-1){add = 0;break;}
                    if(line[i].indexOf("p")!=-1) {
                        add = 0;
                        if (line[i + 1].indexOf("cnf") != -1) {
                            varNum = Integer.parseInt(line[i + 2]);
                            calNum = Integer.parseInt(line[i + 3]);
                            //System.out.println(varNum + " " + calNum);
                            break;
                        }
                    }
                    Literal literal = PosLiteral.make(Integer.toString(Math.abs(Integer.parseInt(line[i]))));
                    if((Integer.parseInt(line[i]))<0){
                        c=c.add(literal.getNegation());
                    }
                    else if ((Integer.parseInt(line[i]))>0){
                    }
                    if (c == null){
                        c = new Clause();
                    }
                }
                if(c!=null&&!c.isEmpty()){f=f.addClause(c);}
            }
            return f;
        }
    }
	
	// TODO: add the main method that reads the .cnf file and calls SATSolver.solve to determine the satisfiability
    public static void main(String[] args) throws FileNotFoundException {
        Formula f1 = fileReading("F:\\Test.cnf");
        //System.out.println(f);
        System.out.println("SAT solver starts!!!");
        long started = System.nanoTime();
        Environment e1 = SATSolver.solve(f1);
        long time = System.nanoTime();
        long timeTaken= time - started;
        System.out.println("Time:" + timeTaken/1000000.0 + "ms");
        if(e1 == null){
            System.out.println("Formula Unsatisfiable");
        }
        else {
            System.out.println("Formula Satisfiable");
        }
        //System.out.println(SATSolver.solve(f));

    }


    public void testSATSolver1(){
    	// (a v b)
    	Environment e = solve(makeFm(makeCl(a,b))	);
/*
    	assertTrue( "one of the literals should be set to true",
    			Bool.TRUE == e.get(a.getVariable())  
    			|| Bool.TRUE == e.get(b.getVariable())	);
    	
*/    	
    }
    
    
    public void testSATSolver2(){
    	// (~a)
    	Environment e = solve(makeFm(makeCl(na)));
/*
    	assertEquals( Bool.FALSE, e.get(na.getVariable()));
*/    	
    }
    
    private static Formula makeFm(Clause... e) {
        Formula f = new Formula();
        for (Clause c : e) {
            f = f.addClause(c);
        }
        return f;
    }
    
    private static Clause makeCl(Literal... e) {
        Clause c = new Clause();
        for (Literal l : e) {
            c = c.add(l);
        }
        return c;
    }
    
    
    
}