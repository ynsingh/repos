/**
 *  PacketRategraph.java
 *    
 *  See LICENCE file for usage and redistribution terms
 *  Copyright (c) 2012, ETRG, IIT Kanpur.
 *  */

/**
 *  @author <a href="mailto:shikhashuklaa.gmail.com">Shikha Shukla</a>
 *  Created for showing data transfer rate graph
 *  @date 19/06/12
 *  */

package org.bss.brihaspatisync.monitor.gui;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.util.GregorianCalendar;

public class PacketRategraph extends JPanel {

    private static final String TITLE = "";
    private static final String START = "Start";
    private static final String STOP = "Stop";
    private static final int MINMAX = 20;
    private static final int FAST = 820;
    private String dateFormat = "yyyy:MM:dd HH:mm:ss";		// XÖáÊ±¼äµ¥Î»µÄÏÔÊ¾¸ñÊ½
    private static int SLIDER_INITIAL_VALUE = 100;
    private static int ALL_LENGTH = 1000 * 60 * 60;
    private static final int COUNT = ALL_LENGTH / 1000;
    private static final int SLOW = FAST * 2;
    private Timer timer;
    static float rate;

    GregorianCalendar begin;
    GregorianCalendar end;
    GregorianCalendar now;	

    public PacketRategraph(final String title) {
	Dimension dim=new Dimension(800,700);
        this.setPreferredSize(dim); 
	setBorder(BorderFactory.createTitledBorder(title));
	now = new GregorianCalendar();
		now.add(GregorianCalendar.MINUTE, -1);
		begin = new GregorianCalendar();
		begin.set(GregorianCalendar.HOUR_OF_DAY, 0);
		begin.set(GregorianCalendar.MINUTE, 0);
		begin.set(GregorianCalendar.SECOND, 0);
		begin.set(GregorianCalendar.MILLISECOND, 0);
		end = new GregorianCalendar();
		end.add(GregorianCalendar.DAY_OF_MONTH, -7);
		
		final DynamicTimeSeriesCollection dataset = new DynamicTimeSeriesCollection(2, COUNT / 100, new Second());
		dataset.setTimeBase(new Second(now.get(GregorianCalendar.SECOND), now.get(GregorianCalendar.MINUTE), now.get(GregorianCalendar.HOUR_OF_DAY),
				now.get(GregorianCalendar.DAY_OF_MONTH), now.get(GregorianCalendar.MONTH) + 1, now.get(GregorianCalendar.YEAR)));
        dataset.addSeries(gaussianData(), 0, "data");
        JFreeChart chart = createChart(dataset);
	
	
        final JButton run = new JButton(STOP);
        run.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (STOP.equals(cmd)) {
                    timer.stop();
                    run.setText(START);
                } else {
                    timer.start();
                    run.setText(STOP);
                }
            }
        });

        final JComboBox combo = new JComboBox();
        combo.addItem("Fast");
        combo.addItem("Slow");
        combo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if ("Fast".equals(combo.getSelectedItem())) {
                    timer.setDelay(FAST);
                } else {
                    timer.setDelay(SLOW);
                }
            }
        });

	ChartPanel chartPanel = new ChartPanel( chart );
	chartPanel.setPreferredSize(dim);

        add(chartPanel, BorderLayout.CENTER);
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(run);
        btnPanel.add(combo);
        add(btnPanel, BorderLayout.SOUTH);

        timer = new Timer(FAST, new ActionListener() {

            float[] newData = new float[1];

            @Override
            public void actionPerformed(ActionEvent e) {
                newData[0] = getValue();
                dataset.advanceTime();
                dataset.appendData(newData);
            }
        });
    }


    private float getValue() {
        return rate;
    }

    private float[] gaussianData() {
        float[] a = new float[COUNT];
        for (int i = 0; i < a.length; i++) {
            a[i] = getValue();
        }
        return a;
    }

    public float getDataValue(float data){
	rate=data;
	return rate;
	}	

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            TITLE, "Time in hh:mm:ss", "Packets Per Second", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setRange(-2, MINMAX);
        return result;
    }

    public void start() {
        timer.start();
    }
}
