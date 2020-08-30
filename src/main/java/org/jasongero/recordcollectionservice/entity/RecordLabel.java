package org.jasongero.recordcollectionservice.entity;

public class RecordLabel {
    private long recordLabelId;
    private String name;
    
    public RecordLabel(long recordLabelId, String name) {
        this.recordLabelId = recordLabelId;
        this.name = name;
    }
    
    public long getRecordLabelId() {
        return recordLabelId;
    }
    
    public String getName() {
        return name;
    }
}