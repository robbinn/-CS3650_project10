package com.siwen;

public class StringToken {

    static int NUMBER = 1;
    static int OPERATOR = 2;
    static int FUNCTION = 3;
    static int UNKNOWN = -1;

    static int LEFT = 7;
    static int RIGHT = 8;

    public StringToken(){}
    public StringToken(String str, int type) {
        _token = str;
        _type = type;
    }

    public int type(){
        return _type;
    }

    public int intType() {
        if(type() == 1 || type() == 2)
            return NUMBER;
        else if (type() == 5)
            return OPERATOR;
        else
            return UNKNOWN;
    }

    public String stringType() {

        if(type() == 1 || type() == 2)
            return "NUMBER";
        else if (type() == 5)
            return "OPERATOR";
        else if (type() == 7)
            return "PARENTHESIS";

            //if type() = -1.
        else
            return "UNKNOWN";
    }

    public String getToken() {
        return _token;
    }

    private String _token;
    private int _type;

    @Override
    public String toString() {
        return "|" + getToken() + "|";
    }
}
