package aell

class AellDictList {

String word
AellDictTypeMaster aellDictTypeMaster
String definition 
String level
    static constraints = {
    word blank: false
    aellDictTypeMaster blank: false
    definition blank: false
    }
}
