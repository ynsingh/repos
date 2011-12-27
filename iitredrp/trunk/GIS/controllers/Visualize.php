<?php
/**
*	@author : Abhay Rana <captn3m0@gmail.com>
*	This is the institute controller
*/
class Controller_Visualize extends Controller_Default{
	/**
	 * This is the default action for the controller 
	 */
	function index(){
		//This is the visualizer default method
		//would redirect to the query builder
		//or maybe give out some sample links to existing queries ?
		$states = R::getAll('SELECT DISTINCT(state) from institute');
		if($this->method=="GET"){
			$c = $this->render('list_visualizations',array('states'=>$states));
			return $this->render('default',array('content'=>$c));
		}
		else{
			$year = isset($_POST['older_than'])? 2011-$_POST['older_than']: 2012;
			$params = array($year);
			$query = 'year <= ? ';
			if(isset($_POST['state'])){
				if($_POST['state'][0]!='all'){
					$valid_states = $_POST['state'];
					$query .= " AND state IN (".R::genSlots($valid_states).")";
					$params = array_merge($params,$valid_states);
				}
			}
			$instis = R::find('institute',$query,$params);
			$json_file_name = sha1($query);
			$this->cache($json_file_name,'institutes_json',true,array('institutes'=>$instis));
			switch($_POST['visualization_type']){
				case 'map':
					$content = $this->render('map_institutes',array('json'=>$json_file_name));//Will use institutes.json
					break;
				case 'table':
					$content = $this->render('table_institutes',array('institutes'=>$instis));//Will use institutes.json
					break;
				case 'bubble':
					$content = $this->render('unavailable');
				
			}

			return $this->render('default',array('content'=>$content));
		}
	}
	/**
	 * This is the map visualizer
	 * ToDo: Specs needed
	 */
	function map($method){
		switch($method){
			case 'institutes':
				//We need to get a list of all the institutes
				//and pass it to the view
				//to create a map
				$institutes = R::find('institute');
				//This file should be regenerated each time someone adds a new institute
				//#TODO - Done
				//This gives us the json needed
				$this->cache('institutes.json','institutes_json',true,array('institutes'=>$institutes));
				$content = $this->render('map_institutes',array('json'=>'institutes.json'));//Will use institutes.json
		}
		return $this->render('default',array('content'=>$content));
	}
}
