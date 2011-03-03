class EvalScaleOptionsService {

    boolean transactional = true
    
    /*
	 * Function to List EvalScaleOptions by EvalScaleI id
	 */
    public listEvalScaleOptionsByEvalScale(def evalScaleId)
	{
    	def assignedEvalscaleOptionsInstanceList= EvalScaleOptions.findAll("from EvalScaleOptions ESO where ESO.activeYesNo='Y' and ESO.evalScale.id= "+evalScaleId)
		return assignedEvalscaleOptionsInstanceList
	}
    
    /*
	 * Function to List EvalScaleOptions by id
	 */
    public listEvalScaleOptionsById(def evalScaleOptionsId)
	{
    	def assignedEvalscaleOptionsInstance = EvalScaleOptions.find("from EvalScaleOptions ESO where ESO.activeYesNo='Y' and ESO.id= "+evalScaleOptionsId)
		return assignedEvalscaleOptionsInstance
	}
    
    /*
	 * Function to get EvalScaleOptions by id
	 */
    
   public getEvalScaleOptions(def evalScaleOptionsId)
    {
    	def evalScaleOptionsInstance = EvalScaleOptions.get(evalScaleOptionsId)
    	return evalScaleOptionsInstance
    }
    

	 /*
	  * Function to get EvalScaleOptions by evalScaleId
	  */
	    
	  public getEvalScaleOptionsByEvalScaleId(def evalScaleId)
	    {
	    	def evalScaleOptionForEvalScaleList = EvalScaleOptions.findAll("from EvalScaleOptions ESO where ESO.evalScale.id= "+evalScaleId)
	    	return evalScaleOptionForEvalScaleList
	    }
	    
   
    /*
	 * Function to delete an Evalscaleoption
	 */
   public deleteEvalscaleoption(def evalScaleOptionsInstance)
	{
	   evalScaleOptionsInstance.activeYesNo = "N"
	   evalScaleOptionsInstance.save()
	   return evalScaleOptionsInstance
	}
  
   
   /*
	 * Function to delete an Evalscaleoption
	 */
	 
	 public evalScaleOptionAssignedInEvalItem(def evalScaleOptionId)
     {
	   def usedEvalScaleOptionsInstance = EvalItem.find("from EvalItem EI where EI.evalScale= "+evalScaleOptionId)
	   return usedEvalScaleOptionsInstance
     }
	 
   
	 /*
	  * Function to get duplicate Evalscale
	  */
	    
	 public alreadyUsedEvalscaleOption(def scaleOption,def evalScaleId)
	   {
	     
		 def evalScaleOptionsDuplicateInstance = EvalScaleOptions.find("from EvalScaleOptions ESO where  ESO.activeYesNo='Y' and ESO.scaleOption='"+scaleOption+"' and ESO.evalScale= "+evalScaleId)
		 return evalScaleOptionsDuplicateInstance
	   
	   }
   
	 
	 /*
	  * Function to get duplicate Evalscale
	  */
	    
	 public getEvalAnswerByEvalOptionsId(def evalScaleOptionsId)
	   {
	     
		 def evalAnswerForEvalScalOptionsInstance = EvalAnswer.find("from EvalAnswer EA where EA.evalScaleOptions.id= "+evalScaleOptionsId)
		 return evalAnswerForEvalScalOptionsInstance
	   
	   }
	 
   
    def serviceMethod() {

    }
}
