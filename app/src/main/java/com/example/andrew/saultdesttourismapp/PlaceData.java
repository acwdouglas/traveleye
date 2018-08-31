package com.example.andrew.saultdesttourismapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PlaceData {

    @SerializedName("html_attributions")
    @Expose
    private List<Object> htmlAttributions = new ArrayList<Object>();
    @SerializedName("result")
    @Expose
    private Results Results;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public PlaceData withHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
        return this;
    }

    public Results getResults() {
        return Results;
    }

    public void setResults(Results results) {
        this.Results = results;
    }

    public PlaceData withResult(Results Results) {
        this.Results = Results;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PlaceData withStatus(String status) {
        this.status = status;
        return this;
    }

}

    class AddressComponent {

    @SerializedName("long_name")
    @Expose
    private String longName;
    @SerializedName("short_name")
    @Expose
    private String shortName;
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<String>();

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public AddressComponent withLongName(String longName) {
        this.longName = longName;
        return this;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public AddressComponent withShortName(String shortName) {
        this.shortName = shortName;
        return this;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public AddressComponent withTypes(List<String> types) {
        this.types = types;
        return this;
    }

}


    class Close {

    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("time")
    @Expose
    private String time;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Close withDay(Integer day) {
        this.day = day;
        return this;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Close withTime(String time) {
        this.time = time;
        return this;
    }

}

    class Geometrys {

    @SerializedName("location")
    @Expose
    private Locations Locations;
    @SerializedName("viewport")
    @Expose
    private Viewports Viewports;

    public Locations getLocations() {
        return Locations;
    }

    public void setLocations(Locations locations) {
        this.Locations = locations;
    }

    public Geometrys withLocation(Locations Locations) {
        this.Locations = Locations;
        return this;
    }

    public Viewports getViewports() {
        return Viewports;
    }

    public void setViewports(Viewports viewports) {
        this.Viewports = viewports;
    }

    public Geometrys withViewport(Viewports Viewports) {
        this.Viewports = Viewports;
        return this;
    }

}

    class Locations {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Locations withLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Locations withLng(Double lng) {
        this.lng = lng;
        return this;
    }

}
    class Northeasts {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Northeasts withLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Northeasts withLng(Double lng) {
        this.lng = lng;
        return this;
    }

}

    class Open {

    @SerializedName("day")
    @Expose
    private Integer day;
    @SerializedName("time")
    @Expose
    private String time;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Open withDay(Integer day) {
        this.day = day;
        return this;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Open withTime(String time) {
        this.time = time;
        return this;
    }

}

    class mOpeningHours {

    @SerializedName("open_now")
    @Expose
    private Boolean openNow;
    @SerializedName("periods")
    @Expose
    private List<Period> periods = new ArrayList<Period>();
    @SerializedName("weekday_text")
    @Expose
    private List<String> weekdayText = new ArrayList<String>();

    public Boolean getOpenNow() {
        return openNow;
    }

    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    public mOpeningHours withOpenNow(Boolean openNow) {
        this.openNow = openNow;
        return this;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public mOpeningHours withPeriods(List<Period> periods) {
        this.periods = periods;
        return this;
    }

    public List<String> getWeekdayText() {
        return weekdayText;
    }

    public void setWeekdayText(List<String> weekdayText) {
        this.weekdayText = weekdayText;
    }

    public mOpeningHours withWeekdayText(List<String> weekdayText) {
        this.weekdayText = weekdayText;
        return this;
    }

}


    class Period {

    @SerializedName("close")
    @Expose
    private Close close;
    @SerializedName("open")
    @Expose
    private Open open;

    public Close getClose() {
        return close;
    }

    public void setClose(Close close) {
        this.close = close;
    }

    public Period withClose(Close close) {
        this.close = close;
        return this;
    }

    public Open getOpen() {
        return open;
    }

    public void setOpen(Open open) {
        this.open = open;
    }

    public Period withOpen(Open open) {
        this.open = open;
        return this;
    }

}


    class Photos {

    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("html_attributions")
    @Expose
    private List<String> htmlAttributions = new ArrayList<String>();
    @SerializedName("photo_reference")
    @Expose
    private String photoReference;
    @SerializedName("width")
    @Expose
    private Integer width;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Photos withHeight(Integer height) {
        this.height = height;
        return this;
    }

    public List<String> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public Photos withHtmlAttributions(List<String> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
        return this;
    }

    public String getPhotoReference() {
        return photoReference;
    }

    public void setPhotoReference(String photoReference) {
        this.photoReference = photoReference;
    }

    public Photos withPhotoReference(String photoReference) {
        this.photoReference = photoReference;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Photos withWidth(Integer width) {
        this.width = width;
        return this;
    }

}




    class Results {

    @SerializedName("address_components")
    @Expose
    private List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
    @SerializedName("adr_address")
    @Expose
    private String adrAddress;
    @SerializedName("formatted_address")
    @Expose
    private String formattedAddress;
    @SerializedName("formatted_phone_number")
    @Expose
    private String formattedPhoneNumber;
    @SerializedName("geometry")
    @Expose
    private Geometrys Geometrys;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("international_phone_number")
    @Expose
    private String internationalPhoneNumber;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("opening_hours")
    @Expose
    private mOpeningHours mOpeningHours;
    @SerializedName("photos")
    @Expose
    private List<Photos> Photos = new ArrayList<Photos>();
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("reference")
    @Expose
    private String reference;
    @SerializedName("reviews")
    @Expose
    private List<Review> reviews = new ArrayList<Review>();
    @SerializedName("scope")
    @Expose
    private String scope;
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<String>();
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("utc_offset")
    @Expose
    private Integer utcOffset;
    @SerializedName("vicinity")
    @Expose
    private String vicinity;
    @SerializedName("website")
    @Expose
    private String website;

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public Results withAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
        return this;
    }

    public String getAdrAddress() {
        return adrAddress;
    }

    public void setAdrAddress(String adrAddress) {
        this.adrAddress = adrAddress;
    }

    public Results withAdrAddress(String adrAddress) {
        this.adrAddress = adrAddress;
        return this;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public Results withFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
        return this;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public Results withFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
        return this;
    }

    public Geometrys getGeometrys() {
        return Geometrys;
    }

    public void setGeometrys(Geometrys geometrys) {
        this.Geometrys = geometrys;
    }

    public Results withGeometry(Geometrys geometrys) {
        this.Geometrys = geometrys;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Results withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Results withId(String id) {
        this.id = id;
        return this;
    }

    public String getInternationalPhoneNumber() {
        return internationalPhoneNumber;
    }

    public void setInternationalPhoneNumber(String internationalPhoneNumber) {
        this.internationalPhoneNumber = internationalPhoneNumber;
    }

    public Results withInternationalPhoneNumber(String internationalPhoneNumber) {
        this.internationalPhoneNumber = internationalPhoneNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Results withName(String name) {
        this.name = name;
        return this;
    }

    public mOpeningHours getmOpeningHours() {
        return mOpeningHours;
    }

    public void setmOpeningHours(mOpeningHours mOpeningHours) {
        this.mOpeningHours = mOpeningHours;
    }

    public Results withOpeningHours(mOpeningHours mOpeningHours) {
        this.mOpeningHours = mOpeningHours;
        return this;
    }

    public List<Photos> getPhotos() {
        return Photos;
    }

    public void setPhotos(List<Photos> Photos) {
        this.Photos = Photos;
    }

    public Results withPhotos(List<Photos> Photos) {
        this.Photos = Photos;
        return this;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public Results withPlaceId(String placeId) {
        this.placeId = placeId;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Results withRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Results withReference(String reference) {
        this.reference = reference;
        return this;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Results withReviews(List<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Results withScope(String scope) {
        this.scope = scope;
        return this;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public Results withTypes(List<String> types) {
        this.types = types;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Results withUrl(String url) {
        this.url = url;
        return this;
    }

    public Integer getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
    }

    public Results withUtcOffset(Integer utcOffset) {
        this.utcOffset = utcOffset;
        return this;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public Results withVicinity(String vicinity) {
        this.vicinity = vicinity;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Results withWebsite(String website) {
        this.website = website;
        return this;
    }

}

    class Review {

    @SerializedName("author_name")
    @Expose
    private String authorName;
    @SerializedName("author_url")
    @Expose
    private String authorUrl;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("profile_photo_url")
    @Expose
    private String profilePhotoUrl;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("relative_time_description")
    @Expose
    private String relativeTimeDescription;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("time")
    @Expose
    private Integer time;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Review withAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public Review withAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Review withLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public Review withProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
        return this;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Review withRating(Integer rating) {
        this.rating = rating;
        return this;
    }

    public String getRelativeTimeDescription() {
        return relativeTimeDescription;
    }

    public void setRelativeTimeDescription(String relativeTimeDescription) {
        this.relativeTimeDescription = relativeTimeDescription;
    }

    public Review withRelativeTimeDescription(String relativeTimeDescription) {
        this.relativeTimeDescription = relativeTimeDescription;
        return this;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Review withText(String text) {
        this.text = text;
        return this;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Review withTime(Integer time) {
        this.time = time;
        return this;
    }

}


    class Southwests {

    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Southwests withLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Southwests withLng(Double lng) {
        this.lng = lng;
        return this;
    }

}


    class Viewports {

    @SerializedName("northeast")
    @Expose
    private Northeasts Northeasts;
    @SerializedName("southwest")
    @Expose
    private Southwests Southwests;

    public Northeasts getNortheasts() {
        return Northeasts;
    }

    public void setNortheasts(Northeasts northeasts) {
        this.Northeasts = northeasts;
    }

    public Viewports withNortheast(Northeasts Northeasts) {
        this.Northeasts = Northeasts;
        return this;
    }

    public Southwests getSouthwests() {
        return Southwests;
    }

    public void setSouthwests(Southwests southwests) {
        this.Southwests = southwests;
    }

    public Viewports withSouthwest(Southwests Southwests) {
        this.Southwests = Southwests;
        return this;
    }

}