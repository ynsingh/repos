

class ItemPurchaseService {

	/**
	 * Function to get item list
	 */
	public List getItemPurchaseList(def projectInstance){
		
		def itemPurchaseInstanceList = ItemPurchase.findAll("from ItemPurchase PQ where PQ.projects.id =" +projectInstance.id+ "and PQ.status='Y'")
		return itemPurchaseInstanceList
	}
	
	/**
	 * Get the list of all Assets based on project
	 */
	public List getAssetList(def projectInstance){
		
		def assetInstanceList = Asset.findAll("from Asset A where A.projects.id ="+projectInstance.id+" and A.activeYesNo='Y'")
		return assetInstanceList
	
	}
}
