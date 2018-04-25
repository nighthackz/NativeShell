package jsr223.nativeshell;

import java.io.Reader;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

public class NativeShellScriptEngine extends AbstractScriptEngine {

    private final NativeShell nativeShell;

    public NativeShellScriptEngine(NativeShell nativeShell) {
        this.nativeShell = nativeShell;
    }

    @Override
    public Object eval(String script, ScriptContext context) throws ScriptException {
        try {
            if(context.getAttribute("returnstdout")==Boolean.TRUE) {
                return new NativeShellRunner(nativeShell).runAndGetOutput(script).trim();
            } else {
                int exitValue = new NativeShellRunner(nativeShell).run(script, context);
                if (exitValue != 0) {
                    throw new ScriptException("Script failed with exit code " + exitValue);
                }
                return exitValue;
            }
        } catch(Throwable t) {
            throw new ScriptException("Script failed with exception "+t);
        }
    }

    @Override
    public Object eval(Reader reader, ScriptContext context) throws ScriptException {
        return eval(IOUtils.toString(reader), context);
    }

    @Override
    public Bindings createBindings() {
        return new SimpleBindings();
    }

    @Override
    public ScriptEngineFactory getFactory() {
        return nativeShell.getFactory();
    }
}
