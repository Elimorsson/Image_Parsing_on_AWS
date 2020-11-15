package localApplication;

public class LocalApplicationMain {
    /**
     * args[0] = name of inputfile
     * args[1] = name of outputfile
     * args[2] = n -> integer to tell how many messages per worker
     * args[3](optional) = if the manager should terminate, it will have "terminate" in args[3]
     */
    public static void main(String[] args) throws Exception {
        // checking arguments
        String argsCheck = LocalApplication.checkArguments(args);
        if (argsCheck != null) {
            System.out.println(argsCheck);
        } else {
            // arguments are fine
            LocalApplication localApp;
            if (args.length > 3 && args[3].equals("terminate")){
                localApp = new LocalApplication(args[0],args[1],Integer.parseInt(args[2]),true);
            }
            else {
                localApp = new LocalApplication(args[0],args[1],Integer.parseInt(args[2]),false);
            }
            localApp.run();
        }
    }
}
