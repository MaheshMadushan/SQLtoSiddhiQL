package SiddhiAppComposites;

import SiddhiAppComposites.Annotation.App.App;
import SiddhiAppComposites.Annotation.Attributes.IAttributes;
import SiddhiAppComposites.Annotation.Common.KeyValue;
import SiddhiAppComposites.Annotation.Info.IInfo;
import SiddhiAppComposites.Annotation.Map.IMap;
import SiddhiAppComposites.Annotation.Sink.ISink;
import SiddhiAppComposites.Annotation.Source.ISource;
import SiddhiAppComposites.Statement.Define.DefineStreamStatement;
import SiddhiAppComposites.Statement.From.FilterExpression.FilterExpression;
import SiddhiAppComposites.Statement.From.FromStatement;
import SiddhiAppComposites.Statement.From.Join.JoinStatement;
import SiddhiAppComposites.Statement.From.Join.OnExpression.OnExpression;
import SiddhiAppComposites.Statement.From.Select.Insert.InsertStatement;
import SiddhiAppComposites.Statement.From.Select.SelectStatement;

import java.util.HashMap;

public class SiddhiApp {

    private final HashMap<Integer, DefineStreamStatement> DefineInputStreamStatementsHashMap = new HashMap<>(10);
    private final HashMap<Integer, DefineStreamStatement> DefineOutputStreamStatementHashMap = new HashMap<>(10);
    private final HashMap<Integer, OnExpression> onExpressionHashMap = new HashMap<>(10);
    private final HashMap<Integer, FilterExpression> filterExpressionHashMap = new HashMap<>(10);
    private final HashMap<Integer, JoinStatement> JoinStatementHashMap = new HashMap<>(10);
    private final HashMap<Integer, SelectStatement> selectStatementHashMap = new HashMap<>(10);
    private final HashMap<Integer, FromStatement> fromStatementHashMap = new HashMap<>(10);
    private final HashMap<Integer, InsertStatement> insertStatementHashMap = new HashMap<>(10);

    private final App annotationApp; // Annotation @app
    private final ISource annotationSource; // Annotation @source
    private final IAttributes annotationAttributes; // Annotation @attributes
    private final IMap annotationMap; // Annotation @map
    private final ISink annotationSink; // Annotation @sink
    private final IInfo annotationInfo; // Annotation @info
    private final StringBuilder stringSiddhiApp = new StringBuilder();

    private SiddhiApp(SiddhiAppBuilder siddhiAppBuilder) {

        this.annotationSource = siddhiAppBuilder.annotationSource;
        this.annotationAttributes = siddhiAppBuilder.annotationAttributes;
        this.annotationMap = siddhiAppBuilder.annotationMap;
        this.annotationSink = siddhiAppBuilder.annotationSink;
        this.annotationInfo = siddhiAppBuilder.annotationInfo;
        this.annotationApp = siddhiAppBuilder.annotationApp;
        fromStatementHashMap.put(0, new FromStatement()); // hack - should avoid
    }

    public void addOnExpressionComposites(final int joinStreamId, final int onExpressionId, ISiddhiAppComposite onExpressionComposite) {
        if (onExpressionHashMap.get(onExpressionId) == null){
            OnExpression onExpression = new OnExpression();
            onExpressionHashMap.put(onExpressionId, onExpression);
            JoinStatementHashMap.get(joinStreamId).addJoinStatementComposite(onExpression);
        }
        onExpressionHashMap.get(onExpressionId).addOnExpressionComposite(onExpressionComposite);
    }

    public void addSymbolToFilterExpression(int filterExpressionId, String symbol){
        if (filterExpressionHashMap.get(filterExpressionId) == null){
            filterExpressionHashMap.put(filterExpressionId, new FilterExpression());
        }
        filterExpressionHashMap.get(filterExpressionId).addSymbol(symbol);
    }

