package org.codehaus.groovy.grails.plugins.ofchart.demo;

public enum DemoCharts {

    SIMPLE_CHART("Simple Line Chart"), MANY_LINES("Many Lines"), LINE_DOT("Line Dot"), LINE_HOLLOW("Line Hollow"),
    COMPLEX_LABELS("X Axis Labels 3"), AREA_HOLLOW("Hollow Area Chart"), PIE_CHART("Pie Chart"),
    BAR_CHART("Simple Bar Chart"), BAR_CHART_GLASS("Simple Glass Bar Chart"),BAR_CHART_3D("3D Bar Chart"),
    BAR_CHART_FILLED("Filled Bar Chart"),BAR_CHART_SKETCH("Sketch Bar Chart"), BAR_H("Horizontal Bar Chart"),
    SCATTER("Scatter Chart"), STACKED_BAR("Stacked Bar Chart"),ADV_BAR_CHART("Bar Chart (Advanced)"),
    ADV_FLOATING_BARS("Floating Bars (Advanced)"),ADV_BAR_CHART_SKETCH("Sketch Bar Chart (Advanced)"),
    ADV_DOT_LINES("Dot Lines (Advanced)");
    
    String display;

    DemoCharts(String display){
        this.display = display;
    }

    public String getDisplay(){
        return this.display;
    }
}
