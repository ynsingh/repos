//list of all editable institutes (as per user matching)<br>
<ol>
<?foreach($institutes as $i):?>
	<li><a href="<?=url('institute','edit',$i->id)?>"><?=$i->name?></a></li>
<?endforeach;?>
</ol>
