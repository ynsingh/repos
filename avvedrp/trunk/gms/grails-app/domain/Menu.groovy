
class Menu {

	String menuName;
	String menuPath;
	Integer parentId;
	String menuDescription;
	Integer menuOrder;
	char activeYesNo;
	String lockedYN;
	String createdBy;
	Date createdDate;
	String modifiedBy;
	Date modifiedDate;

    static constraints = {
    menuPath(nullable:true)
    parentId(nullable:true)
    }
}
