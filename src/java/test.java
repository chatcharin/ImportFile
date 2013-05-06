/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aek
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String t = "Thai'a";
        System.out.println(t);
        System.out.println(t.replaceAll("'","y\u2019"));
        String a = t.replaceAll("'","\u2019");
        System.out.println(a);
    }
}