    public void addFromStatementComposite(int outputStreamStatementId, int inputStreamStatementId, int fromStatementId, Table table) {
        String outputStreamName = String.format("%s", table.getTableName() + "%sOutputStream");
        String inputStreamName = table.getTableName() + "InputStream";

        if (fromStatementHashMap.get(fromStatementId) == null){
            fromStatementHashMap.put(fromStatementId, new FromStatement());
        }
        if (insertStatementHashMap.get(fromStatementId) == null){
            InsertStatement insertStatement = new InsertStatement();
            insertStatement.setOutputStreamName(outputStreamName);
            insertStatementHashMap.put(fromStatementId, insertStatement); // since "from" has an "insert"
        }
        DefineInputStreamStatementsHashMap.get(inputStreamStatementId).setStreamName(inputStreamName); // since from should have ip stream
        DefineOutputStreamStatementHashMap.get(outputStreamStatementId).setStreamName(outputStreamName); // since "from" should have op stream
        fromStatementHashMap.get(fromStatementId).setTableName(table.getTableName());
        fromStatementHashMap.get(fromStatementId).setStreamName(inputStreamName);
    }

    public void addJoinStatement(int fromStatementId, int inputStreamStatementId, int outputStreamStatementId, int joinStatementId, Table table) {
        if (JoinStatementHashMap.get(joinStatementId) == null){
            JoinStatement joinStatement = new JoinStatement();
            joinStatement.setStreamName(table.getTableName() + "InputStream");
            joinStatement.setAlias(table.getTableName());
            JoinStatementHashMap.put(joinStatementId, joinStatement);
        }
        if(DefineInputStreamStatementsHashMap.get(inputStreamStatementId) == null){
            DefineStreamStatement defineStreamStatementForJoin = new DefineStreamStatement();
            defineStreamStatementForJoin.setStreamName(table.getTableName() + "InputStream");
            DefineInputStreamStatementsHashMap.put(inputStreamStatementId, defineStreamStatementForJoin);
        }
        String existingOutputStreamName = insertStatementHashMap.get(fromStatementId).getOutputStream();
        String newOutputStreamName = String.format(existingOutputStreamName, "Joins" + table.getTableName());
        DefineOutputStreamStatementHashMap.get(outputStreamStatementId).setStreamName(newOutputStreamName); // since "from" should have op stream
        insertStatementHashMap.get(fromStatementId).setOutputStreamName(newOutputStreamName);
        fromStatementHashMap.get(fromStatementId).setFromStatementComposites(JoinStatementHashMap.get(joinStatementId));
        fromStatementHashMap.get(fromStatementId).setAlias(fromStatementHashMap.get(fromStatementId).getTableName());
    }
    public void addSelectItem(int selectStatementId, ISiddhiAppComposite selectItem) {
        if (selectStatementHashMap.get(selectStatementId) == null) {
            selectStatementHashMap.put(selectStatementId, new SelectStatement());
        }
        selectStatementHashMap.get(selectStatementId).addSelectItem(selectItem);
    }

    private void addToSourceAnnotationAttributes(ISiddhiAppComposite columnWithDataType) {
        String columnName = ((ColumnWithDataType) columnWithDataType).getColumnName();
        annotationAttributes.addAttributeComposite(new KeyValue<>(columnName,columnName)); // add to attribute annotation
    }

    public void addColumnWithDataTypeToOutputStreamDefinition(int outputStreamStatementId, ISiddhiAppComposite columnWithDataType) {
        if (DefineOutputStreamStatementHashMap.get(outputStreamStatementId) == null){
            DefineOutputStreamStatementHashMap.put(outputStreamStatementId, new DefineStreamStatement());
        }
        DefineOutputStreamStatementHashMap.get(outputStreamStatementId).addAttributeWithDataType(columnWithDataType);
        addToSourceAnnotationAttributes(columnWithDataType);
    }

    public void addFilterExpressionToFromStatement(int fromId, int filterId) {
        fromStatementHashMap.get(fromId).setFromStatementComposites(filterExpressionHashMap.get(filterId));
    }

