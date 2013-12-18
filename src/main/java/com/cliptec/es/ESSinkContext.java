package com.cliptec.es;

import com.cliptec.constants.DefaultConstants;
import com.cliptec.constants.LayoutConstants;
import org.apache.hadoop.mapred.JobConf;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.node.Node;
import static org.elasticsearch.node.NodeBuilder.*;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import cascading.tuple.TupleEntry;
import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/18/13
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class ESSinkContext {
    public String clusterName = null;
    public String indexName = null;
    public String typeName = null;

    public Node node;
    public Client client;

    protected ActionRequestBuilder request;
    public XContentBuilder builder;

    public void setUp(JobConf conf){

//        this.clusterName = conf.get()
        this.clusterName = conf.get(DefaultConstants.CLUSTERNAME);
        this.indexName = conf.get(DefaultConstants.INDEX_NAME);
        this.typeName = conf.get(DefaultConstants.TYPE_NAME);


        this.node = nodeBuilder().local(true).clusterName(this.clusterName).node();
        this.client = this.node.client();

        if(request == null)
            request = client.prepareBulk();
    }

    public void startRecord(){
        try {
            this.builder = jsonBuilder();
            this.builder.startObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startObject(){
        try {
            this.builder.startObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processColumn(String columnName, Object data){
        try {
            this.builder.field(columnName, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void collect(TupleEntry entry){
        this.startRecord();
        String dw_member_id = entry.getString(LayoutConstants.DW_MEMBER_ID);
        this.processColumn(LayoutConstants.DW_MEMBER_ID, dw_member_id);
        int age = entry.getInteger(LayoutConstants.MEMBER_AGE);
        this.processColumn(LayoutConstants.DW_MEMBER_ID, age);
        this.endRecord();
    }

    public void endObject(){
        try {
            this.builder.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endRecord(){
        try {
            this.builder.endObject();
            ((BulkRequestBuilder) request).add(Requests.indexRequest(DefaultConstants.INDEX_NAME).type(DefaultConstants.TYPE_NAME).id("s").create(false).source(this.builder));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean cleanup() {

        BulkResponse response = (BulkResponse) this.request.execute().actionGet();
        if(response.hasFailures()){
            return false;
        }
        return true;
    }
}
