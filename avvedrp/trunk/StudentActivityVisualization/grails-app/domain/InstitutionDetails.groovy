class InstitutionDetails {

	String institutionName;
	String postalAddress;
	String state;
	String district;
	String website;
	String totalArea;
	String totalConstructedArea;
	String establishedYear;
	String location;
	String statutoryBody;
	String institutionType;
	String autonomous;
	String management;
	String specialization;
	String eveningCollege;
	String girlsExclusive;

    static constraints = {
	institutionName(blank:false)
	postalAddress(blank:false)
	state(blank:false)
	district(blank:false)
	totalArea(nullable:true)
	totalConstructedArea(nullable:true)
    }
    String toString() {"${this.institutionName}"}
    
}
