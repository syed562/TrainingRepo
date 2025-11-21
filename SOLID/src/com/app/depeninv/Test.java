package com.app.depeninv;

public class Test {
public static void main(String[] args) {
Fan obj1=new Fan();
LightBulb obj2=new LightBulb();
Switch sw1=new Switch(obj2);
Switch sw2=new Switch(obj1);
sw1.operate(true);//bulb on
sw2.operate(false);//fan off


}
}
