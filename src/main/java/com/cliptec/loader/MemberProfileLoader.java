package com.cliptec.loader;

import cascading.flow.Flow;
import cascading.flow.FlowDef;
import cascading.flow.hadoop.HadoopFlowConnector;
import cascading.operation.Identity;
import cascading.pipe.Each;
import cascading.pipe.Pipe;
import cascading.property.AppProps;
import cascading.scheme.hadoop.SequenceFile;
import cascading.scheme.hadoop.TextDelimited;
import cascading.tap.SinkMode;
import cascading.tap.Tap;
import cascading.tap.hadoop.Hfs;
import cascading.tuple.Fields;
import com.cliptec.constants.LayoutConstants;
import com.cliptec.es.ESScheme;
import com.cliptec.general.function.AgeFunction;
import com.cliptec.utils.FieldUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/6/13
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class MemberProfileLoader {

    public void process(){
        String inputLayoutFIle = "layouts/eligibilityLayout.csv";
        inputLayoutFIle = getClass().getClassLoader().getResource(inputLayoutFIle).toString().replace("file:/", "/");
        String inputFile = "1009/input/claims/eligibility_input.csv";

        String outputPath = "1009/MemberProfile";

        FieldUtils utils = new FieldUtils();

        Fields sourceFields = utils.getFieldsFromLayoutFiles(inputLayoutFIle);

        Tap sourceTap = new Hfs(new TextDelimited(sourceFields, ";"), inputFile);

        Tap sinkTap = new Hfs(new SequenceFile(new Fields(LayoutConstants.DW_MEMBER_ID, LayoutConstants.MEMBER_DOB, LayoutConstants.MEMBER_AGE)), outputPath, SinkMode.REPLACE);

        Tap esTap = new Hfs(new ESScheme(), "1009/dummy", SinkMode.REPLACE);

        Pipe hfsPipe = new Pipe("member_profile");
        hfsPipe = new Each(hfsPipe, new Fields(LayoutConstants.DW_MEMBER_ID, LayoutConstants.MEMBER_DOB), new AgeFunction(), Fields.RESULTS);
        Pipe pipe1=new Pipe("secondPipe",hfsPipe);

//        Pipe esPipe = new Pipe("es", hfsPipe);
//
//        Identity identity = new Identity(sourceFields);
//        esPipe = new Each(hfsPipe, sourceFields, identity, Fields.RESULTS);


//        tapMap.put("member_profile", esTap);

        FlowDef flowDef = new FlowDef();
        flowDef.addSource(hfsPipe.getName(), sourceTap);
        flowDef.addTail(hfsPipe);
        flowDef.addTail(pipe1);
//        flowDef.addTail(esPipe);
        flowDef.addSink(hfsPipe, sinkTap);
        flowDef.addSink(pipe1, esTap);
//        flowDef.addTail(esPipe);
//        flowDef.addSinks(tapMap);
//        flowDef.addSink(hfsPipe, sinkTap);
//        flowDef.addSink(esPipe, esTap);
//        flowDef.addTailSink(esPipe, sinkTap);
//        flowDef.addTail(esPipe);
//        flowDef.addTailSink(esPipe, esTap);

        Properties prop = prepareProperties(this.getClass());
        Flow flow = new HadoopFlowConnector(prop).connect(flowDef);
        flow.complete();

    }

    protected Properties prepareProperties(Class loader) {
        Properties properties = new Properties();
        // pass in the class name of your application
        // this will find the parent jar at runtime
        AppProps.setApplicationJarClass(properties, loader.getClass());
        return properties;
    }

}
