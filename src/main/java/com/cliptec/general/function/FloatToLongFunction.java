package com.cliptec.general.function;
import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;
import com.cliptec.constants.LayoutConstants;


/**
 * Created by IntelliJ IDEA.
 * User: sujshrestha
 * Date: 11/1/12
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class FloatToLongFunction extends BaseOperation implements Function {
    String fieldName="";

    public FloatToLongFunction (String _fieldName){
        super(Fields.ARGS.append(new Fields(_fieldName)));
        this.fieldName = _fieldName;
        call();
    }
    public void call(){
        System.out.println(super.getFieldDeclaration());

    }
    @Override
    public void operate(FlowProcess flowProcess, FunctionCall functionCall) {
        TupleEntry arguments = functionCall.getArguments();
//        Float floatValue = arguments.getFloat(LayoutConstants.MEMBER_DOB);
        long longValue = 22;
        Tuple output = new Tuple();
//        output.add(arguments.getString("dw_member_id"));
//        output.add(arguments.getString(LayoutConstants.MEMBER_DOB));
        output.add(longValue);
        functionCall.getOutputCollector().add(output);
    }
}
