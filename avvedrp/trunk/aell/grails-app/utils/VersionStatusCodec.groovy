class VersionStatusCodec {

  static decode = {target->
  	def val = ""
  	if(target == 'S')
  		val = 'Save'
  	else if (target == 'SR')
  		val = 'Send For Review'
  	else if (target == 'P')
  		val = 'Published' 
    return val;
  }
}
