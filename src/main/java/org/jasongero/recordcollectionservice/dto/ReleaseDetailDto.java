package org.jasongero.recordcollectionservice.dto;

import java.util.List;

public class ReleaseDetailDto {
    public long releaseId;
    public String title;
    public String catalogNumber;
    public byte releaseMonth;
    public byte releaseDay;
    public short releaseYear;
    public String imageUrl;
    public List<ReleaseDetailDto.TrackDto> tracks;
    public List<ReleaseDetailDto.FormatDto> formats;
    public String recordLabelName;
    public String countryName;
    
    public static class TrackDto {
        public String title;
        public String trackNumber;
        public long duration;
    }
    
    public static class FormatDto {
        public String formatName;
        public long quantity;
        public String notes;
    }
}
