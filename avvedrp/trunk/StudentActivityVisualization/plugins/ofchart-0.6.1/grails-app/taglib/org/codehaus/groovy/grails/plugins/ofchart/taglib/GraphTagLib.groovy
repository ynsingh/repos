package org.codehaus.groovy.grails.plugins.ofchart.taglib

class GraphTagLib {

  static namespace = "ofchart"

  def chart = { attrs ->

    if (!attrs.name)
      throwTagError("Tag [chart] is missing required attribute [name]")

    String name = attrs.remove('name')

    String width = attrs.width ? attrs.remove("width") : "350"

    String height = attrs.height ? attrs.remove("height") : "200"

    String url = attrs.url? attrs.remove("url") : ""


    out << "<div id='$name' name='$name'></div>\n"

    out << """
          <script type="text/javascript">
            swfobject.embedSWF("${createLinkTo(dir:pluginContextPath,file:'open-flash-chart.swf')}", "$name", "$width",
              "$height","9.0.0", "expressInstall.swf", {"data-file":"$url"},{'wmode':'transparent'});
        </script>
    """

  }

  def resources = {
    out << """
            <script type="text/javascript" src="${createLinkTo(dir:pluginContextPath,file:"js/swfobject.js")}"></script>\n
            <script type="text/javascript" src="${createLinkTo(dir:pluginContextPath,file:"js/grails-ofchart.js")}"></script>\n  
           """
  }


}
