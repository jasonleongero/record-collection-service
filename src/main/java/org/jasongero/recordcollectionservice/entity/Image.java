package org.jasongero.recordcollectionservice.entity;

public class Image {
    private long artistId;
    private long masterReleaseId;
    private long releaseId;
    private String imageUrl;
    private boolean isPrimary;
    
    public Image(long artistId, long masterReleaseId, long releaseId, String imageUrl, boolean isPrimary) {
        this.artistId = artistId;
        this.masterReleaseId = masterReleaseId;
        this.releaseId = releaseId;
        this.imageUrl = imageUrl;
        this.isPrimary = isPrimary;
    }
    
    public long getArtistId() {
        return artistId;
    }
    
    public long getMasterReleaseId() {
        return masterReleaseId;
    }
    
    public long getReleaseId() {
        return releaseId;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public boolean getIsPrimary() {
        return isPrimary;
    }
}
