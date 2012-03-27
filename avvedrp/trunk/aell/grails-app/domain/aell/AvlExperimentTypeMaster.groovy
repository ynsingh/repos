package aell

class AvlExperimentTypeMaster {
String experimentTypeName
Integer id
static mapping = {
	   id column: "experiment_typeid"
	   version false
	   }
    static constraints = {
	experimentTypeName blank: false
    }
}
