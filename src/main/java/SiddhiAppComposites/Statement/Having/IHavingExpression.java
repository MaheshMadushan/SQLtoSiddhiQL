package SiddhiAppComposites.Statement.Having;

import SiddhiAppComposites.ISiddhiAppComposite;

public interface IHavingExpression extends ISiddhiAppComposite {
    Boolean containsExpressions();
    String getSiddhiAppCompositeAsString();
}
