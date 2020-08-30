package org.jasongero.recordcollectionservice.entity;

import java.util.List;

import org.jasongero.recordcollectionservice.repository.FormatRepository;
import org.jasongero.recordcollectionservice.repository.TrackRepository;

public class Release {
    private long artistId;
    private long masterReleaseId;
    private long releaseId;
    private String catalogNumber;
    private String title;
    private String recordLabelName;
    private String countryName;
    private byte releaseMonth;
    private byte releaseDay;
    private short releaseYear;
    private String primaryImageUrl;
    
    public Release(
            long artistId,
            long masterReleaseId,
            long releaseId,
            String title,
            String catalogNumber,
            String recordLabelName,
            String countryName,
            byte releaseMonth,
            byte releaseDay,
            short releaseYear,
            String primaryImageUrl
    ) {
        this.artistId = artistId;
        this.masterReleaseId = masterReleaseId;
        this.releaseId = releaseId;
        this.title = title;
        this.catalogNumber = catalogNumber;
        this.recordLabelName = recordLabelName;
        this.countryName = countryName;
        this.releaseMonth = releaseMonth;
        this.releaseDay = releaseDay;
        this.releaseYear = releaseYear;
        this.primaryImageUrl = primaryImageUrl;
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
    
    public String getTitle() {
        return title;
    }
    
    public String getCatalogNumber() {
        return catalogNumber;
    }

    public String getRecordLabelName() {
        return recordLabelName;
    }
    
    public String getCountryName() {
        return countryName;
    }
    
    public byte getReleaseMonth() {
        return releaseMonth;
    }
    
    public byte getReleaseDay() {
        return releaseDay;
    }
    
    public short getReleaseYear() {
        return releaseYear;
    }
    
    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }
    
    public List<Track> fetchTracks() {
        return TrackRepository.getTracksByRelease(this.releaseId);
    }
    
    public List<Format> fetchFormats() {
        return FormatRepository.getFormats(this.releaseId);
    }
}
