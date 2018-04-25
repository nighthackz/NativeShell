package jsr223.nativeshell;

import jsr223.nativeshell.bash.Bash;
import jsr223.nativeshell.cmd.Cmd;

import javax.script.ScriptException;

public class Main {

    public static void main(String[] args) throws ScriptException {
        NativeShell shell;
        if (null == args[0]) {
            System.err.println("First argument must be shell name (cmd/bash)");
            System.exit(-1);
        } else switch (args[0]) {
            case "cmd":
                shell = new Cmd(null);
                break;
            case "bash":
                shell = new Bash(null);
                break;
            default:
                System.err.println("First argument must be shell name (cmd/bash)");
                System.exit(-1);
        }

        String script = "";
        for (int i = 1; i < args.length; i++) {
            String arg = args[i];
            script += arg + " ";
        }

        Object returnCode = new NativeShellScriptEngine(null).eval(script);
        System.exit((Integer) returnCode);
    }
}
