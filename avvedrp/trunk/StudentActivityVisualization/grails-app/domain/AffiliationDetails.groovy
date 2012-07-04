class AffiliationDetails {

    InstitutionDetails institutionDetails;
    String universityCode;
    String universityName;
    String yearOfAffiliation;
    
    
    static constraints = {
    universityName(nullable:true)
    }
}
