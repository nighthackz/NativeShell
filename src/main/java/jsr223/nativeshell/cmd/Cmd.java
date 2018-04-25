package jsr223.nativeshell.cmd;

import jsr223.nativeshell.NativeShell;

import javax.script.ScriptEngineFactory;
import java.io.File;

public class Cmd implements NativeShell {
    
    private final ScriptEngineFactory factory;
    public Cmd(ScriptEngineFactory f) { factory = f; }

    @Override
    public ProcessBuilder createProcess(File commandAsFile) {
        return new ProcessBuilder("cmd", "/q", "/c", commandAsFile.getAbsolutePath());
    }

    @Override
    public ProcessBuilder createProcess(String command) {
        return new ProcessBuilder("cmd", "/c", command);
    }

    @Override
    public String getInstalledVersionCommand() {
        return "echo|set /p=%CmdExtVersion%";
    }

    @Override
    public String getMajorVersionCommand() {
        return getInstalledVersionCommand();
    }

    @Override
    public ScriptEngineFactory getFactory() {
        return factory;
    }

    @Override
    public String getFileExtension() {
        return ".bat";
    }

}
