class ProgrammeDetails {
    String mode;
    Department department;
    String level;
    String programmeName;
    String programmeCode;
    String disciplineName;
    String disciplineCode;
    String broadDisciplineName;
    String broadDIsciplineCode;
    String intakeCapacity;
    String numberOfApplicants;
    String programmeDuration;
    String programmeType;
    String examinationSystem;
    String university;
    String fsYOrN;
    String fsCountryName;
    String fsCountryCode;
    String totalFs;
    String femaleFs;
    
    static constraints = {
    }
    String toString(){"${this.programmeName}:${this.disciplineName}:${this.level}:${this.programmeType}"}
}
