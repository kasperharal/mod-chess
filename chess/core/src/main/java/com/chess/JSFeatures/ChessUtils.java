package com.chess.JSFeatures;

import static com.chess.Main.modContainer;

import com.chess.ScriptExection;

public class ChessUtils {
    public static void executeScriptFIle(String filepath) {
        ScriptExection.context.evaluateString(ScriptExection.scope, modContainer.scripts.get(filepath), "scriptFile-"+filepath, 1, null);
    }

    public static void executeAsScript(String script) {
        ScriptExection.context.evaluateString(ScriptExection.scope, script, "scriptJava-string", 1, null);
    }

    public static void print(String msg) {
        System.out.println(msg);
    }

    public static int gamePosToIndex(ChessInstace chess, int x, int y) {
        if (y%2==0) {
            return chess.bord().getBordsize()*y+x;
        }
        else {
            return chess.bord().getBordsize()*y+(chess.bord().getBordsize()-x-1);
        }
        
    }
}
