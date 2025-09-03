package com.chess;

import static com.chess.Main.*;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import com.chess.JSFeatures.ChessInstace;

public class ScriptExection {
    public static Context context = Context.enter();
    public static Scriptable scope = context.initStandardObjects();
    public static ChessInstace executeScripts(ChessInstace chess) {
        try {
            context.setClassShutter(new JSClassShutter());
            context.evaluateString(scope, modContainer.scripts.get("init"), "scriptFile-init", 1, null);
            ScriptableObject.putProperty(scope, "chess", Context.javaToJS(chess, scope));
            context.evaluateString(scope, "init('"+modContainer.name+"');", "scriptJava-init", 1, null);

        } finally {
            Context.exit();
        }
        return chess;
    }
}
