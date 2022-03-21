public class Question5 {
    public static void main(String[] args) {
        A s = new C();
        s.p("1");
    }
    static class A{
        void p(String x){System.out.println("P");}
    }
    static class B extends A{
        void p(int x){System.out.println("Q");}
    }
    static class C extends A{
        void p(String x){System.out.println("R");}
    }
    static class D extends C{}
}
