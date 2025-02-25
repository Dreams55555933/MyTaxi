package ru.fomihykh.mytaxi;


//        values.put(COLUMN_DATE,date);
//        values.put(COLUMN_PROFIT,profit);
//        values.put(COLUMN_NP,np);
//        values.put(COLUMN_GSM,gsm);
//        values.put(COLUMN_COMMENT,comment);

public class Taxi {
    private long id;
    private String date;
    private int profit;
    private int np;
    private int gsm;
    private String comment;
    private int mileage;

    public Taxi(long id,String date,int profit,int np,int gsm,String comment,int mileage){
        this.id=id;
        this.date=date;
        this.profit=profit;
        this.np=np;
        this.gsm=gsm;
        this.comment=comment;
        this.mileage=mileage;

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public int getProfit() {
        return profit;
    }

    public void setNp(int np) {
        this.np = np;
    }

    public int getNp() {
        return np;
    }

    public void setGsm(int gsm) {
        this.gsm = gsm;
    }

    public int getGsm() {
        return gsm;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getMileage() {
        return mileage;
    }
}
