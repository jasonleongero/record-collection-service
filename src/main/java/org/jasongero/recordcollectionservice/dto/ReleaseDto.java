package org.jasongero.recordcollectionservice.dto;

import java.util.List;

public class ReleaseDto {
    public String title;
    public long releaseId;
    public String imageUrl;
    public String catalogNumber;
    public List<FormatDto> formats;
    public long releaseYear;
    
    public static class FormatDto {
        public String formatName;
        public long quantity;
        public String notes;
    }
}
