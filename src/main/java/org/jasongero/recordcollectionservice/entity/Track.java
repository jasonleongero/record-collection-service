package org.jasongero.recordcollectionservice.entity;

public class Track {
    private long releaseId;
    private long order;
    private String trackNumber;
    private long duration;
    private String title;
    
    public Track(
            long releaseId,
            long order,
            String trackNumber,
            long duration,
            String title
    ) {
        this.releaseId = releaseId;
        this.order = order;
        this.trackNumber = trackNumber;
        this.duration = duration;
        this.title = title;
    }
    
    public long getReleaseId() {
        return releaseId;
    }
    
    public long getOrder() {
        return order;
    }
    
    public String getTrackNumber() {
        return trackNumber;
    }
    
    public long getDuration() {
        return duration;
    }
    
    public String getTitle() {
        return title;
    }
}
