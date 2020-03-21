package de.letsdev.products.dico.scanner.backend.helper;

import de.letsdev.products.dico.scanner.backend.db.TestState;

public class TestStateConverter {

    public static TestState.State convertStringToTestState(String state) {
        switch (state) {
            case "positive":
                return TestState.State.IS_POSITIVE;
            case "negative":
                return TestState.State.IS_NEGATIVE;
            default:
                return TestState.State.INITIALIZED;
        }
    }

}
