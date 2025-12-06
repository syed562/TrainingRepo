package com.app.event;
import jdk.jfr.Recording;
import java.nio.file.Path;

public class Run {
public static void main(String[] args) throws Exception {
	
	  Recording recording = new Recording();
      recording.setName("SimpleRecording");
      recording.start();
      
	MyEvent event=new MyEvent();
//	event.begin();
	event.message="Hiii ,i am an event";
	event.value=100;
	event.commit();
	  System.out.println("Custom JFR event fired!");
	  
	  recording.stop();
	  recording.dump(Path.of("simple.jfr"));

      System.out.println("Recording saved as simple.jfr");
      System.out.println(Path.of("simple.jfr").toAbsolutePath());


}
}
