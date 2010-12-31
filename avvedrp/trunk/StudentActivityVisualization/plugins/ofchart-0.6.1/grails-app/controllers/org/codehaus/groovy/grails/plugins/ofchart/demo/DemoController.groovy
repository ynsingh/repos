package org.codehaus.groovy.grails.plugins.ofchart.demo

import jofc2.model.Chart
import jofc2.model.elements.LineChart
import jofc2.model.axis.YAxis
import jofc2.model.axis.XAxis
import jofc2.model.axis.Label
import jofc2.model.elements.AreaChart
import jofc2.model.elements.PieChart
import jofc2.model.axis.Label.Rotation
import jofc2.model.elements.BarChart
import jofc2.OFC
import jofc2.model.elements.FilledBarChart
import jofc2.model.elements.SketchBarChart
import jofc2.model.elements.HorizontalBarChart
import jofc2.model.elements.ScatterChart
import java.math.MathContext
import jofc2.model.elements.StackedBarChart
import jofc2.model.elements.StackedBarChart.StackValue

class DemoController {

  def index = {}

  def SIMPLE_CHART = {

    render new Chart(new Date().toString()).addElements(new LineChart().addValues(9, 8, 7, 6, 5, 4, 3, 2, 1))

  }


  def MANY_LINES = {

    def data1 = new ArrayList<Number>();
    def data2 = new ArrayList<Number>();
    def data3 = new ArrayList<Number>();
    def rand = new Random();

    for (int i = 0; i < 9; i++) {
      data1.add(1 + rand.nextInt(5));
      data2.add(7 + rand.nextInt(5));
      data3.add(14 + rand.nextInt(5));
    }

    render new Chart("Three lines example").setYAxis(new YAxis().setRange(0, 20, 5)).addElements(
            new LineChart(LineChart.Style.DOT).setWidth(4).setColour("#DFC329").setDotSize(5).addValues(data1),
            new LineChart(LineChart.Style.HOLLOW).setWidth(1).setColour("#6363AC").setDotSize(5).addValues(data2),
            new LineChart().setWidth(1).setColour("#5E4725").setDotSize(5).addValues(data3))
  }


  def LINE_DOT = {

    def c = new Chart(new Date().toString()).setYAxis(new YAxis().setRange(0, 15, 5));
    def l1 = new LineChart(LineChart.Style.DOT);
    def l2 = new LineChart(LineChart.Style.DOT);
    def l3 = new LineChart(LineChart.Style.DOT);

    l1.setHaloSize(0).setWidth(2).setDotSize(4);

    l2.setHaloSize(1).setWidth(2).setDotSize(4);

    l3.setHaloSize(1).setWidth(6).setDotSize(4);

    for (float i = 0; i < 6.2; i += 0.2) {
      l1.addValues(Math.sin(i) * 1.9 + 7);
      l2.addValues(Math.sin(i) * 1.9 + 10);
      l3.addValues(Math.sin(i) * 1.9 + 4);
    }

    c.addElements(l1, l2, l3);

    render c;

  }

  def LINE_HOLLOW = {
    def c = new Chart(new Date().toString()).setYAxis(new YAxis().setRange(0, 15, 5));
    def l1 = new LineChart(LineChart.Style.HOLLOW);
    def l2 = new LineChart(LineChart.Style.HOLLOW);
    def l3 = new LineChart(LineChart.Style.HOLLOW);

    l1.setHaloSize(0).setWidth(2).setDotSize(4);

    l2.setHaloSize(1).setWidth(2).setDotSize(4);

    l3.setHaloSize(1).setWidth(6).setDotSize(4);

    for (float i = 0; i < 6.2; i += 0.2) {
      l1.addValues(Math.sin(i) * 1.9 + 7);
      l2.addValues(Math.sin(i) * 1.9 + 10);
      l3.addValues(Math.sin(i) * 1.9 + 4);
    }

    c.addElements(l1, l2, l3);
    render c;
  }

