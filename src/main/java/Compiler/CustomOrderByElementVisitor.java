package Compiler;

import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.OrderByVisitor;

public class CustomOrderByElementVisitor implements OrderByVisitor {
    @Override
    public void visit(OrderByElement orderByElement) {
        System.out.println("in " + CustomOrderByElementVisitor.class);
        System.out.println(orderByElement.toString());
    }
}
