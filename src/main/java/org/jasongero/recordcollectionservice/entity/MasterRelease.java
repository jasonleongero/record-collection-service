package org.jasongero.recordcollectionservice.entity;

import org.jasongero.recordcollectionservice.repository.ReleaseRepository;

public class MasterRelease {
    private long artistId;
    private long masterReleaseId;
    private String title;
    private String imageUrl;
    private long releaseVersions;
    
    public MasterRelease(long artistId, long masterReleaseId, String title, String imageUrl, long releaseVersions) {
        this.artistId = artistId;
        this.masterReleaseId = masterReleaseId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.releaseVersions = releaseVersions;
    }
    
    public long getArtistId() {
        return artistId;
    }
    
    public long getMasterReleaseId() {
        return masterReleaseId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public long getReleaseVersions() {
        return releaseVersions;
    }
    
    public long fetchReleaseVersions() {
        return ReleaseRepository.getReleasesByMasterRelease(masterReleaseId).size();
    }
}
