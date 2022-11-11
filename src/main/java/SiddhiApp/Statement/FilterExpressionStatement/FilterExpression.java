package SiddhiApp.Statement.FilterExpressionStatement;

public class FilterExpression implements IFilterExpression {

    private boolean isparenthesis = false;
    private TreeNode rootNode;
    private String filterStatement;
    private static class TreeNode{
        private TreeNode leftNode;
        private TreeNode rightNode;
        private String data;

        TreeNode(String data){
            leftNode = null;
            rightNode = null;
            this.data = data;
        }
        public void setLeftNode(TreeNode leftNode){
            this.leftNode = leftNode;
        }

        public void setRightNode(TreeNode rightNode){
            this.rightNode = rightNode;
        }

        public void setData(String data){
            this.data = data;
        }
    }

    public void addOperator(String operator){
    }

    public void addOperand(String operand){
    }

    public void setIsparenthesis(boolean isparenthesis) {
        this.isparenthesis = isparenthesis;
    }

    public void addSymbol(String symbol){
        if(isparenthesis){
            this.filterStatement += String.format("(%s)", filterStatement + " " + symbol + " ");
        }else {
            this.filterStatement += String.format("%s", filterStatement + " " + symbol + " ");
        }
    }

    @Override
    public String getSiddhiAppCompositeAsString() {
        return filterStatement;
    }
}




