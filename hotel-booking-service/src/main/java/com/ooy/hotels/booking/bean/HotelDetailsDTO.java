package com.ooy.hotels.booking.bean;


/**
 * This is DTO for hotel details used for REST request and response body
 */
public class HotelDetailsDTO {

	/**
	 * Hotel id
	 */
	private int id;
	/**
	 * Hotel name
	 */
	private String name;
	/**
	 * Description of hotel
	 */
	private String description;

	/**
	 * Validity of offer or price
	 */
	private String validity;

	/**
	 * Duration of package in days
	 */
	private int durationDays;

	/**
	 * Duration of package in nights, 
	 * nights will be always less than 1 day than duration days
	 */
	private int durationNights;

	/**
	 * Hotel room price per days 12 noon to next day 12 noon
	 */
	private double pricePerDay;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public int getDurationDays() {
		return durationDays;
	}
	public void setDurationDays(int durationDays) {
		this.durationDays = durationDays;
	}
	public int getDurationNights() {
		return durationNights;
	}
	public void setDurationNights(int durationNights) {
		this.durationNights = durationNights;
	}
	public double getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
