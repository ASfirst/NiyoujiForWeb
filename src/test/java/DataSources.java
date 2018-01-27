import java.util.ArrayList;

public class DataSources
{
	
	public static ArrayList<Light> getDataSources()
	{
		ArrayList<Light> lights = new ArrayList<>();
		
		Light light = new Light();
		light.setName("1号路口");
		light.setRedTime(5);
		light.setGreenTime(10);
		light.setYellowTime(18);
		
		Light light1 = new Light();
		light1.setName("2号路口");
		light1.setRedTime(10);
		light1.setGreenTime(20);
		light1.setYellowTime(15);
		
		Light light2 = new Light();
		light2.setName("3号路口");
		light2.setRedTime(20);
		light2.setGreenTime(1);
		light2.setYellowTime(6);
		
		Light light3 = new Light();
		light3.setName("4号路口");
		light3.setRedTime(13);
		light3.setGreenTime(2);
		light3.setYellowTime(18);
		
		lights.add(light);
		lights.add(light1);
		lights.add(light2);
		lights.add(light3);
		
		return lights;
	}
	
	
}


