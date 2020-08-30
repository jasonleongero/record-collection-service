package org.jasongero.recordcollectionservice.entity;

public class ReleaseWithArtist extends Release {    
    private String artistName;
    
    public ReleaseWithArtist(
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
        String primaryImageUrl,
        String artistName
    ) {
        super( 
            artistId,
            masterReleaseId,
            releaseId,
            title,
            catalogNumber,
            recordLabelName,
            countryName,
            releaseMonth,
            releaseDay,
            releaseYear,
            primaryImageUrl
        );
        
        this.artistName = artistName;
    }
    
    public String getArtistName() {
        return this.artistName;
    }
}
