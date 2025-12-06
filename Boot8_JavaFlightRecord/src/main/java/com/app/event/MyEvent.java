package com.app.event;

import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Description;

@Name("com.app.MyEvent")

@Description("How to create custom events..")
public class MyEvent extends Event {

    @Label("Message")
   public String message;

    @Label("Value")
   public  int value;
}
