package aell

class AvlExperimentMaster {
	String experimentName
	String experimentDescription
	AvlExperimentTypeMaster avlExperimentTypeMaster
    String experimentStatus 
    Integer id
    static mapping = {
  	   id column: "experiment_id"
  		 avlExperimentTypeMaster column:"experiment_type_id"
  	   version false
  	   }
    static constraints = {
		experimentName blank: false
		avlExperimentTypeMaster blank:false
		experimentStatus blank:false
    }
}
