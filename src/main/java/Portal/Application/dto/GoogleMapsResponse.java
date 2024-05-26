package Portal.Application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleMapsResponse {
    private List<Result> results;
    public List<Result> getResults() {
        return results;
    }
    public void setResults(List<Result> results) {
        this.results = results;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Result {
        private Geometry geometry;
        public Geometry getGeometry() {
            return geometry;
        }
        public void setGeometry(Geometry geometry) {
            this.geometry = geometry;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Geometry {
        private Location location;
        public Location getLocation() {
            return location;
        }
        public void setLocation(Location location) {
            this.location = location;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Location {
        private double lat;
        private double lng;

        @JsonProperty("lat")
        public double getLat() {
            return lat;
        }
        public void setLat(double lat) {
            this.lat = lat;
        }

        @JsonProperty("lng")
        public double getLng() {
            return lng;
        }
        public void setLng(double lng) {
            this.lng = lng;
        }
    }
}

