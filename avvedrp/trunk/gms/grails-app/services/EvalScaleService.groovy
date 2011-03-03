class EvalScaleService {

    boolean transactional = true
    /*
	 * Function to get Evalscale by id
	 */
    public getEvalScaleById(def evalScaleId)
    {
    	 def evalScaleInstance = EvalScale.get(evalScaleId)
    	 return evalScaleInstance
    }
   
    /*
	 * Function to get duplicate Evalscale
	 */
    
    public duplicateEvalScale(def scaleTitle,def partyId)
    {
    	 def evalScaleDuplicateInstance =EvalScale.find("from EvalScale ES where ES.activeYesNo='Y'and ES.scaleTitle= '"+scaleTitle+"'and ES.party="+partyId)
    	 return evalScaleDuplicateInstance
    	
    }
    
    
    /*
	 * Function to delete an Evalscale
	 */
    public deleteEvalscale(def evalScaleInstance)
	{
    	evalScaleInstance.activeYesNo = "N"
    	evalScaleInstance.save()
		return evalScaleInstance
	}
    
    /*
	 * Function to List Evalscale
	 */
    public listEvalscale(def partyId)
	{
    	def evalScaleInstanceList= EvalScale.findAll("from EvalScale ES where ES.activeYesNo='Y' and ES.party.id ="+partyId)
		return evalScaleInstanceList
	}
    
    /*
	 * Function to find Already used Evalscale
	 * 
	 */
	 
	 public alreadyUsedEvalscale(def evalScaleId)
		{
		   def evalScaleForDelete = EvalScaleOptions.find("from EvalScaleOptions ESO where ESO.activeYesNo='Y' and ESO.evalScale = "+evalScaleId )
     		return evalScaleForDelete
		}
	    
     def serviceMethod() {

    }
}
