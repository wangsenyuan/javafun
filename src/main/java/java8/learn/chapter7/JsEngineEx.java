package java8.learn.chapter7;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Created by senyuanwang on 15/5/21.
 */
public class JsEngineEx {

    public Object evalScript(String script) throws ScriptException {
        Objects.requireNonNull(script);
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = engineManager.getEngineByName("nashorn");
        Objects.requireNonNull(scriptEngine);
        return scriptEngine.eval(script);
    }

    public Object evalScriptFile(String fileName) throws IOException, ScriptException {
        Objects.requireNonNull(fileName);
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = engineManager.getEngineByName("nashorn");
        Objects.requireNonNull(scriptEngine);
        return scriptEngine.eval(Files.newBufferedReader(Paths.get(fileName)));
    }
}
