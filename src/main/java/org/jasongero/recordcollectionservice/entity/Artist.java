package org.jasongero.recordcollectionservice.entity;

public class Artist {    
    private long artistId;
    private String name;
    
    public Artist(long artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }
    
    public long getArtistId() {
        return artistId;
    }
    
    public String getName() {
        return name;
    }
}
