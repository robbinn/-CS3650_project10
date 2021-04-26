package com.siwen;
import java.util.Set;

public class StringToken {

    public StringToken(String str, int type) {
        _token = str;
        _type = type;
        _keywords = Set.of("class","constructor","function","method","field","static","var","int","char","boolean",
                "void","true","false","null","this","let","do","if","else","while","return");
        _symbol = Set.of("{","}","(",")","[","]",".",",",";","+","-","*","/","&","|","<",">","=");
    }

    public int type(){
        return _type;
    }

    public String stringType() {
        if (_keywords.contains(getToken()))
            return "keyword";
        else if (_symbol.contains(getToken()))
            return "symbol";
        else if((type() == 1 || type() == 3))
            return "identifier";
        else if (type() == 11 || type() == 13)
            return "integerConstant";
        else if (type() == 19)
            return "stringConstant";

        else if (type() == 41)
            return "SPACE";

        //in this program, means if type() = -1.
        else
            return "UNKNOWN";
    }

    public String getToken() {
        return _token;
    }

    private final String _token;
    private final int _type;
    private final Set<String> _keywords;
    private final Set<String> _symbol;

    @Override
    public String toString() {
        return getToken();
    }
}
