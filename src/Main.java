public class Main {

    public static void main(String[] args) {

        AspectASWriter writer = new AspectASWriter();
        writer.setPath("AspectAS3/src/Tests/original");
        writer.setDirectoryOutput("AspectAS3/src/Tests/instrumented");

        if (args.length == 0) {                // just for debug
            writer.instrument("Test1.as");
        } else {
            for (String arg : args) {
                writer.instrument(arg);
            }
        }
    }
}