  def COMPLEX_LABELS = {

    Chart c = new Chart("X Axis Labels Complex Example").addElements(new LineChart(LineChart.Style.DOT).addValues(9, 8, 7, 6, 5, 4, 3, 2, 1)).setXAxis(new XAxis().setTickHeight(5).addLabels("one", "two", "three", "four", "five").addLabels(
            new Label("six").setColour("#0000FF").setSize(30).setRotation(Rotation.VERTICAL),
            new Label("seven").setColour("#0000FF").setSize(30).setRotation(Rotation.VERTICAL),
            new Label("eight").setColour("#8C773E").setSize(16).setRotation(Rotation.DIAGONAL).setVisible(true),
            new Label("nine").setColour("#2683CF").setSize(16).setRotation(Rotation.HORIZONTAL)));

    c.getXAxis().setStroke(1).setColour("#428C3E").setGridColour("#86BF83").setSteps(1);

    c.getXAxis().getLabels().setSteps(2).setRotation(Rotation.VERTICAL).setColour("#ff0000").setSize(16);

    render c;


  }

  def AREA_HOLLOW = {
    List<Number> data = new ArrayList<Number>(30);
    for (float i = 0; i < 6.2; i += 0.2) {
      data.add(new Float(Math.sin(i) * 1.9));
    }
    Chart c = new Chart("Area Chart").addElements(new AreaChart().addValues(data).setWidth(1));
    XAxis xaxis = new XAxis();
    xaxis.setSteps(2);
    xaxis.getLabels().setSteps(4);
    xaxis.getLabels().setRotation(Rotation.VERTICAL);

    YAxis yaxis = new YAxis();
    yaxis.setRange(-2, 2, 2);
    yaxis.setOffset(false);

    c.setYAxis(yaxis);
    c.setXAxis(xaxis);
    render c;

  }

  def PIE_CHART = {
    Chart c = new Chart("Pie Chart").addElements(new PieChart().setAnimate(true).setStartAngle(35).setBorder(2).setAlpha(0.6f).addValues(2, 3).addSlice(6.5f, "hello (6.5)").setColours("#d01f3c", "#356aa0", "#C79810").setTooltip("#val# of #total#<br>#percent# of 100%"));
    render c;
  }

  def BAR_CHART = {

    render new Chart("Simple Bar Chart").addElements(new BarChart().addValues(9, 8, 7, 6, 5, 4, 3, 2, 1));

  }

  def BAR_CHART_GLASS = {
    render new Chart("Simple Bar Chart").addElements(new BarChart(BarChart.Style.GLASS).addValues(9, 8, 7, 6, 5, 4, 3, 2, 1));
  }

