<table style="width:80%;text-align:justify;border:1px solid black;margin-top:30px;">
			
			<thead>
			    <th colspan=8 style="margin-left:120px;background-color:#7e7e7e;color:white;font-size:22px;text-align:justify;">
				<input type="text" name="part-b" style="width:30%;" value="PART-B : Research publication : Books /Chapters in Books" readonly></th>
			</thead>
		
			<thead style="font-size:20px;">
				<th>Sr. No.</th><th>Activities</th><th>Maximum Score</th><th>Number</th><th>Score Reported</th><th>Score Varified</th>
			</thead>

			<tbody>
				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(i)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="b(i)text" style="width:32%;" value="Text /Reference Books published by international publisher" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="b(i)single" style="width:80%;" value="Single Author" readonly></td>
					<td><input type="text" name="b(i)30per" style="width:50%;" value="30 Per Book" readonly></td>
					<td><input type="text" placeholder="Number of articles" name="Pnumber" style="width:97%;" value="<?php echo @$this->data['Pnumber']; ?>"></td>
					<td><input type="text" placeholder="Score reported" name="Pscore" style="width:97%; " value="<?php echo @$this->data['Pscore']; ?>"></td>
					<td><input type="text" placeholder="Score varified" name="Pvarified" style=" width:97%;" value="<?php echo @$this->data['Pvarified']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="b(i)first" style="width:80%;" value="First Author" readonly></td>
					<td><input type="text" name="b(i)21per" style="width:50%;" value="21 Per Book" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Fnumber" style="width:97%;" value="<?php echo @$this->data['Fnumber']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Fscore" style="width:97%; " value="<?php echo @$this->data['Fscore']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Fvarified" style=" width:97%;" value="<?php echo @$this->data['Fvarified']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="b(i)rest" style="width:80%;" value="Rest of The Authors" readonly></td>
					<td><input type="text" name="b(i)9per" style="width:50%;" value="9 Per Book Equally Shared" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Rnumber" style="width:97%;" value="<?php echo @$this->data['Rnumber']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Rscore" style="width:97%; " value="<?php echo @$this->data['Rscore']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Rvarified" style=" width:97%;" value="<?php echo @$this->data['Rvarified']; ?>"></td>
								
				</tr>

				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(ii)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
						<input type="text" name="part-b(ii)" style="width:42%;" value="Subject Books by National level Publisher OR State /Central Govt. Publications" readonly>
					</td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="b(ii)single" style="width:80%;" value="Single Author" readonly></td>
					<td><input type="text" name="b(ii)20per" style="width:90%;" value="20 Per Book" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Sinumber" style="width:97%;" value="<?php echo @$this->data['Sinumber']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Siscore" style="width:97%; " value="<?php echo @$this->data['Siscore']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Sivarified" style=" width:97%;" value="<?php echo @$this->data['Sivarified']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="b(ii)first" style="width:80%;" value="First Author" readonly></td>
					<td><input type="text" name="b(ii)14per" style="width:90%;" value="14 Per Book" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Fnumber" style="width:97%;" value="<?php echo @$this->data['Fnumber']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Fscore" style="width:97%; " value="<?php echo @$this->data['Fscore']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Fvarified" style=" width:97%;" value="<?php echo @$this->data['Fvarified']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="b(ii)rest" style="width:80%;" value="Rest of The Authors" readonly></td>
					<td><input type="text" name="b(ii)6per" style="width:90%;" value="6 Per Book Equally Shared" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Renumber" style="width:97%;" value="<?php echo @$this->data['Renumber']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Rescore" style="width:97%; " value="<?php echo @$this->data['Rescore']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Revarified" style=" width:97%;" value="<?php echo @$this->data['Revarified']; ?>"></td>
								
				</tr>


				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(iii)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="b(iii)subject" style="width:23%;" value="Subject Book Other Local Publisher" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="b(iii)single" style="width:80%;" value="Single Author" readonly></td>
					<td><input type="text" name="b(iii)15per" style="width:90%;" value="15 Per Book" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Apnumber" style="width:97%;" value="<?php echo @$this->data['Apnumber']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Apscore" style="width:97%; " value="<?php echo @$this->data['Apscore']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Apvarified" style=" width:97%;" value="<?php echo @$this->data['Apvarified']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="b(iii)first" style="width:80%;" value="First Author" readonly></td>
					<td><input type="text" name="b(iii)10per" style="width:90%;" value="10.5 Per Book Equally Shared" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Finumber" style="width:97%;" value="<?php echo @$this->data['Finumber']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Fiscore" style="width:97%; " value="<?php echo @$this->data['Fiscore']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Fivarified" style=" width:97%;" value="<?php echo @$this->data['Fivarified']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="b(iii)rest" style="width:80%;" value="Rest of The Authors" readonly></td>
					<td><input type="text" name="b(iii)4per" style="width:90%;" value="4.5 Per Book Equally Shared" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Resnumber" style="width:97%;" value="<?php echo @$this->data['Resnumber']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Resscore" style="width:97%; " value="<?php echo @$this->data['Resscore']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Resvarified" style=" width:97%;" value="<?php echo @$this->data['Resvarified']; ?>"></td>
								
				</tr>

				<tr>
					<td style="font-size:16px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(iv)</td>	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="b(iv)chapter" style="width:42%;" value="Chapters In Books Published By National and International level publisher" readonly></td>
				</tr>
				
				<tr>
					<td style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(iv).a</td>
	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="b(iv)national" style="width:23%;" value="National Publisher" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="b(iv.a)single" style="width:80%;" value="Single Author" readonly></td>
					<td><input type="text" name="b(iv.a)10per" style="width:90%;" value="10 Per Chapter" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Nanumb" style="width:97%;" value="<?php echo @$this->data['Nanumb']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Nascore" style="width:97%; " value="<?php echo @$this->data['Nascore']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Navari" style=" width:97%;" value="<?php echo @$this->data['Navari']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="b(iv.a)first" style="width:80%;" value="First Author" readonly></td>
					<td><input type="text" name="b(iv.a)7per" style="width:90%;" value="7 Per Chapter" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Firnumb" style="width:97%;" value="<?php echo @$this->data['Firnumb']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Firscore" style="width:97%; " value="<?php echo @$this->data['Firscore']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Firvari" style=" width:97%;" value="<?php echo @$this->data['Firvari']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="b(iv.a)rest" style="width:80%;" value="Rest of The Authors" readonly></td>
					<td><input type="text" name="b(iv.a)3per" style="width:90%;" value="3 Per Chapter Equally Shared" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Restnumb" style="width:97%;" value="<?php echo @$this->data['Restnumb']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Restsc" style="width:97%; " value="<?php echo @$this->data['Restsc']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Restvari" style=" width:97%;" value="<?php echo @$this->data['Restvari']; ?>"></td>
								
				</tr>


				<tr>
					<td style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;text-align:center;">(iv).b</td>
	
					<td colspan=5 style="font-size:18px;background-color:#7e7e7e;color:white;font-weight:bold;">
					<input type="text" name="b(iv.b)interna" style="width:23%;" value="International Publisher" readonly></td>
				</tr>
			
				<tr>
					<td></td>
					<td><input type="text" name="b(iv.b)single" style="width:80%;" value="Single Author" readonly></td>
					<td><input type="text" name="b(iv.b)5per" style="width:90%;" value="5 Per Chapter" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Innumb" style="width:97%;" value="<?php echo @$this->data['Innumb']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Inscore" style="width:97%; " value="<?php echo @$this->data['Inscore']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Invari" style=" width:97%;" value="<?php echo @$this->data['Invari']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="b(iv.b)first" style="width:80%;" value="First Author" readonly></td>
					<td><input type="text" name="b(iv.b)3per" style="width:90%;" value="3.5 Per Chapter" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Firstnum" style="width:97%;" value="<?php echo @$this->data['Firstnum']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Firstsco" style="width:97%; " value="<?php echo @$this->data['Firstsco']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Firstvari" style=" width:97%;" value="<?php echo @$this->data['Firstvari']; ?>"></td>
								
				</tr>

				<tr>
					<td></td>
					<td><input type="text" name="b(iv.b)rest" style="width:80%;" value="Rest of The Authors" readonly></td>
					<td><input type="text" name="b(iv.b)1per" style="width:90%;" value="1.5 Per Chapter Equally Shared" readonly></td>
					<td><input type="text"    placeholder="Number of articles" name="Restnum" style="width:97%;" value="<?php echo @$this->data['"Restnum']; ?>"></td>
					<td><input type="text"    placeholder="Score reported" name="Restsco" style="width:97%; " value="<?php echo @$this->data['Restsco']; ?>"></td>
					<td><input type="text"    placeholder="Score varified" name="Restvar" style=" width:97%;" value="<?php echo @$this->data['Restvar']; ?>"></td>
								
				</tr>

			<tr>
					<td colspan=3></td>
					<td>Total API under category III(B) :</td>

					<td><input type="text"    placeholder="Total" name="Totalapi" style="width:97%; " value="<?php echo @$this->data['Totalapi']; ?>"></td>
					<td><input type="text"    placeholder="Total" name="Totalapicat" style=" width:97%;" value="<?php echo @$this->data['Totalapicat']; ?>"></td>
				</tr>

			</tbody>

		</table>
