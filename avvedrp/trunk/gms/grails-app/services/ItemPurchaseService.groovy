

class ItemPurchaseService {

	/**
	 * Function to get item list
	 */
	public List getItemPurchaseList(def projectInstance){
		
		def itemPurchaseInstanceList = ItemPurchase.findAll("from ItemPurchase PQ where PQ.projects.id =" +projectInstance.id+ "and PQ.status='Y'")
		return itemPurchaseInstanceList
	}
}
