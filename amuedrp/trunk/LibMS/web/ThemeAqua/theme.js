var cmThemeAquaBase = '.';

try
{
	if (myThemeAquaBase)
	{
		cmThemeAquaBase = myThemeAquaBase;
	}
}
catch (e)
{
}

var cmThemeAqua =
{
  	    mainFolderLeft: '<div style="width: 11px; height: 21px" class="themeSpacerDiv" />',
        mainFolderRight: '<div style="width: 19px; height: 21px" class="themeSpacerDiv" />',
        mainItemLeft: '<div style="width: 11px; height: 21px" class="themeSpacerDiv" />',
        mainItemRight: '<div style="width: 19px; height: 21px" class="themeSpacerDiv" />',
        folderLeft: '<div style="width: 15px; height: 23px" class="themeSpacerDiv" />',
        folderRight: '<div style="width: 15px; height: 23px" class="themeSpacerDiv" />',
        itemLeft: '<div style="width: 15px; height: 23px" class="themeSpacerDiv" />',
        itemRight: '<div style="width: 15px; height: 23px" class="themeSpacerDiv" />',
        mainSpacing: 0,
        subSpacing: 0,
        delay: 100
};

var cmThemeAquaHSplit = [_cmNoClick, '<td  class="ThemeAquaMenuSplitLeft"><div></div></td>' +
					                          '<td  class="ThemeAquaMenuSplitText"><div></div></td>' +
					                          '<td  class="ThemeAquaMenuSplitRight"><div></div></td>'
		                         ];

var cmThemeAquaMainVSplit = [_cmNoClick, '<div>' +
                            '<table height="23" width="0" ' +
                            ' cellspacing="0"><tr><td class="ThemeAquaHorizontalSplit">' +
                           '<div class="themeSpacerDiv" style=" width: 1px; height: 1px" /></td></tr></table></div>'];

var cmThemeAquaMainHSplit = [_cmNoClick, '<td  class="ThemeAquaMainSplitLeft"><div></div></td>' +
					                          '<td  class="ThemeAquaMainSplitText"><div></div></td>' +
					                          '<td  class="ThemeAquaMainSplitRight"><div></div></td>'
		                           ];    
 
     