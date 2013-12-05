package com.cliptec.general.function;

import cascading.flow.FlowProcess;
import cascading.operation.BaseOperation;
import cascading.operation.Function;
import cascading.operation.FunctionCall;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;
import cascading.tuple.TupleEntry;
import com.cliptec.constants.LayoutConstants;
import com.cliptec.utils.DateUtils;
import com.cliptec.utils.StringUtils;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/5/13
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class AgeFunction extends BaseOperation implements Function {

    public AgeFunction(){
        super(new Fields(LayoutConstants.DW_MEMBER_ID, LayoutConstants.MEMBER_DOB, LayoutConstants.MEMBER_AGE));
//        super(Fields.ARGS.append(new Fields(LayoutConstants.MEMBER_AGE)));

    }

    @Override
    public void operate(FlowProcess flowProcess, FunctionCall functionCall) {
        System.out.println(":::)))");
        TupleEntry inEntry = functionCall.getArguments();

        String mbr_dob = StringUtils.trimWith(inEntry.getString(LayoutConstants.MEMBER_DOB), "\"");
        String age = String.valueOf(DateUtils.calcAgeFromDOB(mbr_dob));
        Tuple outEntry = new Tuple();
        outEntry.add(StringUtils.trimWith(inEntry.getString(LayoutConstants.DW_MEMBER_ID), "\""));
        outEntry.add(StringUtils.trimWith(inEntry.getString(LayoutConstants.MEMBER_DOB), "\""));
        outEntry.add(age);

        functionCall.getOutputCollector().add(outEntry);
    }

}
