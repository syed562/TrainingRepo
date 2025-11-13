package com.app.hos;

public class BookingService implements Runnable {
    private Room room;
    private Payment payment;
    private boolean lockInReverse;

    public BookingService(Room room, Payment payment, boolean lockInReverse) {
        this.room = room;
        this.payment = payment;
        this.lockInReverse = lockInReverse;
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		 if (lockInReverse) {
	            synchronized (payment) {
	                System.out.println(Thread.currentThread().getName() + " locked payment");
	                try { Thread.sleep(100); } catch (InterruptedException e) {}
	                synchronized (room) {
	                    System.out.println(Thread.currentThread().getName() + " locked room and completed booking");
	                }
	            }
	        } else {
	            synchronized (room) {
	                System.out.println(Thread.currentThread().getName() + " locked room");
	                try { Thread.sleep(100); } catch (InterruptedException e) {}
	                synchronized (payment) {
	                    System.out.println(Thread.currentThread().getName() + " locked payment and completed booking");
	                }
	            }
	        }}
		
	}
