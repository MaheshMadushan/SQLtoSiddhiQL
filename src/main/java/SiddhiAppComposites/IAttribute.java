package SiddhiAppComposites;

import SiddhiAppComposites.visitors.IAttributeVisitor;

public interface IAttribute extends ISiddhiAppComposite {
    void accept(IAttributeVisitor iAttributeVisitor);
}
