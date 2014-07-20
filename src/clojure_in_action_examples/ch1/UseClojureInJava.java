package clojure_in_action_examples.ch1;

import clojure.lang.RT;
import clojure.lang.Var;

import java.io.IOException;

/**
 * @author Tomasz Lelek
 * @since 2014-07-20
 */
public class UseClojureInJava {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        UseClojureInJava useClojureInJava = new UseClojureInJava();


        RT.loadResourceScript("LazySeq.clj");
        Var aClojureFunction = RT.var("clojure_in_action_examples.ch1.LazySeq",
                "fib-safe");
        System.out.println(aClojureFunction.invoke(10));
    }
}
