/**
 * Created by wassimseifeddine on 12/21/15.
 */
public class Constants {

    private static String compileSelectedFirstPart = "public class Main{\n" +
            "\n" +
            "\tpublic static void main (String[] args){\n" +
            "\n" +
            "\t\tSystem.out.println(\"Hello,Wordls\");\n" +
            "}\n";
    private static String getCompileSelectedSecondPart = "\n" +
            "}";


    public Constants(){

    }

    public static String getCompileSelectedFirstPart(){
        return compileSelectedFirstPart;
    }

    public static String getGetCompileSelectedSecondPart(){

        return getCompileSelectedSecondPart;
    }


}