    public void addColumnWithDataTypeToInputStreamDefinition(int outputStreamStatementId, ISiddhiAppComposite columnWithDataType) {
        if (DefineInputStreamStatementsHashMap.get(outputStreamStatementId) == null){
            DefineInputStreamStatementsHashMap.put(outputStreamStatementId, new DefineStreamStatement());
        }
        DefineInputStreamStatementsHashMap.get(outputStreamStatementId).addAttributeWithDataType(columnWithDataType);
        addToSourceAnnotationAttributes(columnWithDataType);
    }

//    public void addColumnWithDataTypeToJoinStreamDefinition(ISiddhiAppComposite columnWithDataType){
////        defineJoinStreamStatement.addAttributeWithDataType(columnWithDataType);
////        addToSourceAnnotationAttributes(columnWithDataType);
//    }

//    public void setStreamNamePrefix(String inputOutputStreamNamePrefix) {
//        this.inputOutputStreamNamePrefix = inputOutputStreamNamePrefix;
//    }

//    public void setRightJoinTable(String rightJoinTable) {
//        this.rightJoinTable = rightJoinTable;
//    }

//    public void addOnExpressions(String column) {
//        this.onExpressions.add(column);
//    }
//    public String getTableName() {
//        return inputOutputStreamNamePrefix;
//    }

//    public String getRightJoinTable() { return rightJoinTable; }
//    public HashSet<String> getColumnNames() {
//        ColumnNameExtractorVisitor iAttributeVisitor = new ColumnNameExtractorVisitor();
//        defineInputStreamStatement.extractColumnNames(iAttributeVisitor); // has all column names in select statement including where clause
//        return iAttributeVisitor.getColumnNames();
//    }

