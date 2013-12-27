package com.cliptec.es;

import cascading.flow.FlowProcess;
import cascading.scheme.Scheme;
import cascading.scheme.SinkCall;
import cascading.scheme.SourceCall;
import cascading.tap.Tap;
import com.cliptec.constants.DefaultConstants;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.OutputCollector;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/18/13
 * Time: 2:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ESScheme extends Scheme<JobConf, RecordReader, OutputCollector, Object[], ESSinkContext> {

    @Override
    public void sinkPrepare(FlowProcess<JobConf> flowProcess, SinkCall<ESSinkContext, OutputCollector> sinkCall) throws IOException {
        super.sinkPrepare(flowProcess, sinkCall);
        ESSinkContext context = sinkCall.getContext();

        if(context == null){
            context = new ESSinkContext();
            context.setUp(flowProcess.getConfigCopy());
            sinkCall.setContext(context);
        }
    }

    @Override
    public void sourceConfInit(FlowProcess<JobConf> flowProcess, Tap<JobConf, RecordReader, OutputCollector> tap, JobConf conf) {

    }

    @Override
    public void sinkConfInit(FlowProcess<JobConf> flowProcess, Tap<JobConf, RecordReader, OutputCollector> tap, JobConf conf) {
        conf.set(DefaultConstants.CLUSTER_NAME, "es_slama");
        conf.set(DefaultConstants.ES_ADDR, "localhost");
        conf.set(DefaultConstants.INDEX_NAME, "1009");
        conf.set(DefaultConstants.TABLE_NAME, "cliptec");
        conf.set(DefaultConstants.RUN_AS_TRANSPORT, "false");
    }

    @Override
    public boolean source(FlowProcess<JobConf> flowProcess, SourceCall<Object[], RecordReader> sourceCall) throws IOException {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sink(FlowProcess<JobConf> flowProcess, SinkCall<ESSinkContext, OutputCollector> sinkCall) throws IOException {
        sinkCall.getContext().collect(sinkCall.getOutgoingEntry());
    }

    @Override
    public void sinkCleanup(FlowProcess<JobConf> flowProcess, SinkCall<ESSinkContext, OutputCollector> sinkCall) throws IOException{
        ESSinkContext context = sinkCall.getContext();
        System.out.println("sink clean up is calling");
        if(!context.cleanup()){
            System.out.println("sink clean up unsuccessfull");
        }
        else{
            System.out.println("sink clean up success");
        }
        super.sinkCleanup(flowProcess, sinkCall);
    }
}
