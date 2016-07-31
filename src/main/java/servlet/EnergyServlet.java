package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtilities;
import org.jfree.data.category.DefaultCategoryDataset;

import launch.EnergyJNA;
import launch.LineChartWithJfree;

@WebServlet(
        name = "EnergyServlet", 
        urlPatterns = {"/GPUEnergy"}
    )
public class EnergyServlet extends HttpServlet {

	public LineChartWithJfree enrgyChart2D;
	public DefaultCategoryDataset energyDataSetGpu;	
	public DefaultCategoryDataset energyDataSetCpu;
	public EnergyJNA energy;
	public boolean start = false;
	//public BufferedImage  energyImage;
	//public Graphics2D     draw;
	@Override
	public void init(ServletConfig config) throws ServletException {
		enrgyChart2D = new LineChartWithJfree();
		energyDataSetGpu = new DefaultCategoryDataset();
		energyDataSetCpu = new DefaultCategoryDataset();
		energy = new EnergyJNA();
		energy.startEnergyJNA();
		energy.getEnergyJNA();
		{
			enrgyChart2D.setDataSetGpue(energyDataSetGpu);
	    	enrgyChart2D.setDataSetCpue(energyDataSetCpu);
	    	
	       	enrgyChart2D.addDatasetGpue(energy.getEnergyGpu(), energy.getTimeSec());
	    	enrgyChart2D.addDatasetCpue(energy.getEnergyCpu(), energy.getTimeSec());
	    	
	    	   	
	    	enrgyChart2D.show2DEnergyChart();
	    	
		}
		
	  }
	
	//Process the HTTP Post request
	@Override
	  public void doPost(HttpServletRequest request, HttpServletResponse response) 
	         throws ServletException, IOException { 
	    doGet(request, response); 
	  }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
    	start =true;
    	getEnergy(req, resp);
    	
        
        
    }    
 
    public void getEnergy(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
    	
    	ServletOutputStream out = resp.getOutputStream();
    	//PrintWriter writer = resp.getWriter();
    	/*
    	enrgyChart2D.setDataSetGpue(energyDataSetGpu);
    	enrgyChart2D.setDataSetCpue(energyDataSetCpu);
    	
    	enrgyChart2D.addDatasetGpue(energy.getEnergyGpu(), energy.getTimeSec());
    	enrgyChart2D.addDatasetCpue(energy.getEnergyCpu(), energy.getTimeSec());
    	   	
    	enrgyChart2D.show2DEnergyChart();
    	*/
    	
    	 
     	resp.setContentType("image/png");
        resp.setHeader("REFRESH", "1");
    	
    	
       	enrgyChart2D.addDatasetGpue(energy.getEnergyGpu(), energy.getTimeSec());
    	enrgyChart2D.addDatasetCpue(energy.getEnergyCpu(), energy.getTimeSec());
 
    	enrgyChart2D.mainFrame.setVisible(true);
    	enrgyChart2D.mainFrame.setLocation(-2000, -2000);
        
    	BufferedImage energyImage = new BufferedImage(enrgyChart2D.mainFrame.getWidth(), enrgyChart2D.mainFrame.getHeight(), BufferedImage.TYPE_INT_RGB);
    	Graphics2D draw = energyImage.createGraphics();
        enrgyChart2D.mainFrame.paint(draw);
        enrgyChart2D.mainFrame.dispose();
        
        ChartUtilities.writeBufferedImageAsPNG(out, energyImage);
        
          
    }
	
    
}
