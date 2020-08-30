package org.jasongero.recordcollectionservice.entity;

public class Format {
    private long releaseId;
    private String formatName;
    private long quantity;
    private String notes;
    
    public Format(
            long releaseId,
            String formatName,
            long quantity,
            String notes
    ) {
        this.releaseId = releaseId;
        this.formatName = formatName;
        this.quantity = quantity;
        this.notes = notes;
    }
    
    public long getReleaseId() {
        return releaseId;
    }
    
    public String getFormatName() {
        return formatName;
    }
    
    public long getQuantity() {
        return quantity;
    }
    
    public String getNotes() {
        return notes;
    }
}
