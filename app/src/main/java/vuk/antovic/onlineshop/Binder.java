package vuk.antovic.onlineshop;

import android.os.RemoteException;

public class Binder extends  IServiceBinder.Stub{
    private boolean sale;
    private String username;

    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public boolean getSale() throws RemoteException {
        return this.sale;
    }

    @Override
    public void setSale(boolean sale) throws RemoteException {
        this.sale = sale;
    }
}
