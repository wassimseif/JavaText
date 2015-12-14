import java.io.*;
import java.lang.Runtime;
import java.nio.charset.Charset;

public class Compiler {

    public static final String UTF8_BOM = "\uFEFF";
    private enum  Job {
        compileAll, compileSelectedWithoutWrapping, compileSelectedWithWrapping, Run
    }
    private String code;
    private long timeForDelay = 1000;
    private String documentName;

    public Compiler(){
        System.out.print("You can't compile vacuum!");
        try {
            Thread.sleep(timeForDelay);
            System.out.print("Idiot!");
        }catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        return ;
    }
    public Compiler(String codeToCompile,String name){

        this.code = codeToCompile;
        this.documentName = name;
        System.out.println(code);
       // executeShellScript(Job.compileAll);
      if (createNewFile()){
            fillFile();
        }

    }

    private boolean createNewFile(){
        try {

            File file = new File(documentName);


            if (file.createNewFile()){
                System.out.println("File is created!" );
                return true;
            }else{
                System.out.println("File already exists.");
                return false ;
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
            code = code.replace("\n", "").replace("\r", "");
            File file = new File(documentName);

           BufferedWriter  writer = new BufferedWriter(new FileWriter(file));
            writer.write(code);
            writer.flush();
            writer.close();

        }catch (IOException ex){
            ex.printStackTrace();
        }
    }


    public void compileAllText(){
        try {
            String target = "./compile.sh";
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(target);

            proc.waitFor();

            StringBuffer output = new StringBuffer();
            BufferedReader scriptOutletReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String scriptOutputLine = "";

            while ((scriptOutputLine = scriptOutletReader.readLine())!= null) {
                output.append(scriptOutputLine + "\n");
            }


        } catch (Throwable t)
        {
            t.printStackTrace();
        }

    }


    private void executeShellScript(Job job){

        switch (job){
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

}