  def BAR_CHART_3D = {

    def c = new Chart(new Date().toString()).setXAxis(new XAxis().setLabels(OFC.stringify(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));

    c.getXAxis().set3D(5);
    c.getXAxis().setColour("#909090");

    def e = new BarChart(BarChart.Style.THREED).setColour("#D54C78");

    Random r = new Random();

    for (int i = 0; i < 10; ++i) {
      e.addValues(2 + r.nextInt(7));
    }

    c.addElements(e);
    render c;

  }

  def BAR_CHART_FILLED = {

    render new Chart(new Date().toString()).addElements(new FilledBarChart().setOutlineColour("#577261").setColour("#E2D66A").addValues(9, 8, 7, 6, 5, 4, 3, 2, 1)).setBackgroundColour("#FFFFFF");

  }

  def BAR_CHART_SKETCH = {

    render new Chart(new Date().toString(), "{color: #567300; font-size: 14px}").addElements(new SketchBarChart("#81AC00", "#567300", 5).addValues(9, 8, 7, 6, 5, 4, 3, 2, 1));

  }

  def BAR_H = {

    def c = new Chart("Our New House Schedule").addElements(new HorizontalBarChart().addBar(0, 4).addBar(4, 8).addBar(8, 11).setColour("#9933CC").setFontSize(10).setText("Time")).setXAxis(new XAxis().setLabels("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")).setYAxis(new YAxis().setLabels("Make garden look sexy", "Paint house", "Move into house"));

    c.getXAxis().setOffset(false);
    c.getYAxis().setOffset(true);

    render c;

  }

  def SCATTER = {

    def c = new Chart(new Date().toString());
    def y = new YAxis();
    def x = new XAxis();

    y.setRange(-2, 2,1);
    x.setRange(-2, 2,1);
    
    c.setYAxis(y);
    c.setXAxis(x);

    def se = new ScatterChart();
    se.setColour("#FFD600");
    se.setDotSize(10);
    se.addPoint(0, 0);

    def se2 = new ScatterChart();
    se2.setColour("#D600FF");
    se2.setDotSize(3);

    def mc = new MathContext(2);
    for (int i = 0; i < 360; i += 5) {
      se2.addPoint(
              BigDecimal.valueOf(Math.sin(Math.toRadians(i))).round(mc),
              BigDecimal.valueOf(Math.cos(Math.toRadians(i))).round(mc)
      );
    }

    c.addElements(se, se2);
    render c;

  }

  def STACKED_BAR = {
    def c = new Chart(new Date().toString()).setXAxis(new XAxis().setLabels("a", "b", "c", "d"));

    def yaxis = new YAxis();
    yaxis.setRange(0, 14, 7);
    c.setYAxis(yaxis);

    def sbc = new StackedBarChart();
    sbc.newStack().addValues(2.5f, 5);
    sbc.newStack().addValues(7.5f);
    sbc.newStack().addValues(5);
    sbc.lastStack().addStackValues(new StackValue(5, "#ff0000"));
    sbc.newStack().addValues(2, 2, 2, 2);
    sbc.lastStack().addStackValues(new StackValue(2, "#ff00ff"));
    c.addElements(sbc);
    render c;
  }

  def ADV_BAR_CHART = {

    def b1 = new BarChart();
    b1.addValues(3, 8, 2);
    b1.addBars(
            new BarChart.Bar(7),
            new BarChart.Bar(3, "#000000").setTooltip("BLACK BAR<br>#top#"),
            (BarChart.Bar) null);
    b1.addValues(5);
    b1.setTooltip("Blue bar<br>Value:#val#");

    def b2 = new BarChart();
    b2.addValues(4, 9, 8, 4, 1, 6, 3);
    b2.setColour("FFEF3F");
    b2.setTooltip("Yello bar<br>Value:#val#");

    def c = new Chart(new Date().toString()).addElements(b1, b2).setBackgroundColour("#FFFFFF");

    render c;

  }

  def ADV_FLOATING_BARS = {
    def b = new BarChart().addValues(130).addBars(
            new BarChart.Bar(150, 130),
            new BarChart.Bar(170, 150),
            new BarChart.Bar(190, 170),
            new BarChart.Bar(200, 150).setColour("#000000").setTooltip("Hello<br>#top#"),
            (BarChart.Bar) null).addValues(150);

    def b2 = new FilledBarChart().setColour("#FFEF3F").setTooltip("top:#top#<br>bottom:#bottom#<br>Value:#val#").addValues(140).addBars(
            new FilledBarChart.Bar(155, 135),
            new FilledBarChart.Bar(175, 155),
            new FilledBarChart.Bar(195, 175),
            new FilledBarChart.Bar(190, 145, "#FF00FF", "#900090").setTooltip("Custom tooltip<br>top:#top#<br>bottom:#bottom#<br>Value:#val#")).addValues(160, 130);

    def y = new YAxis();
    y.setRange(100, 200, 10);

    def c = new Chart(new Date().toString()).setYAxis(y).addElements(b, b2).setBackgroundColour("#FFFFFF");

    render c;
  }

  def ADV_BAR_CHART_SKETCH = {

    render new Chart(new Date().toString(), "{color: #567300; font-size: 14px}").addElements(new SketchBarChart("#81AC00", "#567300", 1).addValues(9, 8, 7, 6).addBars(new SketchBarChart.Bar(5, 8),
            new SketchBarChart.Bar(4).setOutlineColour("#000000")).addValues(3, 2, 1)
    );

  }

  def ADV_DOT_LINES = {

    def lc = new LineChart(LineChart.Style.DOT);
    lc.setWidth(2).setColour("#DFC329").setDotSize(3);

    for (int i = 0; i < 8; i += 0.2) {
      def val = Math.sin(i) + 1.5;

      if (val > 1.75) lc.addDots(new LineChart.Dot(val, "#D02020"));
      else lc.addValues(val);
    }

    def c = new Chart("Advanced dot lines").addElements(lc);

    def yaxis = new YAxis();
    yaxis.setRange(0, 3, 1);
    c.setYAxis(yaxis);

    render c;

  }


}
