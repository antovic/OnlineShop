package vuk.antovic.onlineshop;

public class PurchaseModel {
    public enum State {DELIVERED, CANCELLED, WAITING_FOR_DELIVERY}

    State status;
    int price;
    String date;

    public PurchaseModel(State state, int price, String date) {
        this.status = state;
        this.price = price;
        this.date = date;
    }


    public State getStatus() {
        return status;
    }

    public void setStatus(State status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
