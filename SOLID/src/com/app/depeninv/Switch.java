package com.app.depeninv;

public class Switch {
public Switchable smeth;

public Switch(Switchable swt) {
	this.smeth=swt;
}
void operate(boolean on) {
	if(on)smeth.off();
	else smeth.on();
}

}