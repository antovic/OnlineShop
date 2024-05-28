package vuk.antovic.onlineshop;

import android.os.RemoteException;

public class Binder extends  IServiceBinder.Stub{
    private static boolean sale;
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
        return Binder.sale;
    }

    @Override
    public void setSale(boolean sale) throws RemoteException {
        Binder.sale = sale;
    }
}
