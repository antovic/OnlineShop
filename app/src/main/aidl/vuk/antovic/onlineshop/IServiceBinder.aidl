// IServiceBinder.aidl
package vuk.antovic.onlineshop;

// Declare any non-default types here with import statements

interface IServiceBinder {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    boolean getSale();
    void setSale(boolean sale);

    String getUsername();
    void setUsername(String username);
}