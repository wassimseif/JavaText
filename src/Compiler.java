import javax.swing.*;
import java.io.*;
import java.lang.Runtime;
import java.util.HashMap;
import java.util.Map;


public class Compiler {


    public static final String UTF8_BOM = "\uFEFF";

    public enum Job {
        compileAll, compileSelectedWithoutWrapping, compileSelectedWithWrapping, Run
    }

    private String code;
    private long timeForDelay = 1000;
    private String documentName;

    public Compiler() {
        System.out.print("You can't compile vacuum!");
        try {
            Thread.sleep(timeForDelay);
            System.out.print("Idiot!");
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return;
    }

    public Compiler(String codeToCompile, String name, Job job) {

        this.code = codeToCompile;
        this.documentName = name;

        if (!createNewFile()) {
            System.out.println("File Already exists! when are you planning to fix me ?");

            return;
        }


        switch (job){
            case compileAll:
                fillFile();
                compileAllText();
            case compileSelectedWithoutWrapping:

                code = wrapSelectedText();
                System.out.println(code);
                System.out.println("\n\n\n");

                fillFile();
                compileAllText();
        }



    }


    private boolean createNewFile() {
        try {

            File file = new File(documentName);


            if (file.createNewFile()) {
                System.out.println("File is created!");
                return true;
            } else {
                System.out.println("File already exists.");
                return false;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void fillFile() {

        try {

            code = code + System.getProperty("line.separator");
            // code = code.replaceAll("\\s","");
          //  code = code.replace("\n", "").replace("\r", "");
            File file = new File(documentName);

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(code);
            writer.flush();
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String wrapSelectedText(){
        code = Constants.getCompileSelectedFirstPart() + code;
        code =  code + Constants.getGetCompileSelectedSecondPart() ;

        return code;
    }


    public void compileAllText() {
        try {
            String target = ("javac " + documentName);
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(target);

            proc.waitFor();

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            // read the output from the command
           // System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
           // System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            run();


        } catch (Throwable t) {
            t.printStackTrace();
        }


    }


    private void executeShellScript(Job job) {

        switch (job) {
            case compileAll:
                System.out.println("Should Compile");
                compileAllText();
                break;
            case Run:
                System.out.println("Should Run");
                break;
            default:
                System.out.println("Default Case");
                break;
        }


    }

    private static String removeUTF8BOM(String s) {
        if (s.startsWith(UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }

    private void run() {
        try {
            String target = ("java Main");
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(target);

            proc.waitFor();

            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

            // read the output from the command
           // System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            //System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }


        } catch (Throwable t) {
            t.printStackTrace();
        }


    }

    private void comipleSelected(){



    }




}
