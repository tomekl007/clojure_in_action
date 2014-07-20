package clojure_in_action_examples.ch2;

/**
 * @author Tomasz Lelek
 * @since 2014-07-20
 */
public class LoopRecur {
    public static void main(String[] args) {

    }

    //TailRec
    static int loop(int arg1, int arg2){
        if(arg1 == 1) return arg2;
        return loop( arg1 - 1, arg1 * arg2);
    }
}
