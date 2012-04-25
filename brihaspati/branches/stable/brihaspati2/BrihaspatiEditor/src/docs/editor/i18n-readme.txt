These are the notes for adding additional internationisation text (i18n) to the Reload Editor
$Id: i18n-readme.txt,v 1.1 1998/03/25 20:37:00 ynsingh Exp $
--------------------------------------------------------------------------------------------

Reload Editor Message strings are grouped into three categories:

1.  Java System messages such as those in dialog boxes - "Seach in", "OK", "Cancel".
These messages are dependent on the Locale setting of the computer and its Operating System.
We do not set these.

2.  Tips and Element friendly names.
These are set in the "Helper Files" and can be selected by the Profile drop-down box. New Helper files
can easily be created by editing the xml files found in the
USER_HOME/reload/reload-editor/helpers folders.  Further instruction is available in the Reload User Guide.

3.  Program messages such as Menu Items, Windows, Buttons, Error messages and so on.
The default language is English and a set of English Message Strings are stored internally as a default.
You can set new language Strings by producing language files as follows:

- In the Reload Editor install folder is a folder, "i18n".  This has a folder structure:

  i18n
    |----uk
          |----ac
               |-----reload
                          |----dweezil
                          |----editor
                          |----moonunit
                          
In the last 3 folders are copies of the English String files for reference.  Creating new files with the
language and country codes and over-riding certain Strings will cause the program to use these messages.

For example, to produce French messages, make a copy of each messages.properties file and add the language
code "fr" to the file name using the underscore character ("_"), messages_fr.properties.  Make sure this
file is in the same folder as the messages.properties file.  Now edit the file as follows.

Each String pair consists of a key and a string separated by an equals sign ("=") such as:

   uk.ac.reload.editor.EditorHandler.9=New Document

The key to the left of the equals sign must not be edited.  The String to the right of the equals
sign can be replaced with the new language version:

   uk.ac.reload.editor.EditorHandler.9=Fiche Nouvelle
   
Some strings for menu items contain an ampersand ("&") character.  This means that the character to the
right of the ampersand will be used for the menu mnemonic (short-cut key).

Note - the English message.properties files are there for reference.  They may be deleted since the
English Strings are stored internally by the program by default.

Note - you may delete the whole key/string pair line if you do not wish to set a new string.  It will
default to the parent setting.

The Reload Editor Preferences/Appearance tab allows you to choose the default locale for your system,
or you can over-ride it by selecting another language from the drop-down menu.  Even though many
languages are displayed in the drop-down menu they will only be active if there are message string files
supplied for that language.  Setting the language in Preferences will not affect the Helper files,
since these are selected on a per-Profile basis.


More Information
----------------
http://java.sun.com/developer/technicalArticles/Intl/ResourceBundles/



    
