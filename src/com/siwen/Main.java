package com.siwen;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        String userIn = "15.+6";
        char[] charStr = userIn.toCharArray();
        Queue<StringToken> queue = new LinkedList<>();

        StringTokenizer stk = new StringTokenizer(charStr);

        do {
            StringToken t = stk.extractToken();
            queue.add(t);
            System.out.printf("%-14s%s%n", t.stringType(),t);
        } while (stk.more());
    }
}
