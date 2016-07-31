package launch;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartWithJfree extends JFrame {
	private DefaultCategoryDataset dataSetCpue;
	private DefaultCategoryDataset dataSetGpue;
	private ChartPanel chartPanelGpue;
	private ChartPanel chartPanelCpue;
	public JFrame mainFrame;
	public DefaultCategoryDataset getDataSetCpue() {
		return dataSetCpue;
	}

	public void setDataSetCpue(DefaultCategoryDataset dataSetCpue) {
		this.dataSetCpue = dataSetCpue;
	}
	public DefaultCategoryDataset getDataSetGpue() {
		return dataSetGpue;
	}

	public void setDataSetGpue(DefaultCategoryDataset dataSetGpue) {
		this.dataSetGpue = dataSetGpue;
	}
	
	public ChartPanel getChartPanelGpue() {
		return chartPanelGpue;
	}

	public void setChartPanelGpue(ChartPanel chartPanel) {
		this.chartPanelGpue = chartPanel;
	}
	
	public ChartPanel getChartPanelCpue() {
		return chartPanelCpue;
	}

	public void setChartPanelCpue(ChartPanel chartPanel) {
		this.chartPanelCpue = chartPanel;
	}

	public LineChartWithJfree(){
		mainFrame = new JFrame();
		//mainFrame.setLayout(new GridLayout(1, 0));
		//mainFrame.setSize(600, 400);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void show2DEnergyChart()
	{

		ChartPanel chartPanel;
		mainFrame.setLayout(new GridLayout(1, 0));
		chartPanel = createChartPanel(this.getDataSetGpue(),"GPU Energy");
		this.setChartPanelGpue(chartPanel);
		mainFrame.add(chartPanel);
		chartPanel = createChartPanel(this.getDataSetCpue(),"CPU Energy");
		this.setChartPanelCpue(chartPanel);
		mainFrame.add(chartPanel);
		mainFrame.pack();
		mainFrame.setSize(1200,600);
		mainFrame.setLocationRelativeTo(null);
	
	}
	
 
    private ChartPanel createChartPanel(CategoryDataset dataset, String chartTitle) {


        String categoryAxisLabel = "Time ->";
        String valueAxisLabel = "mJ ->";     
        
     
        JFreeChart chart = ChartFactory.createLineChart(chartTitle,
                categoryAxisLabel, valueAxisLabel, dataset, PlotOrientation.VERTICAL, false, false, false);
        
        return new ChartPanel(chart);  
    }
 
    // Add a new data  
    public CategoryDataset addDatasetGpue(int gpuEnergy, int timeSec) {
        String series1 = "GPU Energy";      
    
        this.getDataSetGpue().addValue(gpuEnergy, series1, String.valueOf(timeSec));        
        return this.getDataSetGpue();
    }
  public CategoryDataset addDatasetCpue(int cpuEnergy, int timeSec) {
         String series2 = "CPU Energy";
        
        this.getDataSetCpue().addValue(cpuEnergy, series2, String.valueOf(timeSec));        
        return this.getDataSetCpue();
    }
}
