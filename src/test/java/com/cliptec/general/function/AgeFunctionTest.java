package com.cliptec.general.function;

import cascading.flow.Flow;
import cascading.operation.Debug;
import cascading.operation.Insert;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.tap.SinkMode;
import cascading.tap.Tap;
import cascading.test.LocalPlatform;
import cascading.test.PlatformRunner;
import cascading.PlatformTestCase;

import cascading.tuple.Fields;
import com.cliptec.constants.DefaultConstants;
import com.cliptec.TestFileLoader;

import com.cliptec.constants.LayoutConstants;
import com.cliptec.utils.FieldUtils;
import org.junit.Test;


/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/5/13
 * Time: 4:58 PM
 * To change this template use File | Settings | File Templates.
 */
@PlatformRunner.Platform(LocalPlatform.class)
public class AgeFunctionTest extends PlatformTestCase {

    TestFileLoader fileLoader = new TestFileLoader();

    @Test
    public void testAgeFunction(){
        String inputFileName = "data/five_members/eligibility_input.csv";
        String outputFileName = "age_func_output.csv";
        String inputLayoutFile = "layouts/eligibilityLayout.csv";
        FieldUtils utils = new FieldUtils();
        Fields inputFields = utils.getFieldsFromLayoutFiles(fileLoader.getFilePath(inputLayoutFile));

        Tap inputTap = getPlatform().getDelimitedFile(inputFields, DefaultConstants.LAYOUT_ENTRY_DELIMETER, fileLoader.getFilePath(inputFileName));
        //Tap outputTap = getPlatform().getDelimitedFile(Fields.UNKNOWN, getOutputPath(outputFileName), SinkMode.REPLACE);
        Tap outTap = getPlatform().getDelimitedFile(Fields.UNKNOWN,true,getOutputPath(inputFileName),SinkMode.REPLACE);

//        FloatToLongFunction function = new FloatToLongFunction(LayoutConstants.MEMBER_AGE);
        AgeFunction function = new AgeFunction();

        Pipe pipe = new Pipe("AgeFunctionTest");
//        Insert insert = new Insert(new Fields(LayoutConstants.MEMBER_AGE), "");
//        pipe = new Each(pipe, Fields.NONE, insert, Fields.ALL);
        pipe = new Each(pipe, new Fields("dw_member_id", LayoutConstants.MEMBER_DOB), function, Fields.RESULTS);
        pipe = new Each(pipe, new Debug("test", true));

        Flow flow = getPlatform().getFlowConnector().connect(inputTap, outTap, pipe);
        flow.complete();
    }

}