    public String getSiddhiAppStringRepresentation(){
        return "";
        // Annotations
            // app name
//        stringSiddhiApp.append(annotationApp.getSiddhiAppCompositeAsString());
//            // source
//         annotationMap.addMapComposite(annotationAttributes);
//        KeyValue<String, String> tableNameSourceComposite = new KeyValue<>("table.name", getTableName());
//        annotationSource.addSourceComposite(tableNameSourceComposite);
//        annotationSource.addSourceComposite(annotationMap);
//        stringSiddhiApp.append(annotationSource.getSiddhiAppCompositeAsString());
//        annotationSource.deleteSourceComposite(tableNameSourceComposite);
//        annotationSource.deleteSourceComposite(annotationMap);
//        defineInputStreamStatement.setStreamName(inputOutputStreamNamePrefix + "InputStream"); // IPStream set I/P Stream Name
//        stringSiddhiApp.append(defineInputStreamStatement.getSiddhiAppCompositeAsString());
//        if (rightJoinTable != null) {
//            KeyValue<String, String> rightJointableNameSourceComposite = new KeyValue<>("table.name", getRightJoinTable());
//            annotationSource.addSourceComposite(rightJointableNameSourceComposite);
//            annotationSource.addSourceComposite(annotationMap);
//            stringSiddhiApp.append(annotationSource.getSiddhiAppCompositeAsString());
//
//            defineJoinStreamStatement.setStreamName(rightJoinTable + "InputStream");
//            stringSiddhiApp.append(defineJoinStreamStatement.getSiddhiAppCompositeAsString());
//        }
//
//        // Annotations
//            // sink
//        stringSiddhiApp.append(annotationSink.getSiddhiAppCompositeAsString());
//
//        defineOutputStreamStatement.setStreamName(inputOutputStreamNamePrefix + "OutputStream"); // OPStream set O/P Stream Name
//        stringSiddhiApp.append(defineOutputStreamStatement.getSiddhiAppCompositeAsString());
//        // Annotations
//            // info
//        stringSiddhiApp.append(annotationInfo.getSiddhiAppCompositeAsString());
//
//        if (rightJoinTable != null) {
//            fromStatement.setStreamName(inputOutputStreamNamePrefix + "InputStream#window.length(3) as " + inputOutputStreamNamePrefix );
//            fromStatement.setFromStatementComposites(filterExpression); // From statement filter expression
//
//            joinStatement.setStreamName(rightJoinTable + "InputStream#window.length(3) as " + rightJoinTable );
//            joinStatement.setFromStatementComposites(filterExpression);
//
//            stringSiddhiApp.append(fromStatement.getSiddhiAppCompositeAsString());
//            String joinInputStream = joinStatement.getSiddhiAppCompositeAsString().replaceAll("from", "join");
//            stringSiddhiApp.append(joinInputStream);
//            stringSiddhiApp.append("on ").append(onExpressions.get(0)).append(" == ").append(onExpressions.get(1)).append("\n");
//
//
//            List<ISiddhiAppComposite> selectFields = ((SelectStatementAttributeList) selectStatement.getSelectItems())
//                    .getAttributesWithOrWithoutAliases();
//            ArrayList<String> fieldsInColumns = new ArrayList<>();
//
//            for (ISiddhiAppComposite selectField : selectFields) {
//                String select = selectField
//                        .getSiddhiAppCompositeAsString().replaceAll("\\s+", "");
//
//                List<ISiddhiAppComposite> columnsInInputStream = ((StreamStatementAttributeList) defineInputStreamStatement
//                        .getAttributes()).getAttributeListWithoutAliasesWithDataType();
//                for (ISiddhiAppComposite iSiddhiAppComposite : columnsInInputStream) {
//                    String column = ((ColumnWithDataType) iSiddhiAppComposite).getColumnName();
//                    if (select.equals(column)) {
//                        fieldsInColumns.add(inputOutputStreamNamePrefix + "." + select);
//                    }
//                }
//
//                List<ISiddhiAppComposite> columnsInJoinStream = ((StreamStatementAttributeList) defineJoinStreamStatement
//                        .getAttributes()).getAttributeListWithoutAliasesWithDataType();
//                for (ISiddhiAppComposite iSiddhiAppComposite : columnsInJoinStream) {
//                    String column = ((ColumnWithDataType) iSiddhiAppComposite).getColumnName();
//                    if (select.equals(column)) {
//                        fieldsInColumns.add(rightJoinTable + "." + select);
//                    }
//                }
//            }
//            StringBuilder allFields = new StringBuilder("select ");
//
//            for (String str : fieldsInColumns) {
//                allFields.append(str).append(", ");
//            }
//            allFields.delete(allFields.length() - 2, allFields.length());
//            allFields.append("\n");
//            stringSiddhiApp.append(allFields);
//
//        } else {
//            fromStatement.setStreamName(inputOutputStreamNamePrefix + "InputStream"); // From Statement set I/P Stream Name
//            fromStatement.setFromStatementComposites(filterExpression); // From statement filter expression
//            stringSiddhiApp.append(fromStatement.getSiddhiAppCompositeAsString());
//            stringSiddhiApp.append(selectStatement.getSiddhiAppCompositeAsString());
//        }
//
//        insertStatement.setOutputStreamName(inputOutputStreamNamePrefix + "OutputStream"); // insert statement set O/P Stream name
//        stringSiddhiApp.append(insertStatement.getSiddhiAppCompositeAsString());
//        return stringSiddhiApp.toString();
    }

//    public void initiateDefineStatementForJoin(SiddhiAppComposites.Table table) {
//    }


    // Bloch’s Builder pattern
    public static class SiddhiAppBuilder{
        private ISource annotationSource;
        private IAttributes annotationAttributes;
        private IMap annotationMap;
        private ISink annotationSink;
        private IInfo annotationInfo;
        private final App annotationApp;

        public SiddhiAppBuilder(String siddhiAppName) {
            annotationApp = new App();
            annotationApp.setSiddhiApplicationName(siddhiAppName);
        }

        public SiddhiAppBuilder setAnnotationSource(ISource annotationSource) {
            this.annotationSource = annotationSource;
            return this;
        }

        public SiddhiAppBuilder setAnnotationAttributes(IAttributes annotationAttributes) {
            this.annotationAttributes = annotationAttributes;
            return this;
        }

        public SiddhiAppBuilder setAnnotationMap(IMap annotationMap) {
            this.annotationMap = annotationMap;
            return this;
        }

        public SiddhiAppBuilder setAnnotationSink(ISink annotationSink) {
            this.annotationSink = annotationSink;
            return this;
        }

        public SiddhiAppBuilder setAnnotationInfo(IInfo annotationInfo) {
            this.annotationInfo = annotationInfo;
            return this;
        }

        public SiddhiApp build(){
            return new SiddhiApp(this);
        }

    }
}