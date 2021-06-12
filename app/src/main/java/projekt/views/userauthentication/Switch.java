package projekt.views.userauthentication;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.StringBinding;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Switch {
    private final List<SwitchCase> cases = new LinkedList<>();

    public static SwitchCase when(BooleanBinding booleanBinding) {
        Switch aSwitch = new Switch();

        return aSwitch.newCase(booleanBinding);
    }

    private SwitchCase newCase(BooleanBinding booleanBinding) {
        SwitchCase switchCase = new SwitchCase(this, booleanBinding);
        cases.add(switchCase);

        return switchCase;
    }


    public SwitchCase orElse(BooleanBinding other) {
        return newCase(other);
    }

    public StringBinding otherwise(String defaultValue) {
        Collections.reverse(cases);

        Iterator<SwitchCase> iterator = cases.iterator();

        SwitchCase last = iterator.next();
        StringBinding nextCondition = Bindings.when(last.booleanBinding).then(last.value).otherwise(defaultValue);
        StringBinding joinedConditions;

        if (iterator.hasNext()) {
            while (iterator.hasNext()) {
                SwitchCase next = iterator.next();

                nextCondition = Bindings.when(next.booleanBinding).then(next.value).otherwise(nextCondition);

            }
        }

        joinedConditions = nextCondition;

        return joinedConditions;
    }

    static class SwitchCase {
        private final Switch parent;
        private final BooleanBinding booleanBinding;
        private String value;

        public SwitchCase(Switch parent, BooleanBinding booleanBinding) {
            this.parent = parent;
            this.booleanBinding = booleanBinding;
        }

        public Switch then(String value) {
            this.value = value;

            return parent;
        }
    }
}
