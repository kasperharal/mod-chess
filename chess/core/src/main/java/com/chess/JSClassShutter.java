package com.chess;

import org.mozilla.javascript.ClassShutter;

public class JSClassShutter implements ClassShutter {
    @Override
    public boolean visibleToScripts(String className) {
        // Allow only java.lang and your custom classes
        return className.startsWith("java.util") || className.startsWith("java.lang") || className.startsWith("org.json") || className.startsWith("com.chess.JSFeatures");
    }
}
