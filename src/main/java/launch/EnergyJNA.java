package launch;
/*JNA*/
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Platform;
import com.sun.jna.*;
import java.nio.ByteBuffer;
import java.util.Random;

public class EnergyJNA {
	public int gcount = 0;

	public int getTimeSec() {
		return timeSec;
	}


	public void setTimeSec(int timeSec) {
		this.timeSec = timeSec;
	}

    public int getEnergyGpu()
    {
    	return energyGpu;
    	
    }
    public int getEnergyCpu()
    {
    	return energyCpu;
    	
    }
	public void setEnergyGpu(int energy) {
		this.energyGpu = energy;
	}
	public void setEnergyCpu(int energy) {
		this.energyCpu = energy;
	}

	private int energyGpu;
	private int energyCpu;
	private int timeSec;
	
	public void startEnergyJNA()
	{
		Thread one = new Thread() {
		    public void run() {
		       
		        	EnergyDLL.INSTANCE.startEnergy();
		    }
		        	
		};

		one.start();		
	}
	
	public void getEnergyJNA()
	{
		Thread one = new Thread() {
		    public void run() {
		        try {
		            //System.out.println("Does it work?");
		        	while(true){
		            getEnergyGpuJNA();
		            getEnergyCpuJNA();
		            Thread.sleep(1000);
		        	}
		            
		            //System.out.println("Nope, it doesnt...again.");
		        } catch(InterruptedException v) {
		            System.out.println(v);
		        }
		    }  
		};

		one.start();
	}
	public void getEnergyGpuJNA()
	{
  	
    	this.setTimeSec(gcount);
    	gcount++;		
    	
    	this.setEnergyGpu(EnergyDLL.INSTANCE.getGPUEnergy());	
	}
	public void getEnergyCpuJNA()
	{
	
		
    	
    	this.setEnergyCpu(EnergyDLL.INSTANCE.getCPUEnergy());	
	}
	public interface EnergyDLL extends Library {
		EnergyDLL INSTANCE = (EnergyDLL) Native.loadLibrary("GPUEnergyDLL", EnergyDLL.class);
        // it's possible to check the platform on which program runs, for example purposes we assume that there's a linux port of the library (it's not attached to the downloadable project)
        void startEnergy();
		int getGPUEnergy();
        int getCPUEnergy();
    }
	
}

