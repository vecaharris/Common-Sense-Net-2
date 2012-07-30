package com.commonsensenet.realfarm.model;

public class Spraying {

	private int mactionid;
	private int mactionNameId;
	private int mQuantity1;
	private String mUnits;
	private String mday;
	private int muserid;
	private int mplotid;
	private String mprobType;
	private int msent;
	private int mIsadmin;
	private String mPestcideType;;

	public Spraying(int actionid, int actionNameid, int quantity1,
			String Units, int plotid, String probType,int sent, int Isadmin,
			 String day, String PestcideType, int userid) {

		mactionid = actionid;
		mQuantity1 = quantity1;
		mactionNameId=actionNameid;
		mUnits = Units;
		mday = day;
		muserid = userid;
		mplotid = plotid;
		mprobType = probType;
		msent = sent;
		mIsadmin = Isadmin;
	    mPestcideType = PestcideType;

	}

	public int getActionId() {
		return mactionid;
	}

	public int getactionNameId() {
		return mactionNameId;
	}

	public int getquantity1() {
		return mQuantity1;
	}

	public String getUnits() {
		return mUnits;
	}

	public String getday() {
		return mday;
	}

	public int getuserid() {
		return muserid;
	}

	public int getplotid() {
		return mplotid;
	}

	public String getProbtype() {
		return mprobType;
	}

	public int getsent() {
		return msent;
	}

	public int getadmin() {
		return mIsadmin;
	}

	public String getPesticidtype() {
		return mPestcideType;
	}

}
