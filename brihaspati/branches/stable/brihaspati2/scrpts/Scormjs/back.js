/**
*script file for the scorm gui all function related to the menu load refresh navigation defined in this file
* @see template Launchcontent.vm
* @author <a href="mailto:palseema30@gmail.com">Manorama pal</a>
* @author <a href="mailto:palseema30@gmail.com">Kishore kumar shukla</a>
* @Created Date: 18-01-2012
*/

function back() {
        document.learner_progress.action ="$link.setPage("call,CourseMgmt_User,ScormPlayerList.vm")";
        document.learner_progress.submit();
}
