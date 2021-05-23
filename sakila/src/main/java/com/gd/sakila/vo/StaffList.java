package com.gd.sakila.vo;

import lombok.Data;

@Data
public class StaffList {
	private int ID;
	private String name;
	private String address;
	private String phone;
	private String zipCode;
	private String city;
	private String country;
	private int SID;
}
