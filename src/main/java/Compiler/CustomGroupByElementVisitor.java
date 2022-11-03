package Compiler;

import net.sf.jsqlparser.statement.select.GroupByElement;
import net.sf.jsqlparser.statement.select.GroupByVisitor;

public class CustomGroupByElementVisitor implements GroupByVisitor {
    @Override
    public void visit(GroupByElement groupByElement) {
        System.out.println("in " + CustomGroupByElementVisitor.class);
        System.out.println(groupByElement.toString());
    }
}